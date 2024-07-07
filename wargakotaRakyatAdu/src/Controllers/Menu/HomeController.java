package Controllers.Menu;

import Models.Menu.HomeModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import service.config;
import javafx.scene.Node;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Controllers.AlertController;
import Controllers.AlertController2;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class HomeController {

    @FXML
    private Button beranda;

    @FXML
    private Button editProfile;

    @FXML
    private Button logout;

    @FXML
    private Button status;

    @FXML
    private Button umpanBalik;

    @FXML
    private Label namaLengkap;

    @FXML
    private ComboBox<String> instansi;

    @FXML
    private TextArea isiLaporan;

    @FXML
    private TextField judulLaporan;

    @FXML
    private ComboBox<String> kategori;

    @FXML
    private TextField lokasi;

    @FXML
    private DatePicker tanggal;

    @FXML
    private Button upload;

    @FXML
    private Label uploadFile;

    @FXML
    private Label uploadSize;

    @FXML
    private Button colorAspirasi;

    @FXML
    private Button colorKonfirmasi;

    @FXML
    private Button colorPengaduan;

    private String username;
    private File selectedFile;
    private Button activeButton;

    AlertController err = new AlertController();
    AlertController2 scc = new AlertController2();
    PetunjukController tampil = new PetunjukController();

    

    public void setUsername(String username) {
        this.username = username;
        initialize();
    }

    @FXML
    private void initialize() {
        if (username != null) {
            String namaLengkapStr = HomeModel.getNamaLengkap(username);
            if (namaLengkapStr != null) {
                namaLengkap.setText(namaLengkapStr);
            } else {
                namaLengkap.setText("Nama tidak ditemukan");
            }

            upload.setOnAction(event -> handleUpload(event));
        }

        ObservableList<String> instansiList = FXCollections.observableArrayList("Dinas Kabupaten Sleman", "Dinas Kabupaten Gunung Kidul", "Dinas Kabupaten Bantul");
        instansi.setItems(instansiList);

        ObservableList<String> kategoriList = FXCollections.observableArrayList("Infrastruktur", "Bencana Alam", "Lainnya");
        kategori.setItems(kategoriList);

        colorPengaduan.setOnAction(this::tblKlasifikasiLaporan);
        colorAspirasi.setOnAction(this::tblKlasifikasiLaporan);
        colorKonfirmasi.setOnAction(this::tblKlasifikasiLaporan);
    }

    @FXML
    void handleUpload(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"),
            new FileChooser.ExtensionFilter("Video Files", "*.mp4", "*.avi")
        );

        File file = fileChooser.showOpenDialog((Stage) ((Node) event.getSource()).getScene().getWindow());
        if (file != null) {
            selectedFile = file;
            uploadFile.setText("Berhasil diunggah");
        }
    }
    
    @FXML
    void tbEditProfile(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/Menu/EditProfileView.fxml");
    }

    @FXML
    void tblBeranda(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/Menu/HomeView.fxml");
    }


@FXML
void tblLapor(ActionEvent event) {
    String judul = judulLaporan.getText();
    String isi = isiLaporan.getText();
    String lokasiStr = lokasi.getText();
    String instansiStr = instansi.getValue();
    String kategoriStr = kategori.getValue();
    LocalDate tanggalLaporan = tanggal.getValue();

    if (judul.isEmpty() || isi.isEmpty() || lokasiStr.isEmpty() || instansiStr == null || kategoriStr == null || tanggalLaporan == null || selectedFile == null) {
        err.showAlert("Gagal Terkirim", "Silahkan isi semua form ini");
        System.out.println("Gagal mengupload laporan karena ada field yang kosong");
        return;
    }

    String formattedDate = tanggalLaporan.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    String namaLengkapStr = HomeModel.getNamaLengkap(username); // Ensure this retrieves the correct full name

    boolean success = insertLaporan(username, namaLengkapStr, judul, isi, lokasiStr, formattedDate, instansiStr, kategoriStr,
        selectedFile.getName(), selectedFile.length(), selectedFile.getAbsolutePath());

    if (success) {
        scc.showAlert("Terkirim", "Laporan Terkirim ke Dinas Terkait, Silahkan Tunggu konfirmasi");
        System.out.println("Laporan berhasil diupload");

        judulLaporan.clear();
        isiLaporan.clear();
        lokasi.clear();
        tanggal.setValue(null);
        selectedFile = null;
        uploadFile.setText("");
        uploadSize.setText("");
    } else {
        err.showAlert("Gagal Terkirim", "Terjadi kesalahan saat mengupload laporan, silahkan coba lagi");
        System.out.println("Gagal mengupload laporan");
    }
}

