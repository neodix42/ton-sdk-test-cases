# TON SDK test cases

Generic set of use cases for testing implementation of various TON libraries and interfaces.

## Use cases

- [Address](https://github.com/neodix42/ton-sdk-test-cases/blob/main/address.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/address/src/test/java/org/ton/java/address/TestTonSdkTestCasesAddress.java)
    - address-1 - Test conversion of raw address in a base chain.
    - address-2 - Test conversion of raw address in a master chain.
    - address-3 - Test conversion of raw address with a wrong (negative) workchain.
    - address-4 - Test conversion of raw address with a wrong (one) workchain.
    - address-5 - Test conversion of raw address with a wrong (two) workchain.
    - address-6 - Test conversion of master raw address.
    - address-7 - Test conversion of elector raw address.
    - address-8 - Test conversion of url-safe base64 encoded address.
    - address-9 - Test conversion of non-url-safe base64 encoded non-bounceable address.
    - address-10 - Test conversion of url-safe base64 encoded address with a wrong checksum.
    - address-11 - Test conversion of raw address with too short hash size.
    - address-12 - Test conversion of raw address with too long hash size.
- [Math operations](https://github.com/neodix42/ton-sdk-test-cases/blob/main/numbers.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/address/src/test/java/org/ton/java/utils/TestTonSdkTestCasesNumbers.java)
    - numbers-1 - Check big number comparison.
    - numbers-2 - Check toncoin sum and rounding to 2 decimals HALF_UP.
    - numbers-3 - Check toNano() or similar. No rounding, truncate >9 decimals.
    - numbers-4 - Check parsing fromNano() or similar.
    - numbers-5 - Check toNano() overflow.
    - numbers-6 - Check fromNano() overflow.
    - numbers-7 - Check fromNano() near max value overflow, 2^120.
    - numbers-8 - "Check fromNano() max value, 2^120-1.
- [Cryptography](https://github.com/neodix42/ton-sdk-test-cases/blob/main/cryptography.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/address/src/test/java/org/ton/java/utils/TestTonSdkTestCasesCryptography.java)
    - cryptography-1 - CRC32C algorithm using the polynomial 0x1EDC6F41 tests.
    - cryptography-2 - CRC-16/XMODEM algorithm tests.
    - cryptography-3 - SHA256 tests.
    - cryptography-4 - SHA-1 tests.
    - cryptography-5 - Signing with Ed25519 string test.
    - cryptography-6 - MD5 tests.
- [Wallets](https://github.com/neodix42/ton-sdk-test-cases/blob/main/smartcontracts.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/address/src/test/java/org/ton/java/smartcontract/TestTonSdkTestCasesSmartContracts.java)
    - smartcontracts-1 - Wallet V3R2 code cell serialized.
    - smartcontracts-2 - Creation of Wallet V3R2 deploy external message.
    - smartcontracts-3 - Creation of Wallet V3R2 deploy external message. Workchain -1.
    - smartcontracts-4 - Create transfer message using Wallet V3R2. With bounce flag. Final BoC with checksum.
    - smartcontracts-5 - Create transfer message using Wallet V3R2. Without bounce flag.
    - smartcontracts-6 - Create transfer message using Wallet V3R2. With bounce flag and comment.
    - smartcontracts-7 - Wallet V4R2 code cell serialized.
    - smartcontracts-8 - Creation of Wallet V4R2 deploy external message. Final BoC with checksum.
    - smartcontracts-9 - Creation of Wallet V4R2. Testing deploy external message. Workchain -1. Final BoC with
      checksum.
    - smartcontracts-10 - Create transfer message using Wallet V4R2. With bounce flag. Final BoC with checksum.
    - smartcontracts-11 - Create transfer message using Wallet V4R2. Without bounce flag.
    - smartcontracts-12 - Create transfer message using Wallet V4R2. With bounce flag and comment.
    - smartcontracts-13 - Create transfer message using Wallet V4R2. With bounce flag and cell as body <b 8 32 u, >.
    - smartcontracts-14 - Test USDT 0.20 jettons transfer on Mainnet using V3R2 wallet with a text payload to a random
      destination.
    - smartcontracts-15 - Test USDT 0.20 jettons transfer on Mainnet using V4R2 wallet with a text payload to a random
      destination.
    - smartcontracts-16 - Test toncoins transfer using HighLoad Wallet V3R1. Send 0.1 Toncoin to a single random
      address.
    - smartcontracts-17 - Test bulk toncoins transfer using HighLoad Wallet V3R1. Send 0.1 Toncoin with unique comment
      to a random 50 addresses.
    - smartcontracts-18 - Test jetton transfer using HighLoad Wallet V3R1. Send 1 NEOJ (100 with decimals) jettons to a
      single random address.
    - smartcontracts-19 - Test bulk jetton transfer using HighLoad Wallet V3R1. Send 1 NEOJ (100 with decimals) jettons
      with unique comment to 300 random addresses.

- Cell
    - [serialization](https://github.com/neodix42/ton-sdk-test-cases/blob/main/cell-serialization.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/address/src/test/java/org/ton/java/cell/TestTonSdkTestCasesCellSerialization.java)
        - cell-serialization-1 - Basic cell serialization to fift output format and BoC (Bag of Cells).
        - cell-serialization-2 - Cell serialization with one reference cell and applying various combinations of BoC
          serialization flags.
        - cell-serialization-3 - Testing cell's depth, refs and bits descriptor calculations.

    - [deserialization](https://github.com/neodix42/ton-sdk-test-cases/blob/main/cell-deserialization.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/address/src/test/java/org/ton/java/cell/TestTonSdkTestCasesCellDeserialization.java)
        - cell-deserialization-1 -Basic cell deserialization.
        - cell-deserialization-2 -Basic cell deserialization.
        - cell-deserialization-3 -Basic cell deserialization.
        - cell-deserialization-4 -Basic cell deserialization.
- TL-B (to finish)
    - [serialization](https://github.com/neodix42/ton-sdk-test-cases/blob/main/tlb-serialization.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/address/src/test/java/org/ton/java/cell/TestTonSdkTestCasesTlbSerialization.java)
        - tlb-serialization-1 - MsgAddress serialization.
        - tlb-serialization-2 - MsgAddress serialization.
    - [deserialization](https://github.com/neodix42/ton-sdk-test-cases/blob/main/tlb-deserialization.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/address/src/test/java/org/ton/java/cell/TestTonSdkTestCasesTlbDeserialization.java)
        - tlb-deserialization-1 - MsgAddress deserialization.
        - tlb-deserialization-2 - MsgAddress deserialization.
- TON Hashmaps (todo)
    - [serialization](https://github.com/neodix42/ton-sdk-test-cases/blob/main/hashmap-serialization.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/address/src/test/java/org/ton/java/cell/TestTonSdkTestCasesHashmapSerialization.java)
        - hashmap-serialization-1 - TonHashmap serialization.
        - hashmap-serialization-2 - TonHashmapE serialization.
        - hashmap-serialization-3 - TonHashmapAug serialization.
        - hashmap-serialization-4 - TonHashmapAugE serialization.
        - hashmap-serialization-5 - TonHashmapPfx serialization.
        - hashmap-serialization-6 - TonHashmapPfxE serialization.

    - [deserialization](https://github.com/neodix42/ton-sdk-test-cases/blob/main/hashmap-deserialization.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/address/src/test/java/org/ton/java/cell/TestTonSdkTestCasesHashmapDeserialization.java)
        - hashmap-deserialization-1 - TonHashmap serialization.
        - hashmap-deserialization-2 - TonHashmapE serialization.
        - hashmap-deserialization-3 - TonHashmapAug serialization.
        - hashmap-deserialization-4 - TonHashmapAugE serialization.
        - hashmap-deserialization-5 - TonHashmapPfx serialization.
        - hashmap-deserialization-6 - TonHashmapPfxE serialization.

## JSON data file structure

Root element in JSON called - `testCases`.

Then follows any number of `category` names, where each category has a number of unique test-case ids.
Each test case has mandatory fields:

* `description`
* `input`
* `expectedOutput`

Each `input` and `expectedOutput` can have arbitrary input parameters, for example:

```
"input": {
  "prvKey": "F182111193F30D79D517F2339A1BA7C25FDF6C52142F0F2C1D960A1F1D65E1E4", 
  "wc": 0, 
"initialSeqno": 0
}, 
"expectedOutput": {
  "rawAddress": "0:258e549638a6980ae5d3c76382afd3f4f32e34482dafc3751e3358589c8de00d", 
  "bocAsHex": "B5EE9C72410103010..."
}
```

Look at validation [scheme.json](
https://raw.githubusercontent.com/neodix42/ton-sdk-test-cases/main/scheme.json) for more details.

## How to use?

### Example provided for ton4j library

Download JSON data file with test cases and put it to your folder [TonSdkTestCases.json](
https://raw.githubusercontent.com/neodix42/ton-sdk-test-cases/main/TonSdkTestCases.json)

```
public static final String TON_TEST_CASES_FILE_NAME = "TonSdkTestCases.json";
Gson gson = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();

@Test
public void testWalletV3R2Creation() throws IOException {

        // read the JSON file with tests cases
        String fileContentWithUseCases = new String(Files.readAllBytes(Paths.get(TON_TEST_CASES_FILE_NAME)));
        TonSdkTestCases tonSdkTestCases = gson.fromJson(fileContentWithUseCases, TonSdkTestCases.class);

        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get("wallets").get("9");

        // fetch test's description and id. It's always good to show test id,  since it is unique across all tests.
        String testId = testCase.getId();
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        // fetch and print input parameters
        String prvKey = (String)testCase.getInput().get("prvKey");
        Long workchain = (Long)testCase.getInput().get("wc");
        Long walletId = (Long)testCase.getInput().get("walletId");

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
        String expectedRawAddress = (String)testCase.getExpectedOutput().get("rawAddress");
        String expectedBocAsHex = (String)testCase.getExpectedOutput().get("bocAsHex");

        log.info("expected results:");
        log.info("  rawAddress: {}", expectedRawAddress);
        log.info("  bocAsHex: {}", expectedBocAsHex);

        assertThat(actualRawAddress).isEqualTo(expectedRawAddress);
        assertThat(actualExtMsgForSerializationAsBoc).isEqualTo(expectedBocAsHex);
}
```

More examples can be found [here](./src/test/java/UsageExampleTest.java).
