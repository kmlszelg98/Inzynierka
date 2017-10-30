package Model;

import Skype.SkypeChat;

/**
 * Created by Kamil on 23.10.2017.
 */
public class SkypeChatsModel {

    private SkypeChat chat;

    public SkypeChatsModel(SkypeChat chat) {
        this.chat = chat;
    }

    public SkypeChat getChat() {
        return chat;
    }

    public void setChat(SkypeChat chat) {
        this.chat = chat;
    }
}
