package Model;

import Imap.MessageImap;
import Imap.Imap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil on 08.09.2017.
 */
public class InboxModel {

    private List<MessageImap> list;

    public InboxModel(String name, String password) {
        list = new ArrayList<MessageImap>();
        String host = "imap.gmail.com";
        String mailStoreType = "imaps";
        String username = "kmlszelg98@gmail.com";
        String pass ="kmlszelg";
        Imap imap = new Imap();
        imap.start(host,mailStoreType,username,pass);
        list = imap.getList();
    }

    public List<MessageImap> getList() {
        return list;
    }

    public void setList(List<MessageImap> list) {
        this.list = list;
    }
}
