import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.ToNumberPolicy;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.ton.java.smartcontract.FiftRunner;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

@Slf4j
@RunWith(JUnit4.class)
public class FiftScriptsTest {

    Gson gson = new GsonBuilder().setObjectToNumberStrategy(ToNumberPolicy.LONG_OR_DOUBLE).create();

    @Test
    public void testWalletV3R2Creation() throws URISyntaxException {

        URL resource = TestFiftRunner.class.getResource("/fift/address.fif");
        File fiftFile = Paths.get(resource.toURI()).toFile();
        String absolutePath = fiftFile.getAbsolutePath();

        FiftRunner fiftRunner = FiftRunner.builder().build();

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, "EQDkZIvi6fkgNVxLOgw5hzquGhxhUvhvJ4B836p-NQ-iLKCv");
        log.info("output: {}", result);

        result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, "Ef--_tW1zCy5ehTig0JrBEAiE67bbnSmqVtkuU8FFqlNUT9n");
        log.info("output: {}", result);

        result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, "-1:befed5b5cc2cb97a14e283426b04402213aedb6e74a6a95b64b94f0516a94d51");
        log.info("output: {}", result);

        result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, "-2:befed5b5cc2cb97a14e283426b04402213aedb6e74a6a95b64b94f0516a94d51");
        log.info("output: {}", result);
    }

}
