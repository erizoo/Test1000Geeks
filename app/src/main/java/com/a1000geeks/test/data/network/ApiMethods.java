package com.a1000geeks.test.data.network;

import com.a1000geeks.test.data.models.ResponseCars;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiMethods {

    @GET("cars")
    Observable<List<ResponseCars>> getCars(@Query("sortBy") String sorterResult);

    @POST("cars")
    Observable<Response<ResponseCars>> saveCar(@Body ResponseCars responseCars);

    @PUT("cars")
    Observable<Response<ResponseCars>> updateCar(@Body ResponseCars responseCars);
}
