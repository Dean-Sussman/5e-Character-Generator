import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


//FIXME can't find file sometimes
//TODO allow user to choose male vs female first name
//can also expand to include non-human names
public class NameGeneration {
	public static void main(String[] args) throws IOException {
		
		String firstname = Files.readAllLines(Paths.get("dist.female.first.txt")).get(0);

		
		String lastname = Files.readAllLines(Paths.get("dist.all.last.txt")).get(50);
		
		System.out.println(firstname + lastname);
	}
}
