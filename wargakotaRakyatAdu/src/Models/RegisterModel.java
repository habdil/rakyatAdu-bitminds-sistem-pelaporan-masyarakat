package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import service.config;

public class RegisterModel {
    public static String namaLengkap;

    public static boolean insertData(String namaLengkap, String username, String password, String confirmPassword) {
        String query = "INSERT INTO tbllogin (namaLengkap, username, password, confirmPassword) VALUES (?, ?, ?, ?)";
        try (Connection conn = config.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, namaLengkap);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, confirmPassword);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");

            // Create a table for the user with namaLengkap column
            String createTableQuery = "CREATE TABLE " + username + "_reports (" +
            "id INT AUTO_INCREMENT PRIMARY KEY, " +
            "namaLengkap VARCHAR(255), " +
            "judulLaporan VARCHAR(255), " +
            "isiLaporan TEXT, " +
            "lokasi VARCHAR(255), " +
            "tanggal DATE, " +
            "instansi VARCHAR(255), " +
            "kategori VARCHAR(255), " +
            "file_name VARCHAR(255), " +
            "file_size BIGINT, " +
            "file_path VARCHAR(255), " +
            "status VARCHAR(255) DEFAULT 'Proses Verifikasi', " +
            "keterangan VARCHAR(255) DEFAULT 'Diproses oleh dinas terkait', " +
            "timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
            ")";
            PreparedStatement createTableStmt = conn.prepareStatement(createTableQuery);
            createTableStmt.executeUpdate();
            System.out.println("Table " + username + "_reports created.");

            // Insert namaLengkap into the user's reports table
            // String insertNamaLengkapQuery = "INSERT INTO " + username + "_reports (namaLengkap) VALUES (?)";
            // PreparedStatement insertNamaLengkapStmt = conn.prepareStatement(insertNamaLengkapQuery);
            // insertNamaLengkapStmt.setString(1, namaLengkap);
            // insertNamaLengkapStmt.executeUpdate();
            // System.out.println("namaLengkap inserted into " + username + "_reports table.");

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getNamaLengkap() {
        return namaLengkap;
    }

    public static void setNamaLengkap(String namaLengkap) {
        RegisterModel.namaLengkap = namaLengkap;
    }

    public static String getNamaLengkap(String username) {

        throw new UnsupportedOperationException("Unimplemented method 'getNamaLengkap'");
    }
}