private boolean insertLaporan(String username, String namaLengkap, String judul, String isi, String lokasi, String tanggal,
                              String instansi, String kategori, String fileName, long fileSize, String filePath) {
    String insertQuery = "INSERT INTO " + username + "_reports (namaLengkap, judulLaporan, isiLaporan, lokasi, tanggal, instansi, kategori, file_name, file_size, file_path) " +
                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    try (Connection conn = config.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {

        pstmt.setString(1, namaLengkap);
        pstmt.setString(2, judul);
        pstmt.setString(3, isi);
        pstmt.setString(4, lokasi);
        pstmt.setString(5, tanggal);
        pstmt.setString(6, instansi);
        pstmt.setString(7, kategori);
        pstmt.setString(8, fileName);
        pstmt.setLong(9, fileSize);
        pstmt.setString(10, filePath);

        int rowsAffected = pstmt.executeUpdate();
        System.out.println(rowsAffected + " row(s) inserted into " + username + "_reports table.");
        return true;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}
    
    
    @FXML
    void tblLogout(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/LoginView.fxml");
    }

    @FXML
    void tblKlasifikasiLaporan(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();

        if (activeButton == null) {
            activateButton(clickedButton);
        } else if (activeButton == clickedButton) {
            deactivateButton(activeButton);
        } else {
            deactivateButton(activeButton);
            activateButton(clickedButton);
        }
    }

    private void activateButton(Button button) {
        button.getStyleClass().add("round-button-active");
        activeButton = button;
    }

    private void deactivateButton(Button button) {
        button.getStyleClass().remove("round-button-active");
        activeButton = null;
    }
    

    @FXML
    void tblPetunjuk(ActionEvent event) {
        tampil.showPetunjuk();
    }

    @FXML
    void tblStatusLaporan(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/Menu/StatusLaporanView.fxml");
    }

    @FXML
    void offMouse(MouseEvent event) {
        beranda.setFont(new Font(14));
        editProfile.setFont(new Font(8));
        status.setFont(new Font(14));
        umpanBalik.setFont(new Font(14));
        logout.setFont(new Font(12));
    }

    @FXML
    void onMouse(MouseEvent event) {
        beranda.setFont(new Font(15));
        editProfile.setFont(new Font(11));
        status.setFont(new Font(15));
        umpanBalik.setFont(new Font(15));
        logout.setFont(new Font(13));
    }

    @FXML
    void tblUmpanBalik(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/Menu/UmpanBalikView.fxml");
    }

    void navigateToPage(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent nextPage = loader.load();

            // Pass the username to the next controller
            if (fxmlFile.contains("HomeView.fxml")) {
                HomeController homeController = loader.getController();
                homeController.setUsername(username);
            } else if (fxmlFile.contains("StatusLaporanView.fxml")) {
                StatusLaporanController statusLaporanController = loader.getController();
                statusLaporanController.setUsername(username);
            } else if (fxmlFile.contains("UmpanBalikView.fxml")) {
                UmpanBalikController umpanBalikController = loader.getController();
                umpanBalikController.setUsername(username);
            } else if (fxmlFile.contains("EditProfileView.fxml")) {
                EditProfileController editProfileController = loader.getController();
                editProfileController.setUsername(username);
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene nextScene = new Scene(nextPage);
            stage.setScene(nextScene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Bermasalah: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
