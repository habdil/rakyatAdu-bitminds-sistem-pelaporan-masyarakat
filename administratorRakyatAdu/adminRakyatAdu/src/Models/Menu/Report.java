package Models.Menu;

public class Report {
    private int id;
    private String namaLengkap;
    private String judulLaporan;
    private String isiLaporan;
    private String lokasi;
    private String tanggal;
    private String instansi;
    private String kategori;
    private String fileName;
    private long fileSize;
    private String filePath;
    private String status;
    private String keterangan;

    public Report(int id, String namaLengkap, String judulLaporan, String isiLaporan, String lokasi, String tanggal, 
                  String instansi, String kategori, String fileName, long fileSize, String filePath, String status, 
                  String keterangan) {
        this.id = id;
        this.namaLengkap = namaLengkap;
        this.judulLaporan = judulLaporan;
        this.isiLaporan = isiLaporan;
        this.lokasi = lokasi;
        this.tanggal = tanggal;
        this.instansi = instansi;
        this.kategori = kategori;
        this.fileName = fileName;
        this.fileSize = fileSize;
        this.filePath = filePath;
        this.status = status;
        this.keterangan = keterangan;
    }

    public int getId() {
        return id;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public String getJudulLaporan() {
        return judulLaporan;
    }

    public String getIsiLaporan() {
        return isiLaporan;
    }

    public String getLokasi() {
        return lokasi;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getInstansi() {
        return instansi;
    }

    public String getKategori() {
        return kategori;
    }

    public String getFileName() {
        return fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getStatus() {
        return status;
    }

    public String getKeterangan() {
        return keterangan;
    }
}
