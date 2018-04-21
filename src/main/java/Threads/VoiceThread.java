package Threads;

import Imap.MessageImap;
import Wideo.JPanelOpenCV;
import javafx.scene.media.MediaPlayer;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Kamil on 28.11.2017.
 */
public class VoiceThread extends Thread{

    private JPanelOpenCV panel;
    private int size;
    private MessageImap msg;
    private boolean read;
    private String message;

    public VoiceThread(JPanelOpenCV panel) {
        this.panel = panel;
        this.size = panel.getLengthVoice();
        this.read = false;
    }

    public void play(String name){
        try {
            File file = new File(name);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);

            Player player = new Player(bis);
            player.play();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void setMsg(MessageImap msg) {
        this.msg = msg;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    @Override
    public void run(){
        if(msg!=null) {
            ReadMessageThread thread;
            thread = new ReadMessageThread(msg,read);
            thread.start();
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (panel.getClicked()) {
                    thread.stopIt();
                    panel.setClicked(false);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        if(message!=null){
            ReadSkypeMessageThread thread;
            thread = new ReadSkypeMessageThread(message);
            thread.start();
            while (true) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (panel.getClicked()) {
                    thread.stopIt();
                    panel.setClicked(false);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }

        String[][] array = panel.getBtn();
        int i=0;
        int j=0;
        boolean next = false;
        while(true){
            play("src/main/java/Sounds"+array[i][j]+".mp3");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(panel.getClicked()){
                if(array[0].length==1) {
                    panel.accept(i);
                    break;
                }
                else {
                    if(!next){
                        next = true;
                        panel.setClicked(false);
                    }
                    else {
                        next = false;
                        panel.accept(i,j-1);
                        if(i == (size-1)) break;
                        panel.setClicked(false);
                        i=-1;
                        j=0;
                    }

                }
            }
            if(!next) {
                i++;
                if (i == size) i = 0;
            } else {
                j++;
                if(j == array[i].length) j=1;
            }
        }
    }
}
