import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;


//TODO: clean up
public class StatGeneration {
	public static void main(String[] args) {
		int strength = 0;
		int dexterity = 0;
		int constitution = 0;
		int intelligence = 0;
		int wisdom = 0;
		int charisma = 0;


		
//TODO: loop until correct user input	
		Scanner input = new Scanner(System.in);
		System.out.println("Enter 1 for standard array, 2 for 3d6, or 3 for 4d6 drop lowest: ");
		int option = input.nextInt();

		System.out.println("Enter \"true\" to reroll 1s, or \"false\" to not: ");
		boolean reroll = input.nextBoolean();
		
		
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
				strength = firstThree(reroll);
				dexterity = firstThree(reroll);
				constitution = firstThree(reroll);
				intelligence = firstThree(reroll);
				wisdom = firstThree(reroll);
				charisma = firstThree(reroll);
				break;
			case(3):
				strength = fourDropLowest(reroll);
				dexterity = fourDropLowest(reroll);
				constitution = fourDropLowest(reroll);
				intelligence = fourDropLowest(reroll);
				wisdom = fourDropLowest(reroll);
				charisma = fourDropLowest(reroll);
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
	
	public static int fourDropLowest(boolean reroll) {
		Random dice = new Random();
		int rollArr[] = new int [4];
		for(int i = 0; i < 4; i++) {
			rollArr[i] = dice.nextInt(6) + 1;
		}
		
		Arrays.sort(rollArr);
		
		if(reroll) {
			int temp = 0;
			for(int i = 0; i < 4; i++) {
				if(rollArr[i] == 1) {
					temp = dice.nextInt(6) + 1;
					while(temp == 1) {
						temp = dice.nextInt(6) + 1;
					}
					rollArr[i] = temp;
				}
			}
		}
		
		return rollArr[1] + rollArr[2] + rollArr[3];
		
	}
	public static int firstThree(boolean reroll) {
		Random dice = new Random();
		if(!reroll) {
			return dice.nextInt(6) + dice.nextInt(6) + dice.nextInt(6) + 3;
		}else {
			int total = 0;
			int temp = 0;
			for(int i = 0; i < 3; i++) {
				temp = dice.nextInt(6) + 1;
				while(temp == 1) {
					temp = dice.nextInt(6) + 1;
				}
				total += temp;
			}
			return total;
		}
	}
}
