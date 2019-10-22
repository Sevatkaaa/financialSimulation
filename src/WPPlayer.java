import java.util.ArrayList;
import java.util.Random;

public class WPPlayer implements manageDebt, Player {
    private static Random random = new Random();
    private static Random randomDebt = new Random();
    private float money;
    private ArrayList<Float> moneyOverTime;
    private ArrayList<Integer> uniqueRandomNumbers;
    private int red, green, blue;
    // starting debt for each player
    private float debt;
    private float paidDebt;

    public WPPlayer(float startFunds) {
        money = startFunds-1;
        moneyOverTime = new ArrayList<>();
        uniqueRandomNumbers = new ArrayList<>();
        moneyOverTime.add(startFunds);
        red = random.nextInt(100);
        green = random.nextInt(100);
        blue = random.nextInt(100);

        // starting debt
        debt = 30100;

        // starting paid debt (equals 0)
        paidDebt = 0;

        //generate 5 random unique numbers for player lottery ticket
        while (uniqueRandomNumbers.size() != 5) {
            int rand = playRandom();
            if (!uniqueRandomNumbers.contains(rand)) {
                uniqueRandomNumbers.add(rand);
            }
        }

        //overall red tint to WELL_PAID
        red += 100;
    }

    // count how much debt well-paid player pays per month
    @Override
    public float payOff(float debt, Random random) {
        return (float) (debt * 0.03 + random.nextFloat() * 20);
    }

    @Override
    public int getR() {
        return red;
    }

    @Override
    public int getG() {
        return green;
    }

    @Override
    public int getB() {
        return blue;
    }

    @Override
    public float getMoney() {
        return money;
    }

    @Override
    public ArrayList<Float> getFunds() {
        return moneyOverTime;
    }

    @Override
    public void updateMoneyEachYear() {
        moneyOverTime.add(money);
    }

    // count each year debt for 12 month and update it
    @Override
    public void updateDebtEachYear() {
        for (int i = 0; i < 12; i++) {
            // generate how much debt need to be paid this month, add it to paid debt and remove from total debt
            float paid = payOff(debt, randomDebt);
            if(debt > paid) {
                debt -= paid;
                paidDebt += paid;
            } else {
                break;
            }
        }
        debt *= 1.2;
    }

    @Override
    public float getDebt() {
        debt = paidDebt;
        return debt;
    }

    @Override
    public int playRandom() {
        return random.nextInt(42) + 1;
    }

    @Override
    public void setMoney(float money) {
        this.money = money;
    }

    @Override
    public ArrayList<Integer> getUniqueRandomNumbers() {
        return uniqueRandomNumbers;
    }

    @Override
    public float totalDebt() {
        return debt;
    }
}
