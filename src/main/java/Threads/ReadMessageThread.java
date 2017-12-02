package Threads;

import Imap.MessageImap;
import Wideo.Player;

/**
 * Created by Kamil on 02.12.2017.
 */
public class ReadMessageThread extends Thread{

    private MessageImap msg;
    private Player player;
    private boolean read;

    public ReadMessageThread(MessageImap msg,boolean read){
        this.msg = msg;
        this.read = read;
    }

    @Override
    public void run(){
        player = new Player();
        player.start(msg,read);
    }

    public void stopIt(){
        player.stop();
    }
}
