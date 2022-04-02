/**
 * A dime
 * @version 4/31/2022
 * @author 24wilber
 */
public class Dime extends Coin {
    @Override
    public double getValue() {
        return 0.1;
    }
    @Override
    public String getName() {
        return this.getClass().getName();
    }
}
