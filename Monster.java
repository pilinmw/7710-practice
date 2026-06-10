/**
 * Represents a Monster in the RPG game.
 */
public class Monster {
    private String name;
    private int health;

    /**
     * Constructs a new Monster.
     *
     * @param name the name of the monster
     * @param health the starting health points (HP) of the monster
     */
    public Monster(String name, int health) {
        this.name = name;
        this.health = health;
    }

    /**
     * Reduces the monster's health by the specified damage amount.
     *
     * @param damage the amount of damage taken from an attack
     * @return true if the monster's health drops to 0 or below, false otherwise
     */
    public boolean takeDamage(int damage) {
        this.health -= damage;
        System.out.println(this.name + " takes " + damage + " damage! Remaining HP: " + this.health);

        return this.health <= 0;
    }
}
