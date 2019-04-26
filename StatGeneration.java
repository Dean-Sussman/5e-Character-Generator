import java.util.Scanner;
import java.util.Random;
import java.util.Arrays;
import java.util.Collections;


//TODO: clean up; loop until correct inputs; join with name generation; choose random class; finish fighter and rogue;
//		let player choose race and adjust stats accordingly
public class StatGeneration {
	public static void main(String[] args) {
		int statOption = 0;
		boolean reroll = false;
		String playerClass = "random";
		Integer[] stats = new Integer[6];
		
//TODO: loop until correct user input	
		Scanner input = new Scanner(System.in);
		System.out.println("Enter 1 for standard array, 2 for 3d6, or 3 for 4d6 drop lowest: ");
		statOption = input.nextInt();

		if(statOption != 1) { //can't reroll a predetermined array
			System.out.println("Enter \"true\" to reroll 1s, or \"false\" to not: ");
			reroll = input.nextBoolean();
		}


		System.out.println("Enter the name of the class you wish to play, or 'random' to have one picked for you: ");
		playerClass = input.next().toLowerCase().trim();

		stats = generateStats(stats, statOption, reroll);
		
		
		//can put this in a function called 'order stats' or something
		//better idea-- if the user entered 'n/a' then print out stats as they are; if not, sort the array and then print

		if(playerClass.equals("random")) {
			//change to call 'playerClass' with a randomly chosen class
			System.out.println("Your stats are: ");
			System.out.println("Strength: " + stats[0]);
			System.out.println("Dexterity: " + stats[1]);
			System.out.println("Constitution: " + stats[2]);
			System.out.println("Intelligence: " + stats[3]);
			System.out.println("Wisdom: " + stats[4]);
			System.out.println("Charisma: " + stats[5]);
		}else {
			printStats(playerClass, stats);
		}
		
		input.close();
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
	
	public static Integer[] generateStats(Integer[] stats, int generationMethod, boolean reroll) {
		switch(generationMethod) {

			case(1):
				stats[0] = 15;
				stats[1] = 14;
				stats[2]= 13;
				stats[3] = 12;
				stats[4] = 10;
				stats[5] = 8;

				break;
			case(2):
				for(int i = 0; i < 6; i++) {
					stats[i] = firstThree(reroll);
				}
				break;
			case(3):
				for(int i = 0; i < 6; i++) {
					stats[i] = fourDropLowest(reroll);
				}
			break;

		}
		
		return stats;
	}
	
	public static void printStats(String playerClass, Integer[] stats) {
		Arrays.sort(stats, Collections.reverseOrder());
		
		//XXX how to distribute stats based on input once rolled
		//Barbarian: Str > Con > Dex > Wis > Cha > Int {could unify with Str no EK fighter}
		//Bard: Cha > Dex > Con > Wis > Int > Str
		//Cleric: Wis > Con > Str > Cha > Int > Dex
		//Druid: Wis > Con > Int > Dex > Cha > Str
		//Fighter: Prompt again--Str or Dex? Then, Eldritch Knight or Not?
				//if Str, no EK-- Str > Con > Dex > Wis > Int > Cha {could unify with Barb}
				//Dex no EK-- Dex > Con > Str > Wis > Int > Cha
				//Str EK-- Str > Int > Con > Dex > Wis > Cha
				//Dex EK-- Dex > Int > Con > Wis > Cha > Str
		//Monk : Dex > Wis > Con > Cha > Int > Str {same as Ranger}
		//Paladin: Str > Cha > Con > Wis > Int > Dex
		//Ranger: Dex > Wis > Con > Cha > Int > Str {same as Monk}
		//Rogue: Prompt again-- Int (Arcane Trickster/Investigation) or Cha (Deception/Social interaction)?
				//Int-- Dex > Int > Cha > Wis > Con > Str
				//Cha-- Dex > Cha > Int > Wis > Con > Str
		//Sorceror: Cha > Con > Dex > Wis > Int > Str {same as Warlock}
		//Warlock: Cha > Con > Dex > Wis > Int > Str {same as Sorceror}
		//Wizard: Int > Con > Wis > Cha > Dex > Str
				
		
		if(playerClass.equals("barbarian")) {
			System.out.println("Your stats are: ");
			System.out.println("Strength: " + stats[0]);
			System.out.println("Dexterity: " + stats[2]);
			System.out.println("Constitution: " + stats[1]);
			System.out.println("Intelligence: " + stats[5]);
			System.out.println("Wisdom: " + stats[3]);
			System.out.println("Charisma: " + stats[4]);
		}else if(playerClass.equals("bard")) {
			System.out.println("Your stats are: ");
			System.out.println("Strength: " + stats[5]);
			System.out.println("Dexterity: " + stats[1]);
			System.out.println("Constitution: " + stats[2]);
			System.out.println("Intelligence: " + stats[4]);
			System.out.println("Wisdom: " + stats[3]);
			System.out.println("Charisma: " + stats[0]);
		}else if(playerClass.equals("cleric")) {
			System.out.println("Your stats are: ");
			System.out.println("Strength: " + stats[5]);
			System.out.println("Dexterity: " + stats[1]);
			System.out.println("Constitution: " + stats[2]);
			System.out.println("Intelligence: " + stats[4]);
			System.out.println("Wisdom: " + stats[3]);
			System.out.println("Charisma: " + stats[0]);
		}else if(playerClass.equals("druid")) {
			System.out.println("Your stats are: ");
			System.out.println("Strength: " + stats[5]);
			System.out.println("Dexterity: " + stats[3]);
			System.out.println("Constitution: " + stats[1]);
			System.out.println("Intelligence: " + stats[2]);
			System.out.println("Wisdom: " + stats[0]);
			System.out.println("Charisma: " + stats[4]);
		}else if(playerClass.equals("fighter")) { //remember str vs dex & ek vs not
			
		}else if(playerClass.equals("monk") || playerClass.equals("ranger")) {
			System.out.println("Your stats are: ");
			System.out.println("Strength: " + stats[5]);
			System.out.println("Dexterity: " + stats[0]);
			System.out.println("Constitution: " + stats[2]);
			System.out.println("Intelligence: " + stats[4]);
			System.out.println("Wisdom: " + stats[1]);
			System.out.println("Charisma: " + stats[3]);
		}else if(playerClass.equals("paladin")) {
			System.out.println("Your stats are: ");
			System.out.println("Strength: " + stats[0]);
			System.out.println("Dexterity: " + stats[5]);
			System.out.println("Constitution: " + stats[2]);
			System.out.println("Intelligence: " + stats[4]);
			System.out.println("Wisdom: " + stats[3]);
			System.out.println("Charisma: " + stats[1]);
		}else if(playerClass.equals("rogue")) { //remember cha vs int
			
		}else if(playerClass.equals("sorcerer") || playerClass.equals("warlock")) {
			System.out.println("Your stats are: ");
			System.out.println("Strength: " + stats[5]);
			System.out.println("Dexterity: " + stats[2]);
			System.out.println("Constitution: " + stats[1]);
			System.out.println("Intelligence: " + stats[4]);
			System.out.println("Wisdom: " + stats[3]);
			System.out.println("Charisma: " + stats[0]);
		}else if(playerClass.equals("wizard")) {
			System.out.println("Your stats are: ");
			System.out.println("Strength: " + stats[5]);
			System.out.println("Dexterity: " + stats[4]);
			System.out.println("Constitution: " + stats[1]);
			System.out.println("Intelligence: " + stats[0]);
			System.out.println("Wisdom: " + stats[2]);
			System.out.println("Charisma: " + stats[3]);
		}
	}
}
