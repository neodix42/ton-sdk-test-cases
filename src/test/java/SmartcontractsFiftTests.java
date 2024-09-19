import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.ton.java.fift.FiftRunner;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(JUnit4.class)
public class SmartcontractsFiftTests {

    Gson gson = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    String fileContentWithUseCases = IOUtils.toString(Paths.get(Paths.get("").toAbsolutePath() + "/smartcontracts.json").toUri());
    TonSdkTestCases tonSdkTestCases = gson.fromJson(fileContentWithUseCases, TonSdkTestCases.class);

    public SmartcontractsFiftTests() throws IOException {
    }


    /**
     * Make sure you have fift and func installed in your system. See <a href="https://github.com/ton-blockchain/packages">packages</a> for instructions.
     */

    @Test
    public void testSmartcontracts03() throws URISyntaxException { // test name changed deliberately to 03, so new-wallet.addr had 0 workchain.

        String testId = "smartcontracts-3";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        URL resource = SmartcontractsFiftTests.class.getResource("/fift-tests/wallets/new-wallet-v3.fif");
        File fiftFile = Paths.get(resource.toURI()).toFile();
        String absolutePath = fiftFile.getAbsolutePath();

        FiftRunner fiftRunner = FiftRunner.builder().build();

        String inputWorkchain = testCase.getInput().get("workchain").toString();
        String inputWalletId = testCase.getInput().get("walletId").toString();

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, inputWorkchain, inputWalletId);
        log.info("output: {}", result);

        String expectedRawAddress = (String) testCase.getExpectedOutput().get("rawAddress");
        String expectedExternalMessageAsHex = (String) testCase.getExpectedOutput().get("externalMessageAsHex");
        String expectedExternalMessageBocAsHexWithCrc = (String) testCase.getExpectedOutput().get("externalMessageBocAsHexWithCrc");

