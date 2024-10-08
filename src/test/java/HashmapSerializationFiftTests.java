import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.BeforeClass;
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
public class HashmapSerializationFiftTests {
    Gson gson = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    String fileContentWithUseCases = IOUtils.toString(Paths.get(Paths.get("").toAbsolutePath() + "/hashmap-serialization.json").toUri());
    // read the JSON file with tests cases
    TonSdkTestCases tonSdkTestCases = gson.fromJson(fileContentWithUseCases, TonSdkTestCases.class);

    static String result;

    public HashmapSerializationFiftTests() throws IOException {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws URISyntaxException {
        URL resource = TestFiftRunner.class.getResource("/fift-tests/hashmap-serialization.fif");
        assert resource != null;
        File fiftFile = Paths.get(resource.toURI()).toFile();
        FiftRunner fiftRunner = FiftRunner.builder().build();
        String absolutePath = fiftFile.getAbsolutePath();
        result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath);
    }

    @Test
    public void testHashmapSerialization4() {

        log.info("{}", result);

        String testId = "hashmap-serialization-4";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = UtilsStr.sb(result, "hashmap-serialization-4-HashMapPrint: ", "end").replaceAll("\\r", "").trim();
        String actualResult2 = UtilsStr.sb(result, "hashmap-serialization-4-HashMapHash: ", "\n").trim();
        String actualResult3 = UtilsStr.sb(result, "hashmap-serialization-4-HashMapBocWithCrc: ", "\n").trim();

        String expectedResultCellPrint = (String) testCase.getExpectedOutput().get("HashMapPrint");
        String expectedResultCellHash = (String) testCase.getExpectedOutput().get("HashMapHash");
        String expectedResultCellBocWithCrc = (String) testCase.getExpectedOutput().get("HashMapBocWithCrc");

        assertThat(actualResult1).isEqualTo(expectedResultCellPrint);
        assertThat(actualResult2).isEqualTo(expectedResultCellHash.toUpperCase());
        assertThat(actualResult3).isEqualTo(expectedResultCellBocWithCrc);
    }

    @Test
    public void testHashmapSerialization5() {

        log.info("{}", result);

        String testId = "hashmap-serialization-5";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = UtilsStr.sb(result, "hashmap-serialization-5-HashMapPrint: ", "end").replaceAll("\\r", "").trim();
        String actualResult2 = UtilsStr.sb(result, "hashmap-serialization-5-HashMapHash: ", "\n").trim();
        String actualResult3 = UtilsStr.sb(result, "hashmap-serialization-5-HashMapBocWithCrc: ", "\n").trim();

        String expectedResultCellPrint = (String) testCase.getExpectedOutput().get("HashMapPrint");
        String expectedResultCellHash = (String) testCase.getExpectedOutput().get("HashMapHash");
        String expectedResultCellBocWithCrc = (String) testCase.getExpectedOutput().get("HashMapBocWithCrc");

        assertThat(actualResult1).isEqualTo(expectedResultCellPrint);
        assertThat(actualResult2).isEqualTo(expectedResultCellHash.toUpperCase());
        assertThat(actualResult3).isEqualTo(expectedResultCellBocWithCrc.toUpperCase());
    }

    @Test
    public void testHashmapSerialization8() {

        log.info("{}", result);

        String testId = "hashmap-serialization-8";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = UtilsStr.sb(result, "hashmap-serialization-8-HashMapPrint: ", "end").replaceAll("\\r", "").trim();
        String actualResult2 = UtilsStr.sb(result, "hashmap-serialization-8-HashMapHash: ", "\n").trim();
        String actualResult3 = UtilsStr.sb(result, "hashmap-serialization-8-HashMapBocWithCrc: ", "\n").trim();

        String expectedResultCellPrint = (String) testCase.getExpectedOutput().get("HashMapPrint");
        String expectedResultCellHash = (String) testCase.getExpectedOutput().get("HashMapHash");
        String expectedResultCellBocWithCrc = (String) testCase.getExpectedOutput().get("HashMapBocWithCrc");

        assertThat(actualResult1).isEqualTo(expectedResultCellPrint);
        assertThat(actualResult2).isEqualTo(expectedResultCellHash.toUpperCase());
        assertThat(actualResult3).isEqualTo(expectedResultCellBocWithCrc);
    }

