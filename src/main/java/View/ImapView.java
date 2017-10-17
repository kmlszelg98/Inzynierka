package View;

import Helpers.FrameHelper;
import Helpers.ViewHelper;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by Kamil on 03.10.2017.
 */
public class ImapView {

    private JPanel panel;
    private JButton inboxButton;
    private JButton sendButton;
    private JButton sentButton;
    private JButton backButton;

    public ImapView() {
        init();

    }

    private void init() {

        Font font = new Font("Arial", Font.ITALIC, 40);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        double x = dim.getHeight() / 5;

        inboxButton = new JButton("ODCZYTAJ");
        inboxButton.setBounds(300, (int) (x / 2), (int) dim.getWidth() / 2 - 200, (int) (x / 2));
        inboxButton.setFont(font.deriveFont(Font.BOLD));
        inboxButton.setBackground(Color.WHITE);
        inboxButton.setIcon(ViewHelper.setIcon("inbox.png",(int)(x/2)));
        panel.add(inboxButton);

        sendButton = new JButton("UTWÓRZ");
        sendButton.setBounds((int) (dim.getWidth() / 2 - 200), (int) (x + x / 2), (int) dim.getWidth() / 2 - 200, (int) (x / 2));
        sendButton.setFont(font.deriveFont(Font.BOLD));
        sendButton.setBackground(Color.WHITE);
        sendButton.setIcon(ViewHelper.setIcon("send.png",(int)(x/2)));
        panel.add(sendButton);

        sentButton = new JButton("WYSŁANE");
        sentButton.setBounds(300, (int) (x * 2 + x / 2), (int) dim.getWidth() / 2 - 200, (int) (x / 2));
        sentButton.setFont(font.deriveFont(Font.BOLD));
        sentButton.setBackground(Color.WHITE);
        sentButton.setIcon(ViewHelper.setIcon("sent.png",(int)(x/2)));
        panel.add(sentButton);

        backButton = new JButton("WSTECZ");
        backButton.setBounds((int) (dim.getWidth() / 2 - 200), (int) (x * 3 + x / 2), (int) dim.getWidth() / 2 - 200, (int) (x / 2));
        backButton.setFont(font.deriveFont(Font.BOLD));
        backButton.setBackground(Color.WHITE);
        backButton.setIcon(ViewHelper.setIcon("return.png",(int)(x/2)));
        panel.add(backButton);

        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();


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
