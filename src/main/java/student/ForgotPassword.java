package student;

public class ForgotPassword {
    private String email;

    ForgotPassword(String email){
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
