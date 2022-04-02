import java.io.File;
import java.io.FileNotFoundException;
import java.text.*;
import java.util.*;

import static java.lang.System.err;
import static java.lang.System.out;

public class CoinSorterMachine {
    private ArrayList<Coin> coins;

    /**
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
        var file = new File("coins1.txt");
        try {
            var scanner = new Scanner(file);
            var penny = new Penny();
            var nickel = new Nickel();
            var dime = new Dime();
            var quarter = new Quarter();
            var halfDollar = new HalfDollar();
            var dollar = new Dollar();
            while (scanner.hasNextInt()) {
                var nextInt = scanner.nextInt();
                switch (nextInt) {
                    case 1 -> coins.add(penny);
                    case 5 -> coins.add(nickel);
                    case 10 -> coins.add(dime);
                    case 25 -> coins.add(quarter);
                    case 50 -> coins.add(halfDollar);
                    case 100 -> coins.add(dollar);
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
        var coinTypes = new ArrayList<Class>();
        var coinValues = new ArrayList<Integer>();
        var coinPlacement = new ArrayList<Integer>();
        var typeCount = 0;
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
        var total = 0.0;
        out.println("Summary of deposit:");
        for (int i = 0; i < coinTypes.size(); i++) {
            var sample = coins.get(coinPlacement.get(i));
            var value = sample.getValue() * coinValues.get(i);
            total+=value;

            // honestly dont know how it works but it works but it does so yea i think its cool then
            double[] filelimits = {0,1,2};
            String[] filepart = {"{1} "+sample.getPluralName(),"\t{1} "+sample.getName(),"{1} "+sample.getPluralName()};
            ChoiceFormat fileform = new ChoiceFormat(filelimits, filepart);
            var currency = NumberFormat.getCurrencyInstance(Locale.US);
            currency.setCurrency(Currency.getInstance(Locale.US));
            Format[] testFormats = {fileform, NumberFormat.getIntegerInstance(), currency};
            MessageFormat pattform = new MessageFormat("{0} {2}");

            pattform.setFormats(testFormats);
            pattform.setFormat(1, NumberFormat.getCurrencyInstance(Locale.US));

            Object[] testArgs = {value, coinValues.get(i), value};

            System.out.println(pattform.format(testArgs));

        }
        out.println("TOTAL DEPOSIT: "+NumberFormat.getCurrencyInstance().format(total));
    }

    /**
     * return the total value of all Coin objects stored in coins as a double
     */
    public double getTotalValue() {
        return 0;
    }

    public static void main(String[] args) {
        var app = new CoinSorterMachine();
        app.depositCoins();
        app.printDepositSummary();
    }
}
