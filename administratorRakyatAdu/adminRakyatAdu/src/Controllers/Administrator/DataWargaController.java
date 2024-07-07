package Controllers.Administrator;

import java.util.List;
import java.util.Optional;

import Models.Administrator.DataWargaModel;
import Models.Administrator.UserWarga;
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

public class DataWargaController {

    @FXML
    private TableColumn<UserWarga, String> coloumnNamaLengkap;

    @FXML
    private TableColumn<UserWarga, String> coloumnPassword;

    @FXML
    private TableColumn<UserWarga, String> coloumnConfimPassword;

    @FXML
    private TableColumn<UserWarga, String> coloumnUsername;

    @FXML
    private TableView<UserWarga> idTableWarga;

    @FXML
    private Button lbKembali;

    @FXML
    private Button idBlokir;

    ViewFactory m = new ViewFactory();


    @FXML
    void clickKembali(ActionEvent event) {
        m.navigateToHomeAdmin(lbKembali);
    }

    @FXML
    public void initialize() {
        coloumnNamaLengkap.setCellValueFactory(new PropertyValueFactory<>("namaLengkap"));
        coloumnUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        coloumnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        coloumnConfimPassword.setCellValueFactory(new PropertyValueFactory<>("confirmPassword"));

        loadUserData();
    }

    private void loadUserData() {
        List<UserWarga> users = DataWargaModel.getAllUsers();
        ObservableList<UserWarga> userData = FXCollections.observableArrayList(users);
        idTableWarga.setItems(userData);
    }

    @FXML
    void clickBlokir(ActionEvent event) {
        UserWarga selectedUser = idTableWarga.getSelectionModel().getSelectedItem();

        if (selectedUser != null) {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Konfirmasi Blokir");
            alert.setHeaderText(null);
            alert.setContentText("Apakah Anda yakin ingin memblokir " + selectedUser.getNamaLengkap() + "?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                boolean success = DataWargaModel.deleteUser(selectedUser);

                if (success) {
                    idTableWarga.getItems().remove(selectedUser);
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
