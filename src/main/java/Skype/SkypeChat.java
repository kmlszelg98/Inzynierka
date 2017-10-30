package Skype;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil on 22.10.2017.
 */
public class SkypeChat {

    private String chatName;
    private List<SkypeMessage> messages;

    public SkypeChat(String chatName) {
        this.chatName = chatName;
        messages = new ArrayList<SkypeMessage>();
    }

    public void addMessage(SkypeMessage msg){
        messages.add(msg);
    }

    public SkypeMessage getMessage(int number){
        return messages.get(number);
    }

    public void setChatName(String chatName) {
        this.chatName = chatName;
    }

    public List<SkypeMessage> getMessages() {
        return messages;
    }

    public String getChatName() {
        return chatName;
    }
}
