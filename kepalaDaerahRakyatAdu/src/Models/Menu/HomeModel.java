package Models.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import service.config;

public class HomeModel {
        public static String getNamaLengkap(String username) {
        String query = "SELECT namaLengkap FROM tbllogindinas WHERE username = ?";
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

}
