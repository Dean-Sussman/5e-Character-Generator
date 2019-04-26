import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;


//FIXME can't find file sometimes
//TODO allow user to choose male vs female first name
//can also expand to include non-human names
public class NameGeneration {
	public static void main(String[] args) throws IOException {
		
		
		Random rand = new Random();
		
		String firstname = Files.readAllLines(Paths.get("src/dist.female.first.txt")).get(rand.nextInt(5495));

		firstname = firstname.split(" ")[0];
		
		String lastname = Files.readAllLines(Paths.get("src/dist.all.last.txt")).get(rand.nextInt(151672));
		
		lastname = lastname.split(" ")[0];
		
		System.out.println(firstname + " " + lastname);
	}
}
