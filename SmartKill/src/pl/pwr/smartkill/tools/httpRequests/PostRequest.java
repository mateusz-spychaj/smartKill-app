package pl.pwr.smartkill.tools.httpRequests;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

import pl.pwr.smartkill.tools.NinjaHTTPClient;
import pl.pwr.smartkill.tools.WebserviceHandler;

import android.content.Context;
import android.util.Log;

public class PostRequest implements HttpRequest {

	private String url;
	private HashMap<String, String> entities;
	
	public PostRequest(String url, HashMap<String, String> entities) {
		this.url=url;
		this.entities=entities;
	}
	
	@Override
	public InputStream execute(Context ctx,CookieStore store) {
		HttpParams httpParameters = new BasicHttpParams();
		DefaultHttpClient client = new NinjaHTTPClient(httpParameters, ctx);
		client.setCookieStore(store);
		HttpPost postRequest = new HttpPost(url);
	
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		for(String ent: entities.keySet()){
				nvps.add(new BasicNameValuePair(ent, entities.get(ent)));
		}
	
		try {
			postRequest.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
		} catch (UnsupportedEncodingException e1) {
		}
         try {
        	if (postRequest == null) Log.e("client", "nullowo");
 			HttpResponse getResponse = client.execute(postRequest);
 			final int statusCode = getResponse.getStatusLine().getStatusCode();

 			if (statusCode != HttpStatus.SC_OK) {
 				Log.w(WebserviceHandler.TAG, "Error " + statusCode + " for URL " + url);
 				return null;
 			}

 			HttpEntity responseEntity = getResponse.getEntity();
 			return responseEntity.getContent();

 		} catch (IOException e) {
 			postRequest.abort();
 			Log.w(WebserviceHandler.TAG, "Error for URL " + url, e);
 		}
 		return null;
	}

	@Override
	public String getUrl() {
		return url;
	}
}
