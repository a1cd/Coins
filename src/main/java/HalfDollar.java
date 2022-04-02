/**
 * A half dollar
 * @version 4/31/2022
 * @author 24wilber
 */
public class HalfDollar extends Coin {
    public double getValue() {
        return 0.5;
    }

    @Override
    public String getName() {
        return "Half Dollar";
    }
}
