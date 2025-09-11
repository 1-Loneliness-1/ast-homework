import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        FileManager fileManager = new FileManager();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            try {
                System.out.print("Enter the information to write to the file: ");
                String dataForFile = bufferedReader.readLine();
                fileManager.writeInFile(dataForFile);
                fileManager.readFromFile();
            } catch (IOException e) {
                System.out.printf("Something went wrong with input. Why? It's %s", e.getMessage());
            } catch (CustomException e) {
                System.out.println("Logic for cases when exceptions occur while reading or writing to a file.");
            }
        } catch (IOException e) {
            System.out.println("Exception when trying to close input stream");
        }

    }

}
