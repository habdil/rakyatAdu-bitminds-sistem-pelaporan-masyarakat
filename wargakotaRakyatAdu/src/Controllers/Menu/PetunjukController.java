package Controllers.Menu;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PetunjukController {

    @FXML
    private ImageView imageView;

    @FXML
    private AnchorPane rootPane;

    private double scaleValue = 1.0;
    private static final double SCALE_DELTA = 1.1;

    private double mouseX;
    private double mouseY;

    @FXML
    void initialize() {
        // Menangani event scroll untuk zoom
        rootPane.setOnScroll((ScrollEvent event) -> {
            event.consume();

            if (event.getDeltaY() == 0) {
                return;
            }

            // Mengatur scaling factor
            double scaleFactor = (event.getDeltaY() > 0) ? SCALE_DELTA : (1 / SCALE_DELTA);

            // Memperbarui skala
            scaleValue *= scaleFactor;

            // Mengatur batas minimum skala
            scaleValue = Math.max(1.0, scaleValue); 

            // Menerapkan skala pada ImageView
            imageView.setScaleX(scaleValue);
            imageView.setScaleY(scaleValue);

            // Reset skala jika mendekati ukuran asli
            if (scaleValue <= 1.0) {
                scaleValue = 1.0;
                imageView.setScaleX(scaleValue);
                imageView.setScaleY(scaleValue);
                imageView.setTranslateX(0);
                imageView.setTranslateY(0);
            }
        });

        // Menangani event mouse untuk menggeser gambar
        imageView.setOnMousePressed(event -> {
            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
        });

        imageView.setOnMouseDragged(event -> {
            double deltaX = event.getSceneX() - mouseX;
            double deltaY = event.getSceneY() - mouseY;

            // Hitung posisi baru
            double newTranslateX = imageView.getTranslateX() + deltaX;
            double newTranslateY = imageView.getTranslateY() + deltaY;

            // Batas kiri dan kanan
            double maxTranslateX = ((imageView.getBoundsInParent().getWidth() - rootPane.getWidth())) * scaleValue;
            double minTranslateX = -maxTranslateX;
            newTranslateX = clamp(newTranslateX, minTranslateX, maxTranslateX);

            // Batas atas dan bawah
            double maxTranslateY = ((imageView.getBoundsInParent().getHeight() - rootPane.getHeight())) * scaleValue;
            double minTranslateY = -maxTranslateY;
            newTranslateY = clamp(newTranslateY, minTranslateY, maxTranslateY);

            // Menerapkan posisi baru
            imageView.setTranslateX(newTranslateX);
            imageView.setTranslateY(newTranslateY);

            mouseX = event.getSceneX();
            mouseY = event.getSceneY();
        });
    }

    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) imageView.getScene().getWindow();
        stage.close();
    }

    public void showPetunjuk() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/Menu/PetunjukView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (Exception e) {
            System.out.println("Error di petunjuk");
            e.printStackTrace();
        }
    }

    private double clamp(double value, double min, double max) {
        if (value < min) {
            return min;
        }
        if (value > max) {
            return max;
        }
        return value;
    }
}
