package Controller;

import Model.InboxModel;
import Threads.CameraThread;
import View.InboxReadView;
import View.InboxView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        view.getNext().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                }
            }
        });

        view.getBack().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
                }
            }
        });
    }


}
