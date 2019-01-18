package com.a1000geeks.test.ui.main;

import com.a1000geeks.test.data.RepositoryManager;
import com.a1000geeks.test.data.models.ResponseCars;
import com.a1000geeks.test.ui.base.BasePresenter;

import java.util.Collection;
import java.util.Collections;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenterImpl<V extends MainMvpView> extends BasePresenter<V>
        implements MainPresenter<V> {

    @Inject
    public MainPresenterImpl(RepositoryManager repositoryManager, CompositeDisposable compositeDisposable) {
        super(repositoryManager, compositeDisposable);
    }

    @Override
    public void getCars(String sorter) {
        getCompositeDisposable().add(
                getRepositoryManager().getServiceNetwork().getCars(sorter)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                getMvpView()::onReceivedCars,
                                getMvpView()::onErrorReceivedCars
                        )
        );
    }

    @Override
    public void saveCar(ResponseCars responseCars) {
        getCompositeDisposable().add(
                getRepositoryManager().getServiceNetwork().saveCar(responseCars)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                responseCarsResponse -> {
                                    if (responseCarsResponse.isSuccessful()) {
                                        getMvpView().onSavedCars();
                                    } else {
                                        getMvpView().onErrorSavedCars();
                                    }
                                }

                        )
        );
    }

    @Override
    public void updateCar(ResponseCars responseCars) {
        getCompositeDisposable().add(
                getRepositoryManager().getServiceNetwork().updateCar(responseCars)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                responseCarsResponse -> {
                                    if (responseCarsResponse.isSuccessful()) {
                                        getMvpView().onSavedCars();
                                    } else {
                                        getMvpView().onErrorSavedCars();
                                    }
                                }

                        )
        );
    }
}
