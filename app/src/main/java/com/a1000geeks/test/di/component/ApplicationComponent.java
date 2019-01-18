package com.a1000geeks.test.di.component;

import android.content.Context;

import com.a1000geeks.test.App;
import com.a1000geeks.test.data.RepositoryManager;
import com.a1000geeks.test.di.ApplicationContext;
import com.a1000geeks.test.di.module.ApiModule;
import com.a1000geeks.test.di.module.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, ApiModule.class})
public interface ApplicationComponent {

    void inject(App application);

    @ApplicationContext
    Context context();

    RepositoryManager getRepositoryManager();

}

