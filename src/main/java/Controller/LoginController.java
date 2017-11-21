package Controller;

import Helpers.FrameHelper;
import Helpers.User;
import Helpers.WindowHelper;
import Imap.Imap;
import Model.LoginModel;
import Model.RegisterModel;
import Skype.SkypeConn.SkypeConnection;
import Threads.CameraThread;
import View.LoginView;
import View.MailView;
import View.RegisterView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.util.Scanner;

/**
 * Created by Kamil on 04.09.2017.
 */
public class LoginController {

    private LoginModel model;
    private LoginView view;
    public static User user;
    public static CameraThread thread;

    public LoginController(LoginModel model, LoginView view) {
        this.model = model;
        this.view = view;
        user = new User();
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
                user = new User();
                System.out.println("Wybierz typ: 0-normalny 1-wideo 2-dzwiek");
                Scanner scanner = new Scanner(System.in);
                int temp = scanner.nextInt();
                user.setType(temp);
                MailView mailView = new MailView();
                SkypeConnection connection = new SkypeConnection(user.getSkypeName(),user.getSkypePass());
                connection.printChats();
                Imap imap = new Imap();
                imap.start("imap.gmail.com","imaps",user.getMailName(),user.getMailPass());
                new MailController(mailView);
                if(user.getType()==1) {
                    thread = new CameraThread(mailView.getPanel());
                    thread.start();
                }

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
