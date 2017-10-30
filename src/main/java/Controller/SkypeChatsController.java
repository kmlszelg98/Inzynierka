package Controller;

import Model.SkypeChatMsgModel;
import Model.SkypeChatsModel;
import Skype.Skype;
import Skype.SkypeMessage;
import Skype.SkypeChat;
import View.MailView;
import View.SkypeChatView;
import View.SkypeChatsView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamil on 23.10.2017.
 */
public class SkypeChatsController {

    private SkypeChatsView view;
    private SkypeChatsModel model;

    public SkypeChatsController(SkypeChatsView view, SkypeChatsModel model) {
        this.view = view;
        this.model = model;
        setListeners();
    }

    public void setListeners(){

        view.getBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MailView mailView = new MailView();
                new MailController(mailView);
            }
        });

        view.getNext().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Skype.number++;
                model = new SkypeChatsModel(Skype.get());
                view.setModel(model);
                view.update();
                //new SkypeChatsController(view,model);
            }
        });

        view.getPrev().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Skype.number--;
                model = new SkypeChatsModel(Skype.get());
                view.setModel(model);
                view.update();
            }
        });

        view.getRead().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SkypeChatMsgModel model2 = new SkypeChatMsgModel(model.getChat());
                SkypeChatView view = new SkypeChatView(model2);
                new SkypeChatController(model2,view);
            }
        });
    }
}
