package Decorator;

public class CoffeeWithSugar extends BasicDecorator {

    public CoffeeWithSugar(Beverage wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public Integer getCost() {
        return super.getCost() + 10;
    }

    @Override
    public String getDescription() {
        return super.getDescription() + " С сахаром.";
    }

}
