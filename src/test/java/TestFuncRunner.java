import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.ton.java.func.FuncRunner;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

@Slf4j
@RunWith(JUnit4.class)
public class TestFuncRunner {

    /**
     * Make sure you have fift and func installed in your system. See <a href="https://github.com/ton-blockchain/packages">packages</a> for instructions.
     */
    @Test
    public void testV4R2CodeCell() throws URISyntaxException {

        URL resource = TestFuncRunner.class.getResource("/fift/wallets/wallet-v4-code.fc");
        File funcFile = Paths.get(resource.toURI()).toFile();
        String absolutePath = funcFile.getAbsolutePath();

        FuncRunner funcRunner = FuncRunner.builder().build();

        String result = funcRunner.run(funcFile.getParent(), "-SPA", absolutePath);
        log.info("output: {}", result);
    }

    @Test
    public void testV4R2PluginCodeCell() throws URISyntaxException {

        URL resource = TestFuncRunner.class.getResource("/fift/wallets/simple-subscription-plugin.fc");
        File funcFile = Paths.get(resource.toURI()).toFile();
        String absolutePath = funcFile.getAbsolutePath();

        FuncRunner funcRunner = FuncRunner.builder().build();

        String result = funcRunner.run(funcFile.getParent(), "-SPA", absolutePath);
        log.info("output: {}", result);
    }
}
