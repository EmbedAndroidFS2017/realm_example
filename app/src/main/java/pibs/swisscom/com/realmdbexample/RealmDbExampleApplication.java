package pibs.swisscom.com.realmdbexample;

import android.app.Application;

import io.realm.Realm;

public class RealmDbExampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Realm when application starts
        Realm.init(this);
    }
}
