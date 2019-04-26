import java.util.Scanner;
import java.util.Random;


//TODO: clean up
public class StatGeneration {
	public static void main(String[] args) {
		int strength = 0;
		int dexterity = 0;
		int constitution = 0;
		int intelligence = 0;
		int wisdom = 0;
		int charisma = 0;

		Random dice = new Random();


		Scanner input = new Scanner(System.in);
		System.out.println("Enter 1 for standard array, 2 for 3d6, or 3 for 4d6 drop lowest: ");
		int option = input.nextInt();

//TODO: change distribution of stats based on the desired class (e.g. if the user enters 'warlock' put highest stats in cha and con, 
//		if they enter 'fighter' put the highest in str, etc
//		System.out.println("Enter the name of the class you want to play (wizard/cleric/figher/etc): ");
//		String playerClass = input.next();

		
		
		input.close();

		switch(option) {

			case(1):
				strength = 15;
				dexterity = 14;
				constitution = 13;
				intelligence = 12;
				wisdom = 10;
				charisma = 8;
				
				break;
			case(2):
				strength = dice.nextInt(6) + dice.nextInt(6) + dice.nextInt(6) + 3;
				dexterity = dice.nextInt(6) + dice.nextInt(6) + dice.nextInt(6) + 3;
				constitution = dice.nextInt(6) + dice.nextInt(6) + dice.nextInt(6) + 3;
				intelligence = dice.nextInt(6) + dice.nextInt(6) + dice.nextInt(6) + 3;
				wisdom = dice.nextInt(6) + dice.nextInt(6) + dice.nextInt(6) + 3;
				charisma = dice.nextInt(6) + dice.nextInt(6) + dice.nextInt(6) + 3;
				break;
			case(3):
				//TODO 4d6 drop lowest
				break;

		}

		System.out.println("Your stats are: ");
		System.out.println("Strength: " + strength);
		System.out.println("Dexterity: " + dexterity);
		System.out.println("Constitution: " + constitution);
		System.out.println("Intelligence: " + intelligence);
		System.out.println("Wisdom: " + wisdom);
		System.out.println("Charisma: " + charisma);
	}
}
