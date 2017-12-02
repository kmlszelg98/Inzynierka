package Wideo;

import Imap.MessageImap;
import com.voicerss.tts.*;

import java.io.*;
import java.net.URLEncoder;

/**
 * Created by Kamil on 02.12.2017.
 */
public class Player {

    private javazoom.jl.player.Player player;

    public void createSound(String text, String lang) throws Exception {
        VoiceProvider tts = new VoiceProvider("210c74ddc7634d1385456b840952b49b");


        String encoded = URLEncoder.encode(text, "UTF-8");
        VoiceParameters params;
        if(lang.equals("pl")) {
            params=new VoiceParameters(encoded, Languages.Polish);
        } else {
            params = new VoiceParameters(encoded,Languages.English_GreatBritain);
        }

        params.setCodec(AudioCodec.MP3);
        params.setFormat(AudioFormat.Format_48KHZ.AF_48khz_16bit_stereo);

        params.setBase64(false);
        params.setSSML(false);
        params.setRate(0);

        byte[] voice = tts.speech(params);


        FileOutputStream stream = new FileOutputStream("src/main/java/Sounds/template.mp3");
        stream.write(voice);
        play("src/main/java/Sounds/template.mp3");

    }

    public void play(String name){
        try {
            File file = new File(name);
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);

            player = new javazoom.jl.player.Player(bis);
            player.play();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void start(MessageImap msg, boolean read){
        try {
            if(!read) {
                if (msg.getLang().equals("en"))
                    createSound("Message from " + msg.getFrom() + ". Topic: " + msg.getSubject(), msg.getLang());
                else createSound("Wiadomość od " + msg.getFrom() + ". Temat: " + msg.getSubject(), msg.getLang());
            } else {
                if (msg.getLang().equals("en"))
                    createSound("Body "+msg.getBody(),msg.getLang());
                else createSound("Treść: "+msg.getBody(), msg.getLang());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        player.close();
    }
}
