package com.example.aabdelnazeer.foodrecipeproject.ui.webView;

import android.view.View;

public interface ViewOnlyWebViewScreen {
	void loadUrl(String url);
	void setWebViewScreenListner(WebViewListner listner);
	View getView();

	void setTitle(String title);
}
