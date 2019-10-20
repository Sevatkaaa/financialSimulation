import java.util.Random;

public class PPPlayer extends AbstractPlayer implements manageDebt {
    public PPPlayer(float startFunds) {
        super(startFunds);

        //overall blue tint to POORLY_PAID
        blue += 100;
    }

    @Override
    public float payOff(float debt, Random random) {
        return (float) (debt * 0.03 + random.nextFloat());
    }

    @Override
    public float totalDebt() {
        return debt;
    }
}
