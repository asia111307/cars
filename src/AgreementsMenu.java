import java.util.Scanner;

public class AgreementsMenu implements Menu {

    static String menuString = "\n"
                + Colors.BLUE_BOLD + "UMOWY \n" + Colors.RESET
                + "\n"
                + " 1. Pokaż wszystkie umowy \n"
                + " 2. Dodaj umowę \n"
                + " 3. Edytuj umowę \n"
                + " 4. Usuń umowę \n"
                + " 5. Zamień rezerwację na pełną umowę \n"
                + " 6. Pokaż statystyki \n"
                + "\n"
                + " x: powrót do głównego Menu \n"
                + "\n";

    public static void handleOptions() {
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();

        while (!option.equals("x")) {
            switch (option) {
                case "1":
                    Agreement.showAllAgreements();
                    break;
                case "2":
                    Agreement.addNewAgreement();
                    break;
                case "3":
                    Agreement.editAgreement();
                    break;
                case "4":
                    Agreement.deleteAgreement();
                    break;
                case "5":
                    Agreement.convertAgreement();
                    break;
                case "6":
                    Agreement.showStatistics();
                    break;
                default:
                    System.out.println(Colors.YELLOW_BOLD + "Niepoprawna opcja. Wracam do menu" + Colors.RESET);
                    break;
            }
            Menu.show(AgreementsMenu.menuString);
            option = scanner.nextLine();
        }
    }
}

