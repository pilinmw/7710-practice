/**
 * The main game arena to demonstrate the interaction.
 */
public class GameArena {
    public static void main(String[] args) {
        Hero bernardo = new Hero("Bernardo Pereira Nunes", 40);
        Monster java = new Monster("JAVA", 70);

        System.out.println("--- Battle Starts! ---");

        bernardo.attack(java);
        System.out.println("----------------------");
        bernardo.attack(java);
    }
}
