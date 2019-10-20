import java.util.Random;

public class WPPlayer extends AbstractPlayer implements manageDebt {
    public WPPlayer(float startFunds) {
        super(startFunds);

        //overall red tint to WELL_PAID
        red += 100;
    }

    // count how much debt well-paid player pays per month
    @Override
    public float payOff(float debt, Random random) {
        return (float) (debt * 0.03 + random.nextFloat() * 20);
    }

    @Override
    public float totalDebt() {
        return debt;
    }
}
