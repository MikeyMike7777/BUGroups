public class Profile {
    private String name;
    private String email;
    private String description;
    private String phoneNumber;


    /*
     * name: formatPhoneNumber
     * parameters: input phone number (numbers only)
     * return: formatted phone number
     * description: changes phone number from 10 numbers to (XXX) XXX-XXXX
     */
    public String formatPhoneNumber(String input) {
        return input.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");
    }
    /*
     * name: verifyPhoneNumber
     * parameters: input phone number (numbers only)
     * return: true if phone number is valid
     * description: makes sure a phone number is valid
     */
    public boolean verfiyPhoneNumber(String input){
        boolean valid = true;
        if(input.length() < 10 || input.length() > 13){
            valid = false;
        }
        return valid;
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
