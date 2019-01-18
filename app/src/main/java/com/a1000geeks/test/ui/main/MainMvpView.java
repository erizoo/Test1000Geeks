package com.a1000geeks.test.ui.main;

import com.a1000geeks.test.data.models.ResponseCars;
import com.a1000geeks.test.ui.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void onReceivedCars(List<ResponseCars> responseCars);

    void onErrorReceivedCars(Throwable throwable);

    void onSavedCars();

    void onErrorSavedCars();
}
