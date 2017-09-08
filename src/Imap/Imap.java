package Imap;

import javax.activation.DataHandler;
import javax.mail.*;
import java.io.IOException;
import java.util.Properties;

public class Imap
{
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

    private void printMessage(Message message) throws MessagingException, IOException {
        System.out.println("---------------------------------");
        System.out.println("Subject: " + message.getSubject());
        System.out.println("From: " + message.getFrom()[0]);
        System.out.println("Body: ");
        Multipart multipart;
        String temp = "";
        try {
            multipart = (Multipart) message.getContent();
            int count = multipart.getCount();
            for(int k=0;k<count;k++){
                BodyPart bodyPart = multipart.getBodyPart(k);
                String disposition = bodyPart.getDisposition();
                if (disposition != null && (disposition.equalsIgnoreCase("ATTACHMENT"))) {
                    System.out.println("Mail have some attachment");

                    DataHandler handler = bodyPart.getDataHandler();
                    System.out.println("file name : " + handler.getName());
                }
                else {
                    String plain = replace(bodyPart.getContent().toString());
                    if(!temp.equals(plain)){
                        System.out.println(plain);
                        temp = plain;
                    }
                }
            }
        } catch (ClassCastException e){
            String plain = replace(message.getContent().toString());
            System.out.println(plain);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Message[] messages = emailFolder.getMessages();
            System.out.println("messages.length---" + messages.length);


            for (int j = 0, n = messages.length; j < n; j++) {
                int i = messages.length-j-1;
                Message message = messages[i];
                printMessage(message);
            }

            emailFolder.close(false);
            store.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void start(){
        String host = "imap.gmail.com";
        String mailStoreType = "imaps";
        String username = "kmlszelg98@gmail.com";
        String password ="kmlszelg";

        check(host, mailStoreType, username, password);
    }
}
