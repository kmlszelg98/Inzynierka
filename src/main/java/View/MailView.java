package View;

import Helpers.FrameHelper;
import Helpers.WindowHelper;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kamil on 06.09.2017.
 */
public class MailView {

    private JButton imapButton;
    private JButton skypeButton;
    private JButton facebookButton;
    private JButton logoutButton;
    private JPanel panel;

    public MailView() {
        init();
    }

    public void init(){


        Font font = new Font("Arial", Font.ITALIC, 40);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        double x = dim.getHeight()/5;

        imapButton = new JButton("MAIL");
        imapButton.setBounds(300,(int)(x/2),(int) dim.getWidth()/2-200,(int)(x/2));
        imapButton.setFont(font.deriveFont(Font.BOLD));
        panel.add(imapButton);

        skypeButton = new JButton("SKYPE");
        skypeButton.setBounds((int)(dim.getWidth()/2-200),(int)(x+x/2),(int) dim.getWidth()/2-200,(int)(x/2));
        skypeButton.setFont(font.deriveFont(Font.BOLD));
        panel.add(skypeButton);

        facebookButton = new JButton("FACEBOOK");
        facebookButton.setBounds(300,(int)(x*2+x/2),(int) dim.getWidth()/2-200,(int)(x/2));
        facebookButton.setFont(font.deriveFont(Font.BOLD));
        panel.add(facebookButton);

        logoutButton = new JButton("WYLOGUJ");
        logoutButton.setBounds((int)(dim.getWidth()/2-200),(int)(x*3+x/2),(int) dim.getWidth()/2-200,(int)(x/2));
        logoutButton.setFont(font.deriveFont(Font.BOLD));
        panel.add(logoutButton);

        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();

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
