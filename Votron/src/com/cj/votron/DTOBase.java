package com.cj.votron;

import java.util.List;

import android.app.Activity;

import com.cj.votron.ServerLink.Fetch;

abstract public class DTOBase {
	
	protected  String query;	
	public String getQuery(){ return query; }
	
	public List<String>members;
	public List<String> getMembers(){ return members; }

	
	void getUpdate(Activity activity) {
		ServerLink.getInstance().execFetch(activity, this);
	}
	
	abstract void load(String rawData);
	
	public class VotersDTO extends DTOBase {
		
		public VotersDTO(){
			query = Config.VOTERS;			
		}

		@Override
		void load(String rawData) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class ElectionsDTO extends DTOBase {
		
		public ElectionsDTO(){
			query = Config.ELECTIONS;			
		}

		@Override
		void load(String rawData) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public class BallotDTO extends DTOBase {
		
		public BallotDTO(){
			query = Config.BALLOT;
			
		}

		@Override
		void load(String rawData) {
			// TODO Auto-generated method stub
			
		}
		
	}	

}
