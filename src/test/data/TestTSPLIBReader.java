package data;

import configuration.Configuration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestTSPLIBReader {
    @Test
    public void test() {
        InstanceReader instanceReader = new InstanceReader(Configuration.instance.dataFilePath);
        instanceReader.open();
        TSPLIBReader tspLibReader = new TSPLIBReader(instanceReader);
        Assertions.assertEquals(280, tspLibReader.getCities().size());
    }
}