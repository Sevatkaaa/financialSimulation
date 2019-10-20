import java.util.ArrayList;

public class PPPlayer extends AbstractPlayer implements manageDebt {
    public PPPlayer(float startFunds) {
        money = startFunds-1;
        moneyOverTime = new ArrayList<>();
        uniqueRandomNumbers = new ArrayList<>();
        moneyOverTime.add(startFunds);
        red = random.nextInt(100);
        green = random.nextInt(100);
        blue = random.nextInt(100);

        //overall blue tint to POORLY_PAID
        blue += 100;

        //generate 5 random unique numbers for player lottery ticket
        while (uniqueRandomNumbers.size() != 5) {
            int rand = playRandom();
            if (!uniqueRandomNumbers.contains(rand)) {
                uniqueRandomNumbers.add(rand);
            }
        }
    }

    @Override
    public float totalDebt() {
        return 0;
    }
}
