package Threads;

import Skype.SkypeMessage;
import Wideo.Player;
import Wideo.PlayerSkype;

/**
 * Created by Kamil on 02.12.2017.
 */
public class ReadSkypeMessageThread extends Thread{

    private String message;
    private PlayerSkype playerSkype;

    public ReadSkypeMessageThread(String message) {
        this.message = message;
    }

    @Override
    public void run(){
        playerSkype = new PlayerSkype();
        playerSkype.start(message);
    }

    public void stopIt(){
        playerSkype.stop();
    }
}
