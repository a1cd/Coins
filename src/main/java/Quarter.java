/**
 * A quarter
 * @version 4/31/2022
 * @author 24wilber
 */
public class Quarter extends Coin {
    public double getValue() {
        return 0.25;
    }

    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
