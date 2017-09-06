package View;

import Helpers.WindowHelper;
import Model.RegisterModel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

/**
 * Created by Kamil on 06.09.2017.
 */
public class RegisterView {

    private JFrame frame;
    private RegisterModel model;
    private JTextField nameArea;
    private JTextField passArea;
    private JTextField mailArea;
    private JComboBox typeBox;
    private JButton registerButton;

    public RegisterView(RegisterModel model) {
        this.model = model;
        init();
    }

    public void init(){
        WindowHelper helper = new WindowHelper();
        helper.setView();
        frame = new JFrame();

        int width = 600;
        int height = 800;

        Font font = new Font("Arial", Font.ITALIC, 18);
        Border border = BorderFactory.createLineBorder(Color.BLACK, 1);


        JLabel nameLabel = new JLabel("NAZWA");
        nameLabel.setBounds(70,50,150,50);
        nameLabel.setFont(font.deriveFont(Font.BOLD));
        frame.add(nameLabel);

        nameArea = new JTextField(model.getName());
        nameArea.setBounds(270,60,230,30);
        nameArea.setFont(font.deriveFont(Font.BOLD));
        nameArea.setBackground(Color.LIGHT_GRAY);
        nameArea.setForeground(Color.BLACK);
        nameArea.setBorder(border);
        frame.add(nameArea);

        JLabel passLabel = new JLabel("HASLO");
        passLabel.setBounds(70,150,150,50);
        passLabel.setFont(font.deriveFont(Font.BOLD));
        frame.add(passLabel);

        passArea = new JTextField(model.getPassword());
        passArea.setBounds(270,160,230,30);
        passArea.setFont(font.deriveFont(Font.BOLD));
        passArea.setBackground(Color.LIGHT_GRAY);
        passArea.setForeground(Color.BLACK);
        passArea.setBorder(border);
        frame.add(passArea);

        JLabel mailLabel = new JLabel("EMAIL");
        mailLabel.setBounds(70,250,150,50);
        mailLabel.setFont(font.deriveFont(Font.BOLD));
        frame.add(mailLabel);

        mailArea = new JTextField(model.getPassword());
        mailArea.setBounds(270,260,230,30);
        mailArea.setFont(font.deriveFont(Font.BOLD));
        mailArea.setBackground(Color.LIGHT_GRAY);
        mailArea.setForeground(Color.BLACK);
        mailArea.setBorder(border);
        frame.add(mailArea);

        JLabel typeLabel = new JLabel("TYP");
        typeLabel.setBounds(70,350,150,50);
        typeLabel.setFont(font.deriveFont(Font.BOLD));
        frame.add(typeLabel);

        String [] types = {"intelektualnie", "niewidzący", "ruchowo"};
        typeBox = new JComboBox(types);
        typeBox.setBounds(270,360,230,30);
        typeBox.setFont(font.deriveFont(Font.BOLD));
        typeBox.setBackground(Color.LIGHT_GRAY);
        typeBox.setForeground(Color.BLACK);
        typeBox.setBorder(border);
        frame.add(typeBox);

        registerButton= new JButton("REJESTRUJ");
        registerButton.setBounds(200,480,200,50);
        registerButton.setFont(font.deriveFont(Font.BOLD));
        frame.add(registerButton);


        JTextArea msgField = new JTextArea();
        msgField.setEditable(false);
        frame.add(msgField);

        frame.getLayeredPane().getComponent(1).setFont(new Font("Lucida",Font.PLAIN,20));
        frame.setTitle("REGISTER FORM");
        frame.setSize(600,600);//600x500

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(dim.width/2-width/2, dim.height/2-height/2);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

    }public void updateModel(){
        model.setName(nameArea.getText());
        model.setPassword(passArea.getText());
        model.setEmail(mailArea.getText());
        model.setType(typeBox.getSelectedItem().toString());
    }



    public JFrame getFrame() {
        return frame;
    }

    public JButton getRegisterButton() {
        return registerButton;
    }
}
