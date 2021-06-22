import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY, property = "type") @JsonSubTypes({
        @JsonSubTypes.Type(value = Reservation.class, name = "reservation"),
        @JsonSubTypes.Type(value = FullAgreement.class, name = "full")
})
public class Agreement {
    @JsonProperty("agreementId") private String agreementId;
    @JsonProperty("client") private Client client;
    @JsonProperty("dateOfConclusion") private String dateOfConclusion;
    @JsonProperty("dateOfRent") private String dateOfRent;
    @JsonProperty("car") private Car car;
    @JsonProperty("numberOfDays") private int numberOfDays;
    @JsonProperty("discount") private int discount;
    @JsonProperty("receptionVenue") private String receptionVenue;
    @JsonProperty("finalPrice") private float finalPrice;

    public Agreement(@JsonProperty("agreementId") String agreementId, @JsonProperty("client") Client client,
                     @JsonProperty("dateOfRent") String dateOfRent, @JsonProperty("dateOfConclusion") String dateOfConclusion,
                     @JsonProperty("car") Car car, @JsonProperty("numberOfDays") int numberOfDays, @JsonProperty("discount") int discount,
                     @JsonProperty("receptionVenue") String receptionVenue, @JsonProperty("finalPrice") float finalPrice) {
        this.agreementId = agreementId;
        this.client = client;
        this.dateOfRent = dateOfRent;
        this.dateOfConclusion = dateOfConclusion;
        this.car = car;
        this.numberOfDays = numberOfDays;
        this.discount = discount;
        this.receptionVenue = receptionVenue;
        this.finalPrice = finalPrice;
    }

    public String toString() {
        return ""
                + " agreementId: " + this.agreementId + "\n"
                + " client: \n  " + this.client + "\n"
                + " dateOfRent: " + this.dateOfRent + "\n"
                + " dateOfConclusion: " + this.dateOfConclusion + "\n"
                + " car: \n  " + this.car + "\n"
                + " numberOfDays: " + this.numberOfDays + "\n"
                + " discount: " + this.discount + "\n"
                + " receptionVenue: " + this.receptionVenue + "\n"
                + " finalPrice: " + this.finalPrice;
    }

    public void setAgreementId(String agreementId) {
        this.agreementId = agreementId;
    }

