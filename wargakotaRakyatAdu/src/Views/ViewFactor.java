package Views;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ViewFactor {
    public void DisplayDetail() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/Menu/DetailLaporanView.fxml"));
            AnchorPane detailPane = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(detailPane));
            stage.show();

        } catch (Exception e) {
            System.err.println("Kesalahan menampilkan detail laporan: " + e.getMessage());
            e.printStackTrace();
        }

    }
}

