package com.a1000geeks.test;

import android.app.Application;

import com.a1000geeks.test.di.component.ApplicationComponent;
import com.a1000geeks.test.di.component.DaggerApplicationComponent;
import com.a1000geeks.test.di.module.ApplicationModule;


public class App extends Application {

    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
        applicationComponent.inject(this);

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
