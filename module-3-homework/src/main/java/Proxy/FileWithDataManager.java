package Proxy;

public class FileWithDataManager implements File {

    private final String filePath;

    public FileWithDataManager(String filePath) {
        this.filePath = filePath;
        loadFileFromDisk();
    }

    private void loadFileFromDisk() {
        System.out.printf("Загружаем файл по пути %s\n", filePath);
    }

    @Override
    public void displayFile() {
        System.out.println("Отображаем данные из загруженного файла.");
    }

}
