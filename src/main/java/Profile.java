import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Profile {
    private String name;
    private String email;
    private String description;
    private String phoneNumber;


    /*
     * name: formatPhoneNumber
     * parameters: input phone number (numbers only)
     * return: formatted phone number if valid number; null if not valid number
     * description: verifies and changes phone number from numbers to output format
     */
    public String formatPhoneNumber(String input) {
        if(verfiyPhoneNumber(input)) {
            return input.replaceFirst("(\\d{1,3})(\\d{3})(\\d{3})(\\d{4})", "+$1 ($2) $3-$4");
        } else {return null;}
    }
    /*
     * name: verifyPhoneNumber
     * parameters: input phone number (numbers only)
     * return: true if phone number is valid
     * description: makes sure a phone number is valid
     */
    public boolean verfiyPhoneNumber(String input){
        Pattern pattern = Pattern.compile("(\\d{1,3})(\\d{3})(\\d{3})(\\d{4})");
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
