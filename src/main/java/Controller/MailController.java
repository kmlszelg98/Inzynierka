package Controller;

import Helpers.FrameHelper;
import Helpers.WindowHelper;
import Imap.Imap;
import Model.LoginModel;
import Model.SkypeChatsModel;
import Skype.Skype;
import Threads.CameraThread;
import Threads.VoiceThread;
import View.ImapView;
import View.LoginView;
import View.MailView;
import View.SkypeChatsView;
import sun.rmi.runtime.Log;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamil on 06.09.2017.
 */
public class MailController {

    private MailView view;
    private static Imap imap;
    private static Skype skype;
    private ImapController imapController;

    static {
        //imap = new Imap();
        //skype = new Skype();
    }


    public MailController(MailView view) {
        this.view = view;

        setListeners();
    }

    public void setListeners(){
        /*WindowHelper helper = new WindowHelper();
        helper.closeWindow(FrameHelper.frame);*/

        view.getFacebookButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });

        view.getImapButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(LoginController.user.getType()==1) {
                    LoginController.thread.exit = true;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
                    }
                    ImapView view = new ImapView();
                    new ImapController(view);
                    LoginController.thread = new CameraThread(view.getPanel());
                    LoginController.thread.start();
                } else if(LoginController.user.getType()==2){
                    ImapView view = new ImapView();
                    new ImapController(view);
                    LoginController.voiceThread = new VoiceThread(view.getPanel());
                    LoginController.voiceThread.start();
                } else {
                    ImapView view = new ImapView();
                    new ImapController(view);
                }
            }
        });

        view.getSkypeButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if(LoginController.user.getType()==1) {
                    LoginController.thread.exit = true;
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e2) {
                        e2.printStackTrace();
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
                    LoginController.voiceThread.setMessage("Chat : "+model.getChat().getChatName());
                    LoginController.voiceThread.start();
                }
            }
        });

        view.getLogoutButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                LoginController.thread.exit = true;
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
                FrameHelper.frame.dispose();
                LoginModel model = new LoginModel();
                LoginView view = new LoginView(model);
                new LoginController(model,view);
            }
        });
    }

    public void setImapController(ImapController imapController) {
        this.imapController = imapController;
    }

    public static Imap getImap() {
        return imap;
    }

    public static Skype getSkype() {
        return skype;
    }
}
