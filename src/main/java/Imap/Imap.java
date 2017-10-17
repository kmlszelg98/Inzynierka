package Imap;

import Threads.ImapMessageThread;
import net.htmlparser.jericho.Source;

import javax.activation.DataHandler;
import javax.mail.*;
import java.io.IOException;
import java.io.InputStream;
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

    public String extractAllText(String htmlText){
        Source source = new Source(htmlText);
        return source.getTextExtractor().toString();
    }

    public void writePart(Part p, StringBuilder builder) throws Exception {

        //check if the content is plain text
        if (p.isMimeType("text/plain")) {
            //System.out.println((String) p.getContent());
            builder.append((String)"\n");
            builder.append((String) p.getContent());
        }
        //check if the content has attachment
        else if (p.isMimeType("multipart/*")) {
            System.out.println("Multipart");
            Multipart mp = (Multipart) p.getContent();
            int count = mp.getCount();
            for (int i = 0; i < count; i++)
                writePart(mp.getBodyPart(i),builder);
        }
        else {
            Object o = p.getContent();
            if (o instanceof String) {
                String extracted = extractAllText((String) ((String) o)).substring(3).replaceAll("â\u0080\u0098","'");
                extracted = extracted.replaceAll("\\s{2,}", "\n");
                builder.append(extracted);
            }

        }

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
            writePart(message,builder);
        } catch (Exception e) {
            e.printStackTrace();
        }

        messageImap.setBody(builder.toString());
        //messageImap.setAttachements(attachements);
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
