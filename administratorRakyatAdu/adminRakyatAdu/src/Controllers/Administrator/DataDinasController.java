package Controllers.Administrator;

import java.util.List;
import java.util.Optional;

import Models.Administrator.DataDinasModel;
import Models.Administrator.UserDinas;
import Views.ViewFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class DataDinasController {
    ViewFactory m = new ViewFactory();

    @FXML
    private TableColumn<UserDinas, String> coloumnAlamat;

    @FXML
    private TableColumn<UserDinas, Integer> coloumnNomorKontak;

    @FXML
    private TableColumn<UserDinas, String> coloumnNamaLengkap;

    @FXML
    private TableColumn<UserDinas, String> coloumnPassword;

    @FXML
    private TableColumn<UserDinas, String> coloumnUsername;

    @FXML
    private TableView<UserDinas> idTableDinas;

    @FXML
    private Button lbKembali;

    @FXML
    private Button idBlokir;

    @FXML
    void clickKembali(ActionEvent event) {
        m.navigateToHomeAdmin(lbKembali);
    }

    private void loadUserData() {
        List<UserDinas> users = DataDinasModel.getAllUsers();
        ObservableList<UserDinas> userData = FXCollections.observableArrayList(users);
        idTableDinas.setItems(userData);
    }

    @FXML
    public void initialize() {
        coloumnNamaLengkap.setCellValueFactory(new PropertyValueFactory<>("namaLengkap"));
        coloumnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        coloumnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        coloumnAlamat.setCellValueFactory(new PropertyValueFactory<>("alamat"));
        coloumnNomorKontak.setCellValueFactory(new PropertyValueFactory<>("nomorKontak"));

        loadUserData();
    }

    @FXML
    void clickBlokir(ActionEvent event) {
        UserDinas selectedUser = idTableDinas.getSelectionModel().getSelectedItem();

        if (selectedUser != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Konfirmasi Blokir");
            alert.setHeaderText(null);
            alert.setContentText("Apakah Anda yakin ingin memblokir " + selectedUser.getNamaLengkap() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean success = DataDinasModel.deleteUser(selectedUser);

                if (success) {
                    idTableDinas.getItems().remove(selectedUser);
                    Alert successAlert = new Alert(AlertType.INFORMATION);
                    successAlert.setTitle("Sukses");
                    successAlert.setHeaderText(null);
                    successAlert.setContentText("Akun berhasil di blokir.");
                    successAlert.showAndWait();
                } else {
                    Alert errorAlert = new Alert(AlertType.ERROR);
                    errorAlert.setTitle("Error");
                    errorAlert.setHeaderText(null);
                    errorAlert.setContentText("Gagal memblokir akun. Silakan coba lagi.");
                    errorAlert.showAndWait();
                }
            }
        } else {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Peringatan");
            alert.setHeaderText(null);
            alert.setContentText("Silakan pilih pengguna yang ingin di blokir.");
            alert.showAndWait();
        }
    }
}
