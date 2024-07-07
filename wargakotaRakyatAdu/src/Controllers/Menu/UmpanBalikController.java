package Controllers.Menu;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.Menu.HomeModel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.util.Duration;
import service.config;

public class UmpanBalikController {

    @FXML
    private Button beranda;

    @FXML
    private Button editProfile;

    @FXML
    private Button logout;

    @FXML
    private Button statusLaporan;

    @FXML
    private Label tfNamaLengkap;

    @FXML
    private Label tfUserType;

    @FXML
    private Button umpanBalik;

    @FXML
    private TextField messageField;

    @FXML
    private ListView<String> messageListView;

    @FXML
    private Button tblSend;

    private ObservableList<String> messages = FXCollections.observableArrayList();

    private String username;

    private Timeline timeline;

    public void setUsername(String username) {
        this.username = username;
        initialize();
        loadMessages();
    }

    @FXML
    private void initialize() {
        if (username != null) {
            String namaLengkapStr = HomeModel.getNamaLengkap(username);
            if (namaLengkapStr != null) {
                tfNamaLengkap.setText(namaLengkapStr);
            } else {
                tfNamaLengkap.setText("Nama tidak ditemukan");
            }
        }

        messageListView.setItems(messages);
        messageListView.setCellFactory(listView -> new MessageCell());

        tblSend.requestFocus();

        messageField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendMessage();
            }
        });

        // Set up the timeline to refresh messages every 5 seconds
        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> loadMessages()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    void offMouse(MouseEvent event) {
        beranda.setFont(new Font(14));
        editProfile.setFont(new Font(8));
        statusLaporan.setFont(new Font(14));
        umpanBalik.setFont(new Font(14));
        logout.setFont(new Font(14));
    }

    @FXML
    void onMouse(MouseEvent event) {
        beranda.setFont(new Font(15));
        editProfile.setFont(new Font(11));
        statusLaporan.setFont(new Font(15));
        umpanBalik.setFont(new Font(15));
        logout.setFont(new Font(13));
    }

    @FXML
    void sendMessage() {
        String message = messageField.getText();
        String namaLengkap = tfNamaLengkap.getText();

        if (namaLengkap.isEmpty() || message.isEmpty()) {
            return;
        }

        try (Connection connection = config.getConnection()) {
            String query = "INSERT INTO tblmessage (namaLengkap, message) VALUES (?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, namaLengkap);
            statement.setString(2, message);
            statement.executeUpdate();

            messages.add(namaLengkap + "\n" + message);
            messageField.clear();
            messageListView.scrollTo(messages.size() - 1); // Scroll ke pesan terbaru
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Terkirim");
    }

    private void loadMessages() {
        messages.clear();

        try (Connection connection = config.getConnection()) {
            String query = "SELECT namaLengkap, message FROM tblmessage ORDER BY timestamp";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String namaLengkap = resultSet.getString("namaLengkap");
                String message = resultSet.getString("message");
                messages.add(namaLengkap + "\n" + message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void tbEditProfile(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/Menu/EditProfileView.fxml");
    }

    @FXML
    void tblBeranda(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/Menu/HomeView.fxml");
    }

    @FXML
    void tblLogout(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/LoginView.fxml");
    }

    @FXML
    void tblStatusLaporan(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/Menu/StatusLaporanView.fxml");
    }

    @FXML
    void tblUmpanBalik(ActionEvent event) {
        navigateToPage(event, "/resource/fxml/Menu/UmpanBalikView.fxml");
    }

    private void navigateToPage(ActionEvent event, String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent nextPage = loader.load();

            if (fxmlFile.contains("HomeView.fxml")) {
                HomeController homeController = loader.getController();
                homeController.setUsername(username);
            } else if (fxmlFile.contains("StatusLaporanView.fxml")) {
                StatusLaporanController statusLaporanController = loader.getController();
                statusLaporanController.setUsername(username);
            } else if (fxmlFile.contains("UmpanBalikView.fxml")) {
                UmpanBalikController umpanBalikController = loader.getController();
                umpanBalikController.setUsername(username);
            } else if (fxmlFile.contains("EditProfileView.fxml")) {
                EditProfileController editProfileController = loader.getController();
                editProfileController.setUsername(username);
            }

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            Scene nextScene = new Scene(nextPage);
            stage.setScene(nextScene);
            stage.show();
        } catch (Exception e) {
            System.err.println("Bermasalah: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public class MessageCell extends ListCell<String> {
        private final double TEXT_PADDING = 15;

        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);

            if (item == null || empty) {
                setText(null);
                setGraphic(null);
            } else {
                String[] parts = item.split("\n", 2);
                if (parts.length == 2) {
                    Text namaLengkap = new Text(parts[0]);
                    namaLengkap.getStyleClass().add("message-namaLengkap");
                    namaLengkap.setTextAlignment(TextAlignment.LEFT);
                    namaLengkap.setWrappingWidth(300 - 2 * TEXT_PADDING);

                    Text messageText = new Text(parts[1]);
                    messageText.getStyleClass().add("message-text");
                    messageText.setTextAlignment(TextAlignment.LEFT);
                    messageText.setWrappingWidth(300 - 2 * TEXT_PADDING);

                    double textWidth = Math.max(namaLengkap.getBoundsInLocal().getWidth(), messageText.getBoundsInLocal().getWidth());
                    double textHeight = namaLengkap.getBoundsInLocal().getHeight() + messageText.getBoundsInLocal().getHeight();

                    Rectangle bubble = new Rectangle();
                    bubble.setWidth(textWidth + 2 * TEXT_PADDING);
                    bubble.setHeight(textHeight + 3 * TEXT_PADDING);
                    bubble.setArcWidth(20);
                    bubble.setArcHeight(20);
                    bubble.getStyleClass().add("message-bubble");
                    

                    VBox vbox = new VBox(namaLengkap, messageText);
                    vbox.setSpacing(TEXT_PADDING);
                    vbox.setPadding(new Insets(TEXT_PADDING));

                    StackPane stackPane = new StackPane();
                    stackPane.getChildren().addAll(bubble, vbox);
                    stackPane.setPadding(new Insets(5));
                    stackPane.setMaxWidth(Region.USE_PREF_SIZE);

                    HBox hbox = new HBox(stackPane);
                    hbox.setAlignment(Pos.TOP_LEFT);
                    hbox.setPadding(new Insets(5));

                    setGraphic(hbox);
                } else {
                    setText(item);
                }
            }
        }
    }
}
