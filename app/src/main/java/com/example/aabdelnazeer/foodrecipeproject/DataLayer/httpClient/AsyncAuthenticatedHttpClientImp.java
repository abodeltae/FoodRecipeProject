package com.example.aabdelnazeer.foodrecipeproject.DataLayer.httpClient;

import android.net.Uri;
import android.os.AsyncTask;

import com.example.aabdelnazeer.foodrecipeproject.CallBacks.SuccessFailureCallBack;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors.JsonProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

 public class AsyncAuthenticatedHttpClientImp implements AsyncAuthenticatedHttpClient {

	private  String authKey;

	public AsyncAuthenticatedHttpClientImp(String authKey) {
		this.authKey=authKey;
	}

		@Override
	  public void asyncCallAPiLink(final String link,
								   Map<String, Object> params,
								   final SuccessFailureCallBack callBack,
								   final JsonProcessor processor) {

		final URL url;
		try {
			url = buildUrl(link, params);
			new AsyncTask<Void, Void, Object>() {

				@Override
				protected Object doInBackground(Void... params) {

					try {
						String response = makeGetRequest(url);
						return processor.process(response);
					} catch (Exception e) {
						e.printStackTrace();
						return e;
					}
				}

				@Override
				protected void onPostExecute(Object o) {
					if(!callBack.isActive())return;
					if (o instanceof Exception) {
						callBack.onFail(o);
						return;
					}
					callBack.onSuccess(o);

				}
			}.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
		} catch (MalformedURLException e) {

			e.printStackTrace();
			if(callBack.isActive()){
				callBack.onFail(e);
			}
		}

	}

	private String makeGetRequest(URL url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		BufferedReader br=new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		StringBuilder builder =new StringBuilder();
		while((line = br.readLine())!= null){
				builder.append(line);
		}
		return builder.toString();
	}

	private URL buildUrl(String link, Map<String, Object> params) throws MalformedURLException {
		Uri.Builder builder = Uri.parse(link).buildUpon();
		appendAuthenticationParameter(builder, authKey);
		if (params != null) {
			for (String key : params.keySet()) {
				Object value=params.get(key);
				if(value != null ){
					builder.appendQueryParameter(key, value.toString());
				}

			}
		}

		Uri uri = builder.build();
		return new URL(uri.toString());

	}

	private void appendAuthenticationParameter(Uri.Builder builder, String authKey) {
		builder.appendQueryParameter("key", authKey);
	}

 }
