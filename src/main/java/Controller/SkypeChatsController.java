package Controller;

import Model.SkypeChatMsgModel;
import Model.SkypeChatsModel;
import Skype.Skype;
import Skype.SkypeMessage;
import Skype.SkypeChat;
import Threads.CameraThread;
import Threads.VoiceThread;
import View.MailView;
import View.SkypeChatView;
import View.SkypeChatsView;

import java.awt.*;
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

        view.getBack().addActionListener(e -> {
            LoginController.thread.exit = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            MailView mailView = new MailView();
            new MailController(mailView);
            view.getBack().setBackground(Color.WHITE);
            if(LoginController.user.getType()==1){
                LoginController.thread = new CameraThread(mailView.getPanel());
                LoginController.thread.start();
            } else if(LoginController.user.getType()==2){
                LoginController.voiceThread = new VoiceThread(mailView.getPanel());
                LoginController.voiceThread.start();
            }
        });

        view.getNext().addActionListener(e -> {
            LoginController.thread.exit = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            Skype.number++;
            model = new SkypeChatsModel(Skype.get());
            view.setModel(model);
            view.update();
            view.getNext().setBackground(Color.WHITE);
            //new SkypeChatsController(view,model);
            if(LoginController.user.getType()==1){
                LoginController.thread = new CameraThread(view.getPanel());
                LoginController.thread.start();
            } else if(LoginController.user.getType()==2){
                LoginController.voiceThread = new VoiceThread(view.getPanel());
                LoginController.voiceThread.setMessage("Chat: "+model.getChat().getChatName());
                LoginController.voiceThread.start();
            }
        });

        view.getPrev().addActionListener(e -> {
            LoginController.thread.exit = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            Skype.number--;
            model = new SkypeChatsModel(Skype.get());
            view.setModel(model);
            view.update();
            view.getPrev().setBackground(Color.WHITE);
            if(LoginController.user.getType()==1){
                LoginController.thread = new CameraThread(view.getPanel());
                LoginController.thread.start();
            } else if(LoginController.user.getType()==2){
                LoginController.voiceThread = new VoiceThread(view.getPanel());
                LoginController.voiceThread.setMessage("Chat: "+model.getChat().getChatName());
                LoginController.voiceThread.start();
            }
        });

        view.getRead().addActionListener(e -> {
            LoginController.thread.exit = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            SkypeChatMsgModel model2 = new SkypeChatMsgModel(model.getChat());
            SkypeChatView view2 = new SkypeChatView(model2);
            new SkypeChatController(model2,view2);
            view.getRead().setBackground(Color.WHITE);
            if(LoginController.user.getType()==1){
                LoginController.thread = new CameraThread(view2.getPanel());
                LoginController.thread.start();
            } else if(LoginController.user.getType()==2){
                LoginController.voiceThread = new VoiceThread(view2.getPanel());
                LoginController.voiceThread.setMessage("Wiadomość od "+model2.getMessage().getAuthor()+". Treść "
                        +model2.getMessage().getBody().replaceAll("\\<.*?\\>",""));
                LoginController.voiceThread.start();
            }
        });

        view.getStopButton().addActionListener(e -> {
            LoginController.thread.exit = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            LoginController.thread = new CameraThread(view.getPanel());
            LoginController.thread.start();
        });
    }
}
