package View;

import Controller.LoginController;
import Helpers.FrameHelper;
import Helpers.ViewHelper;
import Model.SkypeSendModel;
import Wideo.JPanelOpenCV;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamil on 30.10.2017.
 */
public class SkypeSendView {

    private SkypeSendModel model;
    private JPanelOpenCV panel;
    private JTextArea body;
    private JButton accept;
    private JButton cancel;
    private JButton more;
    private JButton abc;
    private JButton stopButton;
    private JButton[][] buttons;
    private JButton[][]buttons2;
    private String[][] btn;
    private String[][] btn2;
    private Font font;
    private JScrollPane pane;

    private String[] alphabet = {"A","Ą","B","C","Ć","D","E","Ę","F","G","H","I","J","K","L","Ł"
            ,"M","N","Ń","O","Ó","P","Q","R","S","Ś","T","U","W","V","X","Y","Z","Ź","Ż"};

    private String[] others = {"0","1","2","3","4","5","6","7","8","9","(",")","[","]","{","}","<",">","\"","'",
            "+","-","*","/","=","^","$","%","&","~"," ",".",",",";",":","?","!","@","#","_"};

    public SkypeSendView(SkypeSendModel sendMailModel) {
        this.model = sendMailModel;

        buttons = new JButton[6][];
        for(int i=0;i<5;i++) buttons[i] = new JButton[7];
        buttons[5] = new JButton[4];

        btn = new String[6][];
        for(int i=0;i<5;i++) btn[i] = new String[8];
        btn[5] = new String[4];

        buttons2 = new JButton[5][];
        for(int i=0;i<4;i++) buttons2[i] = new JButton[10];
        buttons2[4] = new JButton[4];

        btn2 = new String[5][];
        for(int i=0;i<4;i++) btn2[i] = new String[11];
        btn2[4] = new String[4];
        init();
    }

    public void init(){

        font = new Font("Arial", Font.ITALIC, 40);
        Font font2 = new Font("Arial", Font.ITALIC, 50);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        panel = new JPanelOpenCV();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        if(LoginController.user.getType()==0) {
            JLabel bodyLabel = new JLabel("TREŚĆ : ");
            bodyLabel.setBounds(50, 50, 250, 50);
            bodyLabel.setFont(font.deriveFont(Font.BOLD));
            panel.add(bodyLabel);
        }

        body = new JTextArea();
        body.setBounds(50,100,(int)(dim.getWidth()-100),500);
        body.setFont(font2.deriveFont(Font.BOLD));
        body.setLineWrap(true);

        pane = new JScrollPane(body);

        if(LoginController.user.getType()==1){
            pane.setBounds((int)(dim.getWidth()/3),50,(int)(2*dim.getWidth()/3-100),(int)(dim.getHeight()-600));
        } else if(LoginController.user.getType()==2){
            pane.setBounds(50,50,(int)(dim.getWidth()-100),(int)(dim.getHeight()-600));
        }
        else pane.setBounds(50,100,(int)(dim.getWidth()-100),500);
        pane.setBorder(border);
        panel.add(pane);



        double w = dim.getWidth()/2-150;
        double h = dim.getHeight()-975;

        int s = (int)(dim.getWidth()/7);
        int pos = (int)(dim.getHeight()-525);
        addButtons(s,pos);

        double wdth = dim.getWidth()/3-100;

        accept = new JButton("AKCEPTUJ");
        if(LoginController.user.getType()>=1) {
            accept.setBounds(50, pos + 5 * 75, (int) wdth, 50);
        } else {
            accept.setBounds(100, 750, (int) w, (int) h);
        }
        accept.setFont(font.deriveFont(Font.BOLD));
        accept.setBackground(Color.WHITE);
        accept.setIcon(ViewHelper.setIcon("checked.png",50));
        panel.add(accept);

        cancel = new JButton("ANULUJ");
        if(LoginController.user.getType()>=1) {
            cancel.setBounds((int) dim.getWidth() / 3 + 50, pos + 5 * 75, (int) wdth, 50);
        } else {
            cancel.setBounds((int) dim.getWidth() / 2 + 50, 750, (int) w, (int) h);
        }
        cancel.setFont(font.deriveFont(Font.BOLD));
        cancel.setBackground(Color.WHITE);
        cancel.setIcon(ViewHelper.setIcon("cancel.png",50));
        panel.add(cancel);

        more = new JButton("WIECEJ");
        more.setBounds((int)(2*dim.getWidth()/3)+50,pos+5*75,(int)wdth,50);
        more.setFont(font.deriveFont(Font.BOLD));
        more.setBackground(Color.WHITE);
        more.setIcon(ViewHelper.setIcon("more.png",50));


        abc = new JButton("LITERY");
        abc.setBounds((int)(2*dim.getWidth()/3)+50,pos+5*75,(int)wdth,50);
        abc.setFont(font.deriveFont(Font.BOLD));
        abc.setBackground(Color.WHITE);
        abc.setIcon(ViewHelper.setIcon("abc.png",50));

        stopButton = new JButton("STOP");
        stopButton.setFont(font.deriveFont(Font.BOLD));
        stopButton.setBackground(Color.WHITE);
        stopButton.setIcon(ViewHelper.setIcon("stop.png",50));



        if(LoginController.user.getType()>=1) {
            buttons[5][0] = accept;
            buttons[5][1] = cancel;
            buttons[5][2] = more;
            buttons[5][3] = stopButton;
            for (JButton[] button : buttons) {
                for (JButton aButton : button) panel.add(aButton);
            }

            btn[5][1] = "/przyciski/Akceptuj";
            btn[5][2] = "/przyciski/Anuluj";
            btn[5][3] = "/przyciski/Więcej";
            for(int i=0;i<btn.length;i++){
                btn[i][0] = "/alfabet/alfabetG"+i;
            }

            panel.setButtons(buttons);
            panel.setBtn(btn);

            panel.add(more);
        }

        if(LoginController.user.getType()!=0) setBounds();

        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();
    }

