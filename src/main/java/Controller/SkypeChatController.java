package Controller;

import Model.SkypeChatMsgModel;
import Model.SkypeSendModel;
import View.MailView;
import View.SkypeChatView;
import View.SkypeSendView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamil on 24.10.2017.
 */
public class SkypeChatController {

    private SkypeChatMsgModel model;
    private SkypeChatView view;

    public SkypeChatController(SkypeChatMsgModel model, SkypeChatView view) {
        this.model = model;
        this.view = view;
        setListeners();
    }

    public void setListeners(){

        view.getNext().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.next();
                view.init();
                new SkypeChatController(model,view);
            }
        });

        view.getPrev().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.prev();
                view.init();
                new SkypeChatController(model,view);
            }
        });

        view.getBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MailView mailView = new MailView();
                new MailController(mailView);
            }
        });

        view.getReply().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SkypeSendModel model2 = new SkypeSendModel(model.getChat().getChatName());
                SkypeSendView view = new SkypeSendView(model2);
                new SkypeSendController(model2,view);
            }
        });
    }
}
