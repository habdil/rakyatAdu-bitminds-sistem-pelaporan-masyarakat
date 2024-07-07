package Controllers.Administrator;

import Controllers.Alert.AlertController;
import Controllers.Alert.AlertController2;
import Models.Administrator.InputDinas;
import Views.ViewFactory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class ADTambahController {

    AlertController err = new AlertController();
    AlertController2 scc = new AlertController2();

    @FXML
    private Button btnDaftar;

    @FXML
    private Button idAddPetugas;

    @FXML
    private Button idDashboard;

    @FXML
    private Button idLogout;

    @FXML
    private Button idUmpanBalik;

    @FXML
    private Label namaStaff;

    @FXML
    private TextField tfAlamat;

    @FXML
    private TextField tfNamaLengkap;

    @FXML
    private TextField tfNomorKontak;

    @FXML
    private TextField tfPass;

    @FXML
    private TextField tfUsername;

    ViewFactory m = new ViewFactory();

    @FXML
    void btnAddPetugas(ActionEvent event) {

    }

    @FXML
    void btnDashboard(ActionEvent event) {
        m.navigateToHomeAdmin(idDashboard);
    }

    @FXML
    void btnUmpanBalik(ActionEvent event) {
        m.navigateToADUmpanBalik(idUmpanBalik);
    }

    @FXML
    void clickDaftarkan(ActionEvent event) {
        String namaLengkap = tfNamaLengkap.getText();
        String username = tfUsername.getText();
        String password = tfPass.getText();
        String alamat = tfAlamat.getText();
        String nomorKontak = tfNomorKontak.getText();

        if (namaLengkap.isEmpty() || username.isEmpty() || password.isEmpty() || alamat.isEmpty() || nomorKontak.isEmpty()) {
            err.showAlert("Gagal Didaftarkan", "Silahkan isi semua kolom yang diberikan");
            return;
        }

        if (nomorKontak.length() > 15) {
            err.showAlert("Gagal Didaftarkan", "Nomor kontak terlalu panjang (maksimal 15 karakter)");
            return;
        }


        boolean isInserted = InputDinas.insertData(namaLengkap, username, password, alamat, nomorKontak);

        if (isInserted) {
            scc.showAlert("Berhasil Didaftarkan", "Selamat akun dinas berhasil didaftarkan");
            clearFields();
        } else {
            err.showAlert("Kesalahan", "Server Bermasalah");
        }
    }

    private void clearFields() {
        tfNamaLengkap.clear();
        tfUsername.clear();
        tfPass.clear();
        tfAlamat.clear();
        tfNomorKontak.clear();
    }


    @FXML
    void offMouse(MouseEvent event) {
        idDashboard.setFont(new Font(17));
        idUmpanBalik.setFont(new Font(17));
        idAddPetugas.setFont(new Font(17));
        idLogout.setFont(new Font(13));
    }

    @FXML
    void onMouse(MouseEvent event) {
        idDashboard.setFont(new Font(17));
        idUmpanBalik.setFont(new Font(17));
        idAddPetugas.setFont(new Font(17));
        idLogout.setFont(new Font(13));
    }

    @FXML
    void tblLogout(ActionEvent event) {
        m.navigateToLoginPage(idLogout);
    }

}
