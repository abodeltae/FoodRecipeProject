package com.example.aabdelnazeer.foodrecipeproject.DataLayer.BitmapDownloader;

import com.example.aabdelnazeer.foodrecipeproject.CallBacks.SuccessFailureCallBack;

public interface BitmapDownloader {
	void getBitMapFromUrl(String url, SuccessFailureCallBack callBack);
}
