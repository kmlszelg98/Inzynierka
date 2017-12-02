package View;

import Controller.LoginController;
import Helpers.FrameHelper;
import Helpers.ViewHelper;
import Model.SkypeChatsModel;
import Skype.Skype;
import Skype.SkypeChat;
import Wideo.JPanelOpenCV;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Kamil on 23.10.2017.
 */
public class SkypeChatsView {

    private SkypeChatsModel model;
    private JPanelOpenCV panel;
    private JTextArea name;
    private JScrollPane pane;
    private JButton next;
    private JButton prev;
    private JButton read;
    private JButton back;
    private ArrayList<JButton> list;
    private ArrayList<String> list2;


    private Font font2;
    private Border border;
    private Dimension dim;

    public SkypeChatsView(SkypeChatsModel model) {
        this.model = model;
        init();
    }

    public void init(){
        list = new ArrayList<>();
        list2 = new ArrayList<>();

        Font font = new Font("Arial", Font.ITALIC, 40);
        font2 = new Font("Arial", Font.ITALIC, 50);
        border = BorderFactory.createLineBorder(Color.BLACK, 1);

        panel = new JPanelOpenCV();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        dim = Toolkit.getDefaultToolkit().getScreenSize();
        double x = dim.getHeight()/5;

        name = new JTextArea("Od: "+model.getChat().getChatName());
        name.setFont(font2.deriveFont(Font.BOLD));
        name.setLineWrap(true);
        name.setEditable(false);
        name.setBackground(Color.white);

        pane = new JScrollPane(name);
        pane.setBorder(border);

        if(LoginController.user.getType()==1){
           pane.setBounds((int)(dim.getWidth()/3),50,(int)(2*dim.getWidth()/3-100),(int)(dim.getHeight()-600));
        } else pane.setBounds(50,100,(int)(dim.getWidth()-100),300);
        pane.setFont(font2.deriveFont(Font.BOLD));
        panel.add(pane);

        double x2 = (dim.getHeight()-600)/3;
        double w = dim.getWidth()/2-200;



        read = new JButton("CHAT");
        read.setBounds((int)(dim.getWidth()/2-w/2),(int)(450 + x2/2),(int)w,(int)(x2/2));
        read.setFont(font.deriveFont(Font.BOLD));
        read.setBackground(Color.WHITE);
        read.setIcon(ViewHelper.setIcon("chat.png",(int)(x2/2.5)));
        panel.add(read);
        list.add(read);
        list2.add("/przyciski/Chat");

        back = new JButton("WSTECZ");
        back.setBounds((int)(dim.getWidth()/2-w/2),(int)(450 +x2*2 + x2/2),(int)w,(int)(x2/2));
        back.setFont(font.deriveFont(Font.BOLD));
        back.setBackground(Color.WHITE);
        back.setIcon(ViewHelper.setIcon("return.png",(int)(x2/2)));
        panel.add(back);
        list.add(back);
        list2.add("/przyciski/Wstecz");

        next = new JButton("NASTĘPNY");
        next.setBounds((int)(dim.getWidth()/2+100),(int)(450 + x2+ x2/2),(int)w,(int)(x2/2));
        next.setFont(font.deriveFont(Font.BOLD));
        next.setBackground(Color.WHITE);
        next.setIcon(ViewHelper.setIcon("next.png",(int)(x2/2)));
        if(!Skype.isLast()) {
            next.setEnabled(true);
            list.add(0,next);
            list2.add(0,"/przyciski/Następny");
        }
        else next.setEnabled(false);
        panel.add(next);

        prev = new JButton("POPRZEDNI");
        prev.setBounds(100,(int)(450 + x2+ x2/2),(int)w,(int)(x2/2));
        prev.setFont(font.deriveFont(Font.BOLD));
        prev.setBackground(Color.WHITE);
        prev.setIcon(ViewHelper.setIcon("prev.png",(int)(x2/2)));
        if(!Skype.isFirst()) {
            prev.setEnabled(true);
            list.add(0,prev);
            list2.add(0,"/przyciski/Poprzedni");
        }
        else prev.setEnabled(false);
        panel.add(prev);



        if(LoginController.user.getType()==1){
            back.setBounds((int)(dim.getWidth()/2+100),(int)(450 +x2*2 + x2/2),(int)w,(int)(x2/2));
            read.setBounds(100,(int)(450 +x2*2 + x2/2),(int)w,(int)(x2/2));
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

    public void update(){
        list = new ArrayList<>();
        list2 = new ArrayList<>();
        list.add(read);
        list.add(back);
        list2.add("/przyciski/Chat");
        list2.add("/przyciski/Wstecz");

        panel.remove(pane);
        name.setText("Od: "+model.getChat().getChatName());
        panel.add(pane);

        if(!Skype.isLast()) {
            next.setEnabled(true);
            list.add(0,next);
            list2.add(0,"/przyciski/Następny");
        }
        else next.setEnabled(false);

        if(!Skype.isFirst()) {
            prev.setEnabled(true);
            list.add(0,prev);
            list2.add(0,"/przyciski/Poprzedni");
        }
        else prev.setEnabled(false);


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

    public JPanelOpenCV getPanel() {
        return panel;
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
