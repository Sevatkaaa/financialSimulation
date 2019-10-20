import java.util.ArrayList;
import java.util.Random;

public abstract class AbstractPlayer {
    protected static Random random = new Random();
    protected float money;
    protected ArrayList<Float> moneyOverTime;
    protected ArrayList<Integer> uniqueRandomNumbers;
    protected int red, green, blue;
    // starting debt for each player
    protected float debt = 30100;

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
