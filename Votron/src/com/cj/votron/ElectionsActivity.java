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

	  
}
