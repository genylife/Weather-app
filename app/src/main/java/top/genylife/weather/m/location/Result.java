package top.genylife.weather.m.location;

/**
 * Created by wanqi on 2016/12/27.
 *
 * @since 1.0.0
 */
public class Result {

    private Location location;
    private String formatted_address;
    private String business;
    private AddressComponent addressComponent;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public void setFormatted_address(String formatted_address) {
        this.formatted_address = formatted_address;
    }

    public String getBusiness() {
        return business;
    }

    public void setBusiness(String business) {
        this.business = business;
    }

    public AddressComponent getAddressComponent() {
        return addressComponent;
    }

    public void setAddressComponent(AddressComponent addressComponent) {
        this.addressComponent = addressComponent;
    }
}
