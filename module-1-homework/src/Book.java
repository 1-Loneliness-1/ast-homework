public class Book {

    private long numberOfAvailableBooks = 0L;

    private final long bookId;
    private final String title;
    private final String author;
    private final int yearOfRelease;
    private final int numberOfPages;

    public Book(String title, String author, int yearOfRelease, int numberOfPages) {
        this.bookId = getIdForNewBook();
        this.title = title;
        this.author = author;
        this.yearOfRelease = yearOfRelease;
        this.numberOfPages = numberOfPages;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public int getYearOfRelease() {
        return yearOfRelease;
    }

    @Override
    public String toString() {
        return  "\t[bookId=" + bookId +
                "\n\ttitle='" + title +
                "\n\tauthor='" + author +
                "\n\tyearOfRelease=" + yearOfRelease +
                "\n\tnumberOfPages=" + numberOfPages + "]\n";
    }

    private long getIdForNewBook() {
        return numberOfAvailableBooks++;
    }
}
