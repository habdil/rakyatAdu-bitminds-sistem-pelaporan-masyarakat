package Models.Administrator;

public class UserWarga {
    private String namaLengkap;
    private String username;
    private String password;
    private String confirmPassword;

    public UserWarga(String namaLengkap, String username, String password, String confirmPassword) {
        this.namaLengkap = namaLengkap;
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
