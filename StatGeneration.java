import java.util.Scanner;
import java.util.Random;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.InputMismatchException;

enum PlayerClass{
	BARBARIAN, BARD, CLERIC, DRUID, DEX_FIGHTER_EK, STR_FIGHTER_EK, STR_FIGHTER, DEX_FIGHTER, MONK, PALADIN, RANGER, INT_ROGUE, CHA_ROGUE, SORCEROR, WARLOCK, WIZARD;
}

//TODO: clean up; make sure all inputs are being validated
//	Ideas for future additions:
//		let player choose race and adjust stats and name accordingly
//		re-make with buttons/dropdown menus instead of number entry for option choice
public class StatGeneration {
	public static void main(String[] args) throws IOException {
		
		int statOption = 0;
		int nameOption = 0;
		int subclassOption = 0;
		int rerollOption = 0;
		boolean reroll = false;
		
		Random chooser = new Random();
		String playerClass = "random";
		String name = "";
		Integer[] stats = new Integer[6];
		Scanner input = new Scanner(System.in);
		
		System.out.println("Enter 1 for a male name, 2 for a female name, or 3 to have it decided for you: ");
		nameOption = getValidInput(1, 3, input);
		
		if(nameOption == 3) {
			
			nameOption = chooser.nextInt(2) + 1;
			
		}
		name = nameGenerator(nameOption);
	
		System.out.println("Enter 1 for standard array, 2 for 3d6, or 3 for 4d6 drop lowest: ");
		statOption = getValidInput(1, 3, input);

		if(statOption != 1) { //can't reroll a predetermined array
			System.out.println("Enter 1 to reroll 1s, or 2 to not: ");
			//could have user enter true/false and read a boolean, but it would be awkward with every other option being determined by 1/2/etc
			rerollOption = getValidInput(1,2, input);
			reroll = (rerollOption == 1) ? true : false;
		}

		//TODO verify input here
		System.out.println("Enter the name of the class you wish to play, or 'random' to have one picked for you: ");
		playerClass = input.next().toLowerCase().trim();
		String className = playerClass;
		

		if(playerClass.equals("fighter")) {
			
			System.out.println("Enter 1 to be a strength-based fighter, 2 to be a dexterity-based fighter, ");
			System.out.println("3 to be a strength-based Eldritch Knight, or 4 to be a dexterity-based Eldritch Knight: ");
			subclassOption = getValidInput(1,4, input);
			
			if(subclassOption == 1) {
				
				playerClass = "str_fighter";
				
			}else if(subclassOption == 2) {
				
				playerClass = "dex_fighter";
				
			}else if(subclassOption == 3) {
				
				playerClass = "str_fighter_ek";
				
			}else if(subclassOption == 4) {
				
				playerClass = "dex_fighter_ek";
				
			}	
			
		}else if(playerClass.equals("rogue")) {
			
			System.out.println("Enter 1 to focus on Intelligence (Arcane Trickster/Investigation), or 2 to focus on Charisma (Deception/Social interaction): ");
			subclassOption = getValidInput(1,2, input);
			
			 if(subclassOption == 1) {
				 
				playerClass = "int_rogue";
				
			}else if(subclassOption == 2) {
				
				playerClass = "cha_rogue";
				
			}
		}
		
		stats = generateStats(stats, statOption, reroll);
	
		if(playerClass.equals("random")) {
			
			PlayerClass classes[] = PlayerClass.values();
			playerClass = classes[chooser.nextInt(16)].toString().toLowerCase();
			
			if(playerClass.contains("rogue")) {
				
				className = "rogue";
				
			}else if(playerClass.contains("fighter")) {
				
				className = "fighter";
				
			}else {
				
				className = playerClass;
				
			}
		}

		System.out.println("You are " + name + ", a " + className + ".");
		printStats(playerClass, stats);
		
		input.close();
	}

	
	public static int getValidInput(int min, int max, Scanner input) {
		
		int validInput = 0;
		boolean done = false;
		
		while(!done) {
			
			try {
				
				validInput = input.nextInt();
				
				if(validInput < min || validInput > max) {
					
					System.out.println("Invalid input. Please enter a number between " + min + " and " + max);					
					done = false;
					
				}else {
					
					done = true;
					
				}
			}catch(InputMismatchException x) {
				
				System.out.println("Invalid input. Please enter a number between " + min + " and " + max);
				input.next();
				
			}
		}
		
		return validInput;
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
		
		//How to distribute stats based on input once rolled
		//Barbarian: Str > Con > Dex > Wis > Cha > Int {could unify with Str no EK fighter}
		//Bard: Cha > Dex > Con > Wis > Int > Str
		//Cleric: Wis > Con > Str > Cha > Int > Dex
		//Druid: Wis > Con > Int > Dex > Cha > Str
		//Fighter: Prompt again--Str or Dex? Then, Eldritch Knight or Not?
				//Dex EK-- Dex > Int > Con > Wis > Cha > Str
				//Dex no EK-- Dex > Con > Str > Wis > Int > Cha
				//Str EK-- Str > Int > Con > Dex > Wis > Cha
				//Str, no EK-- Str > Con > Dex > Wis > Int > Cha {could unify with Barb}
		//Monk : Dex > Wis > Con > Cha > Int > Str {same as Ranger}
		//Paladin: Str > Cha > Con > Wis > Int > Dex
		//Ranger: Dex > Wis > Con > Cha > Int > Str {same as Monk}
		//Rogue: Prompt again-- Int (Arcane Trickster/Investigation) or Cha (Deception/Social interaction)?
				//Cha-- Dex > Cha > Int > Wis > Con > Str
				//Int-- Dex > Int > Cha > Wis > Con > Str
		//Sorceror: Cha > Con > Dex > Wis > Int > Str {same as Warlock}
		//Warlock: Cha > Con > Dex > Wis > Int > Str {same as Sorceror}
		//Wizard: Int > Con > Wis > Cha > Dex > Str

		System.out.println("Your stats are: ");
		
		if(playerClass.equals("barbarian")) {
			
			System.out.println("Strength: " + stats[0]);
			System.out.println("Dexterity: " + stats[2]);
			System.out.println("Constitution: " + stats[1]);
			System.out.println("Intelligence: " + stats[5]);
			System.out.println("Wisdom: " + stats[3]);
			System.out.println("Charisma: " + stats[4]);
			
		}else if(playerClass.equals("bard")) {
			
			System.out.println("Strength: " + stats[5]);
			System.out.println("Dexterity: " + stats[1]);
			System.out.println("Constitution: " + stats[2]);
			System.out.println("Intelligence: " + stats[4]);
			System.out.println("Wisdom: " + stats[3]);
			System.out.println("Charisma: " + stats[0]);
			
		}else if(playerClass.equals("cleric")) {
			
			System.out.println("Strength: " + stats[5]);
			System.out.println("Dexterity: " + stats[1]);
			System.out.println("Constitution: " + stats[2]);
			System.out.println("Intelligence: " + stats[4]);
			System.out.println("Wisdom: " + stats[3]);
			System.out.println("Charisma: " + stats[0]);
			
		}else if(playerClass.equals("druid")) {
			
			System.out.println("Strength: " + stats[5]);
			System.out.println("Dexterity: " + stats[3]);
			System.out.println("Constitution: " + stats[1]);
			System.out.println("Intelligence: " + stats[2]);
			System.out.println("Wisdom: " + stats[0]);
			System.out.println("Charisma: " + stats[4]);
			
		}else if(playerClass.equals("dex_fighter_ek")) { 
			
			System.out.println("Strength: " + stats[5]);
			System.out.println("Dexterity: " + stats[0]);
			System.out.println("Constitution: " + stats[2]);
			System.out.println("Intelligence: " + stats[1]);
			System.out.println("Wisdom: " + stats[3]);
			System.out.println("Charisma: " + stats[4]);
			
		}else if(playerClass.equals("dex_fighter")){
			
			System.out.println("Strength: " + stats[2]);
			System.out.println("Dexterity: " + stats[0]);
			System.out.println("Constitution: " + stats[1]);
			System.out.println("Intelligence: " + stats[4]);
			System.out.println("Wisdom: " + stats[3]);
			System.out.println("Charisma: " + stats[5]);
			
		}else if(playerClass.equals("str_fighter_ek")){
			
			System.out.println("Strength: " + stats[0]);
			System.out.println("Dexterity: " + stats[3]);
			System.out.println("Constitution: " + stats[2]);
			System.out.println("Intelligence: " + stats[1]);
			System.out.println("Wisdom: " + stats[4]);
			System.out.println("Charisma: " + stats[5]);
			
		}else if(playerClass.equals("str_fighter")){
			
			System.out.println("Strength: " + stats[0]);
			System.out.println("Dexterity: " + stats[2]);
			System.out.println("Constitution: " + stats[1]);
			System.out.println("Intelligence: " + stats[4]);
			System.out.println("Wisdom: " + stats[3]);
			System.out.println("Charisma: " + stats[5]);
			
		}else if(playerClass.equals("monk") || playerClass.equals("ranger")) {
			
			System.out.println("Strength: " + stats[5]);
			System.out.println("Dexterity: " + stats[0]);
			System.out.println("Constitution: " + stats[2]);
			System.out.println("Intelligence: " + stats[4]);
			System.out.println("Wisdom: " + stats[1]);
			System.out.println("Charisma: " + stats[3]);
			
		}else if(playerClass.equals("paladin")) {
			
			System.out.println("Strength: " + stats[0]);
			System.out.println("Dexterity: " + stats[5]);
			System.out.println("Constitution: " + stats[2]);
			System.out.println("Intelligence: " + stats[4]);
			System.out.println("Wisdom: " + stats[3]);
			System.out.println("Charisma: " + stats[1]);
			
		}else if(playerClass.equals("cha_rogue")) { 
			
			System.out.println("Strength: " + stats[5]);
			System.out.println("Dexterity: " + stats[0]);
			System.out.println("Constitution: " + stats[4]);
			System.out.println("Intelligence: " + stats[2]);
			System.out.println("Wisdom: " + stats[3]);
			System.out.println("Charisma: " + stats[1]);
			
		}else if(playerClass.equals("int_rogue")){
			
			System.out.println("Strength: " + stats[5]);
			System.out.println("Dexterity: " + stats[0]);
			System.out.println("Constitution: " + stats[4]);
			System.out.println("Intelligence: " + stats[1]);
			System.out.println("Wisdom: " + stats[3]);
			System.out.println("Charisma: " + stats[2]);
			
		}else if(playerClass.equals("sorcerer") || playerClass.equals("warlock")) {
			
			System.out.println("Strength: " + stats[5]);
			System.out.println("Dexterity: " + stats[2]);
			System.out.println("Constitution: " + stats[1]);
			System.out.println("Intelligence: " + stats[4]);
			System.out.println("Wisdom: " + stats[3]);
			System.out.println("Charisma: " + stats[0]);
			
		}else if(playerClass.equals("wizard")) {
			
			System.out.println("Strength: " + stats[5]);
			System.out.println("Dexterity: " + stats[4]);
			System.out.println("Constitution: " + stats[1]);
			System.out.println("Intelligence: " + stats[0]);
			System.out.println("Wisdom: " + stats[2]);
			System.out.println("Charisma: " + stats[3]);
			
		}
	}
	
	
	public static String nameGenerator(int genderChoice) throws IOException {
		
		String firstname = "";
		String lastname = "";
		Random rand = new Random();
		
		if(genderChoice == 1) {
			
			firstname = Files.readAllLines(Paths.get("src/male_firs_names")).get(rand.nextInt(1220));
			
		}else if(genderChoice == 2) {
			
			firstname = Files.readAllLines(Paths.get("src/female_first_names")).get(rand.nextInt(4276));
			
		}
		
		firstname = firstname.split(" ")[0];
		
		lastname = Files.readAllLines(Paths.get("src/last_names")).get(rand.nextInt(151672));
		lastname = lastname.split(" ")[0];
		
		return firstname + " " + lastname;
	}
}
