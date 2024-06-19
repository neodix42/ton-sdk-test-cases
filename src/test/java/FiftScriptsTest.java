import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.ton.java.address.Address;
import org.ton.java.smartcontract.FiftRunner;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;

@Slf4j
@RunWith(JUnit4.class)
public class FiftScriptsTest {
    @Test
    public void testAddressWithFift() throws URISyntaxException {

        URL resource = TestFiftRunner.class.getResource("/fift/address.fif");
        File fiftFile = Paths.get(resource.toURI()).toFile();
        String absolutePath = fiftFile.getAbsolutePath();

        FiftRunner fiftRunner = FiftRunner.builder().build();

        String result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, "EQDkZIvi6fkgNVxLOgw5hzquGhxhUvhvJ4B836p-NQ-iLKCv");
        log.info("output: {}", result);

        result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, "Ef--_tW1zCy5ehTig0JrBEAiE67bbnSmqVtkuU8FFqlNUT9n");
        log.info("output: {}", result);

        result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, "-1:0000d5b5cc2cb97a14e283426b04402213aedb6e74a6a95b64b94f0516a90000");
        log.info("output: {}", result);

        result = fiftRunner.run(fiftFile.getParent(), "-s", absolutePath, "EQCnuv-ZuR0QsIh5vwxUBuzzocSowbCa7ctdwl6QizBKzGVM");
        log.info("output: {}", result);
    }

    @Test
    public void testAddressWithTon4j() {
        Address address = Address.of("0:a7baff99b91d10b08879bf0c5406ecf3a1c4a8c1b09aedcb5dc25e908b304acc");
        log.info("bounceableUrlSafe: {}", address.toBounceable());
        log.info("nonBounceableUrlSafe: {}", address.toNonBounceable());
        log.info("bounceable: {}", address.toString(true, false, true));
        log.info("nonBounceable: {}", address.toString(true, false, false));

        log.info("bounceable: {}", address.toString(true, false, true, true));
        log.info("nonBounceableTest: {}", address.toString(true, false, false, true));
        log.info("bounceableUrlTest: {}", address.toString(true, true, true, true));
        log.info("nonBounceableUrlTest: {}", address.toString(true, true, false, true));
    }

    @Test(expected = Error.class)
    public void testAddressWithTon4j_2() {
        Address address = Address.of("EQDkZIvi6fkgNVxLOgw5hzquGhxhUvhvJ2B836p-NQ-iLKCv");
        log.info("bounceableUrlSafe: {}", address.toBounceable());
        log.info("nonBounceableUrlSafe: {}", address.toNonBounceable());
        log.info("bounceable: {}", address.toString(true, false, true));
        log.info("nonBounceable: {}", address.toString(true, false, false));

        log.info("bounceable: {}", address.toString(true, false, true, true));
        log.info("nonBounceableTest: {}", address.toString(true, false, false, true));
        log.info("bounceableUrlTest: {}", address.toString(true, true, true, true));
        log.info("nonBounceableUrlTest: {}", address.toString(true, true, false, true));
    }
}
