/**
 * 
 */
package com.cj.votron;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * @author gvamos
 *
 */
public class ElectionsActivity extends Activity {


	HttpAgent electionHttpAgent;
	HttpAgent voterHttpAgent;
	
	private Spinner electionListSpinner;
	private ArrayAdapter<String> electionListAdapter ; 	
	private List<String>electionList = new ArrayList<String>();
	private ElectionUpdater electionUpdater;

	private Spinner votersListSpinner ;  
	private ArrayAdapter<String> voterListAdapter ;
	private List<String>voterList = new ArrayList<String>();
	private VoterUpdater voterUpdater;
	private Context context;
	
	public String ELECTION_URL = "http://votecastomatic.com/elections";
	public String VOTER_URL = "http://votecastomatic.com/voters";


	@Override  
	public void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState); 
		Log.i(this.getClass().getName(),":onCreate");
		context = this;
		setContentView(R.layout.activity_elections); 
		electionList.add("Empty");
		voterList.add("Empty");

		electionUpdater = new ElectionUpdater();
		electionListSpinner = (Spinner) findViewById( R.id.electionSpinner );  	    
		electionListAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, electionList);  
		electionListSpinner.setAdapter( electionListAdapter );   
		electionHttpAgent = new HttpAgent(context);

		voterUpdater = new VoterUpdater();
		votersListSpinner = (Spinner) findViewById( R.id.voterSpinner );  	    
		voterListAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, voterList); 
		votersListSpinner.setAdapter( voterListAdapter ); 
		voterHttpAgent = new HttpAgent(context);
	}
	
	void requestUpdate(String url, Callback callback, String message){
		
		electionHttpAgent.fetch(url,electionUpdater,"loading elections");	
	}

	void doFoo(View v){
		// Example for button action
	}

	public void syncPressed(View view){
		String msg = "Sync pressed";
		
		System.out.println(msg);
		electionHttpAgent.fetch(ELECTION_URL,electionUpdater,"loading elections");
		voterHttpAgent.fetch(VOTER_URL,voterUpdater,"loading voters");
		Toast.makeText(ElectionsActivity.this,msg,Toast.LENGTH_SHORT).show();         
	}
	
	public void checkStatPressed(View view){
		String msg = "checkStat pressed";
		System.out.println(msg);
		Toast.makeText(ElectionsActivity.this,msg,Toast.LENGTH_SHORT).show();         
	}
	
	public void display(String msg){
		System.out.println("DBG display");
		System.out.println(msg);
		System.out.println("DBG display over");
		
	}
	
	class ElectionUpdater implements Callback{

		@Override
		public void ok(String jsonData) {
			electionList = new ArrayList<String>();
			try {
				JSONArray electionItems = new JSONArray(jsonData);
				for(int i =0; i<electionItems.length(); i++){
					JSONObject electionObject = electionItems.getJSONObject(i);
					electionList.add(electionObject.getString("name"));
				}	
				electionListAdapter.clear();
				electionListAdapter.addAll(electionList);
				electionListAdapter.notifyDataSetChanged();				
			} catch (Exception e){
				throw new RuntimeException(e);
			}		
		}

		@Override
		public void fail(String s) {
			throw new RuntimeException("fail called with " +s);			
		}	
	}

	class VoterUpdater implements Callback{

		@Override
		public void ok(String jsonData) {
			voterList = new ArrayList<String>();
			try {
				JSONArray electionItems = new JSONArray(jsonData);
				for(int i =0; i<electionItems.length(); i++){
					JSONObject electionObject = electionItems.getJSONObject(i);
					voterList.add(electionObject.getString("name"));
				}
				voterListAdapter.clear();
				voterListAdapter.addAll(voterList);
				voterListAdapter.notifyDataSetChanged();				
			} catch (Exception e){
				throw new RuntimeException(e);
			}		
		}

		@Override
		public void fail(String s) {
			throw new RuntimeException("fail called with " +s);			
		}	
	}
}