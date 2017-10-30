package Controller;

import Model.SendMailModel;
import View.ImapView;
import View.SendMailBodyView;
import View.SendMailTopicView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamil on 18.10.2017.
 */
public class SendMailTopicController {

    private SendMailTopicView view;
    private SendMailModel model;

    public SendMailTopicController(SendMailTopicView view, SendMailModel model) {
        this.view = view;
        this.model = model;
        setListeners();
    }

    public void setListeners(){

        view.getAccept().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.updateModel();
                SendMailBodyView view2 = new SendMailBodyView(model);
                new SendMailBodyController(view2,model);
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
