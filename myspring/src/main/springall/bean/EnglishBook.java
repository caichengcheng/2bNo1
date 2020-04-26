package springall.bean;

/**
 * @author caichengcheng
 * date:2020-01-10
 */
public class EnglishBook  extends Book{
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    protected void desc() {
        System.out.println(this + ":english book");
    }
}
