import Controller.LoginController;
import Model.LoginModel;
import View.LoginView;
import com.google.api.services.sheets.v4.Sheets;
import google.GoogleSheetsService;
import google.SheetsService;
import org.apache.commons.lang.SystemUtils;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class Main {

    public static GoogleSheetsService googleSheetsService;

    public static void main(String[] args) throws IOException, GeneralSecurityException {

        String libName = "";
        if (SystemUtils.IS_OS_WINDOWS) {
            libName = "opencv_java2413.dll";
        } else if (SystemUtils.IS_OS_LINUX) {
            libName = "libopencv_java2413.so";
        }
        System.load(new File("./src/main/jars/".concat(libName)).getAbsolutePath());

        Imap.Imap imap = new Imap.Imap();
        imap.initialize();

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