import edu.chalmers.grapefruit.Model.Dice;
import org.junit.jupiter.api.Test;

public class DiceTest {
    Dice dice;

    @Test
    public void testIfDiceCanRollInRightRange() {
        dice = new Dice(3);
        int i = 0;
        while (i < 10) {
            assert (dice.roll() <= 3);
            i++;
        }
    }

    @Test
    public void testIfRolledValueIsSaved() {
        dice = new Dice(6);
        int rolledValue = dice.roll();
        assert (rolledValue == dice.getValue());
    }
}