//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package pl.pwr.smartkill.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import com.googlecode.androidannotations.api.SdkVersionHelper;
import pl.pwr.smartkill.R.id;
import pl.pwr.smartkill.R.layout;
import pl.pwr.smartkill.SKApplication;
import pl.pwr.smartkill.tools.PreferencesHelper_;

public final class LoginActivity_
    extends LoginActivity
{

    private Handler handler_ = new Handler();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_login);
    }

    private void init_(Bundle savedInstanceState) {
        prefs = new PreferencesHelper_(this);
        app = ((SKApplication) this.getApplication());
    }

    private void afterSetContentView_() {
        passwordET = ((EditText) findViewById(id.login_password));
        loginET = ((EditText) findViewById(id.login_login));
        button = ((Button) findViewById(id.login_button));
        remember = ((CheckBox) findViewById(id.login_remember));
        {
            View view = findViewById(id.login_button);
            if (view!= null) {
                view.setOnClickListener(new OnClickListener() {


                    @Override
                    public void onClick(View view) {
                        LoginActivity_.this.login();
                    }

                }
                );
            }
        }
        prepare();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
        super.setContentView(view, params);
        afterSetContentView_();
    }

    @Override
    public void setContentView(View view) {
        super.setContentView(view);
        afterSetContentView_();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (((SdkVersionHelper.getSdkInt()< 5)&&(keyCode == KeyEvent.KEYCODE_BACK))&&(event.getRepeatCount() == 0)) {
            onBackPressed();
        }
        return super.onKeyDown(keyCode, event);
    }

    public static LoginActivity_.IntentBuilder_ intent(Context context) {
        return new LoginActivity_.IntentBuilder_(context);
    }

    @Override
    public void loginFinished(final boolean success) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                try {
                    LoginActivity_.super.loginFinished(success);
                } catch (RuntimeException e) {
                    Log.e("LoginActivity_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    @Override
    public void logUser(final String login, final String password) {
        BackgroundExecutor.execute(new Runnable() {


            @Override
            public void run() {
                try {
                    LoginActivity_.super.logUser(login, password);
                } catch (RuntimeException e) {
                    Log.e("LoginActivity_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    public static class IntentBuilder_ {

        private Context context_;
        private final Intent intent_;

        public IntentBuilder_(Context context) {
            context_ = context;
            intent_ = new Intent(context, LoginActivity_.class);
        }

        public Intent get() {
            return intent_;
        }

        public LoginActivity_.IntentBuilder_ flags(int flags) {
            intent_.setFlags(flags);
            return this;
        }

        public void start() {
            context_.startActivity(intent_);
        }

    }

}
