package pl.pwr.smartkill.tools;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.params.HttpParams;

import android.content.Context;

public class NinjaHTTPClient extends DefaultHttpClient 
{    
  final Context context;

  public NinjaHTTPClient(HttpParams hparms, Context context)
  {
    super(hparms);
    this.context = context;     
  }

  @Override
  protected ClientConnectionManager createClientConnectionManager() {
    SchemeRegistry registry = new SchemeRegistry();
    registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));

    // Register for port 443 our SSLSocketFactory with our keystore
    // to the ConnectionManager
    registry.register(new Scheme("https", new EasySSLSocketFactory(), 443));

    return new SingleClientConnManager(getParams(), registry);
  }
}

