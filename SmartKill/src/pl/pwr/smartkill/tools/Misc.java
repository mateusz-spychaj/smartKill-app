package pl.pwr.smartkill.tools;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.NumberFormat;

import pl.pwr.smartkill.SKApplication;

import com.googlecode.androidannotations.annotations.App;
import com.googlecode.androidannotations.annotations.EBean;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

@EBean
public class Misc {
	
	@App
	SKApplication app;
	
	
	//PASSWORDS
	public static String generateSha(String password){
		String out ="";	
		MessageDigest md;
		try {
            md = MessageDigest.getInstance("SHA-512");
            md.update(password.getBytes());
            byte[] mb = md.digest();
            out = "";
            for (int i = 0; i < mb.length; i++) {
                byte temp = mb[i];
                String s = Integer.toHexString(new Byte(temp));
                while (s.length() < 2) {
                    s = "0" + s;
                }
                s = s.substring(s.length() - 2);
                out += s;
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("No Such Algorithm");
        }
		return out;
	}
	//INTERNET
	public void checkInternet(Context ctx){
        NetworkInfo info = ((ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info==null || !info.isConnectedOrConnecting()) {
        	app.setOnline(false);
        }else{
        	app.setOnline(true);
        }
	}
	
	//GPS
	public static String getDistanceString(float distM) {
    	if(distM < 1000)
    		return (int)distM + " m";
    	else if(distM < 10000) {
    		float dist100m = distM / 1000;
    		NumberFormat nf = NumberFormat.getInstance();
    		nf.setMinimumFractionDigits(0);
    		nf.setMaximumFractionDigits(1);
    		return nf.format(dist100m) + " km";
    	} else {
    		return (int)(distM / 1000) + " km"; 
    	}
    }
	//STREAMS
	public static void CopyStream(InputStream is, OutputStream os)
	{
		final int buffer_size=1024;
		try
		{
			byte[] bytes=new byte[buffer_size];
			for(;;)
			{
				int count=is.read(bytes, 0, buffer_size);
				if(count==-1)
					break;
				os.write(bytes, 0, count);
			}
		}
		catch(Exception ex){}
	}
	
	public static String convertStreamToString(Reader reader){
        Writer writer = new StringWriter();

        char[] buffer = new char[1024];
        try {
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
        } catch(Exception e){
        }
        return writer.toString();
	}
}
