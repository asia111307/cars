public class NoElementsInSystemException extends Exception {
    public NoElementsInSystemException() {
        System.out.println(Colors.YELLOW_BOLD + "\nBrak elementów w systemie" + Colors.RESET);
    }
}
