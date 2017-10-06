package Model;

import Controller.MailController;
import Imap.MessageImap;
import Imap.Imap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil on 08.09.2017.
 */
public class InboxModel {

    private MessageImap message;
    public boolean isFirst;

    public InboxModel(MessageImap message) {
        this.message = message;
        isFirst = true;
    }

    public boolean isFirst() {
        return isFirst;
    }

    public void setFirst(boolean first) {
        isFirst = first;
    }

    public MessageImap getMessage() {
        return message;
    }

    public void setMessage(MessageImap message) {
        this.message = message;
    }
}
