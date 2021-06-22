import java.util.*;

public class ClientsMenu implements Menu {

    static String menuString = "\n"
            + Colors.BLUE_BOLD + "KLIENCI \n" + Colors.RESET
            + "\n"
            + " 1. Pokaż klientów \n"
            + " 2. Dodaj nowego klienta \n"
            + " 3. Edytuj dane klienta \n"
            + " 4. Usuń klienta \n"
            + " 5. Pokaż statystyki \n"
            + "\n"
            + " x: powrót do głównego Menu \n";



    public static void handleOptions() {
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        while (!option.equals("x")) {
            switch (option) {
                case "1":
                    Client.showAllClients();
                    break;
                case "2":
                    Client.addNewClient();
                    break;
                case "3":
                    Client.editClient("");
                    break;
                case "4":
                    Client.deleteClient();
                    break;
                case "5":
                    Client.showStatistics();
                    break;
                default:
                    System.out.println(Colors.YELLOW_BOLD + "Niepoprawna opcja. Wracam do menu" + Colors.RESET);
                    break;
            }
            Menu.show(menuString);
            option = scanner.nextLine();
        }
    }
}

