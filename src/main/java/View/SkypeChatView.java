package View;

import Helpers.FrameHelper;
import Helpers.ViewHelper;
import Model.SkypeChatMsgModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Kamil on 24.10.2017.
 */
public class SkypeChatView {

    private SkypeChatMsgModel model;
    private JTextField name;
    private JPanel panel;
    private JTextArea body;
    private JButton next;
    private JButton prev;
    private JButton scroll;
    private JButton reply;
    private JButton back;

    public SkypeChatView(SkypeChatMsgModel model){
        this.model=model;
        init();
    }


    public void init(){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Font font = new Font("Arial", Font.ITALIC, 40);
        Font font2 = new Font("Arial", Font.ITALIC, 50);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        name = new JTextField(model.getMessage().getAuthor());
        name.setBorder(border);
        name.setFont(font2.deriveFont(Font.BOLD));
        name.setBackground(Color.WHITE);
        name.setBounds(50,25,(int)(dim.getWidth()-100),100);

        panel.add(name);

        body = new JTextArea(model.getMessage().getBody().replaceAll("\\<.*?\\>",""));//"",SwingConstants.CENTER);
        //body.setBorder(border);
        body.setFont(font2.deriveFont(Font.BOLD));
        body.setLineWrap(true);
        body.setEditable(false);
        body.setBackground(Color.white);
        body.setBounds(50,150,(int)(dim.getWidth()-100),(int)(dim.getHeight()-600));

        JScrollPane pane = new JScrollPane(body);
        pane.setBounds(50,150,(int)(dim.getWidth()-100),(int)(dim.getHeight()-600));
        pane.setBorder(border);
        panel.add(pane);

        //panel.add(body);

        double w = (dim.getWidth()-100)/3;

        next = new JButton("NASTĘPNA");
        next.setBounds((int)(50+2*w+w/16),(int)(dim.getHeight()-400),(int)(w/2+w/4+w/8),100);
        next.setFont(font.deriveFont(Font.BOLD));
        next.setBackground(Color.WHITE);
        next.setIcon(ViewHelper.setIcon("right-arrow.png",100));
        if(model.checkLast()) next.setEnabled(true);
        else next.setEnabled(false);
        panel.add(next);

        prev = new JButton("POPRZEDNIA");
        prev.setBounds((int)(50+w/16),(int)(dim.getHeight()-400),(int)(w/2+w/4+w/8),100);
        prev.setFont(font.deriveFont(Font.BOLD));
        prev.setBackground(Color.WHITE);
        prev.setIcon(ViewHelper.setIcon("left-arrow.png",100));
        if(model.checkFirst()) prev.setEnabled(true);
        else prev.setEnabled(false);
        panel.add(prev);

        /*scroll = new JButton("PRZEWIŃ");
        scroll.setBounds((int)(50+w+w/16),(int)(dim.getHeight()-400),(int)(w/2+w/4+w/8),100);
        scroll.setFont(font.deriveFont(Font.BOLD));
        scroll.setBackground(Color.WHITE);
        scroll.setIcon(ViewHelper.setIcon("scroll.png",100));
        panel.add(scroll);*/

        back = new JButton("WSTECZ");
        back.setBounds((int)(50+w/2+w/16),(int)(dim.getHeight()-250),(int)(w/2+w/4+w/8),100);
        back.setFont(font.deriveFont(Font.BOLD));
        back.setBackground(Color.WHITE);
        back.setIcon(ViewHelper.setIcon("return.png",100));
        panel.add(back);

        reply = new JButton("ODPOWIEDZ");
        reply.setBounds((int)(50+w/2+w+w/16),(int)(dim.getHeight()-250),(int)(w/2+w/4+w/8),100);
        reply.setFont(font.deriveFont(Font.BOLD));
        reply.setBackground(Color.WHITE);
        reply.setIcon(ViewHelper.setIcon("reply.png",100));
        panel.add(reply);



        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();
    }

    public JButton getPrev() {
        return prev;
    }

    public JButton getScroll() {
        return scroll;
    }

    public JButton getReply() {
        return reply;
    }

    public JButton getNext() {
        return next;
    }

    public JButton getBack() {
        return back;
    }
}
