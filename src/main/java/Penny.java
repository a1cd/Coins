public class Penny extends Coin {
    public double getValue() {
        return 0.01;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
