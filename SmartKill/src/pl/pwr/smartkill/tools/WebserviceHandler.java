package pl.pwr.smartkill.tools;


import java.io.IOException; 
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import org.apache.http.client.CookieStore;
import org.apache.http.impl.client.BasicCookieStore;

import pl.pwr.smartkill.tools.httpRequests.HttpRequest;


import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.googlecode.androidannotations.annotations.EBean;

@EBean
public class WebserviceHandler<T extends Object> {
	public final static String TAG = "Webservice Connector";
	
	static CookieStore cookieStore = new BasicCookieStore();

	@SuppressWarnings("unchecked")
	public T getAndParse(Context ctx,HttpRequest request, T model) {
		Log.i(TAG, "ws invoked for url " + request.getUrl());

		T ret = model;

		InputStream source = request.execute(ctx,cookieStore);
		if (source == null)
			return model;

		Gson gson = new Gson();
		Reader reader = new InputStreamReader(source);
		String readed = Misc.convertStreamToString(reader);
		Log.e("got response",readed);
		if(readed.contains("error:")){
			//TODO handling error if got response with error
		}else{
			try {
				ret = (T) gson.fromJson(readed, model.getClass());
				Log.i(TAG, "successfully parsed on 1st try");
			} catch (JsonParseException e) {
				Log.e(TAG, "parsing failed: ", e);
			}
			
			Log.i(TAG, model.toString());
			
			try {
				source.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		return ret;
	}
}
