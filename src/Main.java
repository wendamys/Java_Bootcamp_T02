class TicketOffice {
    private int availableTickets = 1;

    // Метод синхронизирован. Если один клиент зашел "купить",
    // второй будет ждать на входе в метод.
    public synchronized void buyTicket(String passengerName) {
        if (availableTickets > 0) {
            System.out.println(passengerName + ": Начал оформление...");

            // Имитируем небольшую задержку (проверка паспорта, оплата)
            try { Thread.sleep(100); } catch (InterruptedException e) {}

            availableTickets--;
            System.out.println(passengerName + ": Билет успешно куплен!");
        } else {
            System.out.println(passengerName + ": Извините, билетов больше нет.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TicketOffice office = new TicketOffice();

        // Создаем двух пассажиров (два потока)
        Thread p1 = new Thread(() -> office.buyTicket("Иван"));
        Thread p2 = new Thread(() -> office.buyTicket("Мария"));

        // Они "нажимают кнопку купить" одновременно
        p1.start();
        p2.start();
    }
}
