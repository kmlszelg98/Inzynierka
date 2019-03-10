package Controller;

import Model.InboxModel;
import Model.SendMailModel;
import Threads.CameraThread;
import Threads.VoiceThread;
import View.InboxReadView;
import View.InboxView;
import View.SendMailTopicView;
import View.SendWideoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by Kamil on 11.10.2017.
 */
public class InboxReadController {

    private InboxReadView view;
    private InboxModel models;

    public InboxReadController(InboxReadView view, InboxModel model) {
        this.view = view;
        this.models = model;
        setListeners();
    }

    public void setListeners(){

        view.getNext().addActionListener(e -> {
            System.out.println("Next");
            LoginController.thread.exit = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            view.init();
            new InboxReadController(view,models);
            if(LoginController.user.getType()==1){
                LoginController.thread = new CameraThread(view.getPanel());
                LoginController.thread.start();
            } else if(LoginController.user.getType()==2){
                LoginController.voiceThread = new VoiceThread(view.getPanel());
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
            InboxModel model = new InboxModel(models.getMessage());
            InboxView view = new InboxView(model);
            new InboxController(model,view);
            if(LoginController.user.getType()==1){
                LoginController.thread = new CameraThread(view.getPanel());
                LoginController.thread.start();
            } else if(LoginController.user.getType()==2){
                LoginController.voiceThread = new VoiceThread(view.getPanel());
                LoginController.voiceThread.setMsg(model.getMessage());
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

        view.getMessage().addActionListener(e -> {
            CameraThread thread = new CameraThread(view.getPanel());
            thread.setEmotions(true);
            thread.start();
            int type = thread.getResults();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            while (type<0) {
                type = thread.getResults();
            }

            LoginController.thread.exit = true;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            SendMailModel model = new SendMailModel();
            model.setSubject(models.getMessage().getSubject());
            model.setTo(models.getMessage().getFrom());
            try {
                model.setBody(Imap.Imap.generateValue(models.getMessage().getSubject(),type));
            } catch (IOException e1) {
                e1.printStackTrace();
            }
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
    }


}
