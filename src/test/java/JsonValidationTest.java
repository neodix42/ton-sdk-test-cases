import lombok.extern.slf4j.Slf4j;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@RunWith(JUnit4.class)
public class JsonValidationTest {

    public static final String SCHEMA_FILE_NAME = "scheme.json";
    public static final String[] TON_TEST_CASE_FILES = new String[]{
            "smartcontracts.json",
            "address.json",
            "bitstring.json",
            "cryptography.json",
            "cell-deserialization.json",
            "cell-serialization.json"
    };

    @Test
    public void testJsonValidation() throws IOException {
        System.out.println("Validating JSON data file TonSdkTestCases.json against scheme.json");

        validateJsonDataFileAgainstScheme();
    }

    private static void validateJsonDataFileAgainstScheme() throws IOException {
        InputStream schemaStream = Files.newInputStream(Paths.get(SCHEMA_FILE_NAME));
        JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
        org.everit.json.schema.Schema schema = SchemaLoader.load(rawSchema);
        for (String testCaseFile : TON_TEST_CASE_FILES) {
            InputStream dataStream = Files.newInputStream(Paths.get(testCaseFile));
            JSONObject jsonData = new JSONObject(new JSONTokener(dataStream));

            schema.validate(jsonData);
            log.info("JSON data file {} is valid.", testCaseFile);

        }

    }
}