    public void setBounds(){
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int pos = (int)(dim.getHeight()-525);
        double wdth = dim.getWidth()/3-100;

        if(LoginController.user.getType()!=1) {
            accept.setBounds(50, pos + 5 * 75, (int) wdth, 50);
            cancel.setBounds((int) dim.getWidth() / 3 + 50, pos + 5 * 75, (int) wdth, 50);
            more.setBounds((int) (2 * dim.getWidth() / 3) + 50, pos + 5 * 75, (int) wdth, 50);
            abc.setBounds((int) (2 * dim.getWidth() / 3) + 50, pos + 5 * 75, (int) wdth, 50);
        } else {
            wdth = dim.getWidth()/4-100;
            accept.setBounds(50, pos + 5 * 75, (int) wdth, 50);
            cancel.setBounds((int) dim.getWidth()/4 + 50, pos + 5 * 75, (int) wdth, 50);
            more.setBounds((int) (2 * dim.getWidth()/4) + 50, pos + 5 * 75, (int) wdth, 50);
            abc.setBounds((int) (2 * dim.getWidth()/4) + 50, pos + 5 * 75, (int) wdth, 50);
            stopButton.setBounds((int) (3 * dim.getWidth()/4) + 50, pos + 5 * 75, (int) wdth, 50);
            panel.add(stopButton);
        }
    }

    public void addButtons(int size, int pos){

        int counter = 0;
        for(int i=0;i<35;i++){
            int k = i%7;
            if(k==0 && i!=0){
                pos = pos+75;
                counter++;
            }
            final int l = i;
            JButton button = new JButton(alphabet[i]);
            button.setBounds(k*size+size/4,pos,size/2,50);
            button.setFont(font.deriveFont(Font.BOLD));
            button.setBackground(Color.WHITE);

            button.addActionListener(e -> body.setText(body.getText()+alphabet[l]));
            buttons[counter][k] = button;
            btn[counter][k+1] = "/litery/litera"+alphabet[i];
        }
    }

