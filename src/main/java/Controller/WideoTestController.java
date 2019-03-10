package Controller;

import Helpers.FrameHelper;
import Imap.Imap;
import Model.LoginModel;
import Model.SkypeChatsModel;
import Skype.Skype;
import Threads.CameraThread;
import Threads.VoiceThread;
import View.*;

/**
 * Created by Kamil on 06.09.2017.
 */
public class WideoTestController {

    private WideoTestView view;
    private CameraThread cameraThread;

    public WideoTestController(WideoTestView view) {
        this.view = view;
        setListeners();
    }

    public void setCameraThread(CameraThread cameraThread) {
        this.cameraThread = cameraThread;
    }

    public void setListeners(){
        /*WindowHelper helper = new WindowHelper();
        helper.closeWindow(FrameHelper.frame);*/

        view.getBack().addActionListener(e -> {
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
        });

        view.getPhoto().addActionListener(e -> {
            cameraThread.setEmotions(true);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
            int type = cameraThread.getResults();
            while (type<0) {
                type = cameraThread.getResults();
            }
            /*TODO*/
            view.getBody().setText("Positive");
            view.refresh();
        });

    }
}
