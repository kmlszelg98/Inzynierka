package View;

import Helpers.WindowHelper;
import Model.LoginModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Kamil on 04.09.2017.
 */
public class LoginView {

    private JFrame mainFrame;
    private LoginModel model;
    private JTextField nameArea;
    private JPasswordField passArea;
    private JButton loginButton;
    private JButton registerButton;
    private JPanel panel;

    public LoginView(LoginModel model) {
        this.model = model;
        init();
    }

    public void init(){

        WindowHelper helper = new WindowHelper();
        helper.setView();
        mainFrame = new JFrame();
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        int width = 600;
        int height = 400;

        Font font = new Font("Arial", Font.ITALIC, 18);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        

        JLabel nameLabel = new JLabel("NAZWA");
        nameLabel.setBounds(70,50,150,50);
        nameLabel.setFont(font.deriveFont(Font.BOLD));
        panel.add(nameLabel);

        nameArea = new JTextField(model.getName());
        nameArea.setBounds(270,60,230,30);
        nameArea.setFont(font.deriveFont(Font.BOLD));
        nameArea.setBackground(Color.LIGHT_GRAY);
        nameArea.setForeground(Color.BLACK);
        nameArea.setBorder(border);
        panel.add(nameArea);

        JLabel passLabel = new JLabel("HASLO");
        passLabel.setBounds(70,150,150,50);
        passLabel.setFont(font.deriveFont(Font.BOLD));
        panel.add(passLabel);

        passArea = new JPasswordField(model.getPassword());
        passArea.setBounds(270,160,230,30);
        passArea.setFont(font.deriveFont(Font.BOLD));
        passArea.setBackground(Color.LIGHT_GRAY);
        passArea.setForeground(Color.BLACK);
        passArea.setBorder(border);
        panel.add(passArea);

        loginButton= new JButton("LOGUJ");
        loginButton.setBounds(50,280,200,50);
        loginButton.setFont(font.deriveFont(Font.BOLD));
        panel.add(loginButton);

        registerButton= new JButton("REJESTRUJ");
        registerButton.setBounds(350,280,200,50);
        registerButton.setFont(font.deriveFont(Font.BOLD));
        panel.add(registerButton);

        mainFrame.getContentPane().add(panel);
        mainFrame.getLayeredPane().getComponent(1).setFont(new Font("Lucida",Font.PLAIN,20));
        mainFrame.setTitle("LOGIN FORM");
        mainFrame.setSize(600,400);//600x500

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setLocation(dim.width/2-width/2, dim.height/2-height/2);
        mainFrame.setVisible(true);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


    }

    public void updateModel(){
        model.setName(nameArea.getText());
        model.setPassword(String.valueOf(passArea.getPassword()));
    }

    public LoginModel getModel(){
        updateModel();
        return model;
    }

    public JFrame getMainFrame(){
        return mainFrame;
    }

    public JButton getLoginButton(){
        return loginButton;
    }

    public JButton getRegisterButton(){
        return registerButton;
    }


}
