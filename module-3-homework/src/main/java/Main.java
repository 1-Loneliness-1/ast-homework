import Builder.Person;
import ChainOfResponsibility.Registratura;
import ChainOfResponsibility.Surgeon;
import ChainOfResponsibility.Therapist;
import Proxy.FileProxy;
import Strategy.BubbleSorter;
import Strategy.MergeSorter;
import Strategy.SortController;

public class Main {

    public static void main(String[] args) {

        // Демонстрация работы поведенческого паттерна "Стратегия"
        SortController sortController = new SortController(new BubbleSorter());
        sortController.doSort();
        // Меняем поведение контроллера без изменения кода внутри класса
        sortController.setSorter(new MergeSorter());
        sortController.doSort();

        // Демонстрация работы поведенческого паттерна "Цепочка обязанностей"
        Surgeon surgeon = new Surgeon();
        Therapist therapist = new Therapist(surgeon);
        Registratura registratura = new Registratura(therapist);

        registratura.handle("справка");
        registratura.handle("легкая болезнь");
        registratura.handle("хирургическая проблема");
        registratura.handle("невыполнимый запрос");

        // Демонстрация работы порождающего паттерна "Builder"
        Person person = new Person.Builder("Ivan", "Petrov", 26)
                .setEmail("someemail@gmail.com")
                .build();

        // Демонстрация работы структурного паттерна Proxy
        FileProxy fileProxy = new FileProxy("C:\\SomeFolder\\SubFolder\\important.txt");
        System.out.println("Пробуем загрузить и отобразить файл впервые:");
        fileProxy.displayFile();
        System.out.println("Пробуем повторно загрузить и отобразить файл:");
        fileProxy.displayFile();
    }

}