    public String getAgreementId() {
        return this.agreementId;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return this.client;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Car getCar() {
        return this.car;
    }

    public String getDateOfRent() {
        return this.dateOfRent;
    }

    public void setDateOfRent(String dateOfRent) {
        this.dateOfRent = dateOfRent;
    }

    public void setDateOfConclusion(String dateOfConclusion) {
        this.dateOfConclusion = dateOfConclusion;
    }

    public String getDateOfConclusion() {
        return this.dateOfConclusion;
    }

    public void setNumberOfDays(int numberOfDays) {
        this.numberOfDays = numberOfDays;
    }

    public int getNumberOfDays() { return this.numberOfDays; }

    public int getDiscount() {
        return this.discount;
    }

    public void setDiscount(int discount) { this.discount = discount; }

    public String getReceptionVenue() {
        return this.receptionVenue;
    }

    public void setReceptionVenue(String receptionVenue) { this.receptionVenue = receptionVenue; }

    public float getFinalPrice() {
        return this.finalPrice;
    }

    public void setFinalPrice(float finalPrice) {
        this.finalPrice = finalPrice;
    }



    public static void showAllAgreements() {
        try {
            if (Main.agreements.size() > 0) {

                String leftAlignFormat = "| %-15s | %-11s | %-11s | %-25s | %-16s | %-10s | %-20s | %-12s | %-8s | %-30s | %-10s | %-7s | %n";

                System.out.format("+-----------------+-------------+-------------+---------------------------+------------------+------------+----------------------+--------------+----------+--------------------------------+------------+---------+%n");
                System.out.format("|   " + Colors.PURPLE_BOLD + "agreementId" + Colors.RESET + "   |    " + Colors.PURPLE_BOLD + "type" + Colors.RESET + "     |   " + Colors.PURPLE_BOLD + "status" + Colors.RESET + "    |           " + Colors.PURPLE_BOLD + "client" + Colors.RESET + "          | " + Colors.PURPLE_BOLD + "dateOfConclusion" + Colors.RESET + " | " + Colors.PURPLE_BOLD + "dateOfRent" + Colors.RESET + " |          " + Colors.PURPLE_BOLD + "VIN" + Colors.RESET + "         | " + Colors.PURPLE_BOLD + "numberOfDays" + Colors.RESET + " | " + Colors.PURPLE_BOLD + "discount" + Colors.RESET + " |         " + Colors.PURPLE_BOLD + "receptionVenue" + Colors.RESET + "         | " + Colors.PURPLE_BOLD + "finalPrice" + Colors.RESET + " | " + Colors.PURPLE_BOLD + "advance" + Colors.RESET + " |%n");
                System.out.format("+-----------------+-------------+-------------+---------------------------+------------------|------------+----------------------+--------------+----------+--------------------------------+------------+---------+%n");
                for (Agreement a : Main.agreements) {
                    String type = "";
                    String advance = "";
                    String status = "";

                    if(a instanceof Reservation) {
                        type = "reservation";
                        advance = Float.toString(((Reservation) a).getAdvance());
                    } else if (a instanceof FullAgreement) {
                        type = "full";
                        status = ((FullAgreement) a).getStatus();
                    }

                    System.out.format(leftAlignFormat, a.getAgreementId(), type, status, a.getClient().getName(),
                            a.getDateOfConclusion(), a.getDateOfRent(), a.getCar().getVin(), a.getNumberOfDays(), a.getDiscount(),
                            a.getReceptionVenue(), a.getFinalPrice(), advance);
                }
                System.out.format("+-----------------+-------------+-------------+---------------------------+------------------+------------+----------------------+--------------+----------+--------------------------------+------------+---------+%n");
            } else {
                throw new NoElementsInSystemException();
            }
        }  catch (NoElementsInSystemException e) {
            System.out.printf(Colors.YELLOW_BOLD + "%s\n" + Colors.RESET, e);
        }
    }

    public static void addNewAgreement() {
        Scanner scanner = new Scanner(System.in);

        System.out.print(Colors.CYAN_BOLD + "\nPodaj typ umowy: R (rezerwacja) lub P (pełna): " + Colors.RESET);
        String type = scanner.nextLine();

        Agreement agreement = null;

        System.out.println(Colors.CYAN_BOLD + "\nPodaj wartości dla atrybutów nowej umowy.\n" + Colors.RESET);

        System.out.print("agreementId: ");
        String agreementId = scanner.nextLine();

        System.out.print("dateOfConclusion: ");
        String dateOfConclusion = scanner.nextLine();

        System.out.print("dateOfRent: ");
        String dateOfRent = scanner.nextLine();

        System.out.print("numberOfDays: ");
        int numberOfDays = Integer.parseInt(scanner.nextLine());

        System.out.print("discount: ");
        int discount = Integer.parseInt(scanner.nextLine());

        System.out.print("receptionVenue: ");
        String receptionVenue = scanner.nextLine();

        System.out.print("finalPrice: ");
        int finalPrice = Integer.parseInt(scanner.nextLine());

        if (type.toUpperCase(Locale.ROOT).equals("R")) {
            System.out.print("\nadvance: ");
            int advance = Integer.parseInt(scanner.nextLine());

            System.out.print("dateOfAdvancePayment: ");
            String dateOfAdvancePayment = scanner.nextLine();

            Client client = Client.addNewClient();

            Car car = Car.addNewCar();

            agreement = new Reservation(agreementId, client, dateOfRent, dateOfConclusion, car, numberOfDays, discount, receptionVenue, finalPrice, advance, dateOfAdvancePayment);
        } else if (type.toUpperCase(Locale.ROOT).equals("P")) {
            System.out.print("dateOfReturn: ");
            String dateOfReturn = scanner.nextLine();

            System.out.print("mileageBefore: ");
            int mileageBefore = Integer.parseInt(scanner.nextLine());

            System.out.print("mileageAfter: ");
            int mileageAfter = Integer.parseInt(scanner.nextLine());

            System.out.print("fuelBefore: ");
            int fuelBefore = Integer.parseInt(scanner.nextLine());

            System.out.print("fuelAfter: ");
            int fuelAfter = Integer.parseInt(scanner.nextLine());

            System.out.print("status: ");
            String status = scanner.nextLine();

            Client client = Client.addNewClient();

            Car car = Car.addNewCar();

            agreement = new FullAgreement(agreementId, client, dateOfRent, dateOfConclusion, car, numberOfDays, discount, receptionVenue, finalPrice, dateOfReturn, mileageBefore, mileageAfter, fuelBefore, fuelAfter, status);
        }

        Main.agreements.add(agreement);
        System.out.println(Colors.GREEN_BOLD + "\nDodano nową umowę.\n" + Colors.RESET);
    }

    public static void editAgreement() {
        Agreement agreement = null;

        try {
            if (Main.agreements.size() > 0) {
                Scanner scanner = new Scanner(System.in);
                System.out.print(Colors.CYAN_BOLD + "\nPodaj numer umowy (agreementId): " + Colors.RESET);
                String agreementId = scanner.nextLine();

                for (Agreement a : Main.agreements) {
                    if (a instanceof Reservation) {
                        if (agreementId.equals(((Reservation) a).getAgreementId())) {
                            System.out.println(Colors.CYAN_BOLD + "\nPodaj nowe wartości dla atrybutów umowy.\nW nawiasie podano aktualne wartości. Jesli chcesz je zostawić, wciśnij Enter" + Colors.RESET);

                            System.out.printf("agreementId (%s): ", a.getAgreementId());
                            String agreementid = scanner.nextLine();
                            if (!agreementid.equals("")) {
                                a.setAgreementId(agreementid);
                            }

                            System.out.printf("dateOfConclusion (%s): ", a.getDateOfConclusion());
                            String dateOfConclusion = scanner.nextLine();
                            if (!dateOfConclusion.equals("")) {
                                a.setDateOfConclusion(dateOfConclusion);
                            }

                            System.out.printf("dateOfRent (%s): ", a.getDateOfRent());
                            String dateOfRent = scanner.nextLine();
                            if (!dateOfRent.equals("")) {
                                a.setDateOfRent(dateOfRent);
                            }

                            System.out.printf("numberOfDays (%s): ", a.getNumberOfDays());
                            String numberOfDays = scanner.nextLine();
                            if (!numberOfDays.equals("")) {
                                a.setNumberOfDays(Integer.parseInt(numberOfDays));
                            }

                            System.out.printf("discount (%s): ", a.getDiscount());
                            String discount = scanner.nextLine();
                            if (!discount.equals("")) {
                                a.setDiscount(Integer.parseInt(discount));
                            }

                            System.out.printf("receptionVenue (%s): ", a.getReceptionVenue());
                            String receptionVenue = scanner.nextLine();
                            if (!receptionVenue.equals("")) {
                                a.setReceptionVenue(receptionVenue);
                            }

                            System.out.printf("finalPrice (%s): ", a.getFinalPrice());
                            String finalPrice = scanner.nextLine();
                            if (!finalPrice.equals("")) {
                                a.setFinalPrice(Integer.parseInt(finalPrice));
                            }

                            System.out.printf("advance (%s): ", ((Reservation) a).getAdvance());
                            String advance = scanner.nextLine();
                            if (!advance.equals("")) {
                                ((Reservation) a).setAdvance(Integer.parseInt(advance));
                            }

                            System.out.printf("dateOfAdvancePayment (%s): ", ((Reservation) a).getDateOfAdvancePayment());
                            String dateOfAdvancePayment = scanner.nextLine();
                            if (!dateOfAdvancePayment.equals("")) {
                                ((Reservation) a).setDateOfAdvancePayment(dateOfAdvancePayment);
                            }

                            String id = "";
                            if (a.getClient() instanceof PrivatePerson) {
                                id = ((PrivatePerson) a.getClient()).getPesel();
                            } else if (a.getClient() instanceof Company) {
                                id = ((Company) a.getClient()).getNip();
                            }

                            a.setClient(Client.editClient(id));

                            a.setCar(Car.editCar(a.getCar().getVin()));

                            agreement = a;
                        }
                    } else if (a instanceof FullAgreement) {
                        if (agreementId.equals(((FullAgreement) a).getAgreementId())) {
                            System.out.println(Colors.CYAN_BOLD + "\nPodaj nowe wartości dla atrybutów umowy.\nW nawiasie podano aktualne wartości. Jesli chcesz je zostawić, wciśnij Enter" + Colors.RESET);

                            System.out.printf("agreementId (%s): ", a.getAgreementId());
                            String agreementid = scanner.nextLine();
                            if (!agreementid.equals("")) {
                                a.setAgreementId(agreementid);
                            }

                            System.out.printf("dateOfConclusion (%s): ", a.getDateOfConclusion());
                            String dateOfConclusion = scanner.nextLine();
                            if (!dateOfConclusion.equals("")) {
                                a.setDateOfConclusion(dateOfConclusion);
                            }

                            System.out.printf("dateOfRent (%s): ", a.getDateOfRent());
                            String dateOfRent = scanner.nextLine();
                            if (!dateOfRent.equals("")) {
                                a.setDateOfRent(dateOfRent);
                            }

                            System.out.printf("numberOfDays (%s): ", a.getNumberOfDays());
                            String numberOfDays = scanner.nextLine();
                            if (!numberOfDays.equals("")) {
                                a.setNumberOfDays(Integer.parseInt(numberOfDays));
                            }

                            System.out.printf("discount (%s): ", a.getDiscount());
                            String discount = scanner.nextLine();
                            if (!discount.equals("")) {
                                a.setDiscount(Integer.parseInt(discount));
                            }

                            System.out.printf("receptionVenue (%s): ", a.getReceptionVenue());
                            String receptionVenue = scanner.nextLine();
                            if (!receptionVenue.equals("")) {
                                a.setReceptionVenue(receptionVenue);
                            }

                            System.out.printf("finalPrice (%s): ", a.getFinalPrice());
                            String finalPrice = scanner.nextLine();
                            if (!finalPrice.equals("")) {
                                a.setFinalPrice(Integer.parseInt(finalPrice));
                            }

                            System.out.printf("dateOfReturn (%s): ", ((FullAgreement) a).getDateOfReturn());
                            String dateOfReturn = scanner.nextLine();
                            if (!dateOfReturn.equals("")) {
                                ((FullAgreement) a).setDateOfReturn(dateOfReturn);
                            }

                            System.out.printf("mileageBefore (%s): ", ((FullAgreement) a).getMileageBefore());
                            String mileageBefore = scanner.nextLine();
                            if (!mileageBefore.equals("")) {
                                ((FullAgreement) a).setMileageBefore(Integer.parseInt(mileageBefore));
                            }

                            System.out.printf("mileageAfter (%s): ", ((FullAgreement) a).getMileageAfter());
                            String mileageAfter = scanner.nextLine();
                            if (!mileageAfter.equals("")) {
                                ((FullAgreement) a).setMileageAfter(Integer.parseInt(mileageAfter));
                            }

                            System.out.printf("fuelBefore (%s): ", ((FullAgreement) a).getFuelBefore());
                            String fuelBefore = scanner.nextLine();
                            if (!fuelBefore.equals("")) {
                                ((FullAgreement) a).setFuelBefore(Integer.parseInt(fuelBefore));
                            }

                            System.out.printf("fuelAfter (%s): ", ((FullAgreement) a).getFuelAfter());
                            String fuelAfter = scanner.nextLine();
                            if (!fuelAfter.equals("")) {
                                ((FullAgreement) a).setFuelAfter(Integer.parseInt(fuelAfter));
                            }

                            System.out.printf("status (%s): ", ((FullAgreement) a).getStatus());
                            String status = scanner.nextLine();
                            if (!status.equals("")) {
                                ((FullAgreement) a).setStatus(status);
                            }

                            String id = "";
                            if (a.getClient() instanceof PrivatePerson) {
                                id = ((PrivatePerson) a.getClient()).getPesel();
                            } else if (a.getClient() instanceof Company) {
                                id = ((Company) a.getClient()).getNip();
                            }
                            a.setClient(Client.editClient(id));

                            a.setCar(Car.editCar(a.getCar().getVin()));

                            agreement = a;
                        }
                    }
                }
            } else {
                throw new NoElementsInSystemException();
            }
        }  catch (NoElementsInSystemException e) {
            System.out.printf(Colors.YELLOW_BOLD + "%s\n" + Colors.RESET, e);
        }
        if (agreement == null) {
            System.out.println(Colors.YELLOW_BOLD + "\nNie znaleziono umowy o takim identyfikatorze" + Colors.RESET);
        } else {
            System.out.println(Colors.GREEN_BOLD + "\nUmowa została zaktualizowana" + Colors.RESET);
        }
    }

    public static void deleteAgreement(){
        try {
            if (Main.agreements.size() > 0) {
                Scanner scanner = new Scanner(System.in);

                System.out.print(Colors.CYAN_BOLD + "\nPodaj numer umowy (agreementId): " + Colors.RESET);
                String agreementId = scanner.nextLine();

                if (!Main.agreements.removeIf(a -> agreementId.equals(a.getAgreementId()))) {
                    System.out.println(Colors.YELLOW_BOLD + "Nie znaleziono umowy o takim identyfikatorze" + Colors.RESET);
                } else {
                    System.out.println(Colors.GREEN_BOLD + "\nUmowa została usunięta" + Colors.RESET);
                }
            } else {
                throw new NoElementsInSystemException();
            }
        } catch (NoElementsInSystemException e) {
            System.out.printf(Colors.YELLOW_BOLD + "%s\n" + Colors.RESET, e);
        }
    }

    public static void convertAgreement() {
        Agreement agreement = null;

        try {
            if (Main.agreements.size() > 0) {
                Scanner scanner = new Scanner(System.in);

                System.out.print(Colors.CYAN_BOLD + "\nPodaj numer umowy (agreementId): " + Colors.RESET);
                String agreementId = scanner.nextLine();

                for (Agreement a : Main.agreements) {
                    if (agreementId.equals(a.getAgreementId())) {
                        if (a instanceof Reservation) {
                            agreement = a;
                        } else {
                            System.out.print(Colors.YELLOW_BOLD + "Ta umowa już jest pełną umową\n" + Colors.RESET);
                        }
                    }
                }
            } else {
                throw new NoElementsInSystemException();
            }
        }  catch (NoElementsInSystemException e) {
            System.out.printf(Colors.YELLOW_BOLD + "%s\n" + Colors.RESET, e);
        }

        if (agreement == null) {
            System.out.println(Colors.YELLOW_BOLD + "\nNie znaleziono umowy o takim identyfikatorze" + Colors.RESET);
        } else {
            ((Reservation) agreement).convertToFullAgreement();
            System.out.println(Colors.GREEN_BOLD + "\nPrzekonwertowano umowę" + Colors.RESET);
        }
    }

    public static void showStatistics() {
        try {
            if (Main.agreements.size() > 0) {
                HashMap<String, Integer> map = new HashMap<String, Integer>() {{
                    put("reservations", 0);
                    put("full (all)", 0);
                    put("full (signed)", 0);
                    put("full (in progress)", 0);
                    put("full (finished)", 0);
                }};

                for (Agreement a : Main.agreements) {
                    if (a instanceof Reservation) {
                        map.put("reservations", map.get("reservations") + 1);
                    } else if (a instanceof FullAgreement) {
                        map.put("full (all)", map.get("full (all)") + 1);
                        switch (((FullAgreement) a).getStatus()) {
                            case "sined": {
                                map.put("full (signed)", map.get("full (signed)") + 1);
                            }
                            case "in progress": {
                                map.put("full (in progress)", map.get("full (in progress)") + 1);
                            }
                            case "finished": {
                                map.put("full (finished)", map.get("full (finished)") + 1);
                            }
                        }
                    }
                }

                System.out.println(Colors.BLUE_BOLD + "\nPODSUMOWANIE LICZBY UMÓW: " + Colors.RESET);
                map.forEach((key, value) -> System.out.println(Colors.PURPLE_BOLD + key + ": " + Colors.RESET + Colors.PURPLE + value + Colors.RESET));

            } else {
                throw new NoElementsInSystemException();
            }
        }  catch (NoElementsInSystemException e) {
            System.out.printf(Colors.YELLOW_BOLD + "%s\n" + Colors.RESET, e);
        }
    }
}
