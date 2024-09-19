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

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@RunWith(JUnit4.class)
public class CryptographyFiftTests {
    Gson gson = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    String fileContentWithUseCases = IOUtils.toString(Paths.get(Paths.get("").toAbsolutePath() + "/cryptography.json").toUri());
    // read the JSON file with tests cases
    TonSdkTestCases tonSdkTestCases = gson.fromJson(fileContentWithUseCases, TonSdkTestCases.class);

    static String result;

    public CryptographyFiftTests() throws IOException {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws URISyntaxException {
        log.info("executing fift script, might take long time...");
        URL resource = TestFiftRunner.class.getResource("/fift-tests/cryptography.fif");
        File fiftFile = Paths.get(resource.toURI()).toFile();
        FiftRunner fiftRunner = FiftRunner.builder().build();
        String absolutePath = fiftFile.getAbsolutePath();
        result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath);
    }

    @Test
    public void testCryptography1() {

        log.info("{}", result);

        String testId = "cryptography-1";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = UtilsStr.sb(result, "cryptography-1-result-1: ", "\n").trim();
        String actualResult2 = UtilsStr.sb(result, "cryptography-1-result-2: ", "\n").trim();
        String actualResult3 = UtilsStr.sb(result, "cryptography-1-result-3: ", "\n").trim();
        String actualResult4 = UtilsStr.sb(result, "cryptography-1-result-4: ", "\n").trim();

        String expectedResultAsUnsignedInt1 = (String) testCase.getExpectedOutput().get("resultAsUnsignedInt1");
        String expectedResultAsUnsignedInt2 = (String) testCase.getExpectedOutput().get("resultAsUnsignedInt2");
        String expectedResultAsUnsignedInt3 = (String) testCase.getExpectedOutput().get("resultAsUnsignedInt3");
        String expectedResultAsUnsignedInt4 = (String) testCase.getExpectedOutput().get("resultAsUnsignedInt4");

        assertThat(actualResult1).isEqualTo(expectedResultAsUnsignedInt1);
        assertThat(actualResult2).isEqualTo(expectedResultAsUnsignedInt2);
        assertThat(actualResult3).isEqualTo(expectedResultAsUnsignedInt3);
        assertThat(actualResult4).isEqualTo(expectedResultAsUnsignedInt4);
    }

    @Test
    public void testCryptography2() {

        String testId = "cryptography-2";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = UtilsStr.sb(result, "cryptography-2-result-1: ", "\n").trim();
        String actualResult2 = UtilsStr.sb(result, "cryptography-2-result-2: ", "\n").trim();
        String actualResult3 = UtilsStr.sb(result, "cryptography-2-result-3: ", "\n").trim();
        String actualResult4 = UtilsStr.sb(result, "cryptography-2-result-4: ", "\n").trim();

        String expectedResultAsUnsignedInt1 = (String) testCase.getExpectedOutput().get("resultAsUnsignedInt1");
        String expectedResultAsUnsignedInt2 = (String) testCase.getExpectedOutput().get("resultAsUnsignedInt2");
        String expectedResultAsUnsignedInt3 = (String) testCase.getExpectedOutput().get("resultAsUnsignedInt3");
        String expectedResultAsUnsignedInt4 = (String) testCase.getExpectedOutput().get("resultAsUnsignedInt4");

        assertThat(actualResult1).isEqualTo(expectedResultAsUnsignedInt1);
        assertThat(actualResult2).isEqualTo(expectedResultAsUnsignedInt2);
        assertThat(actualResult3).isEqualTo(expectedResultAsUnsignedInt3);
        assertThat(actualResult4).isEqualTo(expectedResultAsUnsignedInt4);
    }

    @Test
    public void testCryptography3() {

        String testId = "cryptography-3";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = UtilsStr.sb(result, "cryptography-3-result-1: ", "\n").trim();
        String actualResult2 = UtilsStr.sb(result, "cryptography-3-result-2: ", "\n").trim();
        String actualResult3 = UtilsStr.sb(result, "cryptography-3-result-3: ", "\n").trim();
        String actualResult4 = UtilsStr.sb(result, "cryptography-3-result-4: ", "\n").trim();

        String expectedResultAsUnsignedInt1 = (String) testCase.getExpectedOutput().get("resultBase641");
        String expectedResultAsUnsignedInt2 = (String) testCase.getExpectedOutput().get("resultBase642");
        String expectedResultAsUnsignedInt3 = (String) testCase.getExpectedOutput().get("resultBase643");
        String expectedResultAsUnsignedInt4 = (String) testCase.getExpectedOutput().get("resultBase644");

        assertThat(actualResult1).isEqualTo(expectedResultAsUnsignedInt1);
        assertThat(actualResult2).isEqualTo(expectedResultAsUnsignedInt2);
        assertThat(actualResult3).isEqualTo(expectedResultAsUnsignedInt3);
        assertThat(actualResult4).isEqualTo(expectedResultAsUnsignedInt4);
    }

    @Test
    public void testCryptography5() {

        String testId = "cryptography-5";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = UtilsStr.sb(result, "cryptography-5-result-1: ", "\n").trim();
        String actualResult2 = UtilsStr.sb(result, "cryptography-5-result-2: ", "\n").trim();
        String actualResult3 = UtilsStr.sb(result, "cryptography-5-result-3: ", "\n").trim();
        String actualResult4 = UtilsStr.sb(result, "cryptography-5-result-4: ", "\n").trim();

        String expectedResultAsUnsignedInt1 = (String) testCase.getExpectedOutput().get("resultBase641");
        String expectedResultAsUnsignedInt2 = (String) testCase.getExpectedOutput().get("resultBase642");
        String expectedResultAsUnsignedInt3 = (String) testCase.getExpectedOutput().get("resultBase643");
        String expectedResultAsUnsignedInt4 = (String) testCase.getExpectedOutput().get("resultBase644");

        assertThat(actualResult1).isEqualTo(expectedResultAsUnsignedInt1);
        assertThat(actualResult2).isEqualTo(expectedResultAsUnsignedInt2);
        assertThat(actualResult3).isEqualTo(expectedResultAsUnsignedInt3);
        assertThat(actualResult4).isEqualTo(expectedResultAsUnsignedInt4);
    }
}
