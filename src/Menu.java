import java.io.IOException;

public interface Menu {
    static void show(String menuString) {
        try {
            Runtime.getRuntime().exec("clear");
        } catch (IOException e) {
            System.out.printf(Colors.YELLOW + "Problem z czyszczeniem konsoli. %s %n" + Colors.RESET, e);
        }
        System.out.println(menuString);
        System.out.print(Colors.CYAN_BOLD + "\nWybierz opcję: " + Colors.RESET);
    };}
