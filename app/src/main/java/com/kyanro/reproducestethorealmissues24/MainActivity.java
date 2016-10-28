package com.kyanro.reproducestethorealmissues24;

import com.kyanro.reproducestethorealmissues24.model.User;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.realm.Realm;
import io.realm.Realm.Transaction;
import io.realm.RealmConfiguration.Builder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm realm = Realm.getInstance(new Builder()
                .name("short_cache.realm")
                .schemaVersion(1)
                .deleteRealmIfMigrationNeeded()
                .build());

        realm.executeTransaction(new Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });

        realm.executeTransaction(new Transaction() {
            @Override
            public void execute(Realm realm) {
                User user = new User();
                user.id = 1;
                user.name = "reproduce";
                user.age = 36;

                realm.copyToRealm(user);
            }
        });

        realm.executeTransaction(new Transaction() {
            @Override
            public void execute(Realm realm) {
                User age = realm.where(User.class).equalTo("age", 36).findFirst();

                if (age != null) {
                    Log.d("reproduce", "short_cache.realm is created");
                } else {
                    Log.d("reproduce", "short_cache.realm is not created");
                }
            }
        });
    }
}
