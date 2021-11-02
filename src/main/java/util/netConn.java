package util;

import database.Db;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
 
public class netConn {
    
   private String serverDownUrl = "127.0.0.1/server";
   private String Server = "";
   private int port = 0;
     
   public  String getUrl(){
       return serverDownUrl;
   }
   
   public  void setUrl(String url){
        this.serverDownUrl = url;
   }
   
   
    public  void conn() throws IOException {
        URL url = new URL("http://"+serverDownUrl);
        URLConnection httpUrlConnection = url.openConnection();
        
        InputStream inputStream = httpUrlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        while((line = bufferedReader.readLine()) != null) {
        String sp[] = line.split(",");
            Server = sp[0];
            port = Integer.parseInt(sp[1]);
        }
        bufferedReader.close();
    }

    public String getServer() {
        return Server;
    }

    public int getPort() {
        return port;
    }
   
}