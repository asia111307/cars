import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Scanner;

public class Car {
    @JsonProperty("vin") private String vin;
    @JsonProperty("mileage") private int mileage;
    @JsonProperty("pricePerDay") private float pricePerDay;

    public Car(@JsonProperty("vin") String vin, @JsonProperty("mileage") int mileage, @JsonProperty("pricePerDay") float pricePerDay) {
        this.vin = vin;
        this.mileage = mileage;
        this.pricePerDay = pricePerDay;
    }

    public String toString() {
        return ""
                + "    vin: " + this.vin + "\n"
                + "    mileage: " + this.mileage + "\n"
                + "    pricePerDay: " + this.pricePerDay;
    }

    public String getVin() {
        return this.vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public int getMileage() {
        return this.mileage;
    }

    public void setMileage(int mileage) {
        this.mileage = mileage;
    }

    public float getPricePerDay() {
        return this.pricePerDay;
    }

    public void setPricePerDay(float pricePerDay) {
        this.pricePerDay = pricePerDay;
    }



    public static void showAllCars() {
        try {
            if (Main.cars.size() > 0) {

                String leftAlignFormat = "| %-20s | %-7s | %-11s | %n";

                System.out.format("+----------------------+---------+-------------+%n");
                System.out.format("|           " + Colors.PURPLE_BOLD + "VIN" + Colors.RESET + "        | " + Colors.PURPLE_BOLD + "mileage" + Colors.RESET + " | " + Colors.PURPLE_BOLD + "pricePerDay" + Colors.RESET + " |%n");
                System.out.format("+----------------------+---------+-------------+%n");

                for (Car c : Main.cars) {
                    System.out.format(leftAlignFormat, c.getVin(), c.getMileage(), c.getPricePerDay());
                }
                System.out.format("+----------------------+---------+-------------+%n");
            } else {
                throw new NoElementsInSystemException();
            }
        }  catch (NoElementsInSystemException e) {
            System.out.printf(Colors.YELLOW_BOLD + "%s\n" + Colors.RESET, e);
        }
    }

    public static Car addNewCar() {
        Scanner scanner = new Scanner(System.in);
        Car car;
        System.out.println(Colors.CYAN_BOLD + "\nPodaj wartości dla atrybutów nowego samochodu.\n" + Colors.RESET);

        System.out.print("VIN: ");
        String vin = scanner.nextLine();

        System.out.print("mileage: ");
        int mileage = Integer.parseInt(scanner.nextLine());

        System.out.print("pricePerDay: ");
        int pricePerDay = Integer.parseInt(scanner.nextLine());

        car = new Car(vin, mileage, pricePerDay);
        Main.cars.add(car);

        System.out.println(Colors.GREEN_BOLD + "\nDodano nowy samochód.\n" + Colors.RESET);
        return car;
    }

    public static Car editCar(String vin) {
        Car car = null;

        try {
            if (Main.cars.size() > 0) {
                Scanner scanner = new Scanner(System.in);
                if (vin.equals("")) {
                    System.out.print(Colors.CYAN_BOLD + "\nPodaj numer VIN: " + Colors.RESET);
                    vin = scanner.nextLine();
                }

                for (Car c : Main.cars) {
                    if (vin.equals(c.getVin())) {
                        System.out.println(Colors.CYAN_BOLD + "\nPodaj nowe wartości dla atrybutów samochodu.\nW nawiasie podano aktualne wartości. Jesli chcesz je zostawić, wciśnij Enter" + Colors.RESET);

                        System.out.printf("VIN (%s): ", c.getVin());
                        String vinN = scanner.nextLine();
                        if (!vinN.equals("")) {
                            c.setVin(vinN);;
                        }

                        System.out.printf("mileage (%s): ", c.getMileage());
                        String mileage = scanner.nextLine();
                        if (!mileage.equals("")) {
                            c.setMileage(Integer.parseInt(mileage));
                        }

                        System.out.printf("pricePerDay (%s): ", c.getPricePerDay());
                        String pricePerDay = scanner.nextLine();
                        if (!pricePerDay.equals("")) {
                            c.setPricePerDay(Integer.parseInt(pricePerDay));
                        }

                        car = c;
                    }
                }
            } else {
                throw new NoElementsInSystemException();
            }
        }  catch (NoElementsInSystemException e) {
            System.out.printf(Colors.YELLOW_BOLD + "%s\n" + Colors.RESET, e);
        }
        if (car == null) {
            System.out.println(Colors.YELLOW_BOLD + "\nNie znaleziono samochodu o takim numerze VIN" + Colors.RESET);
        } else {
            System.out.println(Colors.GREEN_BOLD + "\nSamochód został zaktualizowany" + Colors.RESET);
        }
        return car;
    }

    public static void deleteCar() {
        try {
            if (Main.cars.size() > 0) {
                Scanner scanner = new Scanner(System.in);

                System.out.print(Colors.CYAN_BOLD + "\nPodaj numer VIN: " + Colors.RESET);
                String vin = scanner.nextLine();

                if (!Main.cars.removeIf(c -> vin.equals(c.getVin()))) {
                    System.out.println(Colors.YELLOW_BOLD + "Nie znaleziono samochodu o takim nurze VIN" + Colors.RESET);
                } else {
                    System.out.println(Colors.GREEN_BOLD + "\nSamochód został usunięty" + Colors.RESET);
                }
            } else {
                throw new NoElementsInSystemException();
            }
        }  catch (NoElementsInSystemException e) {
            System.out.printf(Colors.YELLOW_BOLD + "%s\n" + Colors.RESET, e);
        }
    }

    public static void showStatistics() {
        try {
            if (Main.cars.size() > 0) {

                System.out.printf(Colors.BLUE_BOLD + "\nLICZBA SAMOCHODÓWÓW: " + Colors.RESET + Colors.PURPLE +  "%s\n", Main.cars.size());

                HashMap<String, Integer> map = new HashMap<String, Integer>();

                for (Car c : Main.cars) {
                    if (!map.containsKey(c.getVin())) {
                        map.put(c.getVin(), 1);
                    } else {
                        map.put(c.getVin(), map.get(c.getVin())+1);
                    }
                }

                System.out.println(Colors.BLUE_BOLD + "\nNAJCZĘŚCIEJ WYPOŻYCZANE SAMOCHODY: " + Colors.RESET);
                map.forEach((key, value) -> System.out.println(Colors.PURPLE_BOLD + key + ": " + Colors.RESET + Colors.PURPLE + value + Colors.RESET));

            } else {
                throw new NoElementsInSystemException();
            }
        }  catch (NoElementsInSystemException e) {
            System.out.printf(Colors.YELLOW_BOLD + "%s\n" + Colors.RESET, e);
        }
    }
}