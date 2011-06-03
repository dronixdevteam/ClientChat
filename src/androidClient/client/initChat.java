package androidClient.client;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.TextView;

public class initChat extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chat);
	Intent i=getIntent();
	String stanza=i.getStringExtra("stanza");
	 clientH=androidChatActivity.getClient();
     clientH.setHandler(cHandler);
     clientH.setContext(this);
			clientH.start();
 TextView titolo = (TextView) findViewById(R.id.stanza);
	titolo.setText("Stanza: "+stanza);
		
  
        
    logArea = (TextView) findViewById(R.id.output);
       
    
        Button send=(Button) findViewById(R.id.button1);
		send.setOnClickListener(new OnClickListener(){

			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				   EditText inUser = (EditText) findViewById(R.id.editText1);
				String message=inUser.getText().toString();
				try {
					if(!message.equals(null))sendMessage(message);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			
			
			
			
			
		});
		
    }	
	
   
    private void sendMessage(String message) throws IOException {
       
        // Check that there's actually something to send
        if (message.length() > 0) {
            // Get the message bytes and tell the BluetoothChatService to write
            byte[] send = message.getBytes();
             clientH.write(send);
             if(message.equalsIgnoreCase("exit")){
            	 clientH.close();
            	 finish();
            	 
             }
        }
    }
    
    
      
	 private final static Handler cHandler = new Handler() {
	        @Override
	        public void handleMessage(Message msg) {
	            switch (msg.what) {
	   
	            case 1:
	                byte[] readBuf = (byte[]) msg.obj;
	                // construct a string from the valid bytes in the buffer
	                String readMessage = new String(readBuf, 0, msg.arg1);
	               // mConversationArrayAdapter.add(mConnectedDeviceName+":  " + readMessage);
	                Log.i("EVENT", readMessage);
	                logArea.append(readMessage);  	
	                logArea.append("\n");  	
	                      	 
	        		Scroller sc=new Scroller(logArea.getContext());
	        		sc.extendDuration(BIND_AUTO_CREATE);
	        	      	
	                break;
	            
	            }
	        }
	    };      
	    
	private static void log(String text) {
		// Logga
		
		// Mostra su schermo.
       
	
	

	 
}
			    
	 
	    // String buffer for outgoing messages
	private Context context;

	    private StringBuffer mOutStringBuffer;
private static TextView logArea;
private ClientHandler clientH;
private String mess;
private Intent intent;}