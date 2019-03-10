package View;

import Controller.LoginController;
import Helpers.FrameHelper;
import Helpers.ViewHelper;
import Wideo.JPanelOpenCV;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Kamil on 06.09.2017.
 */
public class WideoTestView {

    private JButton back;
    private JButton photo;
    private JPanelOpenCV panel;
    private JTextArea body;
    private JScrollPane bodyPane;
    private Dimension dim;

    public WideoTestView() {
        init();
    }

    public void init(){

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Font font = new Font("Arial", Font.ITALIC, 40);
        Font font2 = new Font("Arial", Font.ITALIC, 50);
        dim = Toolkit.getDefaultToolkit().getScreenSize();
        double x = dim.getHeight()/5;
        panel = new JPanelOpenCV();
        panel.setTest(true);

        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        double x2 = (dim.getHeight()-600)/3;

        body = new JTextArea();//"",SwingConstants.CENTER);
        body.setFont(font2.deriveFont(Font.BOLD));
        body.setLineWrap(true);
        body.setEditable(false);
        body.setBackground(Color.white);

        bodyPane = new JScrollPane(body);
        bodyPane.setBorder(border);
        bodyPane.setBounds(150+(int)dim.getWidth()-1000, 300, 800, 100);
        panel.add(bodyPane);


        back = new JButton("BACK");
        back.setFont(font.deriveFont(Font.BOLD));
        back.setBackground(Color.WHITE);
        back.setIcon(ViewHelper.setIcon("return.png",(int)(x2/2)));

        back.setBounds(150+(int)dim.getWidth()-1000, 600, 800, 100);
        panel.add(back);

        photo = new JButton("PHOTO");
        photo.setFont(font.deriveFont(Font.BOLD));
        photo.setBackground(Color.WHITE);
        photo.setIcon(ViewHelper.setIcon("scroll.png",(int)(x2/2)));

        photo.setBounds(150+(int)dim.getWidth()-1000, 450, 800, 100);
        panel.add(photo);

        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();

    }

    public JPanelOpenCV getPanel() {
        return panel;
    }

    public JButton getBack() {
        return back;
    }

    public JButton getPhoto() {
        return photo;
    }

    public JScrollPane getBodyPane() {
        return bodyPane;
    }

    public JTextArea getBody() {
        return body;
    }

    public void refresh() {
        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();
    }
}
