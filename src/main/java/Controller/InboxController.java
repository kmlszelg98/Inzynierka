package Controller;

import Helpers.FrameHelper;
import Helpers.WindowHelper;
import Imap.Imap;
import Imap.MessageImap;
import Model.InboxModel;
import View.ImapView;
import View.InboxReadView;
import View.InboxView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamil on 05.10.2017.
 */
public class InboxController {

    private InboxModel model;
    private InboxView view;

    public InboxController(InboxModel model, InboxView view) {
        this.model = model;
        this.view = view;
        setListeners();
    }

    public void setListeners(){

        view.getNext().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(Imap.type==0) {
                    if (Imap.id < (Imap.messages.length - 1)) {
                        model.setMessage(Imap.getNextMessage(true));
                        view.update();
                    }
                } else {
                    if (Imap.id < (Imap.sentMessages.length - 1)) {
                        model.setMessage(Imap.getNextMessage(false));
                        view.update();
                    }
                }
            }
        });

        view.getPrev().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(Imap.type==0) {
                    if (Imap.id > 0) {
                        model.setMessage(Imap.getPrevMessage(true));
                        view.update();
                    }
                } else {
                    if (Imap.id > 0) {
                        model.setMessage(Imap.getPrevMessage(false));
                        view.update();
                    }
                }
            }
        });

        view.getBack().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Imap.id=0;
                ImapView view = new ImapView();
                new ImapController(view);
            }
        });

        view.getRead().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                InboxReadView view = new InboxReadView(model);
                new InboxReadController(view,model);
            }
        });
    }
}
