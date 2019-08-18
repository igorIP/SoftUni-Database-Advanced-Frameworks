package cardealer.domain.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sales")
public class Sale extends BaseEntity implements Serializable {

    private Double discount;

    @OneToOne
    private Car car;

    @ManyToOne
    private Customer customer;


    public Sale() {
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
