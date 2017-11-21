package Wideo;

import Helpers.FrameHelper;
import org.opencv.core.*;
import org.opencv.highgui.VideoCapture;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;

public class JPanelOpenCV extends JPanel{

    private BufferedImage image;
    private JButton[][] buttons;


    public void setImage(BufferedImage image) {
        this.image = image;
    }


    public void change(BufferedImage img){

        FrameHelper.frame.getContentPane().removeAll();
        this.setImage(img);
        FrameHelper.frame.getContentPane().add(this);
        FrameHelper.frame.revalidate();
        FrameHelper.frame.repaint();

    }

    public void setButtons(JButton[][] buttons) {
        this.buttons = buttons;
    }

    public int getLength(){
        if(buttons==null) return 0;
        return buttons.length;
    }

    public void acceptMulti(int row){
        for (JButton button:buttons[row]) {
            button.setBackground(Color.GREEN);
        }
    }

    public void colorColumns(int number,int row){
        int previous = (number>0?number-1:buttons[row].length-1);
        buttons[row][previous].setBackground(Color.WHITE);
        buttons[row][number].setBackground(Color.ORANGE);
    }


    public void colorRows(int number){
        if(buttons!=null) {
            int previous = (number > 0 ? number - 1 : buttons.length-1);
            for(JButton button:buttons[previous]){
                button.setBackground(Color.WHITE);
            }
            for(JButton button:buttons[number]) {
                button.setBackground(Color.ORANGE);
            }
        }
    }

    public void accept(int row){
        buttons[row][0].setBackground(Color.GREEN);

        for (ActionListener a : buttons[row][0].getActionListeners()) {
               a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
               });
           }
        //buttons[row][0].setBackground(Color.WHITE);

    }

    public void accept(int row,int col){
        buttons[row][col].setBackground(Color.GREEN);
        for (ActionListener a : buttons[row][col].getActionListeners()) {
            a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null) {
            });
        }
        buttons[row][col].setBackground(Color.WHITE);
    }

    public int getRowSize(int row){
        return buttons[row].length;
    }

    public void clearColor(int row){
        if(buttons!=null) {
            for(int i =0;i<buttons[row].length;i++)
            buttons[row][i].setBackground(Color.WHITE);
        }
    }

    public boolean isMulti(){
        if(buttons[0].length>1) return true;
        else return false;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        if(image!=null) {
            int w = image.getWidth();
            int h = image.getHeight();
            g.drawImage(image, 50, 50, (int) dim.getWidth() / 4, (int) dim.getWidth() / 4 * h / w, this);
        }
        //g.drawImage(image, 50, 50, this);
    }

    public JPanelOpenCV() {

    }

    public JPanelOpenCV(BufferedImage img) {
        image = img;
    }



}