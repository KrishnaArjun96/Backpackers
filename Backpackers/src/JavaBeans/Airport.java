package JavaBeans;

/**
 * Created by Rahul on 11/11/17.
 */
public class Airport implements java.io.Serializable{
    String id;
    String name;
    String city;
    String country;
    boolean isVisible;

    public Airport() {
        this.isVisible = true;
    }

    public Airport(String id, String name, String city, String country) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.country = country;
        this.isVisible = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }
}
