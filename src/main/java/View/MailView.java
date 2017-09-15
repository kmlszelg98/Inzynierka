package View;

import Helpers.WindowHelper;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kamil on 06.09.2017.
 */
public class MailView {

    private JFrame frame;
    private JButton imapButton;
    private JButton skypeButton;
    private JButton facebookButton;
    private JButton logoutButton;

    public MailView() {
        init();
    }

    public void init(){

        Font font = new Font("Arial", Font.ITALIC, 18);
        frame = new JFrame();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        double x = dim.getHeight()/5;

        imapButton = new JButton("MAIL");
        imapButton.setBounds(300,(int)(x/2),(int) dim.getWidth()/2-200,(int)(x/2));
        imapButton.setFont(font.deriveFont(Font.BOLD));
        frame.add(imapButton);

        skypeButton = new JButton("SKYPE");
        skypeButton.setBounds((int)(dim.getWidth()/2-200),(int)(x+x/2),(int) dim.getWidth()/2-200,(int)(x/2));
        skypeButton.setFont(font.deriveFont(Font.BOLD));
        frame.add(skypeButton);

        facebookButton = new JButton("FACEBOOK");
        facebookButton.setBounds(300,(int)(x*2+x/2),(int) dim.getWidth()/2-200,(int)(x/2));
        facebookButton.setFont(font.deriveFont(Font.BOLD));
        frame.add(facebookButton);

        logoutButton = new JButton("WYLOGUJ");
        logoutButton.setBounds((int)(dim.getWidth()/2-200),(int)(x*3+x/2),(int) dim.getWidth()/2-200,(int)(x/2));
        logoutButton.setFont(font.deriveFont(Font.BOLD));
        frame.add(logoutButton);

        JTextArea msgField = new JTextArea();
        msgField.setEditable(false);
        frame.add(msgField);

        frame.getLayeredPane().getComponent(1).setFont(new Font("Lucida",Font.PLAIN,20));
        frame.setTitle("LOGIN FORM");

        frame.setLocation(0,0);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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

    public JFrame getFrame() {
        return frame;
    }
}
