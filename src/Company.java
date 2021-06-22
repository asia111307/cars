import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("company")
public class Company extends Client {
    @JsonProperty("nip") private String nip;

    public Company(@JsonProperty("name") String name, @JsonProperty("address") String address,
                   @JsonProperty("telephone") String telephone, @JsonProperty("discount") int discount,
                   @JsonProperty("nip") String nip) {
        super(name, address, telephone, discount);
        this.nip = nip;
    }

    public String toString() {
        return ""
                + "    name: " + this.getName() + "\n"
                + "    address: " + this.getAddress() + "\n"
                + "    telephone: " + this.getTelephone() + "\n"
                + "    discount: " + this.getDiscount() + "\n"
                + "    nip: " + this.nip;
    }

    public String getNip() {
        return this.nip;
    }

    public void setNip(String nip) {
        this.nip = nip;
    }
}
