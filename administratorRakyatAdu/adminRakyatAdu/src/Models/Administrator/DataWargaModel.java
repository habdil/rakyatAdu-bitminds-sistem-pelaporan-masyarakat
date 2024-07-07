package Models.Administrator;

import service.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class DataWargaModel {

    public static List<UserWarga> getAllUsers() {
        List<UserWarga> users = new ArrayList<>();
        String query = "SELECT * FROM tbllogin";
        try (Connection conn = config.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String namaLengkap = rs.getString("namaLengkap");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String confirmPassword = rs.getString("confirmPassword");

                UserWarga user = new UserWarga(namaLengkap, username, password, confirmPassword);
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public static boolean deleteUser(UserWarga user) {
        String query = "DELETE FROM tbllogin WHERE username = ?";
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
