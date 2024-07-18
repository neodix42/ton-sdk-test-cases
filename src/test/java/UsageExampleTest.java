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

    @Test
    public void testWalletV3R2Transfer() throws IOException {

        // read the JSON file with tests cases
        String fileContentWithUseCases = new String(Files.readAllBytes(Paths.get(TON_TEST_CASES_SMARTCONTRACTS)));
        TonSdkTestCases tonSdkTestCases = gson.fromJson(fileContentWithUseCases, TonSdkTestCases.class);

        String testId = "smartcontracts-10";

        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // print test case details
        log.info("TestCase: {}", testCase);

        // fetch input parameters
        String prvKey = (String) testCase.getInput().get("privateKey");
        Long workchain = (Long) testCase.getInput().get("workchain");
        Long walletId = (Long) testCase.getInput().get("walletId");
        String destinationAddress = (String) testCase.getInput().get("destinationAddress");
        Long seqNo = (Long) testCase.getInput().get("seqNo");
        Long amountTonCoins = (Long) testCase.getInput().get("amountTonCoins");
        Boolean bounceFlag = (Boolean) testCase.getInput().get("bounceFlag");
        Long validUntil = (Long) testCase.getInput().get("validUntil");
        Long sendMode = (Long) testCase.getInput().get("sendMode");


        // test the functionality of your library

        TweetNaclFast.Signature.KeyPair keyPair = TweetNaclFast.Signature.keyPair_fromSeed(Utils.hexToSignedBytes(prvKey));

        WalletV3R2 contract = WalletV3R2.builder()
                .wc(workchain)
                .keyPair(keyPair)
                .walletId(walletId)
                .build();

        WalletV3Config config = WalletV3Config.builder()
                .destination(Address.of(destinationAddress))
                .walletId(walletId)
                .seqno(seqNo)
                .amount(Utils.toNano(amountTonCoins))
                .bounce(bounceFlag)
                .validUntil(validUntil)
                .mode(Math.toIntExact(sendMode)) // default is 3, can be omitted
                .build();

        Message msg = contract.prepareExternalMsg(config);

        String actualExtMsgForSerializationAsBoc = Utils.bytesToHex(msg.toCell().toBoc(true)).toUpperCase();

        // fetch the expected result and compare it against the actual one
        String expectedBocAsHex = (String) testCase.getExpectedOutput().get("externalMessageBocAsHex");

        assertThat(actualExtMsgForSerializationAsBoc).isEqualTo(expectedBocAsHex);
    }


    @Test
    public void testWalletV3R2CreationMaster() throws IOException {

        // read the JSON file with tests cases
        String fileContentWithUseCases = new String(Files.readAllBytes(Paths.get(TON_TEST_CASES_SMARTCONTRACTS)));
        TonSdkTestCases tonSdkTestCases = gson.fromJson(fileContentWithUseCases, TonSdkTestCases.class);

        String testId = "smartcontracts-13";

        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // print test case details
        log.info("TestCase: {}", testCase);

        // fetch input parameters
        String prvKey = (String) testCase.getInput().get("privateKey");
        Long workchain = (Long) testCase.getInput().get("workchain");
        Long walletId = (Long) testCase.getInput().get("walletId");


        // test the functionality of your library

        TweetNaclFast.Signature.KeyPair keyPair = TweetNaclFast.Signature.keyPair_fromSeed(Utils.hexToSignedBytes(prvKey));

        WalletV3R2 contract = WalletV3R2.builder()
                .wc(workchain)
                .keyPair(keyPair)
                .walletId(walletId)
                .build();

        String actualCodeAsHex = contract.getStateInit().getCode().bitStringToHex();
        String actualDataAsHex = contract.getStateInit().getData().bitStringToHex();
        String actualRawAddress = contract.getAddress().toRaw();

        Message msg = contract.prepareDeployMsg();

        String actualExtMsgAsHex = msg.toCell().bitStringToHex();

        String actualExtMsgAsBoc = Utils.bytesToHex(msg.toCell().toBoc(true)).toUpperCase();

        // fetch the expected result and compare it against the actual one
        String expectedRawAddress = (String) testCase.getExpectedOutput().get("rawAddress");
        String expectedCodeAsHex = (String) testCase.getExpectedOutput().get("codeAsHex");
        String expectedDataAsHex = (String) testCase.getExpectedOutput().get("dataAsHex");
        String expectedExtMsgAsHex = (String) testCase.getExpectedOutput().get("externalMessageAsHex");
        String expectedBocAsHex = (String) testCase.getExpectedOutput().get("bocAsHex");

        log.info("expected results:");
        log.info("  rawAddress: {}", expectedRawAddress);
        log.info("  codeAsHex: {}", expectedCodeAsHex);
        log.info("  dataAsHex: {}", expectedDataAsHex);
        log.info("  extMsgAsHex: {}", expectedExtMsgAsHex);
        log.info("  bocAsHex: {}", expectedBocAsHex);

        assertThat(actualRawAddress).isEqualTo(expectedRawAddress);
        assertThat(actualCodeAsHex).isEqualTo(expectedCodeAsHex);
        assertThat(actualDataAsHex).isEqualTo(expectedDataAsHex);
        assertThat(actualExtMsgAsHex).isEqualTo(expectedExtMsgAsHex);
        assertThat(actualExtMsgAsBoc).isEqualTo(expectedBocAsHex);
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
