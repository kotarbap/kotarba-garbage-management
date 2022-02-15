package pl.edu.wszib.kotarba.garbage.management;

import pl.edu.wszib.kotarba.garbage.management.model.Bag;
import pl.edu.wszib.kotarba.garbage.management.model.TruckPosition;

public class TestUtils {
    public static TruckPosition generateTruckPosition(double price, int positionQuantity) {
        Bag bag = new Bag();
        bag.setId(1);
        bag.setColor("aeggdhhddh");
        bag.setType("agdhhd");
        bag.setPrice(price);
        bag.setQuantity(2);

        TruckPosition truckPosition = new TruckPosition();
        truckPosition.setBag(bag);
        truckPosition.setQuantity(positionQuantity);

        return truckPosition;
    }
}
