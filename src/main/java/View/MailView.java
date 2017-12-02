package View;

import Controller.LoginController;
import Helpers.FrameHelper;
import Helpers.ViewHelper;
import Helpers.WindowHelper;
import Threads.CameraThread;
import Wideo.JPanelOpenCV;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by Kamil on 06.09.2017.
 */
public class MailView {

    private JButton imapButton;
    private JButton skypeButton;
    private JButton facebookButton;
    private JButton logoutButton;
    private JPanelOpenCV panel;
    private JButton[][] buttons;
    private String[][] btn;

    public MailView() {
        init();
    }

    public void init(){

        Font font = new Font("Arial", Font.ITALIC, 40);

        panel = new JPanelOpenCV();
        buttons = new JButton[3][1];
        btn = new String[3][1];

        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        JButton temp = new JButton();
        panel.add(temp);



        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        double x = dim.getHeight()/5;

        imapButton = new JButton("MAIL");
        imapButton.setFont(font.deriveFont(Font.BOLD));
        imapButton.setBackground(Color.WHITE);
        imapButton.setIcon(ViewHelper.setIcon("mail.png",(int)(x/2)));
        panel.add(imapButton);
        buttons[0][0] = imapButton;
        btn[0][0]="/przyciski/Mail";

        skypeButton = new JButton("SKYPE");
        skypeButton.setFont(font.deriveFont(Font.BOLD));
        skypeButton.setBackground(Color.WHITE);
        skypeButton.setIcon(ViewHelper.setIcon("skype.png",(int)(x/2)));
        panel.add(skypeButton);
        buttons[1][0] = skypeButton;
        btn[1][0] = "/przyciski/Skype";

        facebookButton = new JButton("FACEBOOK");

        logoutButton = new JButton("WYLOGUJ");
        logoutButton.setFont(font.deriveFont(Font.BOLD));
        logoutButton.setBackground(Color.WHITE);
        logoutButton.setIcon(ViewHelper.setIcon("logout.png",(int)(x/3)));
        panel.add(logoutButton);
        buttons[2][0] = logoutButton;
        btn[2][0] = "/przyciski/Wyloguj";

        setBounds();
        panel.setButtons(buttons);
        panel.setBtn(btn);

        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();

    }

    private void setBounds(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        double x = dim.getHeight()/5;

        if(LoginController.user.getType()!=1) {
            imapButton.setBounds((int) (dim.getWidth() / 4), (int) (x / 2), (int) dim.getWidth() / 2, (int) (x / 2));//300
            skypeButton.setBounds((int) (dim.getWidth() / 4), (int) (x + x / 2 + x / 2), (int) dim.getWidth() / 2, (int) (x / 2));
            logoutButton.setBounds((int) (dim.getWidth() / 4), (int) (x * 3 + x / 2), (int) dim.getWidth() / 2, (int) (x / 2));
        } else {
            imapButton.setBounds((int) (dim.getWidth() / 3), (int) (x / 2), (int) dim.getWidth() / 2, (int) (x / 2));//300
            skypeButton.setBounds((int) (dim.getWidth() / 3), (int) (x + x / 2 + x / 2), (int) dim.getWidth() / 2, (int) (x / 2));
            logoutButton.setBounds((int) (dim.getWidth() / 3), (int) (x * 3 + x / 2), (int) dim.getWidth() / 2, (int) (x / 2));
        }

        /*CameraThread thread = new CameraThread(panel);
        thread.run();*/


    }



    public JPanelOpenCV getPanel() {
        return panel;
    }

    public JButton getImapButton() {
        return imapButton;
    }

    public JButton getSkypeButton() {
        return skypeButton;
    }

    public JButton getFacebookButton() {
        return facebookButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }

}
