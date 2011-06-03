
package androidClient.client;

import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class androidChatActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		try {
			setupChat();
		
		
		
		
	} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	 Spinner spinner = (Spinner) findViewById(R.id.spinner1);
   final ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.nomi, android.R.layout.simple_spinner_item);
	 
	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	 spinner.setAdapter(adapter);
     spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

		public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			stanza=(String)adapter.getItem(arg2);
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			
		}
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 
     });
     
     
	Button connect=(Button) findViewById(R.id.connect);
	connect.setOnClickListener(new OnClickListener(){

		
		
		public void onClick(View arg0) {
			EditText nick=(EditText) findViewById(R.id.nick);
			
			String nickC=nick.getText().toString();
			try {
				sendMessage(nickC);
		
			startInitChat();
			
			
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}
		
		
		
		
		
	});
	
	
	}

	
	
	
	
	
	
	  private void sendMessage(String message) throws IOException {
	      
	        if (message.length() > 0) {
	            byte[] send = message.getBytes();
	            clientH.write(send);
	        }
	    }
	
	
	
		 private void setupChat() throws IOException {
		        Log.i("EVENT", "setupChat()");
		        clientH=new ClientHandler(this, cHandler);
		        clientH.socketInit();
		
		
		       
	
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
		                
		               
		                break;
		            
		            }
		        }
		    };
private void startInitChat(){

	Intent intentC=new Intent(this, initChat.class);
	intentC.putExtra("stanza", stanza);
	startActivity(intentC);
	
	
}

public static ClientHandler getClient(){
	return clientH;
}


private TextView logArea;
		 private static ClientHandler clientH;
		 private String mess;
		 private Intent intent;
		 private String stanza;
}
