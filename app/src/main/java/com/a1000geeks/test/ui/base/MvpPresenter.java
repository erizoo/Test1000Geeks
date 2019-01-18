package com.a1000geeks.test.ui.base;

import com.a1000geeks.test.data.RepositoryManager;

import io.reactivex.disposables.CompositeDisposable;

public interface MvpPresenter<V> {

    void onAttach(V mvpView);

    void onDetach();

    void onDestroy();

    CompositeDisposable getCompositeDisposable();

    V getMvpView();

    RepositoryManager getRepositoryManager();

}
