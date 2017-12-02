package Controller;

import Model.SkypeChatMsgModel;
import Model.SkypeChatsModel;
import Model.SkypeSendModel;
import Skype.Skype;
import Skype.SkypeConn.SkypeConnection;
import Threads.CameraThread;
import Threads.VoiceThread;
import View.SkypeChatView;
import View.SkypeChatsView;
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
                LoginController.thread.exit = true;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
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
                Skype skype = new Skype();
                skype.init(LoginController.user.getSkypeName());
                Skype.number = 0;
                SkypeChatsModel model = new SkypeChatsModel(Skype.get());
                SkypeChatsView view = new SkypeChatsView(model);
                new SkypeChatsController(view,model);
                if(LoginController.user.getType()==1){
                    LoginController.thread = new CameraThread(view.getPanel());
                    LoginController.thread.start();
                } else if(LoginController.user.getType()==2){
                    LoginController.voiceThread = new VoiceThread(view.getPanel());
                    LoginController.voiceThread.setMessage("Chat: "+model.getChat().getChatName());
                    LoginController.voiceThread.start();
                }
            }
        });

        view.getCancel().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController.thread.exit = true;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                SkypeChatsModel model = new SkypeChatsModel(Skype.get());
                SkypeChatsView view = new SkypeChatsView(model);
                new SkypeChatsController(view,model);
                if(LoginController.user.getType()==1){
                    LoginController.thread = new CameraThread(view.getPanel());
                    LoginController.thread.start();
                } else if(LoginController.user.getType()==2){
                    LoginController.voiceThread = new VoiceThread(view.getPanel());
                    LoginController.voiceThread.setMessage("Chat: "+model.getChat().getChatName());
                    LoginController.voiceThread.start();
                }
            }
        });

        view.getMore().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController.thread.exit = true;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }view.update();
                view.addMoreSigns();
                if(LoginController.user.getType()==1){
                    LoginController.thread = new CameraThread(view.getPanel());
                    LoginController.thread.start();
                } else if(LoginController.user.getType()==2){
                    LoginController.voiceThread = new VoiceThread(view.getPanel());
                    LoginController.voiceThread.start();
                }
            }
        });

        view.getAbc().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController.thread.exit = true;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                view.update();
                view.addLetters();
                if(LoginController.user.getType()==1){
                    LoginController.thread = new CameraThread(view.getPanel());
                    LoginController.thread.start();
                } else if(LoginController.user.getType()==2){
                    LoginController.voiceThread = new VoiceThread(view.getPanel());
                    LoginController.voiceThread.start();
                }
            }
        });
    }

}
