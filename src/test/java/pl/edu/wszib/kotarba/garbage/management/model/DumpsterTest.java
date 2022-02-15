package pl.edu.wszib.kotarba.garbage.management.model;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.wszib.kotarba.garbage.management.TestUtils;

public class DumpsterTest {

    @Test
    public void threePositionsGetSumTest() {
        Dumpster dumpster = new Dumpster();
        dumpster.getTruckPositions().add(TestUtils.generateTruckPosition(15.40, 4));
        dumpster.getTruckPositions().add(TestUtils.generateTruckPosition(17.20, 5));
        dumpster.getTruckPositions().add(TestUtils.generateTruckPosition(5.66, 3));
        double expectedResult = 164.58;

        double result = dumpster.getSum();

        Assert.assertEquals(expectedResult, result, 0.001);
    }

    @Test
    public void emptyDumpsterGetSumTest() {
        Dumpster dumpster = new Dumpster();
        double expectedResult = 0.0;

        double result = dumpster.getSum();

        Assert.assertEquals(expectedResult, result, 0.001);
    }

}
