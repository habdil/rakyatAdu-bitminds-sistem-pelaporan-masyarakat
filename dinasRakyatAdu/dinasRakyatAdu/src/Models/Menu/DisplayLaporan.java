package Models.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import service.config;

public class DisplayLaporan {

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

                System.out.println("ID: " + id);
                System.out.println("Nama Lengkap : " + namaLengkap);
                System.out.println("Judul Laporan: " + judulLaporan);
                System.out.println("Isi Laporan: " + isiLaporan);
                System.out.println("Lokasi: " + lokasi);
                System.out.println("Tanggal: " + tanggal);
                System.out.println("Instansi: " + instansi);
                System.out.println("Kategori: " + kategori);
                System.out.println("File Name: " + fileName);
                System.out.println("File Size: " + fileSize);
                System.out.println("File Path: " + filePath);
                System.out.println("Status: " + status);
                System.out.println("Keterangan : " + keterangan);
                System.out.println("-------------------------------");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
