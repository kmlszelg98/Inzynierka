package View;

import javax.swing.*;
import java.awt.*;
import java.util.*;

/**
 * Created by Kamil on 03.10.2017.
 */
public class ImapView {

    private JFrame frame;
    private JButton inboxButton;
    private JButton sendButton;
    private JButton sentButton;
    private JButton backButton;

    public ImapView() {
        init();

    }

    private void init() {

        Font font = new Font("Arial", Font.ITALIC, 40);
        frame = new JFrame();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        double x = dim.getHeight() / 5;

        inboxButton = new JButton("ODCZYTAJ");
        inboxButton.setBounds(300, (int) (x / 2), (int) dim.getWidth() / 2 - 200, (int) (x / 2));
        inboxButton.setFont(font.deriveFont(Font.BOLD));
        frame.add(inboxButton);

        sendButton = new JButton("UTWÓRZ");
        sendButton.setBounds((int) (dim.getWidth() / 2 - 200), (int) (x + x / 2), (int) dim.getWidth() / 2 - 200, (int) (x / 2));
        sendButton.setFont(font.deriveFont(Font.BOLD));
        frame.add(sendButton);

        sentButton = new JButton("WYSLANE");
        sentButton.setBounds(300, (int) (x * 2 + x / 2), (int) dim.getWidth() / 2 - 200, (int) (x / 2));
        sentButton.setFont(font.deriveFont(Font.BOLD));
        frame.add(sentButton);

        backButton = new JButton("WSTECZ");
        backButton.setBounds((int) (dim.getWidth() / 2 - 200), (int) (x * 3 + x / 2), (int) dim.getWidth() / 2 - 200, (int) (x / 2));
        backButton.setFont(font.deriveFont(Font.BOLD));
        frame.add(backButton);

        JTextArea msgField = new JTextArea();
        msgField.setEditable(false);
        frame.add(msgField);

        frame.getLayeredPane().getComponent(1).setFont(new Font("Lucida", Font.PLAIN, 20));
        frame.setTitle("Aplikacja");

        frame.setLocation(0, 0);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }

    public JFrame getFrame() {
        return frame;
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
