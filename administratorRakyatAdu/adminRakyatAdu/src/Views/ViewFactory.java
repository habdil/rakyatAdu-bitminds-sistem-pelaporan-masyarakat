package Views;

import Controllers.Menu.HomeController;
import Controllers.Menu.UmpanBalikController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewFactory {

    public void navigateToHomePage(Node node, String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/HomeView.fxml"));
            Parent nextPage = loader.load();

            HomeController homeController = loader.getController();
            homeController.setUsername(username);

            Stage stage = (Stage) node.getScene().getWindow();

            Scene scene = new Scene(nextPage);
            stage.setScene(scene);
            stage.setTitle("Home Page");

        } catch (Exception e) {
            System.out.println("Error in navigateToHomePage: " + e.getMessage());
        }
    }

    public void navigateToLoginPage(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/LoginView.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();

            Scene scene = new Scene(nextPage);
            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println("Error in navigateToLoginPage: " + e.getMessage());
        }
    }

    public void navigateToUmpanBalik(Node node, String username) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/UmpanBalikView.fxml"));
            Parent nextPage = loader.load();

            UmpanBalikController umpanBalikController = loader.getController();
            umpanBalikController.setUsername(username);

            Stage stage = (Stage) node.getScene().getWindow();

            Scene scene = new Scene(nextPage);
            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println("Error in navigateToUmpanBalik: " + e.getMessage());
        }
    }

    //navigasi untuk administrator//

    public void navigateToHomeAdmin(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/Administrator/ADHomeView.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();

            Scene scene = new Scene(nextPage);
            stage.setScene(scene);
            stage.setTitle("Home Page");

        } catch (Exception e) {
            System.out.println("Error in navigateToHomePage: " + e.getMessage());
        }
    }

    public void navigateToHomeWargaData(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/Administrator/DataWargaView.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();

            Scene scene = new Scene(nextPage);
            stage.setScene(scene);
            stage.setTitle("Data Warga Kota");

        } catch (Exception e) {
            System.out.println("Error in navigateToHomePage: " + e.getMessage());
        }
    }

    public void navigateToHomeDinasData(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/Administrator/DataDinasView.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();

            Scene scene = new Scene(nextPage);
            stage.setScene(scene);
            stage.setTitle("Data Dinas Terkait");

        } catch (Exception e) {
            System.out.println("Error in navigateToHomePage: " + e.getMessage());
        }
    }

    public void navigateToTambahDinas(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/Administrator/ADTambahkanDinasView.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();

            Scene scene = new Scene(nextPage);
            stage.setScene(scene);
            stage.setTitle("Admininstrator RakyatAdu");

        } catch (Exception e) {
            System.out.println("Error in navigateToHomePage: " + e.getMessage());
        }
    }

    public void navigateToLaporanMasuk(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/Administrator/PaneLaporanMasuk.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();

            Scene scene = new Scene(nextPage);
            stage.setScene(scene);
            stage.setTitle("Laporan Masuk");

        } catch (Exception e) {
            System.out.println("Error in navigateToHomePage: " + e.getMessage());
        }
    }

    public void navigateToLaporanTindakan(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/Administrator/PaneLaporanTindakan.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();

            Scene scene = new Scene(nextPage);
            stage.setScene(scene);
            stage.setTitle("Laporan Tindakan");

        } catch (Exception e) {
            System.out.println("Error in navigateToHomePage: " + e.getMessage());
        }
    }

    public void navigateToLaporanSelesai(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/Administrator/PaneLaporanSelesai.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();

            Scene scene = new Scene(nextPage);
            stage.setScene(scene);
            stage.setTitle("Laporan Tindakan");

        } catch (Exception e) {
            System.out.println("Error in navigateToHomePage: " + e.getMessage());
        }
    }

    public void navigateToADUmpanBalik(Node node) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/Administrator/ADUmpanBalikView.fxml"));
            Parent nextPage = loader.load();

            Stage stage = (Stage) node.getScene().getWindow();

            Scene scene = new Scene(nextPage);
            stage.setScene(scene);

        } catch (Exception e) {
            System.out.println("Error in navigateToUmpanBalik: " + e.getMessage());
        }
    }

}
