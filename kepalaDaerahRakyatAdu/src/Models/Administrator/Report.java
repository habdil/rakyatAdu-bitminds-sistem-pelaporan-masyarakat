package Models.Administrator;

import java.security.Timestamp;
import java.sql.Date;

public class Report {
    private int id;
    private String namaLengkap;
    private String judulLaporan;
    private String isiLaporan;
    private String lokasi;
    private Date tanggal;
    private String instansi;
    private String kategori;
    private String fileName;
    private long fileSize;
    private String filePath;
    private String status;
    private String keterangan;
    private Timestamp timestamp;

    // Constructor, getters, and setters
    public Report(int id, String namaLengkap, String judulLaporan, String isiLaporan, String lokasi, Date tanggal, String instansi, String kategori, String fileName, long fileSize, String filePath, String status, String keterangan, Timestamp timestamp) {
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
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
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

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getFileSize() {
        return fileSize;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }
}

