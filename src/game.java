import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class game {

    private List<Integer> uniqueRandomNumbers;
    private static Random random = new Random();

    game(){
        uniqueRandomNumbers = winningLotNumber();
    }
    public List<Integer> winningLotNumber(){
        //generate new 5 random numbers in range from 1 to 42

        List<Integer> winningNumbers = new ArrayList<>();
        while (winningNumbers.size() != 5) {
            int rand = random.nextInt(42) + 1;
            if (!winningNumbers.contains(rand)) {
                winningNumbers.add(rand);
            }
        }

        // assign it to uniqueRandomNumbers

        uniqueRandomNumbers = winningNumbers;
        return winningNumbers;
    }

    public int countMatchesNumber(AbstractPlayer player) {
        int count = 0;
        for (int i : player.getUniqueRandomNumbers()) {
            if (uniqueRandomNumbers.contains(i)) {
                count++;
            }
        }
        return count;
    }

    //count how much money wins the player with matches

    public double countWin(AbstractPlayer player) {
        int countMatchesNumber = countMatchesNumber(player);
        switch (countMatchesNumber) {
            case 0:
            case 1:
                return -1;
            case 2:
                return 0;
            case 3:
                return 10.86;
            case 4:
                return 197.53;
            case 5:
                return 212534.83;
        }
        return 0;
    }
}
