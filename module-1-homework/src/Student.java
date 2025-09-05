import java.util.ArrayList;
import java.util.List;

public class Student {

    private long numberOfRegisteredStudents = 0L;

    private final long id;
    private final String lastName;
    private final String firstName;
    private final String middleName;
    private final short age;
    private final String phoneNumber;
    private final List<Book> borrowedBooks;

    public Student(String lastName, String firstName, String middleName, Short age, String phoneNumber, List<Book> borrowedBooks) {
        this.id = getUniqueStudentId();
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.borrowedBooks = borrowedBooks;
    }

    @Override
    public String toString() {
        return  "[id = " + id +
                "\nfirstName = " + firstName +
                "\nlastName = " + lastName +
                "\nmiddleName = " + middleName +
                "\nage=" + age +
                "\nborrowedBooks =\n" + borrowedBooks +
                "\nphoneNumber = " + phoneNumber + "]\n\n";
    }

    public List<Book> getBorrowedBooks() {
        return new ArrayList<>(borrowedBooks);
    }

    public void insertNewBook(Book newBook) {
        borrowedBooks.add(newBook);
    }

    private long getUniqueStudentId() {
        numberOfRegisteredStudents++;
        return numberOfRegisteredStudents;
    }

}
