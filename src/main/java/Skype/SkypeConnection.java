package Skype;

import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.SkypeBuilder;
import com.samczsun.skype4j.Visibility;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.exceptions.InvalidCredentialsException;
import com.samczsun.skype4j.exceptions.NotParticipatingException;

import java.util.List;

/**
 * Created by Kamil on 12.09.2017.
 */
public class SkypeConnection {

    private Skype skype;

    public SkypeConnection(String name, String password, List<SkypeMessage> list) {
        skype = new SkypeBuilder(name,password).withAllResources().build();

        try {
            skype.login();
        } catch (NotParticipatingException e){
            e.printStackTrace();
        } catch (InvalidCredentialsException e){
            e.printStackTrace();
        } catch (ConnectionException e){
            e.printStackTrace();
        }

        skype.getEventDispatcher().registerListener(new SkypeListener(list));
        try {
            skype.subscribe();
        } catch (ConnectionException e) {
            e.printStackTrace();
        }
        /*try {
            skype.setVisibility(Visibility.ONLINE);
        } catch (ConnectionException e) {
            e.printStackTrace();
        }*/

    }
}
