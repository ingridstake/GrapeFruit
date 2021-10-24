package edu.chalmers.grapefruit.Model;

/**
 * Class for simulating a die.
 * Can generate a random number in range [1... number of sides of the dice].
 * Number of sides of the dice is selected when the dice is initialized.
 *
 * @author Elvina Fahlgren
 */
public class Dice {

    private int sides;
    private int value;

    /**
     * Construct a Dice object.
     * @param sides specifies the number of "sides" of the die.
     */
    public Dice(int sides) {
        this.sides = sides;
        value = 1;
    }

    /**
     * Returns the random "roll" of the dice.
     * @return a random number in range [1... number of sides of the dice].
     */
    public int roll() {
        value = (int) (Math.random() * sides) + 1;
        return value;
    }

    public int getValue() {
        return value;
    }
}
