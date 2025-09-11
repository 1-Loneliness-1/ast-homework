import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileManager {

    private final File fileForData = new File("example.txt");

    public void writeInFile(String str) throws CustomException {
        System.out.println("Writing started");
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(fileForData, true))) {
            outputStream.write(str.getBytes());
            outputStream.flush();
        } catch (FileNotFoundException e) {
            throw new CustomException("File not found! Message: " + e.getMessage());
        } catch (IOException e) {
            throw new CustomException("Something wrong with input! Cause: " + e.getMessage());
        }
        System.out.println("Writing is over");
    }

    public void readFromFile() throws CustomException {
        System.out.println("Reading...");
        try (BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(fileForData))) {
            byte[] dataFromFile = inputStream.readAllBytes();
            System.out.println("Data from file:\n" + new String(dataFromFile, StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            throw new CustomException("File not found!");
        } catch (IOException e) {
            throw new CustomException("Something wrong with input");
        }
        System.out.println("Reading is over");
        if (fileForData.delete()) {
            System.out.println("The file has been deleted");
        } else {
            System.out.println("The file has not been deleted");
        }
    }

}
