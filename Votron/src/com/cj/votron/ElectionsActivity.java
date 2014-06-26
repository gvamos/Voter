/**
 * 
 */
package com.cj.votron;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * @author gvamos
 *
 */
public class ElectionsActivity extends Activity {


	private Spinner electionListSpinner;
	private ArrayAdapter<String> electionListAdapter ; 	  
	private Config.Elections elections;

	private Spinner votersListSpinner ;  
	private ArrayAdapter<String> voterListAdapter ; 	  
	private Config.Voters voters;

	@Override  
	public void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState); 
		Log.i(this.getClass().getName(),":onCreate");
		setContentView(R.layout.activity_elections); 

		elections = Config.getInstance().getElections();
		elections.updateElections();       

		electionListSpinner = (Spinner) findViewById( R.id.electionSpinner );  	    
		electionListAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, (elections.getElectionsList()));  
		electionListSpinner.setAdapter( electionListAdapter );   

		voters = Config.getInstance().getVoters();
		voters.updateVoters();  

		// Shave the yak.  
		votersListSpinner = (Spinner) findViewById( R.id.voterSpinner );  	    
		voterListAdapter = new ArrayAdapter<String>(this, R.layout.simplerow, (voters.getVotersList()));  
		votersListSpinner.setAdapter( voterListAdapter );        

	}

	void doFoo(View v){
		// Example for button action
	}

	public void syncPressed(View view){
		String msg = "Sync pressed";
		System.out.println(msg);
		ServerLink.getInstance().getElectionQuery(Config.VOTERS, this);
		ServerLink.getInstance().getElectionQuery(Config.ELECTIONS, this);
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
		//debugText.setText(msg);
		System.out.println("DBG display over");
		
	}



}
