package View;

import Helpers.FrameHelper;
import Helpers.ViewHelper;
import Model.InboxModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Kamil on 05.10.2017.
 */
public class InboxView {

    private InboxModel model;
    private JPanel panel;
    private JLabel from;
    private JLabel subject;
    private JButton next;
    private JButton prev;
    private JButton read;
    private JButton back;

    private Font font2;
    private Border border;
    private Dimension dim;

    public InboxView(InboxModel model) {
        this.model = model;
        init();
    }

    private void init(){

        Font font = new Font("Arial", Font.ITALIC, 40);
        font2 = new Font("Arial", Font.ITALIC, 50);
        border = BorderFactory.createLineBorder(Color.BLACK, 1);

        panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.WHITE);

        dim = Toolkit.getDefaultToolkit().getScreenSize();
        double x = dim.getHeight()/5;

        from = new JLabel("OD: "+model.getMessage().getFrom(),SwingConstants.CENTER);
        from.setBorder(border);
        from.setBounds(50,50,(int)(dim.getWidth()-100),200);
        from.setFont(font2.deriveFont(Font.BOLD));
        panel.add(from);

        subject = new JLabel("TEMAT: "+model.getMessage().getSubject(),SwingConstants.CENTER);
        subject.setBorder(border);
        subject.setBounds(50,300,(int)(dim.getWidth()-100),200);
        subject.setFont(font2.deriveFont(Font.BOLD));
        panel.add(subject);

        double x2 = (dim.getHeight()-600)/3;
        double w = dim.getWidth()/2-200;

        read = new JButton("ODCZYTAJ");
        read.setBounds((int)(dim.getWidth()/2-w/2),(int)(450 + x2/2),(int)w,(int)(x2/2));
        read.setFont(font.deriveFont(Font.BOLD));
        read.setBackground(Color.WHITE);
        read.setIcon(ViewHelper.setIcon("read.png",(int)(x2/2.5)));
        panel.add(read);

        next = new JButton("NASTĘPNY");
        next.setBounds((int)(dim.getWidth()/2+100),(int)(450 + x2+ x2/2),(int)w,(int)(x2/2));
        next.setFont(font.deriveFont(Font.BOLD));
        next.setBackground(Color.WHITE);
        next.setIcon(ViewHelper.setIcon("next.png",(int)(x2/2)));
        panel.add(next);

        prev = new JButton("POPRZEDNI");
        prev.setBounds(100,(int)(450 + x2+ x2/2),(int)w,(int)(x2/2));
        prev.setFont(font.deriveFont(Font.BOLD));
        prev.setBackground(Color.WHITE);
        prev.setIcon(ViewHelper.setIcon("prev.png",(int)(x2/2)));
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
        panel.remove(from);
        from = new JLabel("OD: "+model.getMessage().getFrom(),SwingConstants.CENTER);
        from.setBorder(border);
        from.setBounds(50,50,(int)(dim.getWidth()-100),200);
        from.setFont(font2.deriveFont(Font.BOLD));
        panel.add(from);

        panel.remove(subject);
        subject = new JLabel("TEMAT: "+model.getMessage().getSubject(),SwingConstants.CENTER);
        subject.setBorder(border);
        subject.setBounds(50,300,(int)(dim.getWidth()-100),200);
        subject.setFont(font2.deriveFont(Font.BOLD));
        panel.add(subject);

        FrameHelper.frame.getContentPane().removeAll();
        FrameHelper.frame.getContentPane().add(panel);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();

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
