package Decorator;

public class CoffeeWithMilk extends BasicDecorator {

    public CoffeeWithMilk(Beverage wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public Integer getCost() {
        return super.getCost() + 20;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " С молоком!";
    }

}
