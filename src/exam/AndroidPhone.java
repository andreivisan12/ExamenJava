package exam;

public class AndroidPhone extends Phone{
    public AndroidPhone(String id, String brand, String model, double price, double stock, String os) {
        super(id, brand, model, price, stock, os);
    }

    public AndroidPhone() {
    }

    @Override
    public double totalValue() {
        return this.price*(this.stock+1);
    }

}
