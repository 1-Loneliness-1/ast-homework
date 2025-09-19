package Proxy;

public class FileProxy implements File {

    private final String filePath;

    private FileWithDataManager fileWithData;

    public FileProxy(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void displayFile() {
        if (fileWithData == null) {
            System.out.println("Выполняем загрузку файла...");
            fileWithData = new FileWithDataManager(filePath);
            System.out.println("Файл загружен.");
        } else {
            System.out.println("Файл уже был загружен ранее. Отобразим его.");
        }
        fileWithData.displayFile();
    }
}
