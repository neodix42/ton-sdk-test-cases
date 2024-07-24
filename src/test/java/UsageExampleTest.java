import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import com.iwebpp.crypto.TweetNaclFast;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.ton.java.address.Address;
import org.ton.java.smartcontract.types.WalletV3Config;
import org.ton.java.smartcontract.wallet.v3.WalletV3R2;
import org.ton.java.tlb.types.Message;
import org.ton.java.utils.Utils;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(JUnit4.class)
public class UsageExampleTest {

    public static final String TON_TEST_CASES_SMARTCONTRACTS = "smartcontracts.json";
    public static final String TON_TEST_CASES_CRYPTOGRAPHY = "cryptography.json";
    Gson gson = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();

    @Test
    public void testSmartContracts6() throws IOException {

        // read the JSON file with tests cases
        String fileContentWithUseCases = new String(Files.readAllBytes(Paths.get(TON_TEST_CASES_SMARTCONTRACTS)));
        TonSdkTestCases tonSdkTestCases = gson.fromJson(fileContentWithUseCases, TonSdkTestCases.class);

        String testId = "smartcontracts-6";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        log.info("testId: {}", testId);

        String privateKey = testCase.getInput().get("privateKey").toString();
        Long workchain = (Long) testCase.getInput().get("workchain");
        String destinationAddress = testCase.getInput().get("destinationAddress").toString();
        Long walletId = (Long) testCase.getInput().get("walletId");
        Long seqno = (Long) testCase.getInput().get("seqNo");
        BigDecimal amountTonCoins = new BigDecimal(testCase.getInput().get("amountTonCoins").toString());
        Boolean bounceFlag = (Boolean) testCase.getInput().get("bounceFlag");
        Long validUntil = (Long) testCase.getInput().get("validUntil");
        Long sendMode = (Long) testCase.getInput().get("sendMode");
        String comment = testCase.getInput().get("comment").toString();

        byte[] secretKey = Utils.hexToSignedBytes(privateKey);
        TweetNaclFast.Signature.KeyPair keyPair = TweetNaclFast.Signature.keyPair_fromSeed(secretKey);

        WalletV3R2 contract = WalletV3R2.builder()
                .wc(workchain)
                .keyPair(keyPair)
                .walletId(walletId)
                .build();

        String expectedBocAsHex = (String) testCase.getExpectedOutput().get("externalMessageBocAsHexWithCrc");

        WalletV3Config config = WalletV3Config.builder()
                .walletId(walletId)
                .seqno(seqno)
                .destination(Address.of(destinationAddress))
                .amount(Utils.toNano(amountTonCoins))
                .validUntil(validUntil)
                .bounce(bounceFlag)
                .mode(sendMode.intValue())
                .comment(comment)
                .build();
        Message sendMsg = contract.prepareExternalMsg(config);
        assertThat(sendMsg.toCell().toHex(true).toUpperCase()).isEqualTo(expectedBocAsHex);
    }

    @Test
    public void testSmartContracts3() throws IOException {

        // read the JSON file with tests cases
        String fileContentWithUseCases = new String(Files.readAllBytes(Paths.get(TON_TEST_CASES_SMARTCONTRACTS)));
        TonSdkTestCases tonSdkTestCases = gson.fromJson(fileContentWithUseCases, TonSdkTestCases.class);

        String testId = "smartcontracts-3";

        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String privateKey = testCase.getInput().get("privateKey").toString();
        Long workchain = (Long) testCase.getInput().get("workchain");
        Long walletId = (Long) testCase.getInput().get("walletId");
        Long initialSeqno = (Long) testCase.getInput().get("initialSeqno");

        byte[] secretKey = Utils.hexToSignedBytes(privateKey);
        TweetNaclFast.Signature.KeyPair keyPair = TweetNaclFast.Signature.keyPair_fromSeed(secretKey);

        WalletV3R2 contract = WalletV3R2.builder()
                .wc(workchain)
                .keyPair(keyPair)
                .walletId(walletId)
                .build();

        String expectedRawAddress = (String) testCase.getExpectedOutput().get("rawAddress");
        String expectedCodeAsHex = (String) testCase.getExpectedOutput().get("codeAsHex");
        String expectedDataAsHex = (String) testCase.getExpectedOutput().get("dataAsHex");
        String expectedExternalMessageAsHex = (String) testCase.getExpectedOutput().get("externalMessageAsHex");
        String expectedBocAsHex = (String) testCase.getExpectedOutput().get("externalMessageBocAsHexWithCrc");

        String rawAddress = contract.getAddress().toRaw();
        assertThat(rawAddress).isEqualTo(expectedRawAddress);

        assertThat(contract.createCodeCell().bitStringToHex().toUpperCase()).isEqualTo(expectedCodeAsHex);
        assertThat(contract.createDataCell().bitStringToHex().toUpperCase()).isEqualTo(expectedDataAsHex);
        Message msg = contract.prepareDeployMsg();

        assertThat(msg.toCell().bitStringToHex().toUpperCase()).isEqualTo(expectedExternalMessageAsHex);
        assertThat(msg.toCell().toHex(true).toUpperCase()).isEqualTo(expectedBocAsHex);
    }

    @Test
    public void testCryptography5() throws IOException {

        // read the JSON file with tests cases
        String fileContentWithUseCases = new String(Files.readAllBytes(Paths.get(TON_TEST_CASES_CRYPTOGRAPHY)));
        TonSdkTestCases tonSdkTestCases = gson.fromJson(fileContentWithUseCases, TonSdkTestCases.class);

        String testId = "cryptography-5";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // print test case details
        log.info("TestCase: {}", testCase);

        // fetch input parameters
        String prvKey = testCase.getInput().get("privateKey").toString();

        String inputString1 = testCase.getInput().get("inputString1").toString();
        String inputString2 = testCase.getInput().get("inputString2").toString();
        String inputString3 = testCase.getInput().get("inputString3").toString();


        byte[] secretKey = Utils.hexToSignedBytes(prvKey);
        TweetNaclFast.Signature.KeyPair keyPair = Utils.generateSignatureKeyPairFromSeed(secretKey);

        byte[] pubKey = keyPair.getPublicKey();
        byte[] secKey = keyPair.getSecretKey();

        String actualPubKeyAsHex = Utils.bytesToHex(pubKey);

        byte[] actualSigned1 = Utils.signData(pubKey, secKey, inputString1.getBytes());
        byte[] actualSigned2 = Utils.signData(pubKey, secKey, inputString2.getBytes());
        byte[] actualSigned3 = Utils.signData(pubKey, secKey, inputString3.getBytes());

        // fetch expected results
        String expectedPubKey = (String) testCase.getExpectedOutput().get("publicKey");
        String expectedBase641 = testCase.getExpectedOutput().get("resultBase641").toString();
        String expectedBase642 = testCase.getExpectedOutput().get("resultBase642").toString();
        String expectedBase643 = testCase.getExpectedOutput().get("resultBase643").toString();

        assertThat(actualPubKeyAsHex).isEqualTo(expectedPubKey);
        assertThat(Utils.bytesToBase64(actualSigned1)).isEqualTo(expectedBase641);
        assertThat(Utils.bytesToBase64(actualSigned2)).isEqualTo(expectedBase642);
        assertThat(Utils.bytesToBase64(actualSigned3)).isEqualTo(expectedBase643);
    }
}
