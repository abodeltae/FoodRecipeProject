package com.example.aabdelnazeer.foodrecipeproject.DataLayer.httpClient;

import com.example.aabdelnazeer.foodrecipeproject.CallBacks.SuccessFailureCallBack;
import com.example.aabdelnazeer.foodrecipeproject.DataLayer.JsonProcessors.JsonProcessor;

import java.util.Map;

public interface AsyncAuthenticatedHttpClient {
	void asyncCallAPiLink(final String link,
						  Map<String, Object> params,
						  final SuccessFailureCallBack callBack,
						  final JsonProcessor processor);
}
