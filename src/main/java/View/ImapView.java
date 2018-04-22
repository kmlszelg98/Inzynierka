package View;

import Controller.LoginController;
import Helpers.FrameHelper;
import Helpers.ViewHelper;
import Wideo.JPanelOpenCV;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by Kamil on 03.10.2017.
 */
public class ImapView {

    private JPanelOpenCV panel;
    private JButton inboxButton;
    private JButton sendButton;
    private JButton sentButton;
    private JButton backButton;
    private JButton stopButton;
    private JButton[][] buttons;
    private String[][] btn;

    public ImapView() {
        init();

    }

    private void init() {

        buttons = new JButton[5][1];
        btn = new String[4][1];
        Font font = new Font("Arial", Font.ITALIC, 40);

        panel = new JPanelOpenCV();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        double x = dim.getHeight() / 5;

        inboxButton = new JButton("ODCZYTAJ");
        inboxButton.setFont(font.deriveFont(Font.BOLD));
        inboxButton.setBackground(Color.WHITE);
        inboxButton.setIcon(ViewHelper.setIcon("inbox.png",(int)(x/2)));
        panel.add(inboxButton);
        buttons[0][0] = inboxButton;
        btn[0][0] = "/przyciski/Odczytaj";

        sendButton = new JButton("UTWORZ");
        sendButton.setFont(font.deriveFont(Font.BOLD));
        sendButton.setBackground(Color.WHITE);
        sendButton.setIcon(ViewHelper.setIcon("send.png",(int)(x/2)));
        panel.add(sendButton);
        buttons[1][0] = sendButton;
        btn[1][0] = "/przyciski/Utwórz";

        sentButton = new JButton("WYSLANE");
        sentButton.setFont(font.deriveFont(Font.BOLD));
        sentButton.setBackground(Color.WHITE);
        sentButton.setIcon(ViewHelper.setIcon("sent.png",(int)(x/2)));
        panel.add(sentButton);
        buttons[2][0] = sentButton;
        btn[2][0] = "/przyciski/Wysłane";

        backButton = new JButton("WSTECZ");
        backButton.setFont(font.deriveFont(Font.BOLD));
        backButton.setBackground(Color.WHITE);
        backButton.setIcon(ViewHelper.setIcon("return.png",(int)(x/2)));
        panel.add(backButton);
        buttons[3][0] =  backButton;
        btn[3][0] = "/przyciski/Wstecz";

        stopButton = new JButton("STOP");
        stopButton.setFont(font.deriveFont(Font.BOLD));
        stopButton.setBackground(Color.WHITE);
        stopButton.setIcon(ViewHelper.setIcon("stop.png",(int)(x/2)));
        buttons[4][0] = stopButton;

        panel.setButtons(buttons);
        panel.setBtn(btn);

        setBounds();
        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();


    }

    private void setBounds(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        double x = dim.getHeight()/5;

        if(LoginController.user.getType()!=1) {
            inboxButton.setBounds(300, (int) (x / 2), (int) dim.getWidth() / 2 - 200, (int) (x / 2));
            sendButton.setBounds((int) (dim.getWidth() / 2 - 200), (int) (x + x / 2), (int) dim.getWidth() / 2 - 200, (int) (x / 2));
            sentButton.setBounds(300, (int) (x * 2 + x / 2), (int) dim.getWidth() / 2 - 200, (int) (x / 2));
            backButton.setBounds((int) (dim.getWidth() / 2 - 200), (int) (x * 3 + x / 2), (int) dim.getWidth() / 2 - 200, (int) (x / 2));
        } else {
            inboxButton.setBounds((int) (dim.getWidth() / 3), (int) (x/2), (int) dim.getWidth() / 2, (int) (x / 2));//300
            sendButton.setBounds((int) (dim.getWidth() / 3), (int) (3*x/4 + x/2), (int) dim.getWidth() / 2, (int) (x / 2));
            sentButton.setBounds((int) (dim.getWidth() / 3), (int) (3*x/4 * 2 + x/2), (int) dim.getWidth() / 2, (int) (x / 2));
            backButton.setBounds((int) (dim.getWidth() / 3), (int) (3*x/4 * 3 + x/2), (int) dim.getWidth() / 2, (int) (x / 2));
            stopButton.setBounds((int) (dim.getWidth() / 3), (int) (3*x/4 * 4 + x/2), (int) dim.getWidth() / 2, (int) (x / 2));
            panel.add(stopButton);
        }

    }

    public JButton getStopButton() {
        return stopButton;
    }

    public JPanelOpenCV getPanel() {
        return panel;
    }

    public JButton getInboxButton() {
        return inboxButton;
    }

    public JButton getSendButton() {
        return sendButton;
    }

    public JButton getSentButton() {
        return sentButton;
    }

    public JButton getBackButton() {
        return backButton;
    }
}
