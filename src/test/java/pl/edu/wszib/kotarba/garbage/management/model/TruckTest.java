package pl.edu.wszib.kotarba.garbage.management.model;

import org.junit.Assert;
import org.junit.Test;
import pl.edu.wszib.kotarba.garbage.management.TestUtils;

import java.util.HashSet;
import java.util.Set;

public class TruckTest {
    @Test
    public void constructTruckWithTwoPositionsTest() {
        Set<TruckPosition> truckPositionSet = new HashSet<>();
        truckPositionSet.add(TestUtils.generateTruckPosition(10.30, 3));
        truckPositionSet.add(TestUtils.generateTruckPosition(4.20, 4));
        double expectedResult = 47.7;

        Truck truck = new Truck(generateUser(), truckPositionSet);

        Assert.assertEquals(expectedResult, truck.getPrice(), 0.001);
    }

    @Test
    public void constructTruckWithoutPositionsTest() {
        Set<TruckPosition> truckPositionSet = new HashSet<>();
        double expectedResult = 0.0;

        Truck truck = new Truck(generateUser(), truckPositionSet);

        Assert.assertEquals(expectedResult, truck.getPrice(), 0.001);
    }

    private User generateUser() {
        User user = new User();
        user.setId(1);
        user.setLogin("asgga");
        user.setPass("adjgig");
        user.setName("adgd");
        user.setSurname("agsg");

        return user;
    }
}
