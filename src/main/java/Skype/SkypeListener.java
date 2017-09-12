package Skype;

import com.samczsun.skype4j.events.EventHandler;
import com.samczsun.skype4j.events.Listener;
import com.samczsun.skype4j.events.chat.message.MessageReceivedEvent;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.formatting.Message;

import java.util.List;

/**
 * Created by Kamil on 12.09.2017.
 */
public class SkypeListener implements Listener {

    private List<SkypeMessage> list;

    public SkypeListener(List<SkypeMessage> list) {
        this.list = list;
    }

    @EventHandler
    public void onMessage(MessageReceivedEvent e) {
        System.out.println("Got message: " + e.getMessage().getContent());
        SkypeMessage message = new SkypeMessage();
        try {
            message.setAuthor(e.getMessage().getSender().getDisplayName());
        } catch (ConnectionException e1) {
            e1.printStackTrace();
        }
        message.setBody(e.getMessage().getContent().toString());
        list.add(message);
    }

    @EventHandler
    public void onChat(MessageReceivedEvent e){
        String msg = e.getMessage().getContent().toString().toLowerCase();
        /*try {
            e.getChat().sendMessage("Message");
        } catch (ConnectionException e1) {
            e1.printStackTrace();
        }*/
        System.out.println("Got message: " + e.getMessage().getContent());
        SkypeMessage message = new SkypeMessage();
        try {
            message.setAuthor(e.getMessage().getSender().getDisplayName());
        } catch (ConnectionException e1) {
            e1.printStackTrace();
        }
        message.setBody(e.getMessage().getContent().toString());
        list.add(message);
    }
}
