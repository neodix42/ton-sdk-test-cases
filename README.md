# TON SDK test cases

Generic set of use cases for testing implementation of various TON libraries and interfaces.

## Use cases

### [Address](https://github.com/neodix42/ton-sdk-test-cases/blob/main/address.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/address/src/test/java/org/ton/java/address/TestTonSdkTestCasesAddress.java)

1. Test conversion of raw address in a base chain.
2. Test conversion of raw address in a master chain.
3. Test conversion of raw address with a wrong (negative) workchain.
4. Test conversion of raw address with a wrong (one) workchain.
5. Test conversion of raw address with a wrong (two) workchain.
6. Test conversion of master raw address.
7. Test conversion of elector raw address.
8. Test conversion of url-safe base64 encoded address.
9. Test conversion of non-url-safe base64 encoded non-bounceable address.
10. Test conversion of url-safe base64 encoded address with a wrong checksum.
11. Test conversion of raw address with too short hash size.
12. Test conversion of raw address with too long hash size.

### [Math operations](https://github.com/neodix42/ton-sdk-test-cases/blob/main/numbers.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/utils/src/test/java/org/ton/java/utils/TestTonSdkTestCasesNumbers.java)

1. Check big number comparison.
2. Check toncoin sum and rounding to 2 decimals HALF_UP.
3. Check toNano() or similar. No rounding, truncate >9 decimals.
4. Check parsing fromNano() or similar.
5. Check toNano() overflow.
6. Check fromNano() overflow.
7. Check fromNano() near max value overflow, 2^120.
8. Check fromNano() max value, 2^120-1.

### [Cryptography](https://github.com/neodix42/ton-sdk-test-cases/blob/main/cryptography.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/utils/src/test/java/org/ton/java/utils/TestTonSdkTestCasesCryptography.java)

1. CRC32C algorithm using the polynomial 0x1EDC6F41 tests.
2. CRC-16/XMODEM algorithm tests.
3. SHA256 tests.
4. SHA-1 tests.
5. Signing with Ed25519 string test.
6. MD5 tests.

### [Wallets](https://github.com/neodix42/ton-sdk-test-cases/blob/main/smartcontracts.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/smartcontract/src/test/java/org/ton/java/smartcontract/TestTonSdkTestCasesSmartContracts.java)

1. Wallet V3R2 code cell serialized.
2. Creation of Wallet V3R2 deploy external message.
3. Creation of Wallet V3R2 deploy external message. Workchain -1.
4. Create transfer message using Wallet V3R2. With bounce flag. Final BoC with checksum.
5. Create transfer message using Wallet V3R2. Without bounce flag.
6. Create transfer message using Wallet V3R2. With bounce flag and comment.
7. Wallet V4R2 code cell serialized.
8. Creation of Wallet V4R2 deploy external message. Final BoC with checksum.
9. Creation of Wallet V4R2. Testing deploy external message. Workchain -1. Final BoC with
   checksum.
10. Create transfer message using Wallet V4R2. With bounce flag. Final BoC with checksum.
11. Create transfer message using Wallet V4R2. Without bounce flag.
12. Create transfer message using Wallet V4R2. With bounce flag and comment.
13. Create transfer message using Wallet V4R2. With bounce flag and cell as body <b 8 32 u, >.
14. Test USDT 0.20 jettons transfer on Mainnet using V3R2 wallet with a text payload to a random
    destination.
15. Test USDT 0.20 jettons transfer on Mainnet using V4R2 wallet with a text payload to a random
    destination.
16. Test toncoins transfer using HighLoad Wallet V3R1. Send 0.1 Toncoin to a single random
    address.
17. Test bulk toncoins transfer using HighLoad Wallet V3R1. Send 0.1 Toncoin with unique comment
    to a random 50 addresses.
18. Test jetton transfer using HighLoad Wallet V3R1. Send 1 NEOJ (100 with decimals) jettons to a
    single random address.
19. Test bulk jetton transfer using HighLoad Wallet V3R1. Send 1 NEOJ (100 with decimals) jettons
    with unique comment to 300 random addresses.

### Cell

#### [Serialization](https://github.com/neodix42/ton-sdk-test-cases/blob/main/cell-serialization.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/cell/src/test/java/org/ton/java/cell/TestTonSdkTestCasesCellSerialization.java)

1. Basic cell serialization to fift output format and BoC (Bag of Cells).
2. Cell serialization with one reference cell and applying various combinations of BoC
   serialization flags.
3. Testing cell's depth, refs and bits descriptor calculations.

#### [Deserialization](https://github.com/neodix42/ton-sdk-test-cases/blob/main/cell-deserialization.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/cell/src/test/java/org/ton/java/cell/TestTonSdkTestCasesCellDeserialization.java)

1. Basic cell deserialization.
2. Basic cell with refs deserialization.
3. Complex cell deserialization. https://github.com/toncenter/tonweb/issues/70.
4. BoC that contains result of tonlib's configAll query deserialization.
5. Cell deserialization and serialization. fromBoc(input.bocAsHexWithCrc) -> toBoc(). Resulted bocAsHexWithCrc
   should match input bocAsHexWithCrc. Tested cell: <b b{01} s, 5 32 u, 6 31 u, true 1 i, b>.
6. Compare huge cell's hash.
7. Test exotic (merkle-update) cell deserialization.
8. Insane deserialization, issue https://github.com/toncenter/tonweb/issues/74
9. Cell with Account State deserialization.

### TL-B

#### [Serialization](https://github.com/neodix42/ton-sdk-test-cases/blob/main/tlb-serialization.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/address/src/test/java/org/ton/java/cell/TestTonSdkTestCasesTlbSerialization.java)

1. MsgAddress serialization.
2. ShardIdent construction, parent and children lookup.
3. StorageInfo.storage_info$_ serialization

#### [Deserialization](https://github.com/neodix42/ton-sdk-test-cases/blob/main/tlb-deserialization.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/address/src/test/java/org/ton/java/cell/TestTonSdkTestCasesTlbDeserialization.java)

1. CommonMsgInfo.int_msg_info$0 deserialization.
2. CommonMsgInfo.ext_in_msg_info$10 deserialization.
3. Master block deserialization.
4. Workchain block deserialization.
5. Complex block deserialization.

### TON Hashmaps

#### [Serialization](https://github.com/neodix42/ton-sdk-test-cases/blob/main/hashmap-serialization.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/cell/src/test/java/org/ton/java/cell/TestTonSdkTestCasesHashmapSerialization.java)

1. TonHashmap serialization.
2. TonHashmapE serialization.
3. TonHashmapAug serialization.
4. TonHashmapAugE serialization.
5. TonHashmapPfx serialization.
6. TonHashmapPfxE serialization.

#### [Deserialization](https://github.com/neodix42/ton-sdk-test-cases/blob/main/hashmap-deserialization.json) [(ton4j)](https://github.com/neodix42/ton4j/blob/main/cell/src/test/java/org/ton/java/cell/TestTonSdkTestCasesHashmapDeserialization.java)

1. TonHashmap serialization.
2. TonHashmapE serialization.
3. TonHashmapAug serialization.
4. TonHashmapAugE serialization.
5. TonHashmapPfx serialization.
6. TonHashmapPfxE serialization.

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
