package View;

import Model.InboxModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Kamil on 05.10.2017.
 */
public class InboxView {

    private InboxModel model;
    private JFrame frame;
    private JLabel from;
    private JLabel subject;
    private JButton next;
    private JButton prev;
    private JButton read;
    private JButton back;


    public InboxView(InboxModel model) {
        this.model = model;
        init();
    }

    private void init(){

        Font font = new Font("Arial", Font.ITALIC, 40);
        Font font2 = new Font("Arial", Font.ITALIC, 50);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);


        frame = new JFrame();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        double x = dim.getHeight()/5;

        from = new JLabel("OD: "+model.getMessage().getFrom(),SwingConstants.CENTER);
        from.setBorder(border);
        from.setBounds(50,50,(int)(dim.getWidth()-100),200);
        from.setFont(font2.deriveFont(Font.BOLD));
        frame.add(from);

        subject = new JLabel("TEMAT: "+model.getMessage().getSubject(),SwingConstants.CENTER);
        subject.setBorder(border);
        subject.setBounds(50,300,(int)(dim.getWidth()-100),200);
        subject.setFont(font2.deriveFont(Font.BOLD));
        frame.add(subject);

        double x2 = (dim.getHeight()-600)/3;
        double w = dim.getWidth()/2-200;

        read = new JButton("ODCZYTAJ");
        read.setBounds((int)(dim.getWidth()/2-w/2),(int)(450 + x2/2),(int)w,(int)(x2/2));
        read.setFont(font.deriveFont(Font.BOLD));
        frame.add(read);

        next = new JButton("NASTĘPNY");
        next.setBounds((int)(dim.getWidth()/2+100),(int)(450 + x2+ x2/2),(int)w,(int)(x2/2));
        next.setFont(font.deriveFont(Font.BOLD));
        frame.add(next);

        prev = new JButton("POPRZEDNI");
        prev.setBounds(100,(int)(450 + x2+ x2/2),(int)w,(int)(x2/2));
        prev.setFont(font.deriveFont(Font.BOLD));
        frame.add(prev);

        back = new JButton("WSTECZ");
        back.setBounds((int)(dim.getWidth()/2-w/2),(int)(450 +x2*2 + x2/2),(int)w,(int)(x2/2));
        back.setFont(font.deriveFont(Font.BOLD));
        frame.add(back);

        JTextArea msgField = new JTextArea();
        msgField.setEditable(false);
        frame.add(msgField);

        frame.getLayeredPane().getComponent(1).setFont(new Font("Lucida",Font.PLAIN,20));
        frame.setTitle("Aplikacja");

        frame.setLocation(0,0);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);


    }

    public JFrame getFrame() {
        return frame;
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
