package Controller;

import Helpers.FrameHelper;
import Helpers.WindowHelper;
import Imap.Imap;
import Model.LoginModel;
import Model.RegisterModel;
import View.LoginView;
import View.MailView;
import View.RegisterView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;

/**
 * Created by Kamil on 04.09.2017.
 */
public class LoginController {

    private LoginModel model;
    private LoginView view;

    public LoginController(LoginModel model, LoginView view) {
        this.model = model;
        this.view = view;
        setListeners();
    }

    public void setListeners(){
        WindowHelper helper = new WindowHelper();
        helper.closeWindow(view.getMainFrame());

        view.getLoginButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                view.updateModel();
                view.getMainFrame().dispose();
                new FrameHelper();
                MailView mailView = new MailView();
                Imap imap = new Imap();
                imap.start("imap.gmail.com","imaps","szelagkamil0@gmail.com","kmlszelg");
                new MailController(mailView);
            }
        });

        view.getRegisterButton().addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                view.updateModel();
                view.getMainFrame().dispose();
                RegisterModel model = new RegisterModel();
                RegisterView view = new RegisterView(model);
                new RegisterController(model,view);
                System.out.println("Rejestracja");

            }
        });
    }
}
