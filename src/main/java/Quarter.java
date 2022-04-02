public class Quarter extends Coin {
    public double getValue() {
        return 0.25;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
