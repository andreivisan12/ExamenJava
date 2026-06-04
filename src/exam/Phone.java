package exam;

import java.util.Comparator;
import java.util.Objects;

public abstract class Phone implements Valuable, Comparable<Phone> {
    protected String id;
    protected String brand;
    protected String model;
    protected String os;
    protected double price;
    protected double stock;

    public Phone(String id, String brand, String model, double price, double stock, String os) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.stock = stock;
        this.os = os;
    }

    public Phone() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return Double.compare(getPrice(), phone.getPrice()) == 0 && Double.compare(getStock(), phone.getStock()) == 0 && Objects.equals(getId(), phone.getId()) && Objects.equals(getBrand(), phone.getBrand()) && Objects.equals(getModel(), phone.getModel()) && Objects.equals(getOs(), phone.getOs());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBrand(), getModel(), getOs(), getPrice(), getStock());
    }

    @Override
    public String toString() {
        return "Phone{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", os='" + os + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }
    @Override
    public double totalValue()
    {
        return this.price*(this.stock+1);
    }
    @Override
    public int compareTo(Phone p)
    {
        return Double.compare(p.getPrice(),this.getPrice());
    }
}
