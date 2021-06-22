import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("private")
public class PrivatePerson extends Client {
    @JsonProperty("pesel") private String pesel;

    public PrivatePerson(@JsonProperty("name") String name, @JsonProperty("address") String address,
                         @JsonProperty("telephone") String telephone, @JsonProperty("discount") int discount,
                         @JsonProperty("pesel") String pesel) {
        super(name, address, telephone, discount);
        this.pesel = pesel;
    }

    public String toString() {
        return ""
                + "    name: " + this.getName() + "\n"
                + "    address: " + this.getAddress() + "\n"
                + "    telephone: " + this.getTelephone() + "\n"
                + "    discount: " + this.getDiscount() + "\n"
                + "    pesel: " + this.pesel;
    }

    public String getPesel() {
        return this.pesel;
    }

    public void setPesel(String newPesel) {
        this.pesel = newPesel;
    }
}