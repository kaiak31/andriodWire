package com.kaibot.inshanfieldwire;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends Activity {
	private String searchString;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void onSubmit(View view){
		Intent intent = new Intent(this, InshanGrid.class);
		EditText searchText = (EditText)findViewById(R.id.editText1);
		searchString = searchText.getText().toString();
		getResults();
		startActivity(intent);
	}
	
	private String makeSearchString(){
		String apiKey  = "AIzaSyBu4NbqwqPFv1nhYgRM5mLzy2koFqbZfbg";
		String customSearchEngineKey = "citric-scope-495";
		String searchURL = "https://www.googleapis.com/customsearch/v1?";
		String toSearch = searchURL + "key=" + apiKey + "&cx=" + customSearchEngineKey+"&q=";
	     
	    //replace spaces in the search query with +
	    	toSearch+=searchString;
	     
	        //specify response format as json
	        toSearch+="&alt=json";
	    return toSearch;
	}
	
	private void getResults(){
		String request = makeSearchString();
		String result;
		 //pUrl is the URL we created in previous step
	    try
	   {
	         URL url=new URL(request);
	        HttpURLConnection connection=(HttpURLConnection)url.openConnection();
	        BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String line;
	        StringBuffer buffer=new StringBuffer();
	        while((line=br.readLine())!=null){
	            buffer.append(line);
	        }
	        result = buffer.toString();
	        
	    }catch(Exception e){
	        e.printStackTrace();
	   }
	    
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
