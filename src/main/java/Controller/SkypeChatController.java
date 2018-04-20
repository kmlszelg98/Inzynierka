package Controller;

import Model.SkypeChatMsgModel;
import Model.SkypeChatsModel;
import Model.SkypeSendModel;
import Skype.Skype;
import Threads.CameraThread;
import Threads.VoiceThread;
import View.MailView;
import View.SkypeChatView;
import View.SkypeChatsView;
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

        view.getNext().addActionListener(e -> {
            LoginController.thread.exit = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            model.next();
            view.init();
            new SkypeChatController(model,view);
            if(LoginController.user.getType()==1){
                LoginController.thread = new CameraThread(view.getPanel());
                LoginController.thread.start();
            } else if(LoginController.user.getType()==2){
                LoginController.voiceThread = new VoiceThread(view.getPanel());
                LoginController.voiceThread.setMessage("Wiadomość od "+model.getMessage().getAuthor()+". Treść "
                        +model.getMessage().getBody().replaceAll("\\<.*?\\>",""));
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
            model.prev();
            view.init();
            new SkypeChatController(model,view);
            if(LoginController.user.getType()==1){
                LoginController.thread = new CameraThread(view.getPanel());
                LoginController.thread.start();
            }else if(LoginController.user.getType()==2){
                LoginController.voiceThread = new VoiceThread(view.getPanel());
                LoginController.voiceThread.setMessage("Wiadomość od "+model.getMessage().getAuthor()+". Treść "
                        +model.getMessage().getBody().replaceAll("\\<.*?\\>",""));
                LoginController.voiceThread.start();
            }
        });

        view.getBack().addActionListener(e -> {
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
            }else if(LoginController.user.getType()==2){
                LoginController.voiceThread = new VoiceThread(view.getPanel());
                LoginController.voiceThread.setMessage("Chat: "+model.getChat().getChatName());
                LoginController.voiceThread.start();
            }
        });

        view.getReply().addActionListener(e -> {
            LoginController.thread.exit = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            SkypeSendModel model2 = new SkypeSendModel(model.getChat().getChatName());
            SkypeSendView view = new SkypeSendView(model2);
            new SkypeSendController(model2,view);
            if(LoginController.user.getType()==1){
                LoginController.thread = new CameraThread(view.getPanel());
                LoginController.thread.start();
            } else if(LoginController.user.getType()==2){
                LoginController.voiceThread = new VoiceThread(view.getPanel());
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
            LoginController.thread.setEmotions(true);
            LoginController.thread.start();
        });
    }
}
