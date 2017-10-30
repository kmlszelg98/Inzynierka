package Controller;

import Model.SkypeChatMsgModel;
import Model.SkypeSendModel;
import Skype.Skype;
import Skype.SkypeConn.SkypeConnection;
import View.SkypeChatView;
import View.SkypeSendView;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.user.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kamil on 30.10.2017.
 */
public class SkypeSendController {

    private SkypeSendModel model;
    private SkypeSendView view;

    public SkypeSendController(SkypeSendModel model, SkypeSendView view) {
        this.model = model;
        this.view = view;
        setListeners();
    }

    public void setListeners(){

        view.getAccept().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.update();
                System.out.println(model.getChatName());
                List<Chat> list = SkypeConnection.getChatList();
                for (Chat ch: list){
                    Collection<User> users = ch.getAllUsers();
                    for(User u: users){
                        try {
                            if(u.getContact().getDisplayName().equals(model.getChatName())){
                                ch.sendMessage(model.getMessage());
                            }
                        } catch (ConnectionException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
            }
        });

        view.getCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SkypeChatMsgModel model1 = new SkypeChatMsgModel(Skype.get());
                SkypeChatView view1 = new SkypeChatView(model1);
                new SkypeChatController(model1,view1);
            }
        });
    }

}
