//
// DO NOT EDIT THIS FILE, IT HAS BEEN GENERATED USING AndroidAnnotations.
//


package pl.pwr.smartkill.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.googlecode.androidannotations.api.BackgroundExecutor;
import pl.pwr.smartkill.obj.Matches;

public final class ProfileListFragment_
    extends ProfileListFragment
{

    private View contentView_;
    private Handler handler_ = new Handler();

    private void init_() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        init_();
        super.onCreate(savedInstanceState);
    }

    private void afterSetContentView_() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView_ = super.onCreateView(inflater, container, savedInstanceState);
        afterSetContentView_();
        return contentView_;
    }

    public View findViewById(int id) {
        if (contentView_ == null) {
            return null;
        }
        return contentView_.findViewById(id);
    }

    @Override
    public void updateList(final Matches m) {
        handler_.post(new Runnable() {


            @Override
            public void run() {
                try {
                    ProfileListFragment_.super.updateList(m);
                } catch (RuntimeException e) {
                    Log.e("ProfileListFragment_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

    @Override
    public void getMatches() {
        BackgroundExecutor.execute(new Runnable() {


            @Override
            public void run() {
                try {
                    ProfileListFragment_.super.getMatches();
                } catch (RuntimeException e) {
                    Log.e("ProfileListFragment_", "A runtime exception was thrown while executing code in a runnable", e);
                }
            }

        }
        );
    }

}
