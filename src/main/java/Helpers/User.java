package Helpers;

/**
 * Created by Kamil on 31.10.2017.
 */
public class User {

    private String mailName;
    private String mailPass;
    private String skypeName;
    private String skypePass;
    private int type;

    public User() {
        mailName = "szelagkamil0@gmail.com";
        mailPass = "kmlszelg";
        skypeName = "kmlszelg98";
        skypePass = "kmlszelg98";
    }

    public String getMailName() {
        return mailName;
    }

    public void setMailName(String mailName) {
        this.mailName = mailName;
    }

    public String getMailPass() {
        return mailPass;
    }

    public void setMailPass(String mailPass) {
        this.mailPass = mailPass;
    }

    public String getSkypeName() {
        return skypeName;
    }

    public void setSkypeName(String skypeName) {
        this.skypeName = skypeName;
    }

    public String getSkypePass() {
        return skypePass;
    }

    public void setSkypePass(String skypePass) {
        this.skypePass = skypePass;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
