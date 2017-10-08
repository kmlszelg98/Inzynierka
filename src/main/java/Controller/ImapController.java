package Controller;

import Imap.Imap;
import Imap.MessageImap;
import Model.InboxModel;
import View.ImapView;
import View.InboxView;
import View.MailView;

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

        view.getInboxButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                MessageImap temp = Imap.getNextMessage();
                InboxModel model = new InboxModel(temp);
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
                MailView view = new MailView();
                new MailController(view);
            }
        });
    }
}
