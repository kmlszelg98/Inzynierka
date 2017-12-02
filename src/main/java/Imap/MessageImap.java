package Imap;

import java.util.List;

/**
 * Created by Kamil on 08.09.2017.
 */
public class MessageImap {

    private String from;
    private String subject;
    private String body;
    private String lang;
    private List<String> attachements;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public List<String> getAttachements() {
        return attachements;
    }

    public void setAttachements(List<String> attachements) {
        this.attachements = attachements;
    }
}
