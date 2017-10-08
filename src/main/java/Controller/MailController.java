package Controller;

import Helpers.FrameHelper;
import Helpers.WindowHelper;
import Imap.Imap;
import Model.LoginModel;
import Skype.Skype;
import View.ImapView;
import View.LoginView;
import View.MailView;

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
        WindowHelper helper = new WindowHelper();
        helper.closeWindow(FrameHelper.frame);

        view.getFacebookButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });

        view.getImapButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                ImapView view = new ImapView();
                new ImapController(view);
            }
        });

        view.getSkypeButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });

        view.getLogoutButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
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
