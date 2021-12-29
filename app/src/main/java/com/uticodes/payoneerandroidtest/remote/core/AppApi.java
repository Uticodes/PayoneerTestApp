package com.uticodes.payoneerandroidtest.remote.core;

import com.uticodes.payoneerandroidtest.remote.model.PaymentMethodsResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface AppApi {
    @GET("optile/checkout-android/develop/shared-test/lists/listresult.json")
    Single<PaymentMethodsResponse> getPaymentMethods();
}
