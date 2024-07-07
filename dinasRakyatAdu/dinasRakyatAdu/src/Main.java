import java.sql.SQLException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import service.config;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws SQLException {
        config.getConnection();
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Resource/Fxml/LoginView.fxml"));
            AnchorPane root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Dinas - RakyatAdu");
            primaryStage.show();
            primaryStage.setResizable(false);

            Image image = new Image("/Resource/Imageassets/logo-app.png");
            primaryStage.getIcons().add(image);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


// import java.util.List;

// import Models.Menu.*;

// public class Main {
//     public static void main(String[] args) {
//         DisplayLaporan m = new DisplayLaporan();

//         List<String> usernames = m.ambilUsername();

//         for (String username : usernames) {
//             System.out.println("Menampilkan laporan untuk username: " + username);
//             m.tampilkanLaporan(username);
//             System.out.println(); //
//         }
//     }
// }
