package Controllers.Administrator;

import Models.Menu.HomeModel;
import Views.ViewFactory;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
import javafx.util.Duration;
import service.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Controllers.Menu.SharedData;

public class ADUmpanBalikController {

    @FXML
    private Button idAddPetugas;

    @FXML
    private Button idDashboard;

    @FXML
    private Button idLogout;

    @FXML
    private Button idUmpanBalik;

    @FXML
    private TextField messageField;

    @FXML
    private ListView<String> messageListView;

    @FXML
    private Label namaStaff;

    ViewFactory m = new ViewFactory();

    private ViewFactory viewFactory;

    private String username;
    private Timeline timeline;
    private ObservableList<String> messages = FXCollections.observableArrayList();

    public void setUsername(String username) {
        this.username = username;
        initialize();
        loadMessages();
    }

    @FXML
    public void initialize() {
        this.viewFactory = new ViewFactory();
        if (username != null) {
            String namaLengkapStr = SharedData.getInstance().getNamaStaff();
            if (namaLengkapStr == null) {
                namaLengkapStr = HomeModel.getNamaLengkap(username);
                SharedData.getInstance().setNamaStaff(namaLengkapStr);
            }
            if (namaLengkapStr != null) {
                namaStaff.setText(namaLengkapStr);
            } else {
                namaStaff.setText("Nama tidak ditemukan");
            }
        }

        messageListView.setItems(messages);
        messageListView.setCellFactory(listView -> new MessageCell());

        messageField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                sendMessage();
            }
        });

        timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> loadMessages()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    void btnAddPetugas(ActionEvent event) {
        m.navigateToTambahDinas(idAddPetugas);
    }

    @FXML
    void btnDashboard(ActionEvent event) {
        m.navigateToHomeAdmin(idDashboard);
    }

    @FXML
    void btnUmpanBalik(ActionEvent event) {

    }

    @FXML
    void offMouse(MouseEvent event) {
        idDashboard.setFont(new Font(17));
        idUmpanBalik.setFont(new Font(17));
        idLogout.setFont(new Font(13));
    }

    @FXML
    void onMouse(MouseEvent event) {
        idDashboard.setFont(new Font(17));
        idUmpanBalik.setFont(new Font(17));
        idLogout.setFont(new Font(13));
    }

    @FXML
    void tblLogout(ActionEvent event) {
        viewFactory.navigateToLoginPage(idLogout);
    }

    @FXML
    void tblSend(ActionEvent event) {
        sendMessage();
    }

    private void sendMessage() {
        String message = messageField.getText();
        String namaLengkap = namaStaff.getText();

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
            messageListView.scrollTo(messages.size() - 1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
