import java.io.File;
import java.io.FileNotFoundException;
import java.text.*;
import java.util.*;

import static java.lang.System.err;
import static java.lang.System.out;

/**
 * A coin machine for sorting coins
 * <p>extras:</p>
 * <ui>
 *     <li>{@code finally} keyword</li>
 *     <li>subclasses</li>
 *     <li>lambdas</li>
 *     <li>iso currency and number/formatting standardization</li>
 * </ui>
 * @version 4/31/2022
 * @author 24wilber
 */
public class CoinSorterMachine {
    private final ArrayList<Coin> coins;

    /**
     * the constructor that
     * initializes coins ArrayList
     */
    public CoinSorterMachine() {
        coins = new ArrayList<>();
    }

    /**
     * use one or two Scanner objects, prompting user for the appropriate file
     * name and importing the data from filename – MUST handle diabolic values!
     */
    public void depositCoins() {
        var input = new Scanner(System.in);
        out.print("Enter the name of the data file for coin deposit: ");
        var file = new File(input.nextLine());
        out.println("Depositing coins...");
        try (Scanner scanner = new Scanner(file)) {
            var penny = new Penny();
            var nickel = new Nickel();
            var dime = new Dime();
            var quarter = new Quarter();
            var halfDollar = new HalfDollar();
            var dollar = new Dollar();
            while (scanner.hasNextLine()) {
                String nextInt = scanner.nextLine();
                switch (nextInt) {
                    case "1" -> coins.add(penny);
                    case "5" -> coins.add(nickel);
                    case "10" -> coins.add(dime);
                    case "25" -> coins.add(quarter);
                    case "50" -> coins.add(halfDollar);
                    case "100" -> coins.add(dollar);
                    default -> out.println("Coin value " + nextInt + " not recognized");
                }
            }
            Collections.sort(this.coins);
        } catch (FileNotFoundException e) {
            err.println("bro where is the file?!? I need " + file.getName() + " to be in the folder \"" + file.getAbsolutePath() + "\"");//
        }
    }

    /**
     * Prints deposit summary using a DecimalFormat object (see output section)
     */
    public void printDepositSummary() {
        getTotalValue();

        out.println("Summary of deposit:");
        entries.forEach(depositEntry -> out.println("\t"+depositEntry));
        out.println("TOTAL DEPOSIT: "+NumberFormat.getCurrencyInstance().format(total));
    }
    private static class DepositEntry {
        Integer count;
        String name;
        String pluralName;
        Double amount;

        public DepositEntry(Integer count, String name, String pluralName, Double amount) {
            this.count = count;
            this.name = name;
            this.pluralName = pluralName;
            this.amount = amount;
        }

        @Override
        public String toString() {
            double[] filelimits = {0,1,2};
            String[] filepart = {"{1} "+pluralName,"\t{1} "+name,"{1} "+pluralName};
            ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
            var currency = NumberFormat.getCurrencyInstance(Locale.US);
            currency.setCurrency(Currency.getInstance(Locale.US));
            Format[] testFormats = {fileform, NumberFormat.getIntegerInstance(), currency};
            MessageFormat pattform = new MessageFormat("{0} {2}");

            pattform.setFormats(testFormats);
            pattform.setFormat(1, NumberFormat.getCurrencyInstance(Locale.US));

            Object[] testArgs = {amount, count, amount};

            return pattform.format(testArgs);
        }
    }
    private Double total = 0.0;
    private ArrayList<DepositEntry> entries = new ArrayList<>();
    private void processDepositSummary() {
        //noinspection rawtypes
        var coinTypes = new ArrayList<Class>();
        var coinValues = new ArrayList<Integer>();
        var coinPlacement = new ArrayList<Integer>();
        var typeCount = 0;
        //noinspection rawtypes
        Class lastCoin = null;
        for (int i = 0; i < coins.size(); i++) {
            Coin coin = coins.get(i);
            if (coin.getClass().equals(lastCoin))
                coinValues.set(typeCount-1, coinValues.get(typeCount -1) + 1);
            else {
                coinTypes.add(coin.getClass());
                coinValues.add(1);
                typeCount++;
                coinPlacement.add(i);
                lastCoin = coin.getClass();
            }
        }
        total = 0.0;
        entries = new ArrayList<>();

        for (int i = 0; i < coinTypes.size(); i++) {
            var sample = coins.get(coinPlacement.get(i));
            var value = sample.getValue() * coinValues.get(i);
            total+=value;

            var entry = new DepositEntry(coinValues.get(i), sample.getName(), sample.getPluralName(), value);
            entries.add(entry);
        }
    }

    /**
     * return the total value of all Coin objects stored in coins as a double
     * @return the total value
     */
    @SuppressWarnings("UnusedReturnValue")
    public double getTotalValue() {
        processDepositSummary();
        return total;
    }

    /**
     * main method
     * @param args arrrgs
     */
    public static void main(String[] args) {
        var app = new CoinSorterMachine();
        app.depositCoins();
        app.printDepositSummary();
    }
}
