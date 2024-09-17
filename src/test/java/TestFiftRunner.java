import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.ton.java.fift.FiftRunner;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

@Slf4j
@RunWith(JUnit4.class)
public class TestFiftRunner {

    /**
     * Make sure you have fift and func installed in your system. See <a href="https://github.com/ton-blockchain/packages">packages</a> for instructions.
     */
    @Test
    public void testFiftRunner() throws URISyntaxException {

        URL resource = TestFiftRunner.class.getResource("/fift-tests/test.fif");
        File fiftFile = Paths.get(resource.toURI()).toFile();
        String absolutePath = fiftFile.getAbsolutePath();

        FiftRunner fiftRunner = FiftRunner.builder().build();

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath);
        log.info("output: {}", result);
    }

    @Test
    public void testV3R2CodeCell() throws URISyntaxException {

        URL resource = TestFiftRunner.class.getResource("/fift-tests/wallets/wallet-v3-code.fif");
        File fiftFile = Paths.get(resource.toURI()).toFile();
        String absolutePath = fiftFile.getAbsolutePath();

        FiftRunner fiftRunner = FiftRunner.builder().build();

        String result = fiftRunner.run(fiftFile.getParent(), absolutePath);
        log.info("output: {}", result);
    }

    @Test
    public void testV3R2CreateWalletContract2() throws URISyntaxException {

        URL resource = TestFiftRunner.class.getResource("/fift-tests/wallets/new-wallet-v3.fif");
        File fiftFile = Paths.get(resource.toURI()).toFile();
        String absolutePath = fiftFile.getAbsolutePath();

        FiftRunner fiftRunner = FiftRunner.builder().build();

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, "0", "42");
        log.info("output: {}", result);
    }

    @Test
    public void testV3R2CreateWalletContract3() throws URISyntaxException {

        URL resource = TestFiftRunner.class.getResource("/fift-tests/wallets/new-wallet-v3.fif");
        File fiftFile = Paths.get(resource.toURI()).toFile();
        String absolutePath = fiftFile.getAbsolutePath();

        FiftRunner fiftRunner = FiftRunner.builder().build();

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, "-1", "42");
        log.info("output: {}", result);
    }

    @Test
    public void testV4R2CodeCell() throws URISyntaxException {

        URL resource = TestFiftRunner.class.getResource("/fift-tests/wallets/print-v4-hex.fif");
        File fiftFile = Paths.get(resource.toURI()).toFile();
        String absolutePath = fiftFile.getAbsolutePath();

        FiftRunner fiftRunner = FiftRunner.builder().build();

        String result = fiftRunner.run(fiftFile.getParent(), absolutePath);
        log.info("output: {}", result);
    }

    @Test
    public void testCells() throws URISyntaxException {

        URL resource = TestFiftRunner.class.getResource("/fift-tests/cell.fif");
        File fiftFile = Paths.get(resource.toURI()).toFile();
        String absolutePath = fiftFile.getAbsolutePath();

        FiftRunner fiftRunner = FiftRunner.builder().build();

        String result = fiftRunner.run(fiftFile.getParent(), absolutePath);
        log.info("output: {}", result);
    }
}
