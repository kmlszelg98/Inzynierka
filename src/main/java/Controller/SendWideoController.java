package Controller;

import Model.SendMailModel;
import Threads.CameraThread;
import Threads.VoiceThread;
import View.ImapView;
import View.SendWideoView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamil on 05.11.2017.
 */
public class SendWideoController {

    private SendWideoView view;
    private SendMailModel model;


    public SendWideoController(SendWideoView view, SendMailModel model) {
        this.view = view;
        this.model = model;
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
                if(view.getPart()<2) {
                    view.updateModel();
                    view.nextPart();
                    new SendWideoController(view, model);
                    if(LoginController.user.getType()==1) {
                        LoginController.thread = new CameraThread(view.getPanel());
                        LoginController.thread.start();
                    } else {
                        LoginController.voiceThread = new VoiceThread(view.getPanel());
                        LoginController.voiceThread.start();
                    }
                } else {
                    view.updateModel();
                    /*MailSender sender = new MailSender();
                        sender.send();*/
                    ImapView view = new ImapView();
                    new ImapController(view);
                    if(LoginController.user.getType()==1) {
                        LoginController.thread = new CameraThread(view.getPanel());
                        LoginController.thread.start();
                    } else {
                        LoginController.voiceThread = new VoiceThread(view.getPanel());
                        LoginController.voiceThread.start();
                    }
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
                ImapView view = new ImapView();
                new ImapController(view);
                if(LoginController.user.getType()==1) {
                    LoginController.thread = new CameraThread(view.getPanel());
                    LoginController.thread.start();
                } else {
                    LoginController.voiceThread = new VoiceThread(view.getPanel());
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
                }
                view.updateModel();
                view.addMoreSigns();
                //new SendWideoController(view,model);
                if(LoginController.user.getType()==1) {
                    LoginController.thread = new CameraThread(view.getPanel());
                    LoginController.thread.start();
                } else {
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
                view.updateModel();
                view.addLetters();
                //new SendWideoController(view,model);
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
