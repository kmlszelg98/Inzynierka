package Skype;

import java.sql.*;
import java.util.*;

/**
 * Created by Kamil on 12.09.2017.
 */
public class Skype {

    private Map<String,SkypeChat> map;
    private static List<SkypeChat> list;
    public static int number;

    public static SkypeChat get(){
        return list.get(number);
    }

    private Connection connect(String name){
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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
        String sql = "SELECT chatname,author, body_xml FROM Messages";
        String sql2 = "SELECT skypename,fullname FROM Contacts";
        Statement stmt;
        try
        {
            Connection conn = this.connect(name);
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String chatName = rs.getString("chatname");
                if(chatName.charAt(0)=='#'){
                    String temp = chatName.substring(1);
                    String[] ar = temp.split("[/$;]");
                    if(ar[0].equals(name)) chatName = ar[2];
                    else chatName = ar[0];
                }

                if(map.get(chatName)==null) {
                    map.put(chatName,new SkypeChat(chatName));
                }


                SkypeMessage message = new SkypeMessage();
                message.setAuthor(rs.getString("author"));
                message.setBody(rs.getString("body_xml"));
                map.get(chatName).addMessage(message);

            }
            ResultSet rs2 = stmt.executeQuery(sql2);
            while(rs2.next()){
                String skypename = rs2.getString("skypename");
                if(map.get(skypename) != null ) {
                    map.get(skypename).setChatName(rs2.getString("fullname"));
                    List<SkypeMessage> temp = map.get(skypename).getMessages();
                    for(SkypeMessage msg: temp){
                        if(msg.getAuthor().equals(skypename)) msg.setAuthor(rs2.getString("fullname"));
                    }
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        list = new ArrayList<SkypeChat>(map.values());
        for( SkypeChat ch : list){
            System.out.println(ch.getChatName());
        }

    }

    public static boolean isFirst(){
        if(number == 0 ) return true;
        else return false;
    }

    public static boolean isLast(){
        if(number == list.size()-1) return true;
        return false;
    }

    public void init(String name){
        map = new HashMap<String, SkypeChat>();
        list = new ArrayList<SkypeChat>();
        select(name);
    }
}
