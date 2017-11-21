package Controller;

import Imap.MailSender;
import Model.SendMailModel;
import View.ImapView;
import View.SendMailBodyView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamil on 22.10.2017.
 */
public class SendMailBodyController {

    private SendMailBodyView view;
    private SendMailModel model;

    public SendMailBodyController(SendMailBodyView view, SendMailModel model) {
        this.view = view;
        this.model = model;
        setListeners();
    }

    public void setListeners(){
        view.getAccept().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.updateModel();
                /*MailSender sender = new MailSender();
                sender.send();*/
                ImapView view = new ImapView();
                new ImapController(view);
            }
        });

        view.getCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ImapView view = new ImapView();
                new ImapController(view);
            }
        });
    }
}
