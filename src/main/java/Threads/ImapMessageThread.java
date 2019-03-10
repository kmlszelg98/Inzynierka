package Threads;

import Imap.Imap;
import Imap.MessageImap;

import javax.mail.*;
import java.io.IOException;

/**
 * Created by Kamil on 03.10.2017.
 */
public class ImapMessageThread extends Thread{

    public MessageImap messageImap;
    private Message message;

    public ImapMessageThread(Message message) {
        this.message = message;
    }

    public MessageImap getMessage() {
        return messageImap;
    }

    @Override
    public void run() {
        Imap imap = new Imap();
//        try {
//            //MessageImap messageImap = imap.init(message);
//            //this.messageImap = imap.init(message);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }
}
