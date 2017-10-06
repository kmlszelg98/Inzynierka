package Controller;

import Helpers.WindowHelper;
import Imap.Imap;
import Imap.MessageImap;
import Model.InboxModel;
import Model.LoginModel;
import View.ImapView;
import View.InboxView;
import View.LoginView;
import View.MailView;
import com.sun.mail.imap.IMAPMessage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamil on 03.10.2017.
 */
public class ImapController {

    private ImapView view;

    public ImapController(ImapView view) {
        this.view = view;
        setListeners();
    }

    public void setListeners(){
        WindowHelper helper = new WindowHelper();
        helper.closeWindow(view.getFrame());

        view.getInboxButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                view.getFrame().dispose();
                MessageImap temp = Imap.getFirstMessage();
                InboxModel model = new InboxModel(temp);
                System.out.println(temp.getFrom());
                InboxView view = new InboxView(model);
                new InboxController(model,view);
            }
        });

        view.getSendButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });

        view.getSentButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });

        view.getBackButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                view.getFrame().dispose();
                MailView view = new MailView();
                new MailController(view);
            }
        });
    }
}
