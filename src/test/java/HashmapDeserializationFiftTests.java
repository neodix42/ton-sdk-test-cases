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
public class HashmapDeserializationFiftTests {
    Gson gson = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    String fileContentWithUseCases = IOUtils.toString(Paths.get(Paths.get("").toAbsolutePath() + "/hashmap-deserialization.json").toUri());
    TonSdkTestCases tonSdkTestCases = gson.fromJson(fileContentWithUseCases, TonSdkTestCases.class);

    static String result;

    public HashmapDeserializationFiftTests() throws IOException {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws URISyntaxException {
        URL resource = TestFiftRunner.class.getResource("/fift-tests/hashmap-deserialization.fif");
        assert resource != null;
        File fiftFile = Paths.get(resource.toURI()).toFile();
        FiftRunner fiftRunner = FiftRunner.builder().build();
        String absolutePath = fiftFile.getAbsolutePath();
        result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath);
    }

    @Test
    public void testHashmapDeserialization4() {

        log.info("{}", result);

        String testId = "hashmap-deserialization-4";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = UtilsStr.sb(result, "hashmap-deserialization-4-result: ", "\n").trim();


        int expectedResultElementsCount = Integer.parseInt(testCase.getExpectedOutput().get("elementsCount").toString());
        int expectedResultFirstElementValue = Integer.parseInt(testCase.getExpectedOutput().get("firstElementValue").toString());

        assertThat(StringUtils.countMatches(actualResult1, ";")).isEqualTo(expectedResultElementsCount);
        assertThat(actualResult1.contains(Integer.toString(expectedResultFirstElementValue))).isTrue();
    }
}