/**
 * @category Distributed Systems Project4-2
 * @author Preston Lin
 * @date Apr 8, 2014
 * @note Develop Mobile platform for the Web application deployed on Google Web Engine 
 */

package ds.interestingpicture;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/*
 * Because this application needs access to the Internet, you need to add the appropriate permissions to the Android manifest file. 
 * Open the AndroidManifest.xml file and add the following as a child of the <manifest> element:
 * <uses-permission android:name="android.permission.INTERNET" />
 */
public class CoffeeSearch extends Activity {
    
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        /*
         * The click listener will need a reference to this object, so that upon successfully finding a coffee shop info through our web service, it 
         * can callback to this object with the resulting contents.
         */
        final CoffeeSearch ip = this;
        
        /*
         * Find the "submit" button, and add a listener to it
         */
        Button submitButton = (Button)findViewById(R.id.submit);
        
        
      	// Add a listener to the send button
        submitButton.setOnClickListener(new OnClickListener(){
        	public void onClick(View viewParam) {
        		String searchTerm = ((EditText)findViewById(R.id.searchTerm)).getText().toString().replace(" ", "%20");
        		GetContact gp = new GetContact();
        		gp.search(searchTerm, ip); 
        		// Done asynchronously in another thread.  It calls ip.contentReady() in this thread when complete.
        		
        	}
        });
    }
    
    /* 
     * This is called by the AsyncSearch object when the result is ready.  This allows for passing back the Bitmap picture for updating the ImageView
     */
    public void contentReady(String[] s) {
    	
    	WebView webview = (WebView) findViewById(R.id.menu);
		TextView searchView = (TextView) findViewById(R.id.result);
		
		if ((s != null) && !s[1].contains("Please try enter the city")){
			searchView.setText(s[0]+s[1]);
    		searchView.setVisibility(View.VISIBLE);
    		if(s[2].contains("http")){		
    			Uri uri = Uri.parse(s[2]);
	    		webview.loadUrl(s[2]);
	    		webview.setVisibility(View.VISIBLE);
    		}
    		else{
    			webview.setVisibility(View.INVISIBLE);
    		}
    	} else {
    		searchView.setText("Please try enter a city you are searching for a coffee shop!");
    		//searchView.setVisibility(View.VISIBLE);
    		webview.setVisibility(View.INVISIBLE);
    	}
    }
}