package com.a1000geeks.test.data.network;


import com.a1000geeks.test.data.models.ResponseCars;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;

public interface ServiceNetwork {

    Observable<List<ResponseCars>> getCars(String sorter);

    Observable<Response<ResponseCars>> saveCar(ResponseCars responseCars);

    Observable<Response<ResponseCars>> updateCar(ResponseCars responseCars);
}
