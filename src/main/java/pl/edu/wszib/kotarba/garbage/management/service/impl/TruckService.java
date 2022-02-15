package pl.edu.wszib.kotarba.garbage.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.kotarba.garbage.management.database.IBagDAO;
import pl.edu.wszib.kotarba.garbage.management.database.ITruckDAO;
import pl.edu.wszib.kotarba.garbage.management.model.Bag;
import pl.edu.wszib.kotarba.garbage.management.model.Truck;
import pl.edu.wszib.kotarba.garbage.management.model.TruckPosition;
import pl.edu.wszib.kotarba.garbage.management.service.ITruckService;
import pl.edu.wszib.kotarba.garbage.management.session.SessionObject;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class TruckService implements ITruckService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    ITruckDAO truckDAO;

    @Autowired
    IBagDAO bagDAO;

    @Override
    public void confirmTruck() {
        Truck truck = new Truck(this.sessionObject.getUser(), new HashSet<>(this.sessionObject.getDumpster().getTruckPositions()));
        this.truckDAO.addTruck(truck);
        for (TruckPosition truckPosition : truck.getTruckPositions()) {
            Optional<Bag> bagBox = this.bagDAO.getBagById(truckPosition.getBag().getId());
            if(bagBox.isPresent()) {
                bagBox.get().setQuantity(bagBox.get().getQuantity() - truckPosition.getQuantity());
                this.bagDAO.updateBag(bagBox.get());
            }
        }
        this.sessionObject.getDumpster().clearTruckPositions();
    }

    @Override
    public List<Truck> getTrucksForCurrentUser() {
        return this.truckDAO.getTrucksByUserId(this.sessionObject.getUser().getId());
    }

}
