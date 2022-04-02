public abstract class Coin implements Comparable<Coin> {
    public abstract double getValue();

    @Override
    public int compareTo(Coin o) {
        return Double.compare(getValue(), o.getValue());
    }

    public abstract String getName();
    public String getPluralName() {
        return (getName().equals("penny"))? "pennies": getName()+"s";
    }
}
