package Model;

/**
 * Created by Kamil on 30.10.2017.
 */
public class SkypeSendModel {

    private String chatName;
    private String message;

    public SkypeSendModel(String chatName) {
        this.chatName = chatName;
    }

    public String getChatName() {
        return chatName;
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
