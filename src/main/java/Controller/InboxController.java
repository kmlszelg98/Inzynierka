package Controller;

import Helpers.WindowHelper;
import Imap.MessageImap;
import Model.InboxModel;
import View.InboxView;

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
        WindowHelper helper = new WindowHelper();
        helper.closeWindow(view.getFrame());

        view.getNext().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                model.setMessage(new MessageImap());
                view.getFrame().revalidate();
                view.getFrame().repaint();
            }
        });
    }
}
