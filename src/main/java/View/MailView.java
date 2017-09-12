package View;

import Helpers.WindowHelper;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kamil on 06.09.2017.
 */
public class MailView {

    private JFrame frame;

    public MailView() {
        init();
    }

    public void init(){
        WindowHelper helper = new WindowHelper();
        helper.setView();
        frame = new JFrame();

        frame.getLayeredPane().getComponent(1).setFont(new Font("Lucida",Font.PLAIN,20));
        frame.setTitle("MAIN PAGE");

        frame.setLocation(0,0);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }

    public JFrame getFrame() {
        return frame;
    }
}
