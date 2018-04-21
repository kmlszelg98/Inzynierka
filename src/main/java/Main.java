import Controller.LoginController;
import Model.LoginModel;
import View.LoginView;
import org.apache.commons.lang.SystemUtils;

import java.io.File;

public class Main {

    public static void main(String[] args) {

        String libName = "";
        if (SystemUtils.IS_OS_WINDOWS) {
            libName = "opencv_java2413.dll";
        } else if (SystemUtils.IS_OS_LINUX) {
            libName = "libopencv_java2413.so";
        }
        System.load(new File("./src/main/jars/".concat(libName)).getAbsolutePath());

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