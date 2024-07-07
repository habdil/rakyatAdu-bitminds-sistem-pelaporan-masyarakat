package Models.Administrator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import service.config;

public class InputDinas {

    public static boolean insertData(String namaLengkap, String username, String password, String alamat, String nomorKontak) {
        String query = "INSERT INTO tbllogindinas (namaLengkap, username, password, alamat, nomorKontak) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = config.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, namaLengkap);
            pstmt.setString(2, username);
            pstmt.setString(3, password);
            pstmt.setString(4, alamat);
            pstmt.setString(5, nomorKontak);
            int rowsAffected = pstmt.executeUpdate();
            System.out.println(rowsAffected + " row(s) inserted.");
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
