import lombok.extern.slf4j.Slf4j;
import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.FileInputStream;
import java.io.InputStream;

@Slf4j
@RunWith(JUnit4.class)
public class JsonValidationTest {

    public static final String SCHEMA_FILE_NAME = "scheme.json";
    public static final String TON_TEST_CASES_FILE_NAME = "TonApiTestCases.json";

    @Test
    public void testJsonValidation() {
        System.out.println("Validating JSON data file TonApiTestCases.json against scheme.json");

        validateJsonDataFileAgainstScheme();
    }

    private static void validateJsonDataFileAgainstScheme() {
        try (InputStream schemaStream = new FileInputStream(SCHEMA_FILE_NAME)) {
            JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
            org.everit.json.schema.Schema schema = SchemaLoader.load(rawSchema);
            try (InputStream dataStream = new FileInputStream(TON_TEST_CASES_FILE_NAME)) {
                JSONObject jsonData = new JSONObject(new JSONTokener(dataStream));

                schema.validate(jsonData);
                log.info("JSON data is valid.");
            } catch (ValidationException e) {
                log.info("JSON data is invalid:");
                e.getAllMessages().forEach(System.out::println);
            }
        } catch (Exception e) {
            log.info("JSON data is invalid");
        }
    }
}
