package Controller;

import Imap.Imap;
import Imap.MessageImap;
import Model.InboxModel;
import Model.SendMailModel;
import Threads.CameraThread;
import Threads.VoiceThread;
import View.*;
import Wideo.Player;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamil on 03.10.2017.
 */
public class ImapController {

    private ImapView view;

    public ImapController(ImapView view) {
        this.view = view;
        setListeners();
    }

    public void setListeners(){

        view.getInboxButton().addActionListener(e -> {
            LoginController.thread.exit = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            Imap.type = 0;
            MessageImap temp = Imap.getNextMessage(true);
            InboxModel model = new InboxModel(temp);
            InboxView view = new InboxView(model);
            new InboxController(model,view);
            if(LoginController.user.getType()==1){
                LoginController.thread = new CameraThread(view.getPanel());
                LoginController.thread.start();
            } else if(LoginController.user.getType()==2){
                LoginController.voiceThread = new VoiceThread(view.getPanel());
                LoginController.voiceThread.setMsg(temp);
                LoginController.voiceThread.start();
            }
        });

        view.getSendButton().addActionListener(e -> {
            LoginController.thread.exit = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }

            SendMailModel model = new SendMailModel();
            if(LoginController.user.getType()==0) {
                SendMailTopicView view = new SendMailTopicView(model);
                new SendMailTopicController(view, model);
            } else {
                SendWideoView view = new SendWideoView(model);
                new SendWideoController(view,model);
                if(LoginController.user.getType()==1) {
                    LoginController.thread = new CameraThread(view.getPanel());
                    LoginController.thread.start();
                } else {
                    LoginController.voiceThread = new VoiceThread(view.getPanel());
                    LoginController.voiceThread.start();

                }
            }
        });

        view.getSentButton().addActionListener(e -> {
            LoginController.thread.exit = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            Imap.type = 1;
            MessageImap temp = Imap.getNextMessage(false);
            InboxModel model = new InboxModel(temp);
            InboxView view = new InboxView(model);
            new InboxController(model,view);
            if(LoginController.user.getType()==1){
                LoginController.thread = new CameraThread(view.getPanel());
                LoginController.thread.start();
            } else if(LoginController.user.getType()==2){
                LoginController.voiceThread = new VoiceThread(view.getPanel());
                LoginController.voiceThread.setMsg(temp);
                LoginController.voiceThread.start();
            }
        });

        view.getBackButton().addActionListener(e -> {
            LoginController.thread.exit = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            MailView view = new MailView();
            new MailController(view);
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
