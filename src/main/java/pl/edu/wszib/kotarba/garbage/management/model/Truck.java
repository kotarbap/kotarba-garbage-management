package pl.edu.wszib.kotarba.garbage.management.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "ttruck")
public class Truck {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
    private double price;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<TruckPosition> truckPositions = new HashSet<>();
    private LocalDateTime date;

    public enum Status {
        TAKEN,
        DUMPED,
        RECYCLED
    }

    public Truck(int id, User user, double price, Status status, Set<TruckPosition> truckPositions, LocalDateTime date) {
        this.id = id;
        this.user = user;
        this.price = price;
        this.status = status;
        this.truckPositions = truckPositions;
        this.date = date;
    }

    public Truck() {
    }

    public Truck(User user, Set<TruckPosition> truckPositions) {
        this.user = user;
        this.status = Status.TAKEN;
        this.truckPositions = truckPositions;
        date = LocalDateTime.now();
        this.price = 0;
        for(TruckPosition truckPosition : truckPositions) {
            this.price += truckPosition.getBag().getPrice() * truckPosition.getQuantity();
        }
        this.price = Math.round(this.price*100)/100.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Set<TruckPosition> getTruckPositions() {
        return truckPositions;
    }

    public void setTruckPositions(Set<TruckPosition> truckPositions) {
        this.truckPositions = truckPositions;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
