package pl.pwr.smartkill.tools.httpRequests;

import java.io.InputStream;

import org.apache.http.client.CookieStore;

import android.content.Context;

public interface HttpRequest
{
	public final static String LOGIN = "username";
	public final static String PASSWORD = "password";
    public InputStream execute(Context ctx,CookieStore store);
    public String getUrl();
}