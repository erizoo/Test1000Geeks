package com.a1000geeks.test.di.component;

import com.a1000geeks.test.di.PerScreen;
import com.a1000geeks.test.di.module.ScreenModule;
import com.a1000geeks.test.ui.main.MainActivity;

import dagger.Component;

@PerScreen
@Component(modules = ScreenModule.class, dependencies = ApplicationComponent.class)
public interface ScreenComponent {

    void inject(MainActivity mainActivity);
}
