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
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@RunWith(JUnit4.class)
public class CellSerializationFiftTests {
    Gson gson = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();
    String fileContentWithUseCases = IOUtils.toString(Paths.get(Paths.get("").toAbsolutePath() + "/cell-serialization.json").toUri());
    // read the JSON file with tests cases
    TonSdkTestCases tonSdkTestCases = gson.fromJson(fileContentWithUseCases, TonSdkTestCases.class);

    static String result;

    public CellSerializationFiftTests() throws IOException {
    }

    @BeforeClass
    public static void setUpBeforeClass() throws URISyntaxException {
        log.info("executing fift script, might take long time...");
        URL resource = TestFiftRunner.class.getResource("/fift-tests/cell-serialization.fif");
        File fiftFile = Paths.get(resource.toURI()).toFile();
        FiftRunner fiftRunner = FiftRunner.builder().build();
        String absolutePath = fiftFile.getAbsolutePath();
        result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath);
    }

    @Test
    public void testCellSerialization1() {

        log.info("{}", result);

        String testId = "cell-serialization-1";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = sb(result, "cell-serialization-1-cellOutput: ", "\n").trim().toLowerCase();
        String actualResult2 = sb(result, "cell-serialization-1-bocAsHex: ", "\n").trim().toLowerCase();
        String actualResult3 = sb(result, "cell-serialization-1-cellHash: ", "\n").trim().toLowerCase();

        String expectedCellOutput = (String) testCase.getExpectedOutput().get("cellOutput");
        String expectedBocAsHex = (String) testCase.getExpectedOutput().get("bocAsHex");
        String expectedCellHash = (String) testCase.getExpectedOutput().get("cellHash");

        assertThat(actualResult1).isEqualTo(expectedCellOutput);
        assertThat(actualResult2).isEqualTo(expectedBocAsHex);
        assertThat(actualResult3).isEqualTo(expectedCellHash);
    }

    @Test
    public void testCellSerialization2() {

        log.info("{}", result);

        String testId = "cell-serialization-2";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = sb(result, "cell-serialization-2-cellOutput: ", "end").trim().replaceAll("\\r", "");
        String actualResult2 = sb(result, "cell-serialization-2-bocAsHexWithCrcOnly: ", "\n").trim();
        String actualResult3 = sb(result, "cell-serialization-2-bocAsHexWithIndexOnly: ", "\n").trim();
        String actualResult4 = sb(result, "cell-serialization-2-bocAsHexWithCrcAndIndex: ", "\n").trim();
        String actualResult5 = sb(result, "cell-serialization-2-cellHash: ", "\n").trim().toLowerCase();


        String expectedCellOutput = (String) testCase.getExpectedOutput().get("cellOutput");
        String expectedBocAsHexWithCrcOnly = (String) testCase.getExpectedOutput().get("bocAsHexWithCrcOnly");
        String expectedBocAsHexWithIndexOnly = (String) testCase.getExpectedOutput().get("bocAsHexWithIndexOnly");
        String expectedBocAsHexWithCrcAndIndex = (String) testCase.getExpectedOutput().get("bocAsHexWithCrcAndIndex");
        String expectedCellHash = (String) testCase.getExpectedOutput().get("cellHash");

        assertThat(actualResult1).isEqualTo(expectedCellOutput.trim());
        assertThat(actualResult2.toLowerCase()).isEqualTo(expectedBocAsHexWithCrcOnly);
        assertThat(actualResult3).isEqualTo(expectedBocAsHexWithIndexOnly);
        assertThat(actualResult4).isEqualTo(expectedBocAsHexWithCrcAndIndex);
        assertThat(actualResult5).isEqualTo(expectedCellHash);
    }

    @Test
    public void testCellSerialization3() {

        log.info("{}", result);

        String testId = "cell-serialization-3";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        String actualResult1 = sb(result, "cell-serialization-3-cell5Output: ", "end").trim().replaceAll("\\r", "");
        String actualResult2 = sb(result, "cell-serialization-3-cell5bocAsHexWithCrcOnly: ", "\n").trim();
        String actualResult3 = sb(result, "cell-serialization-3-cell5Hash: ", "\n").trim();


        String expectedCellOutput = (String) testCase.getExpectedOutput().get("cell5Output");
        String expectedBocAsHexWithCrcOnly = (String) testCase.getExpectedOutput().get("cell5bocAsHexWithCrcOnly");
        String expectedCellHash = (String) testCase.getExpectedOutput().get("cell5Hash");

        assertThat(actualResult1).isEqualTo(expectedCellOutput.trim());
        assertThat(actualResult2.toLowerCase()).isEqualTo(expectedBocAsHexWithCrcOnly);
        assertThat(actualResult3.toLowerCase()).isEqualTo(expectedCellHash);
    }

    private static String sb(String str, String from, String to) {
        if (str == null || from == null || to == null) {
            return null;
        }

        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        byte[] fromBytes = from.getBytes(StandardCharsets.UTF_8);
        byte[] toBytes = to.getBytes(StandardCharsets.UTF_8);

        int startIndex = indexOf(bytes, fromBytes, 0);
        if (startIndex == -1) {
            return null;
        }
        startIndex += fromBytes.length;

        int endIndex = indexOf(bytes, toBytes, startIndex);
        if (endIndex == -1) {
            return null;
        }

        byte[] resultBytes = Arrays.copyOfRange(bytes, startIndex, endIndex);
        return new String(resultBytes, StandardCharsets.UTF_8);
    }

    private static int indexOf(byte[] array, byte[] target, int fromIndex) {
        if (target.length == 0) {
            return fromIndex;
        }
        if (target.length > array.length) {
            return -1;
        }

        int[] a = new int[256];
        for (int i = 0; i < target.length; i++) {
            a[target[i] & 0xFF] = i;
        }

        int m = target.length;
        int n = array.length;

        int s = fromIndex;
        while (s <= n - m) {
            int j = m - 1;
            while (j >= 0 && target[j] == array[s + j]) {
                j--;
            }
            if (j < 0) {
                return s;
            } else {
                s += Math.max(1, j - a[array[s + j] & 0xFF]);
            }
        }
        return -1;
    }
}
