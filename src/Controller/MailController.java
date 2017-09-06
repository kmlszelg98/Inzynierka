package Controller;

import Helpers.WindowHelper;
import View.MailView;

/**
 * Created by Kamil on 06.09.2017.
 */
public class MailController {

    private MailView view;

    public MailController(MailView view) {
        this.view = view;
        setListeners();
    }

    public void setListeners(){
        WindowHelper helper = new WindowHelper();
        helper.closeWindow(view.getFrame());
    }
}
