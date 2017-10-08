package Imap;

import Threads.ImapMessageThread;

import javax.activation.DataHandler;
import javax.mail.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
/**
 * Created by Kamil on 08.09.2017.
 */
public class Imap
{
    public static Message[] messages;
    private List<MessageImap> list;
    public static int id;

    private String replace(String word){
        String plain = word.replaceAll("\\<.*?\\>", " ");
        int end = plain.lastIndexOf("}");
        plain  = plain.substring(end+1);
        plain = plain.replaceAll("\\s{2,}", " ").trim();
        plain = plain.replaceAll("<.*?>"," ");
        plain = plain.replaceAll("\\*"," ");
        plain = plain.replaceAll("©"," ");
        plain = plain.replaceAll("&copy;"," ");
        plain = plain.replaceAll("&nbsp;"," ");
        plain = plain.replaceAll("\\s{2,}", " ").trim();
        return plain;

    }

    public MessageImap init (Message message) throws MessagingException, IOException {
        MessageImap messageImap = new MessageImap();
        messageImap.setSubject(message.getSubject());
        messageImap.setFrom(message.getFrom()[0].toString());
        List<String> attachements = new ArrayList<String>();
        Multipart multipart;
        String temp = "";
        StringBuilder builder = new StringBuilder();
        try {
            multipart = (Multipart) message.getContent();
            int count = multipart.getCount();
            for(int k=0;k<count;k++){
                BodyPart bodyPart = multipart.getBodyPart(k);
                String disposition = bodyPart.getDisposition();
                if (disposition != null && (disposition.equalsIgnoreCase("ATTACHMENT"))) {
                    DataHandler handler = bodyPart.getDataHandler();
                    attachements.add(handler.getName());
                }
                else {
                    String plain = replace(bodyPart.getContent().toString());
                    if(!temp.equals(plain)){
                        temp = plain;
                        builder.append(plain);
                    }
                }
            }
        } catch (ClassCastException e){
            String plain = replace(message.getContent().toString());
            builder.append(plain);
        } catch (IOException e) {
            e.printStackTrace();
        }
        messageImap.setBody(builder.toString());
        messageImap.setAttachements(attachements);
        return messageImap;
    }

    private void check(String host, String storeType, String user,String password)
    {

        try {
            Properties properties = new Properties();
            properties.put("mail.imaps.host", host);
            properties.put("mail.imaps.port", "993");
            properties.put("mail.imap.ssl.enable", "true");
            Session emailSession = Session.getDefaultInstance(properties);

            Store store = emailSession.getStore("imaps");
            store.connect(host, user, password);

            Folder emailFolder = store.getFolder("INBOX");//[Gmail]/Wszystkie?Wysłane - co z innymi przegladarkami
            emailFolder.open(Folder.READ_ONLY);

            messages = emailFolder.getMessages();

            //emailFolder.close(false);
            //store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static MessageImap getNextMessage(){
        ImapMessageThread imapThread = new ImapMessageThread(messages[messages.length-id-1]);
        imapThread.start();
        try {
            imapThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        id = id+1;
        return imapThread.getMessage();
    }

    public static MessageImap getPrevMessage(){
        ImapMessageThread imapThread = new ImapMessageThread(messages[messages.length-id+1]);
        imapThread.start();
        try {
            imapThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        id = id-1;
        return imapThread.getMessage();
    }




    public List<MessageImap> getList() {
        return list;
    }

    public void start(String host, String mailStoreType, String username, String password){

        /*host = "imap.gmail.com";
        mailStoreType = "imaps";
        username = "szelagkamil0@gmail.com";
        password ="kmlszelg";*/

        check(host, mailStoreType, username, password);
    }
}
