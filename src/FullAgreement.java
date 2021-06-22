import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("full")
public class FullAgreement extends Agreement {
    @JsonProperty("dateOfReturn") private String dateOfReturn;
    @JsonProperty("mileageBefore") private int mileageBefore;
    @JsonProperty("mileageAfter") private int mileageAfter;
    @JsonProperty("fuelBefore") private int fuelBefore;
    @JsonProperty("fuelAfter") private int fuelAfter;
    @JsonProperty("status") private String status;

    public FullAgreement(@JsonProperty("agreementId") String agreementId, @JsonProperty("client") Client client,
                         @JsonProperty("dateOfRent") String dateOfRent, @JsonProperty("dateOfConclusion") String dateOfConclusion,
                         @JsonProperty("car") Car car, @JsonProperty("numberOfDays") int numberOfDays,
                         @JsonProperty("discount") int discount, @JsonProperty("receptionVenue") String receptionVenue,
                         @JsonProperty("finalPrice") float finalPrice, @JsonProperty("dateOfReturn") String dateOfReturn,
                         @JsonProperty("mileageBefore") int mileageBefore, @JsonProperty("mileageAfter") int mileageAfter,
                         @JsonProperty("fuelBefore") int fuelBefore, @JsonProperty("fuelAfter") int fuelAfter,
                         @JsonProperty("status") String status) {
        super(agreementId, client, dateOfRent, dateOfConclusion, car, numberOfDays, discount, receptionVenue, finalPrice);
        this.dateOfReturn = dateOfReturn;
        this.mileageBefore = mileageBefore;
        this.mileageAfter = mileageAfter;
        this.fuelBefore = fuelBefore;
        this.fuelAfter = fuelAfter;
        this.status = status;
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
                + "    dateOfReturn: " + this.dateOfReturn + "\n"
                + "    mileageBefore: " + this.mileageBefore + "\n"
                + "    mileageAfter: " + this.mileageAfter + "\n"
                + "    fuelBefore: " + this.fuelBefore + "\n"
                + "    fuelAfter: " + this.fuelAfter + "\n"
                + "    status: " + this.status;
    }

    public String getDateOfReturn() {
        return this.dateOfReturn;
    }

    public void setDateOfReturn(String dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public int getMileageBefore() {
        return this.mileageBefore;
    }

    public void setMileageBefore(int mileageBefore) {
        this.mileageBefore = mileageBefore;
    }

    public int getMileageAfter() {
        return this.mileageAfter;
    }

    public void setMileageAfter(int mileageAfter) {
        this.mileageAfter = mileageAfter;
    }

    public int getFuelBefore() {
        return this.fuelBefore;
    }

    public void setFuelBefore(int fuelBefore) {
        this.fuelBefore = fuelBefore;
    }

    public int getFuelAfter() {
        return this.fuelAfter;
    }

    public void setFuelAfter(int fuelAfter) {
        this.fuelAfter = fuelAfter;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
