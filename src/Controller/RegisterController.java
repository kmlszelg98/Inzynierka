package Controller;

import Helpers.WindowHelper;
import Model.LoginModel;
import Model.RegisterModel;
import View.LoginView;
import View.MailView;
import View.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamil on 06.09.2017.
 */
public class RegisterController {

    private RegisterModel model;
    private RegisterView view;

    public RegisterController(RegisterModel model, RegisterView view) {
        this.model = model;
        this.view = view;
        setListeners();
    }

    public void setListeners(){
        WindowHelper helper = new WindowHelper();
        helper.closeWindow(view.getFrame());

        view.getRegisterButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.updateModel();
                view.getFrame().dispose();
                LoginModel model = new LoginModel();
                LoginView view = new LoginView(model);
                LoginController controller = new LoginController(model,view);
            }
        });
    }
}
