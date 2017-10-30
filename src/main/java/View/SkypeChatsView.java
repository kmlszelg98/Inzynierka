package View;

import Helpers.FrameHelper;
import Helpers.ViewHelper;
import Model.SkypeChatsModel;
import Skype.Skype;
import Skype.SkypeChat;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Kamil on 23.10.2017.
 */
public class SkypeChatsView {

    private SkypeChatsModel model;
    private JPanel panel;
    private JLabel name;
    private JButton next;
    private JButton prev;
    private JButton read;
    private JButton back;

    private Font font2;
    private Border border;
    private Dimension dim;

    public SkypeChatsView(SkypeChatsModel model) {
        this.model = model;
        init();
    }

    public void init(){
        Font font = new Font("Arial", Font.ITALIC, 40);
        font2 = new Font("Arial", Font.ITALIC, 50);
        border = BorderFactory.createLineBorder(Color.BLACK, 1);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        dim = Toolkit.getDefaultToolkit().getScreenSize();
        double x = dim.getHeight()/5;

        name = new JLabel("OD: "+model.getChat().getChatName(),SwingConstants.CENTER);
        name.setBorder(border);
        name.setBounds(50,200,(int)(dim.getWidth()-100),200);
        name.setFont(font2.deriveFont(Font.BOLD));
        panel.add(name);

        double x2 = (dim.getHeight()-600)/3;
        double w = dim.getWidth()/2-200;

        read = new JButton("CHAT");
        read.setBounds((int)(dim.getWidth()/2-w/2),(int)(450 + x2/2),(int)w,(int)(x2/2));
        read.setFont(font.deriveFont(Font.BOLD));
        read.setBackground(Color.WHITE);
        read.setIcon(ViewHelper.setIcon("chat.png",(int)(x2/2.5)));
        panel.add(read);

        next = new JButton("NASTĘPNY");
        next.setBounds((int)(dim.getWidth()/2+100),(int)(450 + x2+ x2/2),(int)w,(int)(x2/2));
        next.setFont(font.deriveFont(Font.BOLD));
        next.setBackground(Color.WHITE);
        next.setIcon(ViewHelper.setIcon("next.png",(int)(x2/2)));
        if(!Skype.isLast()) next.setEnabled(true);
        else next.setEnabled(false);
        panel.add(next);

        prev = new JButton("POPRZEDNI");
        prev.setBounds(100,(int)(450 + x2+ x2/2),(int)w,(int)(x2/2));
        prev.setFont(font.deriveFont(Font.BOLD));
        prev.setBackground(Color.WHITE);
        prev.setIcon(ViewHelper.setIcon("prev.png",(int)(x2/2)));
        if(!Skype.isFirst()) prev.setEnabled(true);
        else prev.setEnabled(false);
        panel.add(prev);

        back = new JButton("WSTECZ");
        back.setBounds((int)(dim.getWidth()/2-w/2),(int)(450 +x2*2 + x2/2),(int)w,(int)(x2/2));
        back.setFont(font.deriveFont(Font.BOLD));
        back.setBackground(Color.WHITE);
        back.setIcon(ViewHelper.setIcon("return.png",(int)(x2/2)));
        panel.add(back);

        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();
    }

    public void update(){
        panel.remove(name);
        name = new JLabel("OD: "+model.getChat().getChatName(),SwingConstants.CENTER);
        name.setBorder(border);
        name.setBounds(50,200,(int)(dim.getWidth()-100),200);
        name.setFont(font2.deriveFont(Font.BOLD));
        panel.add(name);

        if(!Skype.isLast()) next.setEnabled(true);
        else next.setEnabled(false);

        if(!Skype.isFirst()) prev.setEnabled(true);
        else prev.setEnabled(false);


        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();

    }

    public void setModel(SkypeChatsModel model) {
        this.model = model;
    }

    public JButton getNext() {
        return next;
    }

    public JButton getPrev() {
        return prev;
    }

    public JButton getRead() {
        return read;
    }

    public JButton getBack() {
        return back;
    }
}
