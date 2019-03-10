package View;

import Helpers.FrameHelper;
import Helpers.ViewHelper;
import Model.SendMailModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Kamil on 18.10.2017.
 */
public class SendMailTopicView {

    private SendMailModel sendMailModel;
    private JPanel panel;
    private JTextArea to;
    private JTextArea subject;
    private JButton accept;
    private JButton cancel;

    public SendMailTopicView(SendMailModel sendMailModel) {
        this.sendMailModel = sendMailModel;
        init();
    }

    public void init(){

        Font font = new Font("Arial", Font.ITALIC, 40);
        Font font2 = new Font("Arial", Font.ITALIC, 50);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        JLabel toLabel = new JLabel("TO: ");
        toLabel.setBounds(50,50,150,50);
        toLabel.setFont(font.deriveFont(Font.BOLD));
        panel.add(toLabel);

        to = new JTextArea(sendMailModel.getTo());
        //to.setBorder(border);
        to.setBounds(50,100,(int)(dim.getWidth()-100),200);
        to.setFont(font2.deriveFont(Font.BOLD));
        to.setLineWrap(true);
        //panel.add(to);

        JScrollPane pane = new JScrollPane(to);
        pane.setBounds(50,100,(int)(dim.getWidth()-100),200);
        pane.setBorder(border);
        panel.add(pane);

        JLabel subjectLabel = new JLabel("SUBJECT :");
        subjectLabel.setBounds(50,350,300,50);
        subjectLabel.setFont(font.deriveFont(Font.BOLD));
        panel.add(subjectLabel);




        subject = new JTextArea(sendMailModel.getSubject());
        //subject.setBorder(border);
        subject.setBounds(50,400,(int)(dim.getWidth()-100),200);
        subject.setFont(font2.deriveFont(Font.BOLD));
        subject.setLineWrap(true);
        //panel.add(subject);


        JScrollPane pane2 = new JScrollPane(subject);
        pane2.setBounds(50,400,(int)(dim.getWidth()-100),200);
        pane2.setBorder(border);
        panel.add(pane2);

        double h = dim.getHeight()-975;
        double w = dim.getWidth()/2-150;

        accept = new JButton("ACCEPT");
        accept.setBounds(100,750,(int)w,(int)h);
        accept.setFont(font.deriveFont(Font.BOLD));
        accept.setBackground(Color.WHITE);
        accept.setIcon(ViewHelper.setIcon("checked.png",(int)(h)));
        panel.add(accept);

        cancel = new JButton("CANCEL");
        cancel.setBounds((int)dim.getWidth()/2+50,750,(int)w,(int)h);
        cancel.setFont(font.deriveFont(Font.BOLD));
        cancel.setBackground(Color.WHITE);
        cancel.setIcon(ViewHelper.setIcon("cancel.png",(int)(h)));
        panel.add(cancel);

        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();

    }

    public void updateModel(){
        sendMailModel.setTo(to.getText());
        sendMailModel.setSubject(subject.getText());
    }

    public JButton getAccept() {
        return accept;
    }

    public JButton getCancel() {
        return cancel;
    }
}
