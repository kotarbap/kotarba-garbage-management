package pl.edu.wszib.kotarba.garbage.management.service.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import pl.edu.wszib.kotarba.garbage.management.configuration.TestConfiguration;
import pl.edu.wszib.kotarba.garbage.management.database.IBagDAO;
import pl.edu.wszib.kotarba.garbage.management.database.ITruckDAO;
import pl.edu.wszib.kotarba.garbage.management.database.IUserDAO;
import pl.edu.wszib.kotarba.garbage.management.model.Bag;
import pl.edu.wszib.kotarba.garbage.management.service.IBagService;
import pl.edu.wszib.kotarba.garbage.management.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = TestConfiguration.class)
public class BagServiceTest {

    @Autowired
    IBagService bagService;

    @Resource
    SessionObject sessionObject;

    @MockBean
    IUserDAO userDAO;

    @MockBean
    IBagDAO bagDAO;

    @MockBean
    ITruckDAO truckDAO;

    @Test
    public void correctAddBagQuantityTest() {
        Mockito.when(this.bagDAO.getBagByColor(Mockito.anyString())).thenReturn(generateBag());
        String color = "aggadggh";
        int quantity = 2;

        this.bagService.addBagQuantity(color, quantity);

        Assert.assertNotEquals(this.bagDAO.getBagByColor("aggadggh"), generateBag());
    }

    public Optional<Bag> generateBag() {
        Bag bag = new Bag();
        bag.setId(1);
        bag.setColor("aggadggh");
        bag.setType("Adhdhhadh");
        bag.setPrice(4.20);
        bag.setQuantity(10);

        return Optional.of(bag);
    }
}
