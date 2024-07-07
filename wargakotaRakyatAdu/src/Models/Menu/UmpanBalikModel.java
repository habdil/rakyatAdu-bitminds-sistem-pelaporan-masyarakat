package Models.Menu;

import java.security.Timestamp;

public class UmpanBalikModel {
    private int id;
    private String username;
    private String message;
    private Timestamp timestamp;

    public UmpanBalikModel(int id, String username, String message, Timestamp timestamp) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    
    
}
