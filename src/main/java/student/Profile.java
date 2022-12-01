package student;

import java.util.Vector;

public class Profile {
    private String name;
    private String email;
    private String description;
    private Settings settings;
    private Vector<Availability> availability;
    private Integer phoneNumber; // is this the best way to represent?

    public void setEmail(String email){
        this.email = email;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPhoneNumber(Integer phoneNumber){
        this.phoneNumber = phoneNumber;
    }
}