    public void addOtherSigns(int size, int pos){

        int counter = 0;
        for(int i=0;i<others.length;i++){
            int k = i%10;
            if(k==0 && i!=0){
                pos = pos+75;
                counter++;
            }
            final int l = i;
            JButton button = new JButton(others[i]);
            button.setBounds(k*size+size/4,pos,size/2,50);
            button.setFont(font.deriveFont(Font.BOLD));
            button.setBackground(Color.WHITE);

            button.addActionListener(e -> body.setText(body.getText()+others[l]));

            buttons2[counter][k] = button;
            switch (counter){
                case 0:{
                    btn2[counter][k+1] = "/cyfry/cyfra"+others[i];
                    break;
                }
                case 1:{
                    String tmp = others[i];
                    switch (tmp) {
                        case "\"":
                            btn2[counter][k + 1] = "/nawiasy/nawiasAp";
                            break;
                        case "<":
                            btn2[counter][k + 1] = "/nawiasy/nawiasTo";
                            break;
                        case ">":
                            btn2[counter][k + 1] = "/nawiasy/nawiasTz";
                            break;
                        default:
                            btn2[counter][k + 1] = "/nawiasy/nawias" + tmp;
                            break;
                    }
                    break;
                }
                case 2:{
                    String tmp = others[i];
                    switch (tmp) {
                        case "*":
                            btn2[counter][k + 1] = "/matematyczne/namtematyczneMn";
                            break;
                        case "/":
                            btn2[counter][k + 1] = "/matematyczne/matematyczneDz";
                            break;
                        default:
                            btn2[counter][k + 1] = "/matematyczne/matyematyczne" + tmp;
                            break;
                    }
                    break;
                }
                case 3:{
                    String tmp = others[i];
                    switch (tmp) {
                        case ":":
                            btn2[counter][k + 1] = "/znaki/znakDw";
                            break;
                        case ".":
                            btn2[counter][k + 1] = "/znaki/znakKr";
                            break;
                        case "?":
                            btn2[counter][k + 1] = "/znaki/znakiPt";
                            break;
                        case " ":
                            btn2[counter][k + 1] = "/znaki/znakSp";
                            break;
                        default:
                            btn2[counter][k + 1] = "/znaki/znak" + tmp;
                            break;
                    }
                    break;
                }
            }
        }
    }

    public void addMoreSigns(){

        panel = new JPanelOpenCV();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        panel.add(pane);

        int s = (int)(dim.getWidth()/10);
        int pos = (int)(dim.getHeight()-525);
        addOtherSigns(s,pos);

        accept.setBackground(Color.WHITE);
        cancel.setBackground(Color.WHITE);
        abc.setBackground(Color.WHITE);


        buttons2[4][0] = accept;
        buttons2[4][1] = cancel;
        buttons2[4][2] = abc;
        buttons2[4][3] = stopButton;
        for (JButton[] aButtons2 : buttons2) {
            for (JButton anAButtons2 : aButtons2) panel.add(anAButtons2);
        }
        panel.setButtons(buttons2);

        btn2[4][1] = "/przyciski/Akceptuj";
        btn2[4][2] = "/przyciski/Anuluj";
        btn2[4][3] = "/przyciski/Alfabet";
        for(int i=0;i<btn2.length;i++){
            btn2[i][0] = "/alfabet/alfabetP"+i;
        }
        panel.setBtn(btn2);



        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();
    }

    public void addLetters(){

        panel = new JPanelOpenCV();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        panel.add(pane);

        int s = (int)(dim.getWidth()/7);
        int pos = (int)(dim.getHeight()-525);
        addButtons(s,pos);

        accept.setBackground(Color.WHITE);
        cancel.setBackground(Color.WHITE);
        more.setBackground(Color.WHITE);


        buttons[5][0] = accept;
        buttons[5][1] = cancel;
        buttons[5][2] = more;
        buttons[5][3] = stopButton;
        for (JButton[] button : buttons) {
            for (JButton aButton : button) panel.add(aButton);
        }
        panel.setButtons(buttons);

        btn[5][1] = "/przyciski/Akceptuj";
        btn[5][2] = "/przyciski/Anuluj";
        btn[5][3] = "/przyciski/Więcej";
        for(int i=0;i<btn.length;i++){
            btn[i][0] = "/alfabet/alfabetG"+i;
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

    public void update(){
        model.setMessage(body.getText());
    }

    public JButton getAccept() {
        return accept;
    }

    public JPanelOpenCV getPanel() {
        return panel;
    }

    public JButton getCancel() {
        return cancel;
    }

    public JButton getMore() {
        return more;
    }

    public JButton getAbc() {
        return abc;
    }
}
