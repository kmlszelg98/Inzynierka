package View;

import Helpers.FrameHelper;
import Helpers.ViewHelper;
import Model.SkypeSendModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Kamil on 30.10.2017.
 */
public class SkypeSendView {

    private SkypeSendModel model;
    private JPanel panel;
    private JTextArea body;
    private JButton accept;
    private JButton cancel;

    public SkypeSendView(SkypeSendModel sendMailModel) {
        this.model = sendMailModel;
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

        JLabel bodyLabel = new JLabel("TREŚĆ : ");
        bodyLabel.setBounds(50,50,250,50);
        bodyLabel.setFont(font.deriveFont(Font.BOLD));
        panel.add(bodyLabel);

        body = new JTextArea();
        body.setBounds(50,100,(int)(dim.getWidth()-100),500);
        body.setFont(font2.deriveFont(Font.BOLD));
        body.setLineWrap(true);

        JScrollPane pane = new JScrollPane(body);
        pane.setBounds(50,100,(int)(dim.getWidth()-100),500);
        pane.setBorder(border);
        panel.add(pane);

        double h = dim.getHeight()-975;
        double w = dim.getWidth()/2-150;

        accept = new JButton("AKCEPTUJ");
        accept.setBounds(100,750,(int)w,(int)h);
        accept.setFont(font.deriveFont(Font.BOLD));
        accept.setBackground(Color.WHITE);
        accept.setIcon(ViewHelper.setIcon("checked.png",(int)(h)));
        panel.add(accept);

        cancel = new JButton("ANULUJ");
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

    public void update(){
        model.setMessage(body.getText());
    }

    public JButton getAccept() {
        return accept;
    }

    public JButton getCancel() {
        return cancel;
    }
}
