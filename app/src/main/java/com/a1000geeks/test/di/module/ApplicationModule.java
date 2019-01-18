package com.a1000geeks.test.di.module;

import android.content.Context;

import com.a1000geeks.test.App;
import com.a1000geeks.test.data.RepositoryManager;
import com.a1000geeks.test.data.RepositoryManagerImpl;
import com.a1000geeks.test.di.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    private final App application;

    public ApplicationModule(App application) {
        this.application = application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    @Provides
    App provideApplication() {
        return application;
    }

    @Provides
    @Singleton
    RepositoryManager provideRepositoryManager(RepositoryManagerImpl repositoryManager) {
        return repositoryManager;
    }
}

