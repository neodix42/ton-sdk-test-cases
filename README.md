# TON SDK test cases

Generic set of use cases for testing implementation of various TON libraries and interfaces.

## JSON data file structure

Root element in JSON called - `testCases`.

Then follows any number of `category` names, where each category has a number of unique test-case ids.
Each test case has mandatory fields:

* `id`
* `description`
* `input`
* `expectedOutput`

Each `input` and `expectedOutput` can have arbitrary input parameters, for example:

```json
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

```java
public static final String TON_TEST_CASES_FILE_NAME = "TonSdkTestCases.json";

Gson gson = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();

@Test
public void testWalletV3R2Creation() throws IOException {

    // read the JSON file with tests cases
    String fileContentWithUseCases = new String(Files.readAllBytes(Paths.get(TON_TEST_CASES_FILE_NAME)));
    TonSdkTestCases tonSdkTestCases = gson.fromJson(fileContentWithUseCases, TonSdkTestCases.class);

    // select particular test case by category name and test id
    TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get("wallets").get("9");

    // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
    String testId = testCase.getId();
    String description = testCase.getDescription();

    log.info("testId: {}", testId);
    log.info("description: {}", description);

    // fetch and print input parameters
    String prvKey = (String) testCase.getInput().get("prvKey");
    Long workchain = (Long) testCase.getInput().get("wc");
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
```

More examples can be found [here](./src/test/java/UsageExampleTest.java).
