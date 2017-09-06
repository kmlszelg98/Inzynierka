package Helpers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;

/**
 * Created by Kamil on 06.09.2017.
 */
public class WindowHelper {

    public void closeWindow(JFrame frame){
       frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                UIManager.put("OptionPane.minimumSize",new Dimension(250,250));
                UIManager.put("OptionPane.messageFont", new Font("Arial", Font.BOLD, 20));
                UIManager.put("OptionPane.buttonFont", new Font("Arial", Font.PLAIN, 20));
                Object options[] ={"Tak","Nie"};
                if (JOptionPane.showOptionDialog(frame, "Zakończyć działanie programu ?",
                        null, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                        null, options, options[1]) == JOptionPane.YES_OPTION){

                    System.exit(0);
                }
            }
        });
    }

    public void setView(){
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }
}
