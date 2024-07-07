import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import service.config;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        config.getConnection();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/resource/fxml/LoginView.fxml"));
        Parent root = loader.load();
        primaryStage.setTitle("RakyatAdu");
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.setResizable(false);

        Image image = new Image("/resource/imageassets/logo-app.png");
        primaryStage.getIcons().add(image);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
