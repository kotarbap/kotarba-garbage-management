package pl.edu.wszib.kotarba.garbage.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.kotarba.garbage.management.database.IBagDAO;
import pl.edu.wszib.kotarba.garbage.management.model.Bag;
import pl.edu.wszib.kotarba.garbage.management.model.TruckPosition;
import pl.edu.wszib.kotarba.garbage.management.service.IDumpsterService;
import pl.edu.wszib.kotarba.garbage.management.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class DumpsterService implements IDumpsterService {

    @Autowired
    IBagDAO bagDAO;

    @Resource
    SessionObject sessionObject;

    public void addBagToDumpster(int bagId) {
        Optional<Bag> bagBox = this.bagDAO.getBagById(bagId);

        if(bagBox.isEmpty()) {
            return;
        }

        Bag bag = bagBox.get();
        if(bag.getQuantity() <= 0) {
            return;
        }

        for(TruckPosition truckPosition : this.sessionObject
                .getDumpster().getTruckPositions()) {
            if(truckPosition.getBag().getId() == bagId) {
                truckPosition.incrementQuantity();
                return;
            }
        }

        TruckPosition truckPosition = new TruckPosition(0, bag, 1);
        this.sessionObject.getDumpster().getTruckPositions().add(truckPosition);
    }
}
