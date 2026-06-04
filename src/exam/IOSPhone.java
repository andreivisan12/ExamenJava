package exam;

public class IOSPhone extends Phone{
    public IOSPhone(String id, String brand, String model, double price, double stock, String os) {
        super(id, brand, model, price, stock, os);
    }

    public IOSPhone() {
    }
    @Override
    public double totalValue()
    {
        return this.price*(this.stock+1);
    }
}
