import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Random;
import java.util.Scanner;


//TODO expand to include non-human names
public class NameGeneration {
	public static void main(String[] args) throws IOException {
		int choice = 0;
		String firstname = "";
		String lastname = "";
		
		Scanner input = new Scanner(System.in);
		System.out.println("Enter 1 for a male name, 2 for a female name, or 3 if you don't care either way: ");
		choice = input.nextInt();

		Random rand = new Random();
		
		if(choice == 3) {
			choice = rand.nextInt(2) + 1;
		}	

		
		if(choice == 1) {
			firstname = Files.readAllLines(Paths.get("src/dist.male.first.txt")).get(rand.nextInt(1220));
		}else if(choice == 2) {
			firstname = Files.readAllLines(Paths.get("src/dist.female.first.txt")).get(rand.nextInt(4276));
		}
		
		firstname = firstname.split(" ")[0];
		
		lastname = Files.readAllLines(Paths.get("src/dist.all.last.txt")).get(rand.nextInt(151672));
		lastname = lastname.split(" ")[0];
		
		System.out.println(firstname + " " + lastname);
		
		input.close();
	}
}
