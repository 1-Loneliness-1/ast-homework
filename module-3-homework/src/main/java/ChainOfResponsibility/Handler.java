package ChainOfResponsibility;

public abstract class Handler {

    private Handler nextHandler;

    public Handler() {
        this.nextHandler = null;
    }

    public Handler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public Handler getNextHandler() {
        return nextHandler;
    }

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public void handle(String request) {
        if (nextHandler != null) {
            nextHandler.handle(request);
        }
    }

}
