import Controller.LoginController;
import Model.LoginModel;
import View.LoginView;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Arrays;

public class Main {

    //private static final SessionFactory ourSessionFactory;

    /*static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }*/

    /*public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }*/

    public static void main(String[] args) {


        LoginModel model = new LoginModel();
        LoginView view = new LoginView(model);
        new LoginController(model,view);
        /*Imap.Imap imap = new Imap.Imap();
        imap.start("","","","");*/


    }
}