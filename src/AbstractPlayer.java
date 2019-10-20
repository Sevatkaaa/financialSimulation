import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractPlayer {
    protected static Random random = new Random();
    protected static Random randomDebt = new Random();
    protected float money;
    protected ArrayList<Float> moneyOverTime;
    protected ArrayList<Integer> uniqueRandomNumbers;
    protected int red, green, blue;
    // starting debt for each player
    protected float debt;
    protected float paidDebt;

    AbstractPlayer(float startFunds) {
        money = startFunds-1;
        moneyOverTime = new ArrayList<>();
        uniqueRandomNumbers = new ArrayList<>();
        moneyOverTime.add(startFunds);
        red = random.nextInt(100);
        green = random.nextInt(100);
        blue = random.nextInt(100);
        debt = 30100;
        paidDebt = 0;

        //generate 5 random unique numbers for player lottery ticket
        while (uniqueRandomNumbers.size() != 5) {
            int rand = playRandom();
            if (!uniqueRandomNumbers.contains(rand)) {
                uniqueRandomNumbers.add(rand);
            }
        }
    }

    public abstract float payOff(float debt, Random random);

    public int getR() {
        return red;
    }

    public int getG() {
        return green;
    }

    public int getB() {
        return blue;
    }

    public float getMoney() {
        return money;
    }

    public ArrayList<Float> getFunds() {
        return moneyOverTime;
    }

    public void updateMoneyEachYear() {
        moneyOverTime.add(money);
    }

    public void updateDebtEachYear() {
        for (int i = 0; i < 12; i++) {
            float paid = payOff(debt, randomDebt);
            if((debt - paid) > 0) {
                debt -= paid;
                paidDebt += paid;
            }
            else
                break;
        }
        debt *= 1.2;
    }

    public float getDebt() {
        debt = paidDebt;
        return debt;
    }

    public int playRandom() {
        return random.nextInt(42) + 1;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public ArrayList<Integer> getUniqueRandomNumbers() {
        return uniqueRandomNumbers;
    }
}
