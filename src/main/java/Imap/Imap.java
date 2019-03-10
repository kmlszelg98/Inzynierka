package Imap;

import Threads.ImapMessageThread;
import google.GoogleSheetsService;

import javax.mail.Message;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Objects;

/**
 * Created by Kamil on 04.03.2019.
 */
public class Imap {

    public static GoogleSheetsService googleSheetsService;
    public static MessageImap[] messages;
    public static MessageImap[] sentMessages;
    public static int id;
    public static int type;

    public void initialize() throws IOException, GeneralSecurityException {
        googleSheetsService = new GoogleSheetsService();
    }

    public void start(String host, String mailStoreType, String username, String password){
        id = 0;
        try {
            messages = googleSheetsService.readAllMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            sentMessages = googleSheetsService.readAllMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static MessageImap getNextMessage(boolean type){
        id = id+1;
        if(type) {
            return messages[id];
        } else {
            return sentMessages[id];
        }
    }

    public static MessageImap getPrevMessage(boolean type){
        id = id-1;
        if(type) {
            return messages[id];
        } else {
            return sentMessages[id];
        }
    }

    public static boolean isLast(boolean types){
        if(type==0) {
            return id == messages.length - 1;
        } else {
            return id == sentMessages.length - 1;
        }

    }

    public static boolean isFirst(){
        return id == 1;
    }

    public static String generateValue(String message, int type) throws IOException {
        int index = googleSheetsService.getResponsesCount()+2;
        googleSheetsService.writeToSheet(message, type, index);
        while (true) {
            String value = googleSheetsService.readSingleValue("C"+index);
            if (!Objects.equals(value, "")) return value;
        }

    }
}
