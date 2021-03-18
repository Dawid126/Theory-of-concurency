import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) throws IOException {

        int n1 = 1;
        int n2 = 1;
        int n3 = 5;

    File file = new File("Hello1.txt");

    // creates the file
    file.createNewFile();

    // creates a FileWriter Object
    FileWriter writer = new FileWriter(file);

    // Writes the content to the file
    writer.write("This\n is\n an\n example\n");
    writer.flush();
    writer.close();

    // Creates a FileReader Object
    FileReader fr = new FileReader(file);
    char [] a = new char[50];
    fr.read(a);   // reads the content to the array

    for(char c : a)
        System.out.print(c);   // prints the characters one by one
    fr.close();
    }
}
