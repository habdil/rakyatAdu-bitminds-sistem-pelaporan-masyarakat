package Controllers.Menu;

import Models.Menu.DisplayLaporan;
import Models.Menu.HomeModel;
import Views.ViewFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import service.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class HomeController {

    @FXML
    private Button idListLaporan;

    @FXML
    private Button idLogout;

    @FXML
    private Button idUmpanBalik;

    @FXML
    private Label namaStaff;

    @FXML
    private VBox laporanContainer;

    private ViewFactory viewFactory;
    private DisplayLaporan displayLaporan;
    private List<String> usernames;
    private Timeline timeline;

    private String username;

    public HomeController() {
        this.viewFactory = new ViewFactory();
        this.displayLaporan = new DisplayLaporan();
    }

    @FXML
    public void initialize() {
        if (username != null) {
            String namaLengkapStr = HomeModel.getNamaLengkap(username);
            if (namaLengkapStr != null) {
                SharedData.getInstance().setNamaStaff(namaLengkapStr);
                namaStaff.setText(namaLengkapStr);
            } else {
                namaStaff.setText("Nama tidak ditemukan");
            }
        }

        timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> updateLaporan()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        loadUsernamesAndLaporan();
    }

    @FXML
    void btnListLaporan(ActionEvent event) {

    }

    @FXML
    void btnUmpanBalik(ActionEvent event) {
        viewFactory.navigateToUmpanBalik(idUmpanBalik, username);
    }

    @FXML
    void offMouse(MouseEvent event) {
        idListLaporan.setFont(new Font(17));
        idUmpanBalik.setFont(new Font(17));
        idLogout.setFont(new Font(13));
    }

    @FXML
    void onMouse(MouseEvent event) {
        idListLaporan.setFont(new Font(17));
        idUmpanBalik.setFont(new Font(17));
        idLogout.setFont(new Font(13));
    }

    @FXML
    void tblLogout(ActionEvent event) {
        viewFactory.navigateToLoginPage(idLogout);
    }

    private void loadUsernamesAndLaporan() {
        usernames = displayLaporan.ambilUsername();
        updateLaporan();
    }

    private void updateLaporan() {
        List<String> currentUsers = displayLaporan.ambilUsername();
        
        for (String newUser : currentUsers) {
            if (!usernames.contains(newUser)) {
                usernames.add(newUser);
                loadLaporan(newUser);
            }
        }

        laporanContainer.getChildren().clear();
        for (String username : usernames) {
            loadLaporan(username);
        }
    }

    private void loadLaporan(String username) {
        try (Connection connection = config.getConnection()) {
            String query = "SELECT id, namaLengkap, judulLaporan, status FROM " + username + "_reports";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String namaLengkap = resultSet.getString("namaLengkap");
                String judulLaporan = resultSet.getString("judulLaporan");
                String status = resultSet.getString("status");

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/BubbleLaporan.fxml"));
                AnchorPane laporanPane = loader.load();
                BubbleLaporanController controller = loader.getController();
                controller.setBubbleLaporan(namaLengkap, judulLaporan, status);

                laporanPane.setOnMouseClicked(e -> {
                    displayDetailLaporan(username, id);
                });

                laporanContainer.getChildren().add(laporanPane);
            }
        } catch (Exception e) {
            System.err.println("Error loading laporan for username: " + username + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void displayDetailLaporan(String username, int id) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/DetailLaporan.fxml"));
            AnchorPane detailPane = loader.load();

            DetailLaporanController detailController = loader.getController();
            detailController.loadDetailLaporan(username, id);

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(detailPane));
            stage.show();
        } catch (Exception e) {
            System.err.println("Kesalahan menampilkan detail laporan: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void setUsername(String username) {
        this.username = username;
        initialize();
    }
}
