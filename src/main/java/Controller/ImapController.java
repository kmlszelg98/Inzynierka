package Controller;

import Helpers.WindowHelper;
import Model.LoginModel;
import View.ImapView;
import View.LoginView;
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
        WindowHelper helper = new WindowHelper();
        helper.closeWindow(view.getFrame());

        view.getInboxButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

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
