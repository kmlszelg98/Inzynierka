import Controller.LoginController;
import Imap.MailSender;
import Model.LoginModel;
import Skype.Skype;
import Skype.SkypeConn.SkypeConnection;
import Threads.VoiceThread;
import View.LoginView;
import Wideo.JPanelOpenCV;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.opencv.core.Core;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Arrays;

public class Main {

    /*private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();

    }*/

    public static void main(String[] args) {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

        LoginModel model = new LoginModel();
        LoginView view = new LoginView(model);
        new LoginController(model,view);

        /*VoiceThread thread = new VoiceThread(new JPanelOpenCV());
        thread.run();*/

        /*Skype skype = new Skype();
        skype.init("kmlszelg98");
        System.out.println("Finish");*/

        /*Imap.Imap imap = new Imap.Imap();
        imap.start("","","","");*/


    }
}