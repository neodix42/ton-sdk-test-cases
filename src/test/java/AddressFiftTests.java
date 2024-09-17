import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
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
public class AddressFiftTests {

    Gson gson = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();

    URL resource = TestFiftRunner.class.getResource("/fift-tests/address.fif");
    File fiftFile = Paths.get(resource.toURI()).toFile();

    String fileContentWithUseCases = IOUtils.toString(Paths.get(Paths.get("").toAbsolutePath() + "/address.json").toUri());

    // read the JSON file with tests cases
    TonSdkTestCases tonSdkTestCases = gson.fromJson(fileContentWithUseCases, TonSdkTestCases.class);


    FiftRunner fiftRunner = FiftRunner.builder().build();
    String absolutePath = fiftFile.getAbsolutePath();

    public AddressFiftTests() throws IOException, URISyntaxException {
    }


    @Test
    public void testAddress1() {

        String testId = "address-1";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        // fetch input parameters and print test case details
        String inputAddress = (String) testCase.getInput().get("inputRawAddress");

        log.info("input parameters:");
        log.info("  address: {}", inputAddress);

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, inputAddress);

        String nonBounceable = sb(result, "Non-bounceable address (for init only): ", "\n").trim();
        String bounceable = sb(result, "Bounceable address (for later access): ", "\n").trim();
        String nonBounceableUrlSafe = sb(result, "Non-bounceable address, Base64url (for init): ", "\n").trim();
        String bounceableUrlSafe = sb(result, "Bounceable address, Base64url (for later access): ", "\n").trim();

        String nonBounceableTest = sb(result, "Non-bounceable test address (for init only): ", "\n").trim();
        String bounceableTest = sb(result, "Bounceable test address (for later access): ", "\n").trim();
        String nonBounceableUrlSafeTest = sb(result, "Non-bounceable test address, Base64url (for init): ", "\n").trim();
        String bounceableUrlSafeTest = sb(result, "Bounceable test address, Base64url (for later access): ", "\n").trim();

        // fetch the expected result and compare it against the actual one
        String expectedBounceableUrlSafe = (String) testCase.getExpectedOutput().get("bounceableUrlSafe");
        String expectedNonBounceableUrlSafe = (String) testCase.getExpectedOutput().get("nonBounceableUrlSafe");
        String expectedBounceable = (String) testCase.getExpectedOutput().get("bounceable");
        String expectedNonBounceable = (String) testCase.getExpectedOutput().get("nonBounceable");
        String expectedBounceableUrlSafeTest = (String) testCase.getExpectedOutput().get("bounceableUrlSafeTest");
        String expectedNonBounceableUrlSafeTest = (String) testCase.getExpectedOutput().get("nonBounceableUrlSafeTest");
        String expectedBounceableTest = (String) testCase.getExpectedOutput().get("bounceableTest");
        String expectedNonBounceableTest = (String) testCase.getExpectedOutput().get("nonBounceableTest");

        assertThat(bounceableUrlSafe).isEqualTo(expectedBounceableUrlSafe);
        assertThat(nonBounceableUrlSafe).isEqualTo(expectedNonBounceableUrlSafe);
        assertThat(bounceable).isEqualTo(expectedBounceable);
        assertThat(nonBounceable).isEqualTo(expectedNonBounceable);
        assertThat(bounceableUrlSafeTest).isEqualTo(expectedBounceableUrlSafeTest);
        assertThat(nonBounceableUrlSafeTest).isEqualTo(expectedNonBounceableUrlSafeTest);
        assertThat(bounceableTest).isEqualTo(expectedBounceableTest);
        assertThat(nonBounceableTest).isEqualTo(expectedNonBounceableTest);
    }

    @Test
    public void testAddress2() {

        String testId = "address-2";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        // fetch input parameters and print test case details
        String inputAddress = (String) testCase.getInput().get("inputRawAddress");

        log.info("input parameters:");
        log.info("  address: {}", inputAddress);

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, inputAddress);
        log.info("output: {}", result);

        String nonBounceable = sb(result, "Non-bounceable address (for init only): ", "\n").trim();
        String bounceable = sb(result, "Bounceable address (for later access): ", "\n").trim();
        String nonBounceableUrlSafe = sb(result, "Non-bounceable address, Base64url (for init): ", "\n").trim();
        String bounceableUrlSafe = sb(result, "Bounceable address, Base64url (for later access): ", "\n").trim();

        // fetch the expected result and compare it against the actual one
        String expectedBounceableUrlSafe = (String) testCase.getExpectedOutput().get("bounceableUrlSafe");
        String expectedNonBounceableUrlSafe = (String) testCase.getExpectedOutput().get("nonBounceableUrlSafe");
        String expectedBounceable = (String) testCase.getExpectedOutput().get("bounceable");
        String expectedNonBounceable = (String) testCase.getExpectedOutput().get("nonBounceable");

        assertThat(bounceableUrlSafe).isEqualTo(expectedBounceableUrlSafe);
        assertThat(nonBounceableUrlSafe).isEqualTo(expectedNonBounceableUrlSafe);
        assertThat(bounceable).isEqualTo(expectedBounceable);
        assertThat(nonBounceable).isEqualTo(expectedNonBounceable);
    }

    @Test
    public void testAddress6() {

        String testId = "address-6";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        // fetch input parameters and print test case details
        String inputAddress = (String) testCase.getInput().get("inputRawAddress");

        log.info("input parameters:");
        log.info("  address: {}", inputAddress);

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, inputAddress);
        log.info("output: {}", result);

        String nonBounceable = sb(result, "Non-bounceable address (for init only): ", "\n").trim();
        String bounceable = sb(result, "Bounceable address (for later access): ", "\n").trim();
        String nonBounceableUrlSafe = sb(result, "Non-bounceable address, Base64url (for init): ", "\n").trim();
        String bounceableUrlSafe = sb(result, "Bounceable address, Base64url (for later access): ", "\n").trim();

        // fetch the expected result and compare it against the actual one
        String expectedBounceableUrlSafe = (String) testCase.getExpectedOutput().get("bounceableUrlSafe");
        String expectedNonBounceableUrlSafe = (String) testCase.getExpectedOutput().get("nonBounceableUrlSafe");
        String expectedBounceable = (String) testCase.getExpectedOutput().get("bounceable");
        String expectedNonBounceable = (String) testCase.getExpectedOutput().get("nonBounceable");

        assertThat(bounceableUrlSafe).isEqualTo(expectedBounceableUrlSafe);
        assertThat(nonBounceableUrlSafe).isEqualTo(expectedNonBounceableUrlSafe);
        assertThat(bounceable).isEqualTo(expectedBounceable);
        assertThat(nonBounceable).isEqualTo(expectedNonBounceable);
    }

    @Test
    public void testAddress7() {

        String testId = "address-7";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        // fetch input parameters and print test case details
        String inputAddress = (String) testCase.getInput().get("inputRawAddress");

        log.info("input parameters:");
        log.info("  address: {}", inputAddress);

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, inputAddress);
        log.info("output: {}", result);

        String nonBounceable = sb(result, "Non-bounceable address (for init only): ", "\n").trim();
        String bounceable = sb(result, "Bounceable address (for later access): ", "\n").trim();
        String nonBounceableUrlSafe = sb(result, "Non-bounceable address, Base64url (for init): ", "\n").trim();
        String bounceableUrlSafe = sb(result, "Bounceable address, Base64url (for later access): ", "\n").trim();

        // fetch the expected result and compare it against the actual one
        String expectedBounceableUrlSafe = (String) testCase.getExpectedOutput().get("bounceableUrlSafe");
        String expectedNonBounceableUrlSafe = (String) testCase.getExpectedOutput().get("nonBounceableUrlSafe");
        String expectedBounceable = (String) testCase.getExpectedOutput().get("bounceable");
        String expectedNonBounceable = (String) testCase.getExpectedOutput().get("nonBounceable");

        assertThat(bounceableUrlSafe).isEqualTo(expectedBounceableUrlSafe);
        assertThat(nonBounceableUrlSafe).isEqualTo(expectedNonBounceableUrlSafe);
        assertThat(bounceable).isEqualTo(expectedBounceable);
        assertThat(nonBounceable).isEqualTo(expectedNonBounceable);
    }

    @Test
    public void testAddress8() {

        String testId = "address-8";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        // fetch input parameters and print test case details
        String inputAddress = (String) testCase.getInput().get("bounceableUrlSafe");

        log.info("input parameters:");
        log.info("  address: {}", inputAddress);

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, inputAddress);
        log.info("output: {}", result);

        String rawAddress = sb(result, "Source wallet address = ", "\n").trim();

        String expectedRawAddress = (String) testCase.getExpectedOutput().get("rawAddress");

        assertThat(rawAddress).isEqualTo(expectedRawAddress);
    }

    @Test
    public void testAddress9() {

        String testId = "address-9";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        // fetch input parameters and print test case details
        String inputAddress = (String) testCase.getInput().get("nonBounceable");

        log.info("input parameters:");
        log.info("  address: {}", inputAddress);

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, inputAddress);
        log.info("output: {}", result);

        String rawAddress = sb(result, "Source wallet address = ", "\n").trim();

        String expectedRawAddress = (String) testCase.getExpectedOutput().get("rawAddress");

        assertThat(rawAddress).isEqualTo(expectedRawAddress);
    }

    @Test
    public void testAddress10() {

        String testId = "address-10";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        // fetch input parameters and print test case details
        String inputAddress = (String) testCase.getInput().get("bounceableUrlSafe");

        log.info("input parameters:");
        log.info("  address: {}", inputAddress);

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, inputAddress);
        log.info("output: {}", result);

        assertThat(result).contains("Error");
    }

    @Test
    public void testAddress11() {

        String testId = "address-11";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        // fetch input parameters and print test case details
        String inputAddress = (String) testCase.getInput().get("bounceableUrlSafe");

        log.info("input parameters:");
        log.info("  address: {}", inputAddress);

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, inputAddress);
        log.info("output: {}", result);

        assertThat(result).contains("Error");
    }

    @Test
    public void testAddress12() {

        String testId = "address-12";
        // select particular test case by category name and test id
        TonSdkTestCases.TestCase testCase = tonSdkTestCases.getTestCases().get(testId);

        // fetch test's description and id. It's always good to show test id, since it is unique across all tests.
        String description = testCase.getDescription();

        log.info("testId: {}", testId);
        log.info("description: {}", description);

        // fetch input parameters and print test case details
        String inputAddress = (String) testCase.getInput().get("bounceableUrlSafe");

        log.info("input parameters:");
        log.info("  address: {}", inputAddress);

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, inputAddress);
        log.info("output: {}", result);

        assertThat(result).contains("Error");
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
