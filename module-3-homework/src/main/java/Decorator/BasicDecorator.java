package Decorator;

abstract class BasicDecorator implements Beverage {

    private final Beverage wrappedObject;

    protected BasicDecorator(Beverage wrappedObject) {
        this.wrappedObject = wrappedObject;
    }

    protected Beverage getWrappedObject() {
        return wrappedObject;
    }

    @Override
    public Integer getCost() {
        return wrappedObject.getCost();
    }

    @Override
    public String getDescription() {
        return wrappedObject.getDescription();
    }
}
