import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import java.util.Scanner;

@JsonTypeName("reservation")
public class Reservation extends Agreement {
    @JsonProperty("advance") private float advance;
    @JsonProperty("dateOfAdvancePayment") private String dateOfAdvancePayment;

    public Reservation(@JsonProperty("agreementId") String agreementId, @JsonProperty("client") Client client,
                       @JsonProperty("dateOfRent") String dateOfRent, @JsonProperty("dateOfConclusion") String dateOfConclusion,
                       @JsonProperty("car") Car car, @JsonProperty("numberOfDays") int numberOfDays,
                       @JsonProperty("discount") int discount, @JsonProperty("receptionVenue") String receptionVenue,
                       @JsonProperty("finalPrice") float finalPrice, @JsonProperty("advance") float advance,
                       @JsonProperty("dateOfAdvancePayment") String dateOfAdvancePayment) {
        super(agreementId, client, dateOfRent, dateOfConclusion, car, numberOfDays, discount, receptionVenue, finalPrice);
        this.advance = advance;
        this.dateOfAdvancePayment = dateOfAdvancePayment;
    }

    public String toString() {
        return ""
                + "    agreementId: " + this.getAgreementId() + "\n"
                + "    client: " + this.getClient() + "\n"
                + "    dateOfRent: " + this.getDateOfRent() + "\n"
                + "    dateOfConclusion: " + this.getDateOfConclusion() + "\n"
                + "    car: " + this.getCar() + "\n"
                + "    numberOfDays: " + this.getNumberOfDays() + "\n"
                + "    discount: " + this.getDiscount() + "\n"
                + "    receptionVenue: " + this.getReceptionVenue() + "\n"
                + "    finalPrice: " + this.getFinalPrice() + "\n"
                + "    advance: " + this.advance + "\n"
                + "    dateOfAdvancePayment: " + this.dateOfAdvancePayment;
    }

    public float getAdvance() {
        return this.advance;
    }

    public void setAdvance(float advance) {
        this.advance = advance;
    }

    public String getDateOfAdvancePayment() {
        return this.dateOfAdvancePayment;
    }

    public void setDateOfAdvancePayment(String dateOfAdvancePayment) {
        this.dateOfAdvancePayment = dateOfAdvancePayment;
    }



    public void convertToFullAgreement() {
        Scanner scanner = new Scanner(System.in);
        FullAgreement agreement = null;
        System.out.println(Colors.CYAN_BOLD + "\nPodaj wartości dla atrybutów pełnej umowy.\n" + Colors.RESET);

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

        String status = "signed";

        agreement = new FullAgreement(this.getAgreementId(), this.getClient(), this.getDateOfRent(), this.getDateOfConclusion(),
                this.getCar(), this.getNumberOfDays(), this.getDiscount(), this.getReceptionVenue(), this.getFinalPrice(),
                dateOfReturn, mileageBefore, mileageAfter, fuelBefore, fuelAfter, status);
        Main.agreements.add(agreement);
        Main.agreements.removeIf(a -> this.getAgreementId().equals(a.getAgreementId()) && (a instanceof Reservation));
    }
}
