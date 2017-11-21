package View;

import Helpers.FrameHelper;
import Helpers.ViewHelper;
import Model.SendMailModel;
import Wideo.JPanelOpenCV;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Kamil on 05.11.2017.
 */
public class SendWideoView {

    private SendMailModel model;
    private JPanelOpenCV panel;
    private JTextArea body;
    private JButton accept;
    private JButton cancel;
    private JButton more;
    private JButton abc;
    private int part;
    private JButton[][] buttons;
    private JButton[][] buttons2;

    private JLabel bodyLabel;
    private JScrollPane pane;
    private Font font;

    private String[] alphabet = {"A","Ą","B","C","Ć","D","E","Ę","F","G","H","I","J","K","L","Ł"
            ,"M","N","Ń","O","Ó","P","Q","R","S","Ś","T","U","W","V","X","Y","Z","Ź","Ż"};

    private String[] others = {"0","1","2","3","4","5","6","7","8","9","(",")","[","]","{","}","<",">","\"","'",
            "+","-","*","/","=","^","$","%","&","~"," ",".",",",";",":","?","!","@","#","_"};

    public SendWideoView(SendMailModel model){
        this.model = model;

        buttons = new JButton[6][];
        for(int i=0;i<5;i++) buttons[i] = new JButton[7];
        buttons[5] = new JButton[3];

        buttons2 = new JButton[5][];
        for(int i=0;i<4;i++) buttons2[i] = new JButton[10];
        buttons2[4] = new JButton[3];

       buttons2[4][0] = accept;
        buttons2[4][1] = cancel;
        buttons2[4][2] = abc;

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

        bodyLabel = new JLabel();
        bodyLabel.setBounds((int)(dim.getWidth()/3),0,250,50);
        bodyLabel.setFont(font.deriveFont(Font.BOLD));
        panel.add(bodyLabel);

        if(part==0) {
            bodyLabel.setText("DO : ");
            body = new JTextArea(model.getTo());
        } else if(part==1){
            bodyLabel.setText("TEMAT : ");
            body = new JTextArea(model.getSubject());
        } else {
            bodyLabel.setText("TREŚĆ : ");
            body = new JTextArea(model.getBody());
        }
        //body.setBounds((int)(dim.getWidth()/3),100,(int)(dim.getWidth()-100),500);
        body.setFont(font2.deriveFont(Font.BOLD));
        body.setLineWrap(true);

        pane = new JScrollPane(body);
        pane.setBounds((int)(dim.getWidth()/3),50,(int)(2*dim.getWidth()/3-100),(int)(dim.getHeight()-600));
        pane.setBorder(border);
        panel.add(pane);

        double w = dim.getWidth()/2-150;
        int s = (int)(dim.getWidth()/7);
        int pos = (int)(dim.getHeight()-525);
        addButtons(s,pos);

        double wdth = dim.getWidth()/3-100;

        accept = new JButton("AKCEPTUJ");
        accept.setBounds(50,pos+5*75,(int)wdth,50);
        accept.setFont(font.deriveFont(Font.BOLD));
        accept.setBackground(Color.WHITE);
        accept.setIcon(ViewHelper.setIcon("checked.png",50));
        panel.add(accept);

        cancel = new JButton("ANULUJ");
        cancel.setBounds((int)dim.getWidth()/3+50,pos+5*75,(int)wdth,50);
        cancel.setFont(font.deriveFont(Font.BOLD));
        cancel.setBackground(Color.WHITE);
        cancel.setIcon(ViewHelper.setIcon("cancel.png",50));
        panel.add(cancel);

        more = new JButton("WIĘCEJ");
        more.setBounds((int)(2*dim.getWidth()/3)+50,pos+5*75,(int)wdth,50);
        more.setFont(font.deriveFont(Font.BOLD));
        more.setBackground(Color.WHITE);
        more.setIcon(ViewHelper.setIcon("more.png",50));
        panel.add(more);

        abc = new JButton("LITERY");
        abc.setBounds((int)(2*dim.getWidth()/3)+50,pos+5*75,(int)wdth,50);
        abc.setFont(font.deriveFont(Font.BOLD));
        abc.setBackground(Color.WHITE);
        abc.setIcon(ViewHelper.setIcon("abc.png",50));

        buttons[5][0] = accept;
        buttons[5][1] = cancel;
        buttons[5][2] = more;
        for(int i=0;i<buttons.length;i++){
            for(int j=0;j<buttons[i].length;j++) panel.add(buttons[i][j]);
        }

        panel.setButtons(buttons);

        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();
    }

    public void addMoreSigns(){

        panel = new JPanelOpenCV();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        panel.add(bodyLabel);
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
        for(int i=0;i<buttons2.length;i++){
            for(int j=0;j<buttons2[i].length;j++) panel.add(buttons2[i][j]);
        }
        panel.setButtons(buttons2);

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
        panel.add(bodyLabel);
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
        for(int i=0;i<buttons.length;i++){
            for(int j=0;j<buttons[i].length;j++) panel.add(buttons[i][j]);
        }
        panel.setButtons(buttons);

        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();
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

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    body.setText(body.getText()+alphabet[l]);
                }
            });
            buttons[counter][k] = button;
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

            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    body.setText(body.getText()+others[l]);
                }
            });

            buttons2[counter][k] = button;
        }
    }

    public JPanelOpenCV getPanel() {
        return panel;
    }

    public void nextPart(){

        part++;
        init();
    }

    public int getPart() {
        return part;
    }

    public void updateModel(){
        if(part==0){
            model.setTo(body.getText());
        } else if(part==1){
            model.setSubject(body.getText());
        } else {
            model.setBody(body.getText());
        }
    }

    public JButton getAccept() {
        return accept;
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
