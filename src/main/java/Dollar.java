public class Dollar extends Coin {
    public double getValue() {
        return 1.0;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
