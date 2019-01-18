package com.a1000geeks.test.data.network;

import com.a1000geeks.test.data.models.ResponseCars;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import retrofit2.Response;

public class ServiceNetworkImp implements ServiceNetwork {

    private static final String SORTER_ASC = "SORTER_ASC";
    private static final String SORTER_DESC = "SORTER_DESC";
    private ApiMethods apiMethods;

    @Inject
    public ServiceNetworkImp(ApiMethods apiMethods) {
        this.apiMethods = apiMethods;
    }

    @Override
    public Observable<List<ResponseCars>> getCars(String sorter) {
        String sorterResult = null;
        if (sorter.equals(SORTER_ASC)){
            sorterResult = "price asc";
        } else if (sorter.equals(SORTER_DESC)){
            sorterResult = "price desc";
        }
        return apiMethods.getCars(sorterResult);
    }

    @Override
    public Observable<Response<ResponseCars>> saveCar(ResponseCars responseCars) {
        return apiMethods.saveCar(responseCars);
    }

    @Override
    public Observable<Response<ResponseCars>> updateCar(ResponseCars responseCars) {
        return apiMethods.updateCar(responseCars);
    }
}
