package View;

import Controller.LoginController;
import Helpers.FrameHelper;
import Helpers.ViewHelper;
import Model.SkypeChatMsgModel;
import Wideo.JPanelOpenCV;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Kamil on 24.10.2017.
 */
public class SkypeChatView {

    private SkypeChatMsgModel model;
    private JTextField name;
    private JPanelOpenCV panel;
    private JTextArea body;
    private JButton next;
    private JButton prev;
    private JButton scroll;
    private JButton reply;
    private JButton back;
    private JButton stopButton;
    private ArrayList<JButton> list;
    private ArrayList<String> list2;

    public SkypeChatView(SkypeChatMsgModel model){
        this.model=model;
        init();
    }


    public void init(){
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        panel = new JPanelOpenCV();
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

        panel.add(name);

        body = new JTextArea(model.getMessage().getBody().replaceAll("\\<.*?\\>",""));//"",SwingConstants.CENTER);
        //body.setBorder(border);
        body.setFont(font2.deriveFont(Font.BOLD));
        body.setLineWrap(true);
        body.setEditable(false);
        body.setBackground(Color.white);
        body.setBounds(50,150,(int)(dim.getWidth()-100),(int)(dim.getHeight()-600));

        JScrollPane pane = new JScrollPane(body);

        if(LoginController.user.getType()==1){
            name.setBounds((int)(dim.getWidth()/3),25,(int)(2*dim.getWidth()/3-100),100);
            pane.setBounds((int)(dim.getWidth()/3),150,(int)(2*dim.getWidth()/3-100),(int)(dim.getHeight()-600));
        } else {
            name.setBounds(50,25,(int)(dim.getWidth()-100),100);
            pane.setBounds(50,150,(int)(dim.getWidth()-100),(int)(dim.getHeight()-600));
        }

        pane.setBorder(border);
        panel.add(pane);

        //panel.add(body);

        double w = (dim.getWidth()-100)/3;

        /*scroll = new JButton("PRZEWIŃ");
        scroll.setBounds((int)(50+w+w/16),(int)(dim.getHeight()-400),(int)(w/2+w/4+w/8),100);
        scroll.setFont(font.deriveFont(Font.BOLD));
        scroll.setBackground(Color.WHITE);
        scroll.setIcon(ViewHelper.setIcon("scroll.png",100));
        panel.add(scroll);*/

        reply = new JButton("ODPOWIEDZ");
        reply.setBounds((int)(50+w/2+w+w/16),(int)(dim.getHeight()-250),(int)(w/2+w/4+w/8),100);
        reply.setFont(font.deriveFont(Font.BOLD));
        reply.setBackground(Color.WHITE);
        reply.setIcon(ViewHelper.setIcon("reply.png",100));
        panel.add(reply);
        list.add(reply);
        list2.add("/przyciski/Odpowiedz");

        back = new JButton("WSTECZ");
        back.setBounds((int)(50+w/2+w/16),(int)(dim.getHeight()-250),(int)(w/2+w/4+w/8),100);
        back.setFont(font.deriveFont(Font.BOLD));
        back.setBackground(Color.WHITE);
        back.setIcon(ViewHelper.setIcon("return.png",100));
        panel.add(back);
        list.add(back);
        list2.add("/przyciski/Wstecz");

        stopButton = new JButton("STOP");
        stopButton.setFont(font.deriveFont(Font.BOLD));
        stopButton.setBackground(Color.WHITE);
        stopButton.setIcon(ViewHelper.setIcon("stop.png",100));
        list.add(stopButton);

        next = new JButton("NASTĘPNY");
        next.setBounds((int)(50+2*w+w/16),(int)(dim.getHeight()-400),(int)(w/2+w/4+w/8),100);
        next.setFont(font.deriveFont(Font.BOLD));
        next.setBackground(Color.WHITE);
        next.setIcon(ViewHelper.setIcon("right-arrow.png",100));
        if(model.checkLast()) {
            next.setEnabled(true);
            list.add(0,next);
            list2.add(0,"/przyciski/Następny");
        }
        else next.setEnabled(false);
        panel.add(next);

        prev = new JButton("POPRZEDNI");
        prev.setBounds((int)(50+w/16),(int)(dim.getHeight()-400),(int)(w/2+w/4+w/8),100);
        prev.setFont(font.deriveFont(Font.BOLD));
        prev.setBackground(Color.WHITE);
        prev.setIcon(ViewHelper.setIcon("left-arrow.png",100));
        if(model.checkFirst()) {
            prev.setEnabled(true);
            list.add(0,prev);
            list2.add(0,"/przyciski/Poprzedni");
        }
        else prev.setEnabled(false);
        panel.add(prev);

        if(LoginController.user.getType()==1){
            double x2 = (dim.getHeight()-600)/3;

            w= dim.getWidth()/3-100;
            reply.setBounds(100,(int)(450+ x2*2 + x2/2),(int)w,(int)(x2/2));
            next.setBounds((int)(2*dim.getWidth()/3+50),(int)(450 + x2+ x2/2),(int)w,(int)(x2/2));
            prev.setBounds(100,(int)(450 + x2+ x2/2),(int)w,(int)(x2/2));
            back.setBounds((int)(2*dim.getWidth()/3+50),(int)(450 +x2*2 + x2/2),(int)w,(int)(x2/2));
            stopButton.setBounds((int)(dim.getWidth()/3+75),(int)(450+ x2*2 + x2/2),(int)w,(int)(x2/2));
            panel.add(stopButton);
        }

        JButton buttons[][] = new JButton[list.size()][1];
        for(int i=0;i<list.size();i++){
            buttons[i][0] = list.get(i);
        }
        panel.setButtons(buttons);

        String btn [][] = new String[list2.size()][1];
        for(int i=0;i<list2.size();i++){
            btn[i][0] = list2.get(i);
        }

        panel.setBtn(btn);


        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();
    }


    public JButton getStopButton() {
        return stopButton;
    }

    public JPanelOpenCV getPanel() {
        return panel;
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
