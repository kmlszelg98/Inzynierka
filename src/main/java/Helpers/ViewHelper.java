package Helpers;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Kamil on 17.10.2017.
 */
public class ViewHelper {

    public static ImageIcon setIcon(String name, int size){
        ImageIcon imageIcon = new ImageIcon("src/main/java/Images/"+name);
        Image image = imageIcon.getImage(); // transform it
        Image newimg = image.getScaledInstance(size,size,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        imageIcon = new ImageIcon(newimg);
        return imageIcon;
    }
}
