package ChainOfResponsibility;

public class Registratura extends Handler {

    public Registratura() {
        super();
    }

    public Registratura(Handler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void handle(String request) {
        if (request.equals("справка")) {
            System.out.println("Запрос выполнен. Выдаем справку...");
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
