package Skype;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kamil on 12.09.2017.
 */
public class Skype {

    private List<SkypeMessage> list;

    private Connection connect(String name){
        String OS = System.getProperty("os.name").toLowerCase();
        String url = "";
        if(OS.contains("win")){ // Windows XP ???? C:\Documents and Settings\<WindowsUserName>\Application Data\Skype\<SkypeUserName>\main.db
            url = "jdbc:sqlite:C:\\Users\\"+System.getProperty("user.name")+"\\AppData\\Roaming\\Skype\\"+name+"\\main.db";
        } else if (OS.contains("mac")){
            url = "jdbc:sqlite:~/Library/Application Support/Skype/"+name+"/main.db";
        } else if (OS.contains("nux")){
            url = "jdbc:sqlite:~/.Skype/"+name+"/main.db";
        } else {

        }
        //String url = "jdbc:sqlite:C:\\Users\\Kamil\\AppData\\Roaming\\Skype\\teresa.szelg34\\main.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void select(String name){
        String sql = "SELECT author, body_xml FROM Messages";
        Statement stmt;
        try
        {
            Connection conn = this.connect(name);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                SkypeMessage message = new SkypeMessage();
                message.setAuthor(rs.getString("author"));
                message.setBody(rs.getString("body_xml"));
                list.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void init(String name){
        list = new ArrayList<SkypeMessage>();
        select(name);
    }
}
