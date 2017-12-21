package Controller;

import Helpers.FrameHelper;
import Helpers.WindowHelper;
import Imap.Imap;
import Imap.MessageImap;
import Model.InboxModel;
import Threads.CameraThread;
import Threads.VoiceThread;
import View.ImapView;
import View.InboxReadView;
import View.InboxView;
import Wideo.Player;
import com.sun.mail.imap.IMAPMessage;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamil on 05.10.2017.
 */
public class InboxController {

    private InboxModel model;
    private InboxView view;

    public InboxController(InboxModel model, InboxView view) {
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
            if(Imap.type==0) {
                if (Imap.id < (Imap.messages.length - 1)) {
                    model.setMessage(Imap.getNextMessage(true));
                    view.update();
                }
            } else {
                if (Imap.id < (Imap.sentMessages.length - 1)) {
                    model.setMessage(Imap.getNextMessage(false));
                    view.update();
                }
            }

            if(LoginController.user.getType()==1){
                LoginController.thread = new CameraThread(view.getPanel());
                LoginController.thread.start();
            } else if(LoginController.user.getType()==2){
                LoginController.voiceThread = new VoiceThread(view.getPanel());
                LoginController.voiceThread.setMsg(model.getMessage());
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
            if(Imap.type==0) {
                if (Imap.id > 0) {
                    model.setMessage(Imap.getPrevMessage(true));
                    view.update();
                }
            } else {
                if (Imap.id > 0) {
                    model.setMessage(Imap.getPrevMessage(false));
                    view.update();
                }
            }

            if(LoginController.user.getType()==1){
                LoginController.thread = new CameraThread(view.getPanel());
                LoginController.thread.start();
            } else if(LoginController.user.getType()==2){
                LoginController.voiceThread = new VoiceThread(view.getPanel());
                LoginController.voiceThread.setMsg(model.getMessage());
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
            Imap.id=0;
            ImapView view = new ImapView();
            new ImapController(view);
            if(LoginController.user.getType()==1){
                LoginController.thread = new CameraThread(view.getPanel());
                LoginController.thread.start();
            } else if(LoginController.user.getType()==2){
                LoginController.voiceThread = new VoiceThread(view.getPanel());
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
            InboxReadView view = new InboxReadView(model);
            new InboxReadController(view,model);
            if(LoginController.user.getType()==1){
                LoginController.thread = new CameraThread(view.getPanel());
                LoginController.thread.start();
            } else if(LoginController.user.getType()==2){
                LoginController.voiceThread = new VoiceThread(view.getPanel());
                LoginController.voiceThread.setMsg(model.getMessage());
                LoginController.voiceThread.setRead(true);
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
