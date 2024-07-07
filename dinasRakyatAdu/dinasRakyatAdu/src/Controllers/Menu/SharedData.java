package Controllers.Menu;

public class SharedData {
    private static SharedData instance;
    private String namaStaff;

    private SharedData() {}

    public static SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public String getNamaStaff() {
        return namaStaff;
    }

    public void setNamaStaff(String namaStaff) {
        this.namaStaff = namaStaff;
    }
}

