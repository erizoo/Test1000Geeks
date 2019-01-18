package com.a1000geeks.test.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import com.a1000geeks.test.di.ActivityContext;
import com.a1000geeks.test.di.PerScreen;
import com.a1000geeks.test.ui.main.MainMvpView;
import com.a1000geeks.test.ui.main.MainPresenter;
import com.a1000geeks.test.ui.main.MainPresenterImpl;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
public class ScreenModule {

    private final AppCompatActivity activity;

    public ScreenModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return activity;
    }

    @Provides
    AppCompatActivity provideActivity() {
        return activity;
    }

    @Provides
    CompositeDisposable provideCompositeDisposable() {
        return new CompositeDisposable();
    }

    @Provides
    @PerScreen
    MainPresenter<MainMvpView> provideMainPresenter(MainPresenterImpl<MainMvpView> presenter) {
        return presenter;
    }


}
