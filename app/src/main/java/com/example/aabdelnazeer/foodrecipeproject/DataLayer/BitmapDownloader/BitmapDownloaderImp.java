package com.example.aabdelnazeer.foodrecipeproject.DataLayer.BitmapDownloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.aabdelnazeer.foodrecipeproject.CallBacks.SuccessFailureCallBack;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

 public class BitmapDownloaderImp implements BitmapDownloader {



	private static Bitmap getBitMapFromUrl(String src) throws IOException {

			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		}


	@Override
	public void getBitMapFromUrl(final String url, final SuccessFailureCallBack callBack) {
		new AsyncTask<Void,Void,Object>(){

			@Override
			protected Object doInBackground(Void... params) {
				try {
					return getBitMapFromUrl(url);
				} catch (IOException e) {
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

	}


}