        assertThat(result).contains(expectedRawAddress);
        assertThat(result).contains(expectedExternalMessageAsHex);
        assertThat(result).contains(expectedExternalMessageBocAsHexWithCrc);
    }

    @Test
    public void testSmartcontracts2() throws URISyntaxException {

        String testId = "smartcontracts-2";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        URL resource = SmartcontractsFiftTests.class.getResource("/fift-tests/wallets/new-wallet-v3.fif");
        File fiftFile = Paths.get(resource.toURI()).toFile();
        String absolutePath = fiftFile.getAbsolutePath();

        FiftRunner fiftRunner = FiftRunner.builder().build();

        String inputWorkchain = testCase.getInput().get("workchain").toString();
        String inputWalletId = testCase.getInput().get("walletId").toString();

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, inputWorkchain, inputWalletId);
        log.info("output: {}", result);

        String expectedRawAddress = (String) testCase.getExpectedOutput().get("rawAddress");
        String expectedExternalMessageBocAsHexWithCrc = (String) testCase.getExpectedOutput().get("externalMessageBocAsHexWithCrc");

        assertThat(result).contains(expectedRawAddress);
        assertThat(result).contains(expectedExternalMessageBocAsHexWithCrc);
    }


    @Test
    public void testSmartcontracts4() throws URISyntaxException {

        String testId = "smartcontracts-4";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        URL resource = SmartcontractsFiftTests.class.getResource("/fift-tests/wallets/wallet-v3.fif");
        File fiftFile = Paths.get(resource.toURI()).toFile();
        String absolutePath = fiftFile.getAbsolutePath();

        FiftRunner fiftRunner = FiftRunner.builder().build();

        String inputWalletId = testCase.getInput().get("walletId").toString();
        String inputSeqno = testCase.getInput().get("seqNo").toString();
        String inputDestinationAddress = testCase.getInput().get("destinationAddress").toString();
        String inputAmountTonCoins = testCase.getInput().get("amountTonCoins").toString();
        String inputBounceFlag = testCase.getInput().get("bounceFlag").toString();
        String inputValidUntil = testCase.getInput().get("validUntil").toString();
        String inputSendMode = testCase.getInput().get("sendMode").toString();

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath,
                "new-wallet",
                inputDestinationAddress,
                inputWalletId,
                inputSeqno,
                inputAmountTonCoins,
                Boolean.parseBoolean(inputBounceFlag) ? "-b" : "-n",  //inputBounceFlag
                "-t", inputValidUntil, // wallet-v3.fif a little modified - "now + timeout" replaced by "timeout"
                "-m", inputSendMode
        );
        log.info("output: {}", result);

        String expectedExternalMessageBocAsHexWithCrc = (String) testCase.getExpectedOutput().get("externalMessageBocAsHexWithCrc");

        assertThat(result).contains(expectedExternalMessageBocAsHexWithCrc);
    }

    @Test
    public void testSmartcontracts5() throws URISyntaxException {

        String testId = "smartcontracts-5";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        URL resource = SmartcontractsFiftTests.class.getResource("/fift-tests/wallets/wallet-v3.fif");
        File fiftFile = Paths.get(resource.toURI()).toFile();
        String absolutePath = fiftFile.getAbsolutePath();

        FiftRunner fiftRunner = FiftRunner.builder().build();

        String inputWalletId = testCase.getInput().get("walletId").toString();
        String inputSeqno = testCase.getInput().get("seqNo").toString();
        String inputDestinationAddress = testCase.getInput().get("destinationAddress").toString();
        String inputAmountTonCoins = testCase.getInput().get("amountTonCoins").toString();
        String inputBounceFlag = testCase.getInput().get("bounceFlag").toString();
        String inputValidUntil = testCase.getInput().get("validUntil").toString();
        String inputSendMode = testCase.getInput().get("sendMode").toString();

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath,
                "new-wallet",
                inputDestinationAddress,
                inputWalletId,
                inputSeqno,
                inputAmountTonCoins,
                Boolean.parseBoolean(inputBounceFlag) ? "-b" : "-n",
                "-t", inputValidUntil, // wallet-v3.fif a little modified - "now + timeout" replaced by "timeout
                "-m", inputSendMode
        );
        log.info("output: {}", result);

        String expectedExternalMessageBocAsHexWithCrc = (String) testCase.getExpectedOutput().get("externalMessageBocAsHexWithCrc");

        assertThat(result).contains(expectedExternalMessageBocAsHexWithCrc);
    }

    @Test
    public void testSmartcontracts6() throws URISyntaxException {

        String testId = "smartcontracts-6";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        URL resource = SmartcontractsFiftTests.class.getResource("/fift-tests/wallets/wallet-v3.fif");
        File fiftFile = Paths.get(resource.toURI()).toFile();
        String absolutePath = fiftFile.getAbsolutePath();

        FiftRunner fiftRunner = FiftRunner.builder().build();

        String inputWalletId = testCase.getInput().get("walletId").toString();
        String inputSeqno = testCase.getInput().get("seqNo").toString();
        String inputDestinationAddress = testCase.getInput().get("destinationAddress").toString();
        String inputAmountTonCoins = testCase.getInput().get("amountTonCoins").toString();
        String inputBounceFlag = testCase.getInput().get("bounceFlag").toString();
        String inputValidUntil = testCase.getInput().get("validUntil").toString();
        String inputSendMode = testCase.getInput().get("sendMode").toString();
        String inputComment = testCase.getInput().get("comment").toString();

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath,
                "new-wallet",
                inputDestinationAddress,
                inputWalletId,
                inputSeqno,
                inputAmountTonCoins,
                Boolean.parseBoolean(inputBounceFlag) ? "-b" : "-n",
                "-t", inputValidUntil, // wallet-v3.fif a little modified - "now + timeout" replaced by "timeout
                "-m", inputSendMode,
                "-C", inputComment
        );
        log.info("output: {}", result);

        String expectedExternalMessageBocAsHexWithCrc = (String) testCase.getExpectedOutput().get("externalMessageBocAsHexWithCrc");

        assertThat(result).contains(expectedExternalMessageBocAsHexWithCrc);
    }

    @Test
    public void testV4R2CodeCell() throws URISyntaxException {

        URL resource = SmartcontractsFiftTests.class.getResource("/fift-tests/wallets/print-v4-hex.fif");
        File fiftFile = Paths.get(resource.toURI()).toFile();
        String absolutePath = fiftFile.getAbsolutePath();

        FiftRunner fiftRunner = FiftRunner.builder().build();

        String result = fiftRunner.run(fiftFile.getParent(), absolutePath);
        log.info("output: {}", result);
    }
}
