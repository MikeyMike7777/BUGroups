package database.student;

import java.util.Objects;

public class Profile {
    private String name;

    private String id;
    private String email;
    private Availability availability;
    private String phoneNumber; // is this the best way to represent?

    public void setEmail(String email){
        this.email = email;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Availability getAvailability() {
        return availability;
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Profile(String id, String name, String email,String phoneNumber, Availability availability) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.availability = availability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(name, profile.name) && Objects.equals(id, profile.id) && Objects.equals(email, profile.email) && Objects.equals(availability, profile.availability) && Objects.equals(phoneNumber, profile.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, email, availability, phoneNumber);
    }
}
