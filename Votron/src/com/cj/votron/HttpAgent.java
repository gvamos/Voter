package com.cj.votron;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class HttpAgent {

	String url;
	Context context;
	String payload;
	String message;
	Callback resultAgent;

	HttpAgent(Context context) {
		this.context = context;
	}

	void fetch(String url, Callback resultAgent, String message) {
		this.url = url;
		this.resultAgent = resultAgent;
		this.message = message;
		HttpAsychTask task = new HttpAsychTask();
		task.execute();
	}

	public class HttpAsychTask extends AsyncTask<Void, Void, Void> {

		ProgressDialog dialog;

		@Override
		protected void onPreExecute() {
			dialog = new ProgressDialog(context);
			dialog.setTitle(message);
			dialog.show();
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			HttpClient client = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(url);
			try {
				HttpEntity entity = client.execute(getRequest).getEntity();
				if (entity != null) {
					BufferedReader reader = new BufferedReader(
							new InputStreamReader(entity.getContent()));
					StringBuilder builder = new StringBuilder();
					String line;
					while ((line = reader.readLine()) != null) {
						builder.append(line);
					}
					payload = builder.toString();
				}
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			dialog.dismiss();
			resultAgent.ok(payload);
			super.onPostExecute(result);
		}
	}
}