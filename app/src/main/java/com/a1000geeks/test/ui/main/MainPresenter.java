package com.a1000geeks.test.ui.main;

import com.a1000geeks.test.data.models.ResponseCars;
import com.a1000geeks.test.ui.base.MvpPresenter;

public interface MainPresenter <V extends MainMvpView> extends MvpPresenter<V> {

    void getCars(String sorterAsc);

    void saveCar(ResponseCars responseCars);

    void updateCar(ResponseCars responseCars);
}
