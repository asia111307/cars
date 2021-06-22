import java.util.Scanner;

public class CarsMenu implements Menu {

    static String menuString = "\n"
            + Colors.BLUE_BOLD + "SAMOCHODY \n" + Colors.RESET
            + "\n"
            + " 1. Pokaż wszystkie samochody \n"
            + " 2. Dodaj samochód \n"
            + " 3. Edytuj samochód \n"
            + " 4. Usuń samochód \n"
            + " 5. Pokaż statystyki \n"
            + "\n"
            + " x: powrót do głównego Menu \n";



    public static void handleOptions() {
        Scanner scanner = new Scanner(System.in);
        String option = scanner.nextLine();
        String vin;

        while (!option.equals("x")) {
            switch (option) {
                case "1":
                    Car.showAllCars();
                    break;
                case "2":
                    Car.addNewCar();
                    break;
                case "3":
                    Car.editCar("");
                    break;
                case "4":
                    Car.deleteCar();
                    break;
               case "5":
                   Car.showStatistics();
                    break;
                default:
                    System.out.println(Colors.YELLOW_BOLD + "Niepoprawna opcja. Wracam do menu" + Colors.RESET);
            }
            Menu.show(menuString);
            option = scanner.nextLine();
        }
    }
}

