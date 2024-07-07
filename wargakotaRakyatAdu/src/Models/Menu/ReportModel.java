package Models.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashSet;
import service.config;

public class ReportModel {
    private LinkedHashSet<Report> reportSet = new LinkedHashSet<>();

    public void loadReports(String username) throws Exception {
        try (Connection connection = config.getConnection()) {
            String query = "SELECT id, judulLaporan, lokasi, timestamp AS tanggal, status FROM " + username + "_reports ORDER BY timestamp"; 
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            reportSet.clear();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String judulLaporan = resultSet.getString("judulLaporan");
                String lokasi = resultSet.getString("lokasi");
                String timestamp = resultSet.getString("tanggal");
                String status = resultSet.getString("status");

                reportSet.add(new Report(id, judulLaporan, lokasi, timestamp, status));
            }
        }
    }

    public LinkedHashSet<Report> searchReports(String keyword) {
        LinkedHashSet<Report> result = new LinkedHashSet<>();
        for (Report report : reportSet) {
            if (report.getJudulLaporan().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(report);
            }
        }
        return result;
    }
}
