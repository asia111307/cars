import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

public class Main {

    public static ArrayList<Agreement> agreements = new ArrayList<Agreement>();
    public static ArrayList<Client> clients = new ArrayList<Client>();
    public static ArrayList<Car> cars = new ArrayList<Car>();

    public static void main(String[] args) {
        Menu.show(MainMenu.menuString);
        MainMenu.handleOptions();
    }

    public static void createObjectsFromJSON(String json) throws IOException {

        Thread thread = new Thread(() -> {
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            JsonNode jsonAgreements;
            try {
                jsonAgreements = mapper.readTree(json).path("agreements");
                ArrayList<Agreement> newAgreements = null;
                newAgreements = mapper.readerFor(new TypeReference<ArrayList<Agreement>>(){}).readValue(jsonAgreements);

                for (Agreement a : newAgreements) {
                    agreements.add(a);
                    clients.add(a.getClient());
                    cars.add(a.getCar());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        thread.start();
    }

    public static void readDataFromFile() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print(Colors.CYAN_BOLD + "Nazwa pliku " + Colors.RESET + Colors.CYAN + "(data.json): " + Colors.RESET);
            String filename = scanner.nextLine();
            if (filename.equals("")) {
                filename = "data.json";
            }
            if (!filename.endsWith(".json")) {
                throw new WrongFileExtensionException(Colors.CYAN_BOLD + "Niepoprawne rozszerzenie pliku" + Colors.RESET);
            }
            File file = new File(filename);
            Scanner reader = new Scanner(file);
            String data = reader.useDelimiter("\\A").next();
            reader.close();
            Main.createObjectsFromJSON(data);
            System.out.printf (Colors.GREEN_BOLD + "Odczytano dane z pliku %s\n" + Colors.RESET, filename);
        } catch (WrongFileExtensionException | IOException e) {
            System.out.printf(Colors.RED_BOLD + "Problem z plikiem, nie można wczytać danych. %s %n" + Colors.RESET, e);
        }
    }

    public static void saveDataToFile() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print(Colors.CYAN_BOLD + "Nazwa pliku " + Colors.RESET + Colors.CYAN + "(output.json): " + Colors.RESET);
            String filename = scanner.nextLine();
            if (filename.equals("")) {
                filename = "output.json";
            }
            if (!filename.endsWith(".json")) {
                throw new WrongFileExtensionException(Colors.YELLOW_BOLD + "Niepoprawne rozszerzenie pliku" + Colors.RESET);
            }

            ObjectWriter jsonWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = jsonWriter.writeValueAsString(Main.agreements);

            FileWriter writer = new FileWriter(filename);
            writer.write(json);
            writer.close();
            System.out.printf (Colors.GREEN_BOLD + "Zapisano dane do pliku %s\n" + Colors.RESET, filename);
        } catch (WrongFileExtensionException | IOException e) {
            System.out.printf(Colors.RED_BOLD + "Problem z plikiem, nie można wczytać danych. %s %n" + Colors.RESET, e);
        }
    }

    public static void showStatistics() {
        Agreement.showStatistics();
        Client.showStatistics();
        Car.showStatistics();
    }
}