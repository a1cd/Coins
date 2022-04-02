/**
 * A penny
 * @version 4/31/2022
 * @author 24wilber
 */
public class Penny extends Coin {
    public double getValue() {
        return 0.01;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