    @Test
    public void testHashmapSerialization9() {

        log.info("{}", result);

        String testId = "hashmap-serialization-9";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = UtilsStr.sb(result, "hashmap-serialization-9-HashMapPrint: ", "end").replaceAll("\\r", "").trim();
        String actualResult2 = UtilsStr.sb(result, "hashmap-serialization-9-HashMapHash: ", "\n").trim();
        String actualResult3 = UtilsStr.sb(result, "hashmap-serialization-9-HashMapBocWithCrc: ", "\n").trim();

        String expectedResultCellPrint = (String) testCase.getExpectedOutput().get("HashMapPrint");
        String expectedResultCellHash = (String) testCase.getExpectedOutput().get("HashMapHash");
        String expectedResultCellBocWithCrc = (String) testCase.getExpectedOutput().get("HashMapBocWithCrc");

        assertThat(actualResult1).isEqualTo(expectedResultCellPrint);
        assertThat(actualResult2).isEqualTo(expectedResultCellHash.toUpperCase());
        assertThat(actualResult3).isEqualTo(expectedResultCellBocWithCrc);
    }

    @Test
    public void testHashmapSerialization10() {

        log.info("{}", result);

        String testId = "hashmap-serialization-10";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        result = result.replaceAll("\\r", "");
        String actualResult1 = UtilsStr.sb(result, "hashmap-serialization-10-HashmapPrint: ", "end").replaceAll("\\r", "").trim();
        String actualResult2 = UtilsStr.sb(result, "hashmap-serialization-10-HashMapHash: ", "\n").trim();
        String actualResult3 = UtilsStr.sb(result, "hashmap-serialization-10-HashMapBocWithCrc: ", "\n").trim();
        String actualResult4 = StringUtils.substringBetween(result, "hashmap-serialization-10-cellWithHashmapPrint: ", "flash").replaceAll("\\r", "").trim();
        String actualResult5 = UtilsStr.sb(result, "hashmap-serialization-10-cellWithHashmapHash: ", "\n").trim();
        String actualResult6 = UtilsStr.sb(result, "hashmap-serialization-10-cellWithHashmapBoCWithCrc: ", "\n").trim();

        String expectedResultHashMapPrint = (String) testCase.getExpectedOutput().get("HashMapPrint");
        String expectedResultHashMapHash = (String) testCase.getExpectedOutput().get("HashMapHash");
        String expectedResultHashMapBocWithCrc = (String) testCase.getExpectedOutput().get("HashMapBocWithCrc");
        String expectedResultCellWithHashMapPrint = (String) testCase.getExpectedOutput().get("cellWithHashMapPrint");
        String expectedResultCellWithHashMapHash = (String) testCase.getExpectedOutput().get("cellWithHashMapHash");
        String expectedResultCellWithHashMapBocWithCrc = (String) testCase.getExpectedOutput().get("cellWithHashMapBocWithCrc");

        assertThat(actualResult1).isEqualTo(expectedResultHashMapPrint);
        assertThat(actualResult2).isEqualTo(expectedResultHashMapHash.toUpperCase());
        assertThat(actualResult3).isEqualTo(expectedResultHashMapBocWithCrc);
        assertThat(actualResult4).isEqualTo(expectedResultCellWithHashMapPrint);
        assertThat(actualResult5).isEqualTo(expectedResultCellWithHashMapHash);
        assertThat(actualResult6).isEqualTo(expectedResultCellWithHashMapBocWithCrc);
    }
}