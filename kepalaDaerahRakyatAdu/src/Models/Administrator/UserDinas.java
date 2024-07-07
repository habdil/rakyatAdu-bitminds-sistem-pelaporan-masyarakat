package Models.Administrator;

public class UserDinas {
    private String namaLengkap;
    private String username;
    private String password;
    private String alamat;
    private int nomorKontak;
    
    public UserDinas(String namaLengkap, String username, String password, String alamat, int nomorKontak) {
        this.namaLengkap = namaLengkap;
        this.username = username;
        this.password = password;
        this.alamat = alamat;
        this.nomorKontak = nomorKontak;
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
    public String getAlamat() {
        return alamat;
    }
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    public int getNomorKontak() {
        return nomorKontak;
    }
    public void setNomorKontak(int nomorKontak) {
        this.nomorKontak = nomorKontak;
    }

    
}
