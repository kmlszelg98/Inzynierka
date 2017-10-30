package Model;

import Skype.SkypeChat;
import Skype.SkypeMessage;

import java.util.List;

/**
 * Created by Kamil on 24.10.2017.
 */
public class SkypeChatMsgModel {


    private SkypeMessage message;
    private SkypeChat chat;
    private List<SkypeMessage> list;
    private static int number;

    public SkypeChatMsgModel(SkypeChat chat) {
        this.chat = chat;
        list = chat.getMessages();
        number = list.size()-1;
        message = list.get(number);
    }

    public void next(){
        number--;
        message = list.get(number);
    }

    public void prev(){
        number++;
        message = list.get(number);
    }

    public SkypeMessage getMessage() {
        return message;
    }

    public boolean checkLast(){
        if(number == 0) return false;
        return true;
    }

    public boolean checkFirst(){
        if( number == list.size()-1) return false;
        return true;
    }

    public SkypeChat getChat() {
        return chat;
    }
}
