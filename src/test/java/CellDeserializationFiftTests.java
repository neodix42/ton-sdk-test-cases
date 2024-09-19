import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.ton.java.fift.FiftRunner;
import org.ton.java.utils.Utils;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(JUnit4.class)
public class CellDeserializationFiftTests {
    Gson gson = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    String fileContentWithUseCases = IOUtils.toString(Paths.get(Paths.get("").toAbsolutePath() + "/cell-deserialization.json").toUri());
    // read the JSON file with tests cases
    TonSdkTestCases tonSdkTestCases = gson.fromJson(fileContentWithUseCases, TonSdkTestCases.class);

    static String result;

    public CellDeserializationFiftTests() throws IOException {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws URISyntaxException {
        URL resource = TestFiftRunner.class.getResource("/fift-tests/cell-deserialization.fif");
        File fiftFile = Paths.get(resource.toURI()).toFile();
        FiftRunner fiftRunner = FiftRunner.builder().build();
        String absolutePath = fiftFile.getAbsolutePath();
        result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath);
    }

    @Test
    public void testCellDeserialization1() {

        log.info("{}", result);

        String testId = "cell-deserialization-1";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        Integer actualResult1 = Integer.valueOf(UtilsStr.sb(result, "cell-deserialization-1-loadUint7: ", "\n").trim().toLowerCase());
        String actualResult2 = UtilsStr.sb(result, "cell-deserialization-1-bitStringToFiftHex: ", "end").trim().replaceAll("\\r", "");

        Integer expectedLoadUint7 = Integer.valueOf(testCase.getExpectedOutput().get("loadUint7").toString());
        String expectedBitStringToFiftHex = (String) testCase.getExpectedOutput().get("bitStringToFiftHex");

        assertThat(actualResult1).isEqualTo(expectedLoadUint7);
        assertThat(actualResult2).isEqualTo(expectedBitStringToFiftHex);
    }

    @Test
    public void testCellDeserialization2() {

        String testId = "cell-deserialization-2";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = UtilsStr.sb(result, "cell-deserialization-2-cell1_bits: ", "\n").trim();
        String actualResult2 = UtilsStr.sb(result, "cell-deserialization-2-cell2_bytes: ", "\n").trim();
        Integer actualResult3 = Integer.parseInt(UtilsStr.sb(result, "cell-deserialization-2-cell3_address: ", "\n").trim());
        Integer actualResult4 = Integer.parseInt(UtilsStr.sb(result, "cell-deserialization-2-cell4_toncoins: ", "\n").trim());
        String actualResult5 = UtilsStr.sb(result, "cell-deserialization-2-cell5_bytes: ", "\n").trim();

        Boolean expectedCell1_bits = (Boolean) testCase.getExpectedOutput().get("cell1_bits");
        byte[] expectedCell2_bytes = gson.fromJson(testCase.getExpectedOutput().get("cell2_bytes").toString(), byte[].class);

        String expectedCell3_address = (String) testCase.getExpectedOutput().get("cell3_address");
        Integer expectedCell4_toncoins = Integer.parseInt(testCase.getExpectedOutput().get("cell4_toncoins").toString());
        byte[] expectedCell5_bytes = gson.fromJson(testCase.getExpectedOutput().get("cell5_bytes").toString(), byte[].class);

        assertThat(actualResult1.equals("1")).isEqualTo(expectedCell1_bits);
        assertThat(actualResult2).isEqualTo(Utils.bytesToHex(expectedCell2_bytes));
        assertThat(actualResult3).isEqualTo(0);
        assertThat(actualResult4).isEqualTo(expectedCell4_toncoins);
        assertThat(actualResult5).isEqualTo(Utils.bytesToHex(expectedCell5_bytes));
    }


    @Test
    public void testCellDeserialization3() {

        String testId = "cell-deserialization-3";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = UtilsStr.sb(result, "cell-deserialization-3-expectedFiftOutput: ", "end").trim().replaceAll("\\r", "");

        String expectedFiftOutput = (String) testCase.getExpectedOutput().get("expectedFiftOutput");

        assertThat(actualResult1).isEqualTo(expectedFiftOutput);
    }

    @Test
    public void testCellDeserialization4() {

        String testId = "cell-deserialization-4";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = UtilsStr.sb(result, "cell-deserialization-4-deserialized-ok", "\n").trim();

        assertThat(actualResult1).isNotNull();
    }

    @Test
    public void testCellDeserialization5() {

        String testId = "cell-deserialization-5";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = UtilsStr.sb(result, "cell-deserialization-5-bocAsHexWithCrc: ", "\n").trim();

        String expectedBocAsHexWithCrc = (String) testCase.getExpectedOutput().get("bocAsHexWithCrc");
        assertThat(actualResult1).isEqualTo(expectedBocAsHexWithCrc);
    }

    @Test
    public void testCellDeserialization6() {

        log.info("{}", result);

        String testId = "cell-deserialization-6";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = UtilsStr.sb(result, "cell-deserialization-6-hash: ", "\n").trim();

        String expectedBocAsHexWithCrc = (String) testCase.getExpectedOutput().get("hash");
        assertThat(actualResult1).isEqualTo(expectedBocAsHexWithCrc.toUpperCase());
    }

    @Test
    public void testCellDeserialization7() {

        log.info("{}", result);

        String testId = "cell-deserialization-7";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = UtilsStr.sb(result, "cell-deserialization-7-hash: ", "\n").trim();

        String expectedBocAsHexWithCrc = (String) testCase.getExpectedOutput().get("hash");
        assertThat(actualResult1).isEqualTo(expectedBocAsHexWithCrc.toUpperCase());
    }

    @Test
    public void testCellDeserialization8() {

        log.info("{}", result);

        String testId = "cell-deserialization-8";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = UtilsStr.sb(result, "cell-deserialization-8-hash: ", "\n").trim();
//        Integer actualResult2 = Integer.parseInt(UtilsStr.sb(result, "cell-deserialization-8-sizeOfRefs: ", "\n").trim());

        String expectedBocAsHexWithCrc = (String) testCase.getExpectedOutput().get("hash");
//        Integer expectedSizeOfRefs = Integer.parseInt(testCase.getExpectedOutput().get("sizeOfRefs").toString());

        assertThat(actualResult1).isEqualTo(expectedBocAsHexWithCrc.toUpperCase());
//        assertThat(actualResult2).isEqualTo(expectedSizeOfRefs);
    }

    @Test
    public void testCellDeserialization9() {

        log.info("{}", result);

        String testId = "cell-deserialization-9";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        Integer actualResult1 = Integer.parseInt(UtilsStr.sb(result, "cell-deserialization-9-cellBitLength: ", "\n").trim());

        Integer expectedCellBitLength = Integer.parseInt(testCase.getExpectedOutput().get("cellBitLength").toString());
        assertThat(actualResult1).isEqualTo(expectedCellBitLength);
    }
}
