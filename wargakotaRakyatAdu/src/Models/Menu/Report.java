package Models.Menu;

public class Report {
    private int id;
    private String judulLaporan;
    private String lokasi;
    private String tanggal;
    private String status;

    public Report(int id, String judulLaporan, String lokasi, String tanggal, String status) {
        this.id = id;
        this.judulLaporan = judulLaporan;
        this.lokasi = lokasi;
        this.tanggal = tanggal;
        this.status = status;
    }

    public int getId() { return id; }
    public String getJudulLaporan() { return judulLaporan; }
    public String getLokasi() { return lokasi; }
    public String getTanggal() { return tanggal; }
    public String getStatus() { return status; }

    @Override
    public String toString() {
        return "Report{id=" + id + ", judulLaporan='" + judulLaporan + "', lokasi='" + lokasi + "', tanggal='" + tanggal + "', status='" + status + "'}";
    }
}
