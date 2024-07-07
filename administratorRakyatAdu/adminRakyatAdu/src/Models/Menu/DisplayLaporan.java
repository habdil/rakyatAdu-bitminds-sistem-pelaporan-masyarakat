package Models.Menu;

import service.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class DisplayLaporan {

    private LinkedHashSet<Report> reportSet = new LinkedHashSet<>();

    public List<String> ambilUsername() {
        List<String> usernames = new ArrayList<>();
        String query = "SELECT username FROM tbllogin";
        try (Connection conn = config.getConnection()) {
            PreparedStatement selectStmt = conn.prepareStatement(query);
            ResultSet rs = selectStmt.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                usernames.add(username);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usernames;
    }

    public void tampilkanLaporan(String username) {
        String sqlLaporan = "SELECT * FROM " + username + "_reports";
        try (Connection conn = config.getConnection()) {
            PreparedStatement selectStmt = conn.prepareStatement(sqlLaporan);
            ResultSet rs = selectStmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String namaLengkap = rs.getString("namaLengkap");
                String judulLaporan = rs.getString("judulLaporan");
                String isiLaporan = rs.getString("isiLaporan");
                String lokasi = rs.getString("lokasi");
                String tanggal = rs.getDate("tanggal").toString();
                String instansi = rs.getString("instansi");
                String kategori = rs.getString("kategori");
                String fileName = rs.getString("file_name");
                long fileSize = rs.getLong("file_size");
                String filePath = rs.getString("file_path");
                String status = rs.getString("status");
                String keterangan = rs.getString("keterangan");

                Report report = new Report(id, namaLengkap, judulLaporan, isiLaporan, lokasi, tanggal, instansi, 
                                           kategori, fileName, fileSize, filePath, status, keterangan);
                reportSet.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public LinkedHashSet<Report> searchReports(String keyword) {
        LinkedHashSet<Report> result = new LinkedHashSet<>();
        for (Report report : reportSet) {
            if (report.getNamaLengkap().toLowerCase().contains(keyword.toLowerCase()) ||
                report.getJudulLaporan().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(report);
            }
        }
        return result;
    }

    public LinkedHashSet<Report> getReportSet() {
        return reportSet;
    }

    public List<String> getReportTables() {
        List<String> tableNames = new ArrayList<>();
        String query = "SHOW TABLES LIKE '%_reports'";
        
        try (Connection conn = config.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                tableNames.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableNames;
    }

    public Map<String, Integer> countReportsByStatus() {
        Map<String, Integer> statusCounts = new HashMap<>();
        List<String> tableNames = getReportTables();
        
        for (String tableName : tableNames) {
            String query = "SELECT status, COUNT(*) as count FROM " + tableName + " GROUP BY status";
            try (Connection conn = config.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    String status = rs.getString("status");
                    int count = rs.getInt("count");
                    statusCounts.put(status, statusCounts.getOrDefault(status, 0) + count);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return statusCounts;
    }

    public List<Report> getReportsByStatusProsesVerifikasi() {
        List<Report> verifikasiReports = new ArrayList<>();
        List<String> tableNames = getReportTables();

        for (String tableName : tableNames) {
            String query = "SELECT * FROM " + tableName + " WHERE status = 'Proses Verifikasi'";
            try (Connection conn = config.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String namaLengkap = rs.getString("namaLengkap");
                    String judulLaporan = rs.getString("judulLaporan");
                    String isiLaporan = rs.getString("isiLaporan");
                    String lokasi = rs.getString("lokasi");
                    String tanggal = rs.getDate("tanggal").toString();
                    String instansi = rs.getString("instansi");
                    String kategori = rs.getString("kategori");
                    String fileName = rs.getString("file_name");
                    long fileSize = rs.getLong("file_size");
                    String filePath = rs.getString("file_path");
                    String status = rs.getString("status");
                    String keterangan = rs.getString("keterangan");

                    Report report = new Report(id, namaLengkap, judulLaporan, isiLaporan, lokasi, tanggal, instansi, 
                                               kategori, fileName, fileSize, filePath, status, keterangan);
                    verifikasiReports.add(report);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return verifikasiReports;
    }

    public List<Report> getReportsByStatusTindakan() {
        List<Report> verifikasiReports = new ArrayList<>();
        List<String> tableNames = getReportTables();

        for (String tableName : tableNames) {
            String query = "SELECT * FROM " + tableName + " WHERE status = 'Ditindak Lanjut'";
            try (Connection conn = config.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String namaLengkap = rs.getString("namaLengkap");
                    String judulLaporan = rs.getString("judulLaporan");
                    String isiLaporan = rs.getString("isiLaporan");
                    String lokasi = rs.getString("lokasi");
                    String tanggal = rs.getDate("tanggal").toString();
                    String instansi = rs.getString("instansi");
                    String kategori = rs.getString("kategori");
                    String fileName = rs.getString("file_name");
                    long fileSize = rs.getLong("file_size");
                    String filePath = rs.getString("file_path");
                    String status = rs.getString("status");
                    String keterangan = rs.getString("keterangan");

                    Report report = new Report(id, namaLengkap, judulLaporan, isiLaporan, lokasi, tanggal, instansi, 
                                               kategori, fileName, fileSize, filePath, status, keterangan);
                    verifikasiReports.add(report);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return verifikasiReports;
    }

    public List<Report> getReportsByStatusSelesai() {
        List<Report> verifikasiReports = new ArrayList<>();
        List<String> tableNames = getReportTables();

        for (String tableName : tableNames) {
            String query = "SELECT * FROM " + tableName + " WHERE status = 'Laporan Selesai'";
            try (Connection conn = config.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String namaLengkap = rs.getString("namaLengkap");
                    String judulLaporan = rs.getString("judulLaporan");
                    String isiLaporan = rs.getString("isiLaporan");
                    String lokasi = rs.getString("lokasi");
                    String tanggal = rs.getDate("tanggal").toString();
                    String instansi = rs.getString("instansi");
                    String kategori = rs.getString("kategori");
                    String fileName = rs.getString("file_name");
                    long fileSize = rs.getLong("file_size");
                    String filePath = rs.getString("file_path");
                    String status = rs.getString("status");
                    String keterangan = rs.getString("keterangan");

                    Report report = new Report(id, namaLengkap, judulLaporan, isiLaporan, lokasi, tanggal, instansi, 
                                               kategori, fileName, fileSize, filePath, status, keterangan);
                    verifikasiReports.add(report);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return verifikasiReports;
    }

    public java.sql.Timestamp getLastUpdateTimestamp() {
        java.sql.Timestamp timestamp = null;
        try (Connection conn = config.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT MAX(updated_at) AS last_update FROM laporan")) {
            if (rs.next()) {
                timestamp = rs.getTimestamp("last_update");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return timestamp;
    }
}
