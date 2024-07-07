package Models.Administrator;

import service.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataDinasModel {

    public static List<UserDinas> getAllUsers() {
        List<UserDinas> users = new ArrayList<>();
        String query = "SELECT * FROM tbllogindinas";
        
        try (Connection conn = config.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
             
            while (rs.next()) {
                String namaLengkap = rs.getString("namaLengkap");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String alamat = rs.getString("alamat");
                String noTelepon = rs.getString("nomorKontak"); 

                UserDinas user = new UserDinas(namaLengkap, username, password, alamat, noTelepon);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return users;
    }

    public static boolean deleteUser(UserDinas user) {
        String query = "DELETE FROM tbllogindinas WHERE username = ?";
        try (Connection conn = config.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, user.getUsername());
            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
