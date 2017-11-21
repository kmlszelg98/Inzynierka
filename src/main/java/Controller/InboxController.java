package Controller;

import Helpers.FrameHelper;
import Helpers.WindowHelper;
import Imap.Imap;
import Imap.MessageImap;
import Model.InboxModel;
import Threads.CameraThread;
import View.ImapView;
import View.InboxReadView;
import View.InboxView;

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

        view.getNext().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Next");
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
                }
            }
        });

        view.getPrev().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Prev");
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
                }
            }
        });

        view.getBack().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
                }
            }
        });

        view.getRead().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                }
            }
        });
    }
}
