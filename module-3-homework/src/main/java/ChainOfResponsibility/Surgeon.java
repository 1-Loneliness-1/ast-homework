package ChainOfResponsibility;

public class Surgeon extends Handler {

    public Surgeon() {
        super();
    }

    public Surgeon(Handler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(String request) {
        if (request.equals("хирургическая проблема")) {
            System.out.println("Запрос выполнен. Хирург вылечил пациента.");
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
