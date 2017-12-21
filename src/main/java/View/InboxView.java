package View;

import Controller.LoginController;
import Helpers.FrameHelper;
import Helpers.ViewHelper;
import Model.InboxModel;
import Wideo.JPanelOpenCV;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Kamil on 05.10.2017.
 */
public class InboxView {

    private InboxModel model;
    private JPanelOpenCV panel;
    private JTextArea from;
    private JTextArea subject;
    private JButton next;
    private JButton prev;
    private JButton read;
    private JButton back;
    private JButton stopButton;

    private JScrollPane fromPane;
    private JScrollPane subjectPane;

    private Font font2;
    private Border border;
    private Dimension dim;
    private ArrayList<JButton> list;
    private ArrayList<String> strList;


    public InboxView(InboxModel model) {
        this.model = model;
        init();
    }

    private void init(){

        list = new ArrayList<>();
        strList = new ArrayList<>();
        Font font = new Font("Arial", Font.ITALIC, 40);
        font2 = new Font("Arial", Font.ITALIC, 50);
        border = BorderFactory.createLineBorder(Color.BLACK, 1);

        panel = new JPanelOpenCV();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        dim = Toolkit.getDefaultToolkit().getScreenSize();
        double x = dim.getHeight()/5;

        from = new JTextArea("OD: "+model.getMessage().getFrom());
        from.setFont(font2.deriveFont(Font.BOLD));
        from.setLineWrap(true);
        from.setEditable(false);
        from.setBackground(Color.WHITE);

        fromPane = new JScrollPane(from);
        fromPane.setBorder(border);
        panel.add(fromPane);

        subject = new JTextArea("TEMAT: "+model.getMessage().getSubject());
        subject.setFont(font2.deriveFont(Font.BOLD));
        subject.setLineWrap(true);
        subject.setEditable(false);
        subject.setBackground(Color.WHITE);

        subjectPane = new JScrollPane(subject);
        subjectPane.setBorder(border);
        panel.add(subjectPane);

        double x2 = (dim.getHeight()-600)/3;
        double w = dim.getWidth()/2-200;

        read = new JButton("ODCZYTAJ");
        read.setFont(font.deriveFont(Font.BOLD));
        read.setBackground(Color.WHITE);
        read.setIcon(ViewHelper.setIcon("read.png",(int)(x2/2.5)));
        panel.add(read);
        list.add(read);
        strList.add("/przyciski/Odczytaj");

        back = new JButton("WSTECZ");
        back.setFont(font.deriveFont(Font.BOLD));
        back.setBackground(Color.WHITE);
        back.setIcon(ViewHelper.setIcon("return.png",(int)(x2/2)));
        panel.add(back);
        list.add(back);
        strList.add("/przyciski/Wstecz");

        stopButton = new JButton("STOP");
        stopButton.setFont(font.deriveFont(Font.BOLD));
        stopButton.setBackground(Color.WHITE);
        stopButton.setIcon(ViewHelper.setIcon("stop.png",(int)(x/2)));
        list.add(stopButton);


        next = new JButton("NASTĘPNY");
        next.setFont(font.deriveFont(Font.BOLD));
        next.setBackground(Color.WHITE);
        next.setIcon(ViewHelper.setIcon("next.png",(int)(x2/2)));
        if(!Imap.Imap.isLast(true)){
            next.setEnabled(true);
            list.add(0,next);
            strList.add(0,"/przyciski/Następny");
        }
        else next.setEnabled(false);
        panel.add(next);


        prev = new JButton("POPRZEDNI");
        prev.setFont(font.deriveFont(Font.BOLD));
        prev.setBackground(Color.WHITE);
        prev.setIcon(ViewHelper.setIcon("prev.png",(int)(x2/2)));
        if(!Imap.Imap.isFirst()) {
            prev.setEnabled(true);
            list.add(0,prev);
            strList.add(0,"/przyciski/Poprzedni");
        }
        else prev.setEnabled(false);

        panel.add(prev);




        JButton buttons[][] = new JButton[list.size()][1];
        for(int i=0;i<list.size();i++){
            buttons[i][0] = list.get(i);
        }

        String[][] btn = new String[strList.size()][1];
        for(int i=0;i<strList.size();i++){
            btn[i][0] = strList.get(i);
        }

        panel.setButtons(buttons);
        panel.setBtn(btn);

        setBounds();
        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();


    }

