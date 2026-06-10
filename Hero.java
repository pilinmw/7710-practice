/**
 * Represents a Hero in the RPG game.
 */
public class Hero {
    private String name;
    private int attackPower;

    /**
     * Constructs a new Hero.
     *
     * @param name the name of the hero
     * @param attackPower the amount of damage this hero deals per attack
     */
    public Hero(String name, int attackPower) {
        this.name = name;
        this.attackPower = attackPower;
    }

    /**
     * Attacks a specific monster target.
     * This method demonstrates passing an object as a parameter.
     *
     * @param target the Monster object being attacked
     */
    public void attack(Monster target) {
        System.out.println(this.name + " powerfully swings his keyboard like a sword!");

        boolean isDefeated = target.takeDamage(this.attackPower);

        if (isDefeated) {
            System.out.println("Victory! " + this.name + " defeated the monster!");
        } else {
            System.out.println("The monster is still standing! Get ready for the next round.");
        }
    }
}
