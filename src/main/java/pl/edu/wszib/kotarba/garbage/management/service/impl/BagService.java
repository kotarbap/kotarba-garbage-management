package pl.edu.wszib.kotarba.garbage.management.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.wszib.kotarba.garbage.management.database.IBagDAO;
import pl.edu.wszib.kotarba.garbage.management.model.Bag;
import pl.edu.wszib.kotarba.garbage.management.service.IBagService;

import java.util.List;
import java.util.Optional;

@Service
public class BagService implements IBagService {

    @Autowired
    IBagDAO bagDAO;

    public List<Bag> getAllBags() {
        return this.bagDAO.getBags();
    }

    @Override
    public void addBagQuantity(String color, int quantity) {

        Optional<Bag> bagBox = this.bagDAO.getBagByColor(color);

        if(bagBox.isPresent()) {
            bagBox.get().setQuantity(bagBox.get().getQuantity() + quantity);
            this.bagDAO.updateBag(bagBox.get());
        }

    }
}
