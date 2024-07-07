package Controllers.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ImageController {

    @FXML
    private Button lcClose;

    @FXML
    private ImageView imageView;

    @FXML
    void clickClose(ActionEvent event) {
        Stage stage = (Stage) lcClose.getScene().getWindow();
        stage.close();
    }

    public void setImage(Image image) {
        imageView.setImage(image);
    }
}
