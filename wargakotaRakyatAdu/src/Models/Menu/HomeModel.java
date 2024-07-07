package Models.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import service.config;
import java.io.File;

public class HomeModel {

    private File file;

    public HomeModel(File file2) {
        this.file = file2;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return file.getName();
    }

    public long getFileSize() {
        return file.length();
    }

    public static String getNamaLengkap(String username) {
        String query = "SELECT namaLengkap FROM tbllogin WHERE username = ?";
        try (Connection conn = config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("namaLengkap");
                } else {
                    System.out.println("No result found for username: " + username);
                }
            }
        } catch (SQLException e) {
            System.err.println("SQL Error in getNamaLengkap: " + e.getMessage());
        }
        return null;
    }

    public static boolean insertLaporan(String username, String judulLaporan, String isiLaporan, String lokasi, String tanggal, String instansi, String kategori, String file_name, long file_size, String file_path) {
        String query = "INSERT INTO " + username + "_reports (judulLaporan, isiLaporan, lokasi, tanggal, instansi, kategori, file_name, file_size, file_path) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, judulLaporan);
            pstmt.setString(2, isiLaporan);
            pstmt.setString(3, lokasi);
            pstmt.setString(4, tanggal);
            pstmt.setString(5, instansi);
            pstmt.setString(6, kategori);
            pstmt.setString(7, file_name);
            pstmt.setLong(8, file_size);
            pstmt.setString(9, file_path);
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("SQL Error in insertLaporan: " + e.getMessage());
            return false;
        }
    }
}
