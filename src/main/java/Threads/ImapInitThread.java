package Threads;

import Imap.Imap;
import Imap.MessageImap;

import javax.mail.*;
import java.io.IOException;

/**
 * Created by Kamil on 03.10.2017.
 */
public class ImapInitThread implements Runnable{

    public static MessageImap[] imaps;
    private int id;
    private Message message;

    public ImapInitThread(int id, Message message) {
        this.id = id;
        this.message = message;
    }

    public static void setImaps(MessageImap[] imaps) {
        ImapInitThread.imaps = imaps;
    }

    public void run() {
        Imap imap = new Imap();
        try {
            MessageImap messageImap = imap.init(message);
            System.out.println(id+" "+messageImap.getSubject());
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
