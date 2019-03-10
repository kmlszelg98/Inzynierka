package Skype.SkypeConn;

import Skype.*;
import com.samczsun.skype4j.Skype;
import com.samczsun.skype4j.SkypeBuilder;
import com.samczsun.skype4j.Visibility;
import com.samczsun.skype4j.chat.Chat;
import com.samczsun.skype4j.chat.IndividualChat;
import com.samczsun.skype4j.events.Listener;
import com.samczsun.skype4j.exceptions.ChatNotFoundException;
import com.samczsun.skype4j.exceptions.ConnectionException;
import com.samczsun.skype4j.exceptions.InvalidCredentialsException;
import com.samczsun.skype4j.exceptions.NotParticipatingException;
import com.samczsun.skype4j.user.Contact;
import com.samczsun.skype4j.user.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Kamil on 12.09.2017.
 */
public class SkypeConnection {

    private Skype skype;
    private static List<Chat> chatList;

    public SkypeConnection(String name, String password) {
        skype = new SkypeBuilder(name,password).withAllResources().build();

//        try {
//            skype.login();
//        } catch (NotParticipatingException e){
//            e.printStackTrace();
//        } catch (InvalidCredentialsException e){
//            e.printStackTrace();
//        } catch (ConnectionException e){
//            //e.printStackTrace();
//        }

        //skype.getEventDispatcher().registerListener(new SkypeListener(list));
//        try {
////            skype.subscribe();
////        } catch (ConnectionException e) {
            //e.printStackTrace();
        }
        /*try {
            skype.loadMoreChats(10);
        } catch (ConnectionException e) {
            e.printStackTrace();
        }*/
        /*try {
            skype.setVisibility(Visibility.ONLINE);
        } catch (ConnectionException e) {
            e.printStackTrace();
        }*/



    public void printChats(){
        chatList = new ArrayList<>();
        Collection<Contact> cont = skype.getAllContacts();
        for (Contact c: cont){
            try {
                Chat chat = c.getPrivateConversation();
                chatList.add(chat);
            } catch (ConnectionException e) {
                e.printStackTrace();
            } catch (ChatNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<Chat> getChatList() {
        return chatList;
    }
}
