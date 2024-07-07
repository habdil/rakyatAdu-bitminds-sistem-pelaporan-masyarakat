package Controllers.Alert;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AlertController {

    @FXML
    private Label message;

    @FXML
    private Label title;

    @FXML
    void tblClose(ActionEvent event) {
        Stage stage = (Stage) ((javafx.scene.Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setAlertDetails(String alertTitle, String alertMessage) {
        title.setText(alertTitle);
        message.setText(alertMessage);
    }

    public void showAlert(String alertTitle, String alertMessage) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/AlertView.fxml"));
        Parent root = loader.load();
        AlertController alertController = loader.getController();
        alertController.setAlertDetails(alertTitle, alertMessage);

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(new Scene(root));
        stage.showAndWait();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
}