    public void update(){
        panel.remove(fromPane);
        from.setText("OD: "+model.getMessage().getFrom());
        panel.add(fromPane);

        panel.remove(subjectPane);
        subject.setText("TEMAT: "+model.getMessage().getSubject());
        panel.add(subjectPane);

        list = new ArrayList<>();
        list.add(read);
        list.add(back);
        list.add(stopButton);


        strList = new ArrayList<>();
        strList.add("/przyciski/Odczytaj");
        strList.add("/przyciski/Wstecz");

        next.setBackground(Color.WHITE);
        if(!Imap.Imap.isLast(true)){
            next.setEnabled(true);
            list.add(0,next);
            strList.add(0,"/przyciski/Następny");
        }
        else next.setEnabled(false);

        prev.setBackground(Color.WHITE);
        if(!Imap.Imap.isFirst()) {
            prev.setEnabled(true);
            list.add(0,prev);
            strList.add(0,"/przyciski/Poprzedni");
        }
        else prev.setEnabled(false);

        JButton buttons[][] = new JButton[list.size()][1];
        for(int i=0;i<list.size();i++){
            buttons[i][0] = list.get(i);
        }


        String[][] btn = new String[strList.size()][1];
        for(int i=0;i<strList.size();i++){
            btn[i][0] = strList.get(i);
        }
        panel.setButtons(buttons);
        panel.setBtn(btn);

        setBounds();
        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();

    }

    public void setBounds(){
        double x2 = (dim.getHeight()-600)/3;
        double w = dim.getWidth()/2-200;

        if(LoginController.user.getType()==1) {
            w= dim.getWidth()/3-100;
            fromPane.setBounds((int)(dim.getWidth()/3), 50, (int) (2*dim.getWidth()/3 - 100), 250);
            subjectPane.setBounds((int)(dim.getWidth()/3), 350, (int) (2*dim.getWidth()/3 - 100), 250);
            read.setBounds(100,(int)(450+ x2*2 + x2/2),(int)w,(int)(x2/2));
            next.setBounds((int)(2*dim.getWidth()/3+50),(int)(450 + x2+ x2/2),(int)w,(int)(x2/2));
            prev.setBounds(100,(int)(450 + x2+ x2/2),(int)w,(int)(x2/2));
            back.setBounds((int)(2*dim.getWidth()/3+50),(int)(450 +x2*2 + x2/2),(int)w,(int)(x2/2));
            stopButton.setBounds((int)(dim.getWidth()/3+75),(int)(450+ x2*2 + x2/2),(int)w,(int)(x2/2));
            panel.add(stopButton);

        } else {
            fromPane.setBounds(50, 50, (int) (dim.getWidth() - 100), 200);
            subjectPane.setBounds(50, 300, (int) (dim.getWidth() - 100), 200);
            read.setBounds((int)(dim.getWidth()/2-w/2),(int)(450 + x2/2),(int)w,(int)(x2/2));
            next.setBounds((int)(dim.getWidth()/2+100),(int)(450 + x2+ x2/2),(int)w,(int)(x2/2));
            prev.setBounds(100,(int)(450 + x2+ x2/2),(int)w,(int)(x2/2));
            back.setBounds((int)(dim.getWidth()/2-w/2),(int)(450 +x2*2 + x2/2),(int)w,(int)(x2/2));
        }

    }

    public JButton getStopButton() {
        return stopButton;
    }

    public JPanelOpenCV getPanel() {
        return panel;
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
