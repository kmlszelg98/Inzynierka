package Controller;

import Helpers.WindowHelper;
import Imap.Imap;
import Model.LoginModel;
import Skype.Skype;
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
        helper.closeWindow(view.getFrame());

        view.getFacebookButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });

        view.getImapButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });

        view.getSkypeButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

            }
        });

        view.getLogoutButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                view.getFrame().dispose();
                LoginModel model = new LoginModel();
                LoginView view = new LoginView(model);
                LoginController controller = new LoginController(model,view);
            }
        });
    }

    public static Imap getImap() {
        return imap;
    }

    public static Skype getSkype() {
        return skype;
    }
}
