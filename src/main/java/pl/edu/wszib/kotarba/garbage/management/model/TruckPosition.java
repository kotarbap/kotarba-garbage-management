package pl.edu.wszib.kotarba.garbage.management.model;

import javax.persistence.*;

@Entity(name = "ttruckposition")
public class TruckPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.EAGER)
    private Bag bag;
    private int quantity;

    public TruckPosition(int id, Bag bag, int quantity) {
        this.id = id;
        this.bag = bag;
        this.quantity = quantity;
    }

    public TruckPosition() {
    }

    public void incrementQuantity() {
        this.quantity++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
