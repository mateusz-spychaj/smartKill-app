package pl.pwr.smartkill.tools;

import com.googlecode.androidannotations.annotations.sharedpreferences.DefaultString;
import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref;
import com.googlecode.androidannotations.annotations.sharedpreferences.SharedPref.Scope;

@SharedPref(value=Scope.APPLICATION_DEFAULT)
public interface PreferencesHelper {

	@DefaultString("")
	String lastLogin();

	@DefaultString("")
	String lastPassword();

}
