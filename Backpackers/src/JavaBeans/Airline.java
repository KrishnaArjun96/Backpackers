package JavaBeans;

/**
 * Created by Rahul on 11/11/17.
 */
public class Airline implements java.io.Serializable {
    String id;
    String name;

    public Airline(String id, String name) {
        this.id = id;
        this.name = name;
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
}
