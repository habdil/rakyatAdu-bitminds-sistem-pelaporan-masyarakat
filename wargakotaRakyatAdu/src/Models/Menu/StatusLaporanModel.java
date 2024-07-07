package Models.Menu;

public class StatusLaporanModel {
    private String judulLaporan;
    private String isiLaporan;
    private String lokasi;
    private String tanggal;
    private String instansi;
    private String kategori;
    private String file_name;
    private long file_size;
    private String file_path;

    public StatusLaporanModel(String judulLaporan, String isiLaporan, String lokasi, String tanggal, String instansi, String kategori, String file_name, long file_size, String file_path) {
        this.judulLaporan = judulLaporan;
        this.isiLaporan = isiLaporan;
        this.lokasi = lokasi;
        this.tanggal = tanggal;
        this.instansi = instansi;
        this.kategori = kategori;
        this.file_name = file_name;
        this.file_size = file_size;
        this.file_path = file_path;
    }

    public String getJudulLaporan() {
        return judulLaporan;
    }

    public void setJudulLaporan(String judulLaporan) {
        this.judulLaporan = judulLaporan;
    }

    public String getIsiLaporan() {
        return isiLaporan;
    }

    public void setIsiLaporan(String isiLaporan) {
        this.isiLaporan = isiLaporan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getInstansi() {
        return instansi;
    }

    public void setInstansi(String instansi) {
        this.instansi = instansi;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public long getFile_size() {
        return file_size;
    }

    public void setFile_size(long file_size) {
        this.file_size = file_size;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    
}
