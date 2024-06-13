import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.iwebpp.crypto.TweetNaclFast;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.ton.java.smartcontract.wallet.v3.WalletV3R2;
import org.ton.java.tlb.types.Message;
import org.ton.java.utils.Utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(JUnit4.class)
public class FiftScriptsTest {

    public static final String TON_TEST_CASES_SMARTCONTRACTS = "smartcontracts.json";
    public static final String TON_TEST_CASES_CRYPTOGRAPHY = "cryptography.json";

    Gson gson = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();

    @Test
    public void testWalletV3R2Creation() throws IOException {

        // read the JSON file with tests cases
        String fileContentWithUseCases = new String(Files.readAllBytes(Paths.get(TON_TEST_CASES_SMARTCONTRACTS)));
        TonSdkTestCases tonSdkTestCases = gson.fromJson(fileContentWithUseCases, TonSdkTestCases.class);

        String testId = "smartcontracts-9";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        // fetch input parameters and print test case details
        String prvKey = (String) testCase.getInput().get("privateKey");
        Long workchain = (Long) testCase.getInput().get("workchain");
        Long walletId = (Long) testCase.getInput().get("walletId");

        log.info("input parameters:");
        log.info("  prvKey: {}", prvKey);
        log.info("  workchain: {}", workchain);
        log.info("  walletId: {}", walletId);

        // test the functionality of your library

        TweetNaclFast.Signature.KeyPair keyPair = TweetNaclFast.Signature.keyPair_fromSeed(Utils.hexToSignedBytes(prvKey));

        WalletV3R2 contract = WalletV3R2.builder()
                .wc(workchain)
                .keyPair(keyPair)
                .walletId(walletId)
                .build();

        String actualRawAddress = contract.getAddress().toRaw();

        Message msg = contract.prepareDeployMsg();

        String actualExtMsgForSerializationAsBoc = Utils.bytesToHex(msg.toCell().toBoc(true)).toUpperCase();

        // fetch the expected result and compare it against the actual one
        String expectedRawAddress = (String) testCase.getExpectedOutput().get("rawAddress");
        String expectedBocAsHex = (String) testCase.getExpectedOutput().get("bocAsHex");

        log.info("expected results:");
        log.info("  rawAddress: {}", expectedRawAddress);
        log.info("  bocAsHex: {}", expectedBocAsHex);

        assertThat(actualRawAddress).isEqualTo(expectedRawAddress);
        assertThat(actualExtMsgForSerializationAsBoc).isEqualTo(expectedBocAsHex);
    }

}
