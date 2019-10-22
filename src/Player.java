import java.util.ArrayList;
import java.util.Random;

// parent interface for player to define base logic and behavior
public interface Player {

    // method that will be overridden in each implementation
    float payOff(float debt, Random random);

    int getR();

    int getG();

    int getB();

    float getMoney();

    ArrayList<Float> getFunds();

    void updateMoneyEachYear();

    // count each year debt for 12 month and update it
    void updateDebtEachYear();

    float getDebt();

    int playRandom();

    void setMoney(float money);

    ArrayList<Integer> getUniqueRandomNumbers();
}
