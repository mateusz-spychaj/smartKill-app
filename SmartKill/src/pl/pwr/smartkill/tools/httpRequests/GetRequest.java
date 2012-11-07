package pl.pwr.smartkill.tools.httpRequests;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;

import pl.pwr.smartkill.tools.NinjaHTTPClient;
import pl.pwr.smartkill.tools.WebserviceHandler;


import android.content.Context;
import android.util.Log;

public class GetRequest implements HttpRequest {

	private String url;
	
	public GetRequest(String url) {
		this.url=url;
	}
	
	@Override
	public InputStream execute(Context ctx,CookieStore store) {
		Log.e("execute","gra");
		HttpParams httpParameters = new BasicHttpParams();
		DefaultHttpClient client = new NinjaHTTPClient(httpParameters, ctx);
		client.setCookieStore(store);
		HttpGet getRequest = new HttpGet(url);
	
	
         try {
 			HttpResponse getResponse = client.execute(getRequest);
 			final int statusCode = getResponse.getStatusLine().getStatusCode();
 			Log.e("status code",statusCode+"");
 			if (statusCode != HttpStatus.SC_OK) {
 				Log.e(WebserviceHandler.TAG, "Error " + statusCode + " for URL " + url);
 				return null;
 			}

 			HttpEntity responseEntity = getResponse.getEntity();
 			return responseEntity.getContent();

 		} catch (IOException e) {
 			getRequest.abort();
 			Log.e(WebserviceHandler.TAG, "Error for URL " + url, e);
 		}
 		return null;
	}

	@Override
	public String getUrl() {
		return url;
	}
}
