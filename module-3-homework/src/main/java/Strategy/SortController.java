package Strategy;

public class SortController {

    private AbstractSort sorter;

    public SortController(AbstractSort sorter) {
        super();
        this.sorter = sorter;
    }

    public void setSorter(AbstractSort sorter) {
        this.sorter = sorter;
    }

    public void doSort() {
        sorter.sort();
    }

}
