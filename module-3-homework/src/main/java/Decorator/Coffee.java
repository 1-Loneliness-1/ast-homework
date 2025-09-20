package Decorator;

public class Coffee implements Beverage {

    private final String description;
    private final Integer cost;

    public Coffee(String description, Integer cost) {
        this.description = description;
        this.cost = cost;
    }

    @Override
    public Integer getCost() {
        return cost;
    }

    @Override
    public String getDescription() {
        return description;
    }

}
