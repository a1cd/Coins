public class Nickel extends Coin {
    public double getValue() {
        return 0.05;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
