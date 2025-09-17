import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Main {

    private static final List<Book> availableBooks = new ArrayList<>(Arrays.asList(
            new Book("1984", "George Orwell", 1949, 328),
            new Book("To Kill a Mockingbird", "Harper Lee", 1954, 281),
            new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925, 180),
            new Book("One Hundred Years of Solitude", "Gabriel García Márquez", 1967, 417),
            new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954, 1178),
            new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", 1997, 320),
            new Book("The Catcher in the Rye", "J.D. Salinger", 1951, 234),
            new Book("The Da Vinci Code", "Dan Brown", 2003, 489),
            new Book("The Hobbit", "J.R.R. Tolkien", 1937, 310),
            new Book("Brave New World", "Aldous Huxley", 1932, 288),
            new Book("The Alchemist", "Paulo Coelho", 1988, 208),
            new Book("The Shining", "Stephen King", 1977, 447),
            new Book("Pride and Prejudice", "Jane Austen", 1813, 432),
            new Book("The Odyssey", "Homer", -800, 541),
            new Book("The Little Prince", "Antoine de Saint-Exupéry", 1943, 96)
    ));

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>(Arrays.asList(
                new Student(
                        "Ivanov", "Ivan", "Petrovich", (short) 20,
                        "+7 (912) 345-67-89",
                        Arrays.asList(
                                availableBooks.get(0), availableBooks.get(7), availableBooks.get(10),
                                availableBooks.get(3), availableBooks.get(0), availableBooks.get(11)
                        )
                ),
                new Student(
                        "Ivanova", "Maria", "Alexandrovna", (short) 21,
                        "+7 (923) 456-78-90",
                        Arrays.asList(
                                availableBooks.get(13), availableBooks.get(9), availableBooks.get(8),
                                availableBooks.get(3), availableBooks.get(9), availableBooks.get(13)
                        )
                ),
                new Student(
                        "Sidorov", "Alexey", "Dmitrievich", (short) 19,
                        "+7 (934) 567-89-01",
                        Arrays.asList(
                                availableBooks.get(13), availableBooks.get(9), availableBooks.get(8),
                                availableBooks.get(3), availableBooks.get(9)
                        )
                ),
                new Student(
                        "Smirnova", "Ekaterina", "Olegovna", (short) 22,
                        "+7 (945) 678-90-12",
                        Arrays.asList(
                                availableBooks.get(7), availableBooks.get(11), availableBooks.get(6),
                                availableBooks.get(13), availableBooks.get(13), availableBooks.get(0),
                                availableBooks.get(1), availableBooks.get(5)
                        )
                ),
                new Student(
                        "Kuznetsov", "Oleg", "Petrovich", (short) 35,
                        "+7 (956) 789-01-23",
                        Arrays.asList(
                                availableBooks.get(0), availableBooks.get(0), availableBooks.get(8),
                                availableBooks.get(1), availableBooks.get(1), availableBooks.get(10)
                        )
                )
        ));

        students.stream()
                .peek(System.out::println)
                .map(Student::getBorrowedBooks)
                .flatMap(List::stream)
                .sorted(Comparator.comparingInt(Book::getNumberOfPages))
                .distinct()
                .filter(book -> book.getYearOfRelease() > 2000)
                .limit(3)
                .findFirst()
                .ifPresentOrElse(
                        book -> System.out.printf("The book has been found. It was published in %d", book.getYearOfRelease()),
                        () -> System.out.println("The book was not found!")
                );
    }

}
