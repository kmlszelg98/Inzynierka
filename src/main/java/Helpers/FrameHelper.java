package Helpers;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kamil on 08.10.2017.
 */
public class FrameHelper {

    public static JFrame frame;

    public FrameHelper(){
        frame = new JFrame();
        frame.getLayeredPane().getComponent(1).setFont(new Font("Lucida",Font.PLAIN,20));
        frame.setTitle("Aplikacja");

        frame.setLocation(0,0);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
}
