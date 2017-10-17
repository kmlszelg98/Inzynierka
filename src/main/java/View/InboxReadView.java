package View;

import Helpers.FrameHelper;
import Helpers.ViewHelper;
import Model.InboxModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Kamil on 11.10.2017.
 */
public class InboxReadView {

    private InboxModel model;
    private JPanel panel;
    private JLabel subject;
    private JTextArea body;
    private int number;
    private String[] array;
    private JButton next;
    private JButton back;

    public InboxReadView(InboxModel model){
        this.model=model;
        number = 0;
        init();
    }

    public JButton getNext() {
        return next;
    }

    public JButton getBack() {
        return back;
    }

    public String setValue(){
        FontMetrics fm = body.getFontMetrics(body.getFont());
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int count = (int)(dim.getHeight()-500)/50 -1;
        int how = 0;
        String temp = "";
        int limit = 0;
        for(int i=number;i<array.length;i++){
            int width = fm.stringWidth(array[i]);
            int c = width/(int)(dim.getWidth()-100) +1;
            /*Jesli dlugosc jednej linijki tekstu przekracza normy to podziel tekst samemu na mnniej wiecej rowne fragmenty*/
            if(how+c<count){
                if(how>0) temp+="\n";
                temp += array[i];
                how = how+c;
                limit++;
                if(i==array.length-1) number = number + limit;
            } else {
                number = number + limit;
                break;
            }
        }
        return temp;
    }

    public void init(){
        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
        Font font = new Font("Arial", Font.ITALIC, 40);
        Font font2 = new Font("Arial", Font.ITALIC, 50);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        body = new JTextArea();//"",SwingConstants.CENTER);
        body.setBorder(border);
        body.setFont(font2.deriveFont(Font.BOLD));
        body.setLineWrap(true);
        body.setEditable(false);
        body.setBackground(Color.white);

        String text = model.getMessage().getBody();
        //text = text.replaceAll("\\s{2,}", " ");//.replaceAll("\n","<br>");
        //if(text.startsWith("<br>")) text=text.substring(4);
        array = text.split("\n");
        String temp = setValue();

        body.setText(temp);
        body.setBounds(50,50,(int)(dim.getWidth()-100),(int)(dim.getHeight()-500));

        panel.add(body);

        double w = dim.getWidth()/2-200;

        next = new JButton("DALEJ");
        next.setBounds((int)(dim.getWidth()/2-w/2),(int)(dim.getHeight()-400),(int)w,100);
        next.setFont(font.deriveFont(Font.BOLD));
        next.setBackground(Color.WHITE);
        next.setIcon(ViewHelper.setIcon("right.png",100));
        if(number>=(array.length)){
            next.setEnabled(false);
        }
        panel.add(next);

        back = new JButton("WSTECZ");
        back.setBounds((int)(dim.getWidth()/2-w/2),(int)(dim.getHeight()-250),(int)w,100);
        back.setFont(font.deriveFont(Font.BOLD));
        back.setBackground(Color.WHITE);
        back.setIcon(ViewHelper.setIcon("return.png",100));
        panel.add(back);

        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();
    }
}
