package com.example.aabdelnazeer.foodrecipeproject;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.aabdelnazeer.foodrecipeproject.ui.webView.ViewOnlyWebViewScreen;
import com.example.aabdelnazeer.foodrecipeproject.ui.webView.ViewOnlyWebViewScreenImp;
import com.example.aabdelnazeer.foodrecipeproject.ui.webView.WebViewListner;

public class ViewOnlyWebViewActivity extends AppCompatActivity {

	private final static String EXTRA_TITLE="EXTRA_TITLE";
	private static final String EXTRA_URL="EXTRA_URL";

	private WebViewListner listner=createWebViewScreenLister();



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ViewOnlyWebViewScreenImp screen = new ViewOnlyWebViewScreenImp(this);
		setContentView(screen.getView());
		Intent intent=getIntent();
		String url= intent.getStringExtra(EXTRA_URL);
		String title=intent.getStringExtra(EXTRA_TITLE);
		screen.setTitle(title);
		screen.loadUrl(url);
		screen.setWebViewScreenListner(listner);

	}

	public static Intent getStartIntent(Context context,String title, String url){
		Intent intent=new Intent(context,ViewOnlyWebViewActivity.class);
		intent.putExtra(EXTRA_TITLE,title);
		intent.putExtra(EXTRA_URL,url);
		return intent;
	}
	private WebViewListner createWebViewScreenLister() {
		return new WebViewListner() {
			@Override
			public void onBackClicked() {
				finish();
			}
		};
	}

}
