import java.lang.Readable;

public class FileManager {

    private Writable writer;
    private Readable reader;

    public FileManager(Writable writer, Readable reader) {
        this.writer = writer;
        this.reader = reader;
    }

    public void writeInFile(String str) {

    }

}
