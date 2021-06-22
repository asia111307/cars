import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.util.*;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY, property = "type") @JsonSubTypes({
        @JsonSubTypes.Type(value = PrivatePerson.class, name = "private"),
        @JsonSubTypes.Type(value = Company.class, name = "company")
})
public class Client {
    @JsonProperty("name") private String name;
    @JsonProperty("address") private String address;
    @JsonProperty("telephone") private String telephone;
    @JsonProperty("discount") private int discount;

    public Client(@JsonProperty("name") String name, @JsonProperty("address") String address,
                  @JsonProperty("telephone") String telephone, @JsonProperty("discount") int discount) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.discount = discount;
    }

    public String toString() {
        return ""
                + "    name: " + this.name + "\n"
                + "    address: " + this.address + "\n"
                + "    telephone: " + this.telephone + "\n"
                + "    discount: " + this.discount;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getDiscount() {
        return this.discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }



    public static void showAllClients() {
        try {
            if (Main.clients.size() > 0) {

                String leftAlignFormat = "| %-25s | %-11s | %-44s | %-10s | %-8s | %-12s | %-12s | %n";

                System.out.format("+---------------------------+-------------+----------------------------------------------+------------+----------+--------------+--------------+%n");
                System.out.format("|            " + Colors.PURPLE_BOLD + "name" + Colors.RESET + "           |    " + Colors.PURPLE_BOLD + "type" + Colors.RESET + "     |                    " + Colors.PURPLE_BOLD + "address" + Colors.RESET + "                   | " + Colors.PURPLE_BOLD + "telephone" + Colors.RESET + "  | " + Colors.PURPLE_BOLD + "discount" + Colors.RESET + " |    " + Colors.PURPLE_BOLD + "PESEL" + Colors.RESET + "     |      " + Colors.PURPLE_BOLD + "NIP" + Colors.RESET + "     |%n");
                System.out.format("+---------------------------+-------------+----------------------------------------------+------------+----------|--------------+--------------+%n");

                for (Client c : Main.clients) {
                    String type = "";
                    String pesel = "";
                    String nip = "";

                    if(c instanceof PrivatePerson) {
                        type = "private";
                        pesel = ((PrivatePerson) c).getPesel();
                    } else if (c instanceof Company) {
                        type = "company";
                        nip = ((Company) c).getNip();
                    }

                    System.out.format(leftAlignFormat, c.getName(), type, c.getAddress(), c.getTelephone(), c.getDiscount(), pesel, nip);
                }
                System.out.format("+---------------------------+-------------+----------------------------------------------+------------+----------+--------------+--------------+%n");
            } else {
                throw new NoElementsInSystemException();
            }
        }  catch (NoElementsInSystemException e) {
            System.out.printf(Colors.YELLOW_BOLD + "%s\n" + Colors.RESET, e);
        }
    }

    public static Client addNewClient() {
        Scanner scanner = new Scanner(System.in);

        System.out.print(Colors.CYAN_BOLD + "\nPodaj typ klienta: P (prywatny) lub F (formowy): " + Colors.RESET);
        String type = scanner.nextLine();

        Client client = null;
        System.out.println(Colors.CYAN_BOLD + "\nPodaj wartości dla atrybutów nowego klienta.\n" + Colors.RESET);

        System.out.print("name: ");
        String name = scanner.nextLine();

        System.out.print("address: ");
        String address = scanner.nextLine();

        System.out.print("telephone: ");
        String telephone = scanner.nextLine();

        System.out.print("discount: ");
        int discount = Integer.parseInt(scanner.nextLine());

        if (type.toUpperCase(Locale.ROOT).equals("P")) {
            System.out.print("PESEL: ");
            String pesel = scanner.nextLine();
            client = new PrivatePerson(name, address, telephone, discount, pesel);
        } else if (type.toUpperCase(Locale.ROOT).equals("F")) {
            System.out.print("NIP: ");
            String nip = scanner.nextLine();
            client = new Company(name, address, telephone, discount, nip);
        }

        Main.clients.add(client);
        System.out.println(Colors.GREEN_BOLD + "\nDodano nowego klienta.\n" + Colors.RESET);

        return client;
    }

    public static Client editClient(String id) {
        Client client = null;

        try {
            if (Main.clients.size() > 0) {
                Scanner scanner = new Scanner(System.in);

                if (id.equals("")) {
                    System.out.print(Colors.CYAN_BOLD + "\nPodaj identyfikator klienta (PESEL lub NIP): " + Colors.RESET);
                    id = scanner.nextLine();
                }

                for (Client c : Main.clients) {
                    if (c instanceof PrivatePerson) {
                        if (id.equals(((PrivatePerson) c).getPesel())) {
                            System.out.println(Colors.CYAN_BOLD + "\nPodaj nowe wartości dla atrybutów klienta.\nW nawiasie podano aktualne wartości. Jesli chcesz je zostawić, wciśnij Enter" + Colors.RESET);

                            System.out.printf("name (%s): ", c.getName());
                            String name = scanner.nextLine();
                            if (!name.equals("")) {
                                c.setName(name);
                            }

                            System.out.printf("address (%s): ", c.getAddress());
                            String address = scanner.nextLine();
                            if (!address.equals("")) {
                                c.setAddress(address);
                            }

                            System.out.printf("telephone (%s): ", c.getTelephone());
                            String telephone = scanner.nextLine();
                            if (!telephone.equals("")) {
                                c.setTelephone(telephone);
                            }

                            System.out.printf("discount (%s): ", c.getDiscount());
                            String discount = scanner.nextLine();
                            if (!discount.equals("")) {
                                c.setDiscount(Integer.parseInt(discount));
                            }

                            System.out.printf("PESEL (%s): ", ((PrivatePerson) c).getPesel());
                            String pesel = scanner.nextLine();
                            if (!pesel.equals("")) {
                                ((PrivatePerson) c).setPesel(pesel);
                            }

                            client = c;
                        }
                    } else if (c instanceof Company) {
                        if (id.equals(((Company) c).getNip())) {
                            System.out.println(Colors.CYAN_BOLD + "\nPodaj nowe wartości dla atrybutów klienta.\nW nawiasie podano aktualne wartości. Jesli chcesz je zostawić, wciśnij Enter" + Colors.RESET);

                            System.out.printf("name (%s): ", c.getName());
                            String name = scanner.nextLine();
                            if (!name.equals("")) {
                                c.setName(name);
                            }

                            System.out.printf("address (%s): ", c.getAddress());
                            String address = scanner.nextLine();
                            if (!address.equals("")) {
                                c.setAddress(address);
                            }

                            System.out.printf("telephone (%s): ", c.getTelephone());
                            String telephone = scanner.nextLine();
                            if (!telephone.equals("")) {
                                c.setTelephone(telephone);
                            }

                            System.out.printf("discount (%s): ", c.getDiscount());
                            String discount = scanner.nextLine();
                            if (!discount.equals("")) {
                                c.setDiscount(Integer.parseInt(discount));
                            }

                            System.out.printf("NIP (%s): ", ((Company) c).getNip());
                            String nip = scanner.nextLine();
                            if (!nip.equals("")) {
                                ((Company) c).setNip(nip);;
                            }

                            client = c;
                        }
                    }
                }
            } else {
                throw new NoElementsInSystemException();
            }
        }  catch (NoElementsInSystemException e) {
            System.out.printf(Colors.YELLOW_BOLD + "%s\n" + Colors.RESET, e);
        }
        if (client == null) {
            System.out.println(Colors.YELLOW_BOLD + "\nNie znaleziono klienta o takim identyfikatorze" + Colors.RESET);
        } else {
            System.out.println(Colors.GREEN_BOLD + "\nKlient został zaktualizowany" + Colors.RESET);
        }
        return client;
    }

    public static void deleteClient() {
        try {
            if (Main.clients.size() > 0) {
                Scanner scanner = new Scanner(System.in);

                System.out.print(Colors.CYAN_BOLD + "\nPodaj identyfikator klienta (PESEL lub NIP): " + Colors.RESET);
                String id = scanner.nextLine();

                boolean found = false;

                for (Client c : Main.clients) {
                    if (c instanceof PrivatePerson) {
                        if (id.equals(((PrivatePerson) c).getPesel())) {
                            Main.clients.remove(c);
                            found = true;
                        }
                    } else if (c instanceof Company) {
                        if (id.equals(((Company) c).getNip())) {
                            Main.clients.remove(c);
                            found = true;
                        }
                    }
                }
                if (!found) {
                    System.out.println(Colors.YELLOW_BOLD + "\nNie znaleziono klienta o takim identyfikatorze" + Colors.RESET);
                } else {
                    System.out.println(Colors.GREEN_BOLD + "\nKlient został usunięty" + Colors.RESET);
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
            if (Main.clients.size() > 0) {
                HashMap<String, Integer> map = new HashMap<String, Integer>() {{
                    put("private", 0);
                    put("company", 0);
                }};

                HashMap<String, Integer> idsMap = new HashMap<String, Integer>();

                for (Client c : Main.clients) {
                    if (c instanceof PrivatePerson) {
                        map.put("private", map.get("private") + 1);
                        if (!idsMap.containsKey(((PrivatePerson) c).getPesel())) {
                            idsMap.put(((PrivatePerson) c).getPesel(), 1);
                        } else {
                            idsMap.put(((PrivatePerson) c).getPesel(), idsMap.get(((PrivatePerson) c).getPesel())+1);
                        }
                    } else if (c instanceof Company) {
                        map.put("company", map.get("company") + 1);
                        if (!idsMap.containsKey(((Company) c).getNip())) {
                            idsMap.put(((Company) c).getNip(), 1);
                        } else {
                            idsMap.put(((Company) c).getNip(), idsMap.get(((Company) c).getNip())+1);
                        }
                    }
                }

                System.out.println(Colors.BLUE_BOLD + "\nPODSUMOWANIE LICZBY KLIENTÓW: " + Colors.RESET);
                map.forEach((key, value) -> System.out.println(Colors.PURPLE_BOLD + key + ": " + Colors.RESET + Colors.PURPLE + value + Colors.RESET));

                System.out.println(Colors.BLUE_BOLD + "\nKLIENCI WG LICZBY ZAWARTYCH UMÓW: " + Colors.RESET);
                idsMap.forEach((key, value) -> System.out.println(Colors.PURPLE_BOLD + key + ": " + Colors.RESET + Colors.PURPLE + value + Colors.RESET));

            } else {
                throw new NoElementsInSystemException();
            }
        }  catch (NoElementsInSystemException e) {
            System.out.printf(Colors.YELLOW_BOLD + "%s\n" + Colors.RESET, e);
        }
    }
}

