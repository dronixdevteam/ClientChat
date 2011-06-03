package androidClient.client;


import java.io.*;
import java.net.*;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Scroller;
import android.widget.TextView;

public class ClientHandler extends Thread{
	
	public ClientHandler(Context context, Handler handler) throws IOException{
		 //intent=new Intent(getApplicationContext(), androidChatActivity.class);
		 
		this.context=context;
		this.handler=handler;
 
		}

	public ClientHandler(Parcel in, Context context, Handler han){
	this.context=(Context)in.readValue(getContextClassLoader());
	this.handler=(Handler)in.readValue(getContextClassLoader());
		

}
	
	
public int socketInit() throws UnknownHostException, IOException {
	//ip dalvik virtual machine 
	client = new Socket("10.0.2.2", 8080);
	 bufferR=new BufferedReader(new InputStreamReader(client.getInputStream()));
	dataWrite=new DataOutputStream(client.getOutputStream());
    
	return 1;

}
public void setHandler(Handler h){
	this.handler=h;
	
}
public void close() throws IOException{
	
	client.close();
	bufferR.close();
	dataWrite.close();
}
public void setContext(Context c){
	this.context=c;
	
}
public void run() {

  String buffer ="";
  byte[] bytes= new byte[1024];

    // Keep listening to the InputStream while connected
    while (true) {
        try {
            // Read from the InputStream
            buffer = bufferR.readLine();
            bytes=buffer.getBytes();	
            // Send the obtained bytes to the UI Activity
           
            handler.obtainMessage(1, buffer.length(), -1, bytes)
                    .sendToTarget();
        } catch (IOException e) {
        	e.printStackTrace();
        }
    }
}
public void blockT(){
	interrupt();
	
}

public void write(byte[] message ) throws IOException{
	
	dataWrite.write(message);
	
}

private Socket client;
private static Context context;
private static Handler handler;
private TextView view;
private BufferedReader bufferR;	
private DataOutputStream dataWrite;
private Intent intent;


}