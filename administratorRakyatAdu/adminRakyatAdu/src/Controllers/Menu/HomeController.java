package Controllers.Menu;

import Models.Menu.DisplayLaporan;
import Models.Menu.HomeModel;
import Models.Menu.Report;
import Views.ViewFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class HomeController {

    @FXML
    private TextField lbSearch;

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
    private boolean isSearching = false;
    private Set<Integer> displayedReportIds = new HashSet<>();

    public HomeController() {
        this.viewFactory = new ViewFactory();
        this.displayLaporan = new DisplayLaporan();
    }

    @FXML
    public void initialize() {
        if (username != null) {
            String namaLengkapStr = HomeModel.getNamaLengkap(username);
            if (namaLengkapStr != null) {
                namaStaff.setText(namaLengkapStr);
            } else {
                namaStaff.setText("Nama tidak ditemukan");
            }
        }

        // ScheduledService to update reports periodically
        ScheduledService<Void> scheduledService = new ScheduledService<Void>() {
            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {
                    @Override
                    protected Void call() {
                        if (!isSearching) {
                            updateLaporan();
                        }
                        return null;
                    }
                };
            }
        };
        scheduledService.setPeriod(Duration.seconds(10));
        scheduledService.start();

        loadUsernamesAndLaporan();
    }

    @FXML
    void btnListLaporan(ActionEvent event) {
        viewFactory.navigateToHomePage(idListLaporan, username);
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
        for (String username : usernames) {
            displayLaporan.tampilkanLaporan(username);
        }
        updateLaporan();
    }

    private void updateLaporan() {
        LinkedHashSet<Report> reports = displayLaporan.getReportSet();
        for (Report report : reports) {
            if (!displayedReportIds.contains(report.getId())) {
                addLaporanToContainer(report);
                displayedReportIds.add(report.getId());
            }
        }
    }

    private void addLaporanToContainer(Report report) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/BubbleLaporan.fxml"));
            AnchorPane laporanPane = loader.load();
            BubbleLaporanController controller = loader.getController();
            controller.setBubbleLaporan(report.getNamaLengkap(), report.getJudulLaporan(), report.getStatus());

            laporanPane.setOnMouseClicked(e -> {
                displayDetailLaporan(report.getId());
            });

            laporanContainer.getChildren().add(laporanPane);
        } catch (Exception e) {
            System.err.println("Error displaying report: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void displayDetailLaporan(int id) {
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

    @FXML
    void clikSearch(ActionEvent event) {
        isSearching = true;
        String keyword = lbSearch.getText();
        LinkedHashSet<Report> searchResults = displayLaporan.searchReports(keyword);
        displayReports(searchResults);
        isSearching = false;
    }

    private void displayReports(LinkedHashSet<Report> reports) {
        laporanContainer.getChildren().clear();
        displayedReportIds.clear();
        for (Report report : reports) {
            addLaporanToContainer(report);
            displayedReportIds.add(report.getId());
        }
    }
}
