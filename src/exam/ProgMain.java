package exam;
//Implement a Server that listens for connections and a Client that sends one of the following commands.
// The server must respond using Stream API to format the output.
//        Commands:
//        TOP 2 -> Returns the top 2 most expensive phones.
//        FILTER <OS> -> Returns all phones matching that exact OS (e.g., FILTER Android).
//        INVENTORY -> Returns the total inventory value calculated.
//        Formatting Rule:
//        The Server must return the data on a single line, separating multiple phones with | and the phone properties with ;.
import java.util.List;

public class ProgMain {
    public static void main(String[] args)
    {
        PhoneManager phoneManager = new PhoneManager();
        List<Phone> phones = phoneManager.readFile();
        phoneManager.multithreadingTasks(phones);

        PhoneServer phoneServer = new PhoneServer();
        phoneServer.startServer(phones);
    }
}
