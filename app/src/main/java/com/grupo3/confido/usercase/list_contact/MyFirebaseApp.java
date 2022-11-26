package com.grupo3.confido.usercase.list_contact;

import com.google.firebase.database.FirebaseDatabase;

public class MyFirebaseApp extends android.app.Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
