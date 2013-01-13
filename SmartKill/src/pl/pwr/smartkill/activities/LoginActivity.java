package pl.pwr.smartkill.activities;

import java.util.HashMap;

import pl.pwr.smartkill.R;
import pl.pwr.smartkill.SKApplication;
import pl.pwr.smartkill.obj.Login;
import pl.pwr.smartkill.tools.PreferencesHelper_;
import pl.pwr.smartkill.tools.WebserviceHandler;
import pl.pwr.smartkill.tools.httpRequests.HttpRequest;
import pl.pwr.smartkill.tools.httpRequests.PostRequest;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.googlecode.androidannotations.annotations.AfterViews;
import com.googlecode.androidannotations.annotations.App;
import com.googlecode.androidannotations.annotations.Background;
import com.googlecode.androidannotations.annotations.Click;
import com.googlecode.androidannotations.annotations.EActivity;
import com.googlecode.androidannotations.annotations.UiThread;
import com.googlecode.androidannotations.annotations.ViewById;
import com.googlecode.androidannotations.annotations.sharedpreferences.Pref;

@EActivity(R.layout.activity_login)
public class LoginActivity extends SherlockActivity {

	@ViewById(R.id.login_login)
	EditText loginET;

	@ViewById(R.id.login_password)
	EditText passwordET;

	@ViewById(R.id.login_button)
	Button button;

	@ViewById(R.id.login_remember)
	CheckBox remember;

	@Pref
	PreferencesHelper_ prefs;

	@App
	SKApplication app;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
	}

	@AfterViews
	public void prepare() {
		if (prefs.lastLogin().get().length() > 0) {
			loginET.setText(prefs.lastLogin().get());
			passwordET.setText(prefs.lastPassword().get());
			remember.setChecked(true);
		}
	}

	@Click(R.id.login_button)
	public void login() {
		passwordET.setError(null);
		loginET.setError(null);
		button.setEnabled(false);
		boolean loginBool = true;
		String login = loginET.getText().toString();
		String password = passwordET.getText().toString();

		if (login == null || login.length() == 0) {
			loginBool = false;
			button.setEnabled(true);
			loginET.setError(getString(R.string.not_empty));
		}
		if (password == null || password.length() == 0) {
			loginBool = false;
			button.setEnabled(true);
			passwordET.setError(getString(R.string.not_empty));
		}
		if (loginBool) {
			setProgressBarIndeterminateVisibility(true);
			logUser(login, password);
		}
	}

	@Background
	public void logUser(String login, String password) {
		HashMap<String, String> creditentials = new HashMap<String, String>();
		creditentials.put(HttpRequest.LOGIN, login);
		creditentials.put(HttpRequest.PASSWORD, password);
		Login response = new WebserviceHandler<Login>().getAndParse(
				this, new PostRequest(SKApplication.API_URL + "login",
						creditentials), new Login());
		if (response.getStatus().contains("success")) {
			app.setSessionId(response.getId());
			app.setMyProfile(response.getUser());
			loginFinished(true);
		} else {
			loginFinished(false);
		}
	}

	@UiThread
	public void loginFinished(boolean success) {
		if (remember.isChecked()) {
			prefs.edit().lastLogin().put(loginET.getText().toString()).apply();
			prefs.edit().lastPassword().put(passwordET.getText().toString())
					.apply();
		} else {
			prefs.edit().lastLogin().put("").apply();
			prefs.edit().lastPassword().put("").apply();
		}
		setProgressBarIndeterminateVisibility(false);
		button.setEnabled(true);
		if (success) {
			startActivity(new Intent(this, MainActivity_.class));
			if (!remember.isChecked()) {
				loginET.setText("");
				passwordET.setText("");
			}
		} else {
			Toast.makeText(this, R.string.incorrect_login_or_password,
					Toast.LENGTH_LONG).show();
			// showLoginFailedDialog();TODO
		}
	}
}
