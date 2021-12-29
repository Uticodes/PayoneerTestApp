package com.uticodes.payoneerandroidtest.domain.usecases.paymentMethods;

import com.uticodes.payoneerandroidtest.presentation.paymentMenthodAdapter.PaymentMethod;
import java.util.List;
import io.reactivex.rxjava3.core.Single;


public interface GetPaymentMethods {
    Single<List<PaymentMethod>> execute();
}


