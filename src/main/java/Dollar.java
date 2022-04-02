/**
 * A dollar
 * @version 4/31/2022
 * @author 24wilber
 */
public class Dollar extends Coin {
    public double getValue() {
        return 1.0;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
