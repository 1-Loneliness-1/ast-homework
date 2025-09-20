package ChainOfResponsibility;

public class Therapist extends Handler {

    public Therapist() {
        super();
    }

    public Therapist(Handler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(String request) {
        if (request.equals("легкая болезнь")) {
            System.out.println("Запрос выполнен. План лечения назначен.");
        } else {
            Handler nextHandler = getNextHandler();
            if (nextHandler != null) {
                nextHandler.handle(request);
            } else {
                System.out.println("Мы не можем обработать ваш запрос.");
            }
        }
    }

}
