import java.util.Scanner;

public class MainMenu implements Menu {

    static String menuString = "\n"
               + Colors.BLUE_BOLD + "PANEL ADMINISTRACYJNY WYPOZYCZALNI SAMOCHODÓW \n" + Colors.RESET
               + "\n"
               + " 0. Wczytaj dane z pliku \n"
               + "\n"
               + " 1. Umowy \n"
               + " 2. Samochody \n"
               + " 3. Klienci \n"
               + "\n"
               + " 4. Statystyki \n"
               + "\n"
               + " 9. Zapisz dane do pliku \n"
               + "\n"
               + " x: wyjście z programu \n";

    public static void handleOptions() {
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        while (!option.equals("x")) {
            switch (option) {
                case "0":
                    Main.readDataFromFile();
                    break;
                case "1":
                    Menu.show(AgreementsMenu.menuString);
                    AgreementsMenu.handleOptions();
                    break;
                case "2":
                    Menu.show(CarsMenu.menuString);
                    CarsMenu.handleOptions();
                    break;
                case "3":
                    Menu.show(ClientsMenu.menuString);
                    ClientsMenu.handleOptions();
                    break;
                case "9":
                    Main.saveDataToFile();
                    break;
                case "4":
                    Main.showStatistics();
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

