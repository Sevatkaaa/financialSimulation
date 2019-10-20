import java.util.*;
import java.util.stream.Collectors;

public final class communityLSim {

    private static Random random = new Random();

    ArrayList<AbstractPlayer> players;
    int numPeeps;
    game game;
    Map<String, String> map = new HashMap<>();
    List<AbstractPlayer> sixtyPercentPP = new ArrayList<>();
    List<AbstractPlayer> fortyPercentWP = new ArrayList<>();
    List<Integer> sixtyPercentPPIndexes = new ArrayList<>();
    List<Integer> fortyPercentWPIndexes = new ArrayList<>();
    //you will need to add more instance variables

    public communityLSim(int numP) {
        numPeeps = numP;
        //create the players
        players = new ArrayList<AbstractPlayer>();
        game = new game();
        //generate a community of 30
        for (int i = 0; i < numPeeps; i++) {
            if (i < numPeeps / 2.0)
                players.add(new PPPlayer((float) (99 + Math.random())));
            else
                players.add(new WPPlayer((float) (100.1 + Math.random())));
        }

        buildWiningMap();

    }

    private void buildWiningMap() {
        map.put("0", "None number match so  (to the winning lottery number) the winnings is -1");
        map.put("1", "matches 2 numbers (to the winning lottery number) the winnings is one dollar, $1");
        map.put("2", "matches 3 numbers (to the winning lottery number) the winnings is $10.86");
        map.put("3", "matches 4 numbers (to the winning lottery number) the winnings is $197.53");
        map.put("4", "matches 5 numbers (to the winning lottery number) the winnings is $212534.83");

    }

    public int getSize() {
        return numPeeps;
    }

    public AbstractPlayer getPlayer(int i) {
        return players.get(i);
    }

    public void addSalary() {
        for (AbstractPlayer p : players) {
            if (p instanceof WPPlayer) {
                p.setMoney(p.getMoney() + 0.1f);
            } else {
                p.setMoney(p.getMoney() + 0.03f);
            }
        }

    }

    private void reDoWhoPlays() {
        sixtyPercentPP = new ArrayList<>();
        fortyPercentWP = new ArrayList<>();
        sixtyPercentPPIndexes = new ArrayList<>();
        fortyPercentWPIndexes = new ArrayList<>();
        int sixyPerPP = ((players.size() / 2) * 60) / 100;
        int fortyPerWP = ((players.size() / 2) * 40) / 100;

        // generate random unique indexes of 60% poor-paid players
        // add it lo list of poorly-paid player for this year

        while (sixtyPercentPPIndexes.size() != sixyPerPP) {
            int index = randomUniqIndx(0, players.size() / 2 - 1);
            if (!sixtyPercentPPIndexes.contains(index)) {
                sixtyPercentPPIndexes.add(index);
                sixtyPercentPP.add(players.get(index));
            }
        }

        // generate random unique indexes of 40% well-paid players
        // add it lo list of well-paid player for this year
        while (fortyPercentWPIndexes.size() != fortyPerWP) {
            int index = randomUniqIndx(players.size() / 2 - 1, players.size() - 1);
            if (!fortyPercentWPIndexes.contains(index)) {
                fortyPercentWPIndexes.add(index);
                fortyPercentWP.add(players.get(index));
            }
        }
    }

    private int randomUniqIndx(int startRange, int endRange) {
        return random.nextInt(endRange - startRange + 1) + startRange;
    }

    public void simulateYears(int numYears) {
        /*now simulate lottery play for some years */
        for (int year = 0; year < numYears; year++) {
            reDoWhoPlays();
            addSalary();
            // add code so that each member of the community who plays, plays
            //right now just everyone updates their list of funds each year
            for (AbstractPlayer p : players) {
                p.updateMoneyEachYear();
                // for each player that plays this year count how much he wins
                if (sixtyPercentPP.contains(p) || fortyPercentWP.contains(p)) {
                    double win = game.countWin(p);
                    // and add this money
                    p.setMoney(p.getMoney() + (float) win);
                    //if someone loses money, return it to one of players with 70% to well-paid and 30% to poorly paid
                    if (win == -1) {
                        double rand = Math.random();
                        if (rand > 0.3) {
                            // return to well paid with 70%
                            // get all well-paid players

                            List<AbstractPlayer> wellPaidPlayers = players.stream()
                                    .filter(pl -> pl instanceof WPPlayer)
                                    .collect(Collectors.toList());
                            //generate random index with a well-paid player
                            int randIndex = (int) (Math.random() * wellPaidPlayers.size());
                            // add 1 dollar
                            wellPaidPlayers.get(randIndex).setMoney(wellPaidPlayers.get(randIndex).getMoney() + 1);
                        } else {
                            // return to poorly paid with 30%
                            // get all poorly-paid players

                            List<AbstractPlayer> poorlyPaidPlayers = players.stream()
                                    .filter(pl -> pl instanceof PPPlayer)
                                    .collect(Collectors.toList());
                            //generate random index with a poorly-paid player
                            int randIndex = (int) (Math.random() * poorlyPaidPlayers.size());
                            // add 1 dollar
                            poorlyPaidPlayers.get(randIndex).setMoney(poorlyPaidPlayers.get(randIndex).getMoney() + 1);
                        }
                    }
                }
            }

            //create new lottery for next game
            game.winningLotNumber();
            System.out.println("After year " + year + " :");

            Optional<AbstractPlayer> mostMoney = players.stream().collect(Collectors.maxBy(Comparator.comparing(AbstractPlayer::getMoney)));
            Optional<AbstractPlayer> leastMoney = players.stream().collect(Collectors.minBy(Comparator.comparing(AbstractPlayer::getMoney)));

            System.out.println("The person with the most money has: " + mostMoney.get().getMoney());
            System.out.println("The person with the least money has: " + leastMoney.get().getMoney());

        } //years
    }

    public float maxDebtPay() {
        return 0;
    }

    public float minDebtPay() {
        return 0;
    }

    public float maxPocket() {
        return players.stream().max(Comparator.comparing(AbstractPlayer::getMoney)).get().getMoney();
    }

    public float minPocket() {
        return players.stream().min(Comparator.comparing(AbstractPlayer::getMoney)).get().getMoney();
    }
}
