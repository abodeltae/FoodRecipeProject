package com.example.aabdelnazeer.foodrecipeproject.ui.webView;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.aabdelnazeer.foodrecipeproject.R;

import java.lang.ref.WeakReference;

public class ViewOnlyWebViewScreenImp extends RelativeLayout implements ViewOnlyWebViewScreen{
	private WebView webView;
	private TextView titleTv;
	WeakReference<WebViewListner>webViewListnerWeakReference=new WeakReference<WebViewListner>(null);

	public ViewOnlyWebViewScreenImp(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		inflate(context,R.layout.web_view_screen,this);
		webView=(WebView)findViewById(R.id.webViewScreenWebView);
		webView.setWebViewClient(new WebViewClient());
		titleTv=(TextView)findViewById(R.id.webViewScreenTitle);
		findViewById(R.id.webViewScreenBack).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				WebViewListner webViewListner = webViewListnerWeakReference.get();
				if(webViewListner != null){
					webViewListner.onBackClicked();
				}
			}
		});

	}

	@Override
	public void loadUrl(String url) {
		webView.loadUrl(url);
	}

	@Override
	public void setWebViewScreenListner(WebViewListner listner) {
		webViewListnerWeakReference=new WeakReference<WebViewListner>(listner);
	}

	@Override
	public View getView() {
		return this;
	}

	@Override
	public void setTitle(String title) {
		titleTv.setText(title);
	}
}
