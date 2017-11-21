package Controller;

import Imap.Imap;
import Imap.MessageImap;
import Model.InboxModel;
import Model.SendMailModel;
import Threads.CameraThread;
import View.*;

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

        view.getInboxButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                LoginController.thread.exit = true;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                MessageImap temp = Imap.getNextMessage(true);
                Imap.type = 0;
                InboxModel model = new InboxModel(temp);
                InboxView view = new InboxView(model);
                new InboxController(model,view);
                if(LoginController.user.getType()==1){
                    LoginController.thread = new CameraThread(view.getPanel());
                    LoginController.thread.start();
                }
            }
        });

        view.getSendButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                LoginController.thread.exit = true;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }

                SendMailModel model = new SendMailModel();
                if(LoginController.user.getType()!=1) {
                    SendMailTopicView view = new SendMailTopicView(model);
                    new SendMailTopicController(view, model);
                } else {
                    SendWideoView view = new SendWideoView(model);
                    new SendWideoController(view,model);
                    LoginController.thread = new CameraThread(view.getPanel());
                    LoginController.thread.start();
                }
            }
        });

        view.getSentButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                LoginController.thread.exit = true;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                MessageImap temp = Imap.getNextMessage(false);
                Imap.type = 1;
                InboxModel model = new InboxModel(temp);
                InboxView view = new InboxView(model);
                new InboxController(model,view);
                if(LoginController.user.getType()==1){
                    LoginController.thread = new CameraThread(view.getPanel());
                    LoginController.thread.start();
                }
            }
        });

        view.getBackButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
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
                }
            }
        });
    }
}
