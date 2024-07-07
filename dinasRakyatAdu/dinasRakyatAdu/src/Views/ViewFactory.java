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
}
