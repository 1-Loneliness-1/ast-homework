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
    }

}
