package com.uticodes.payoneerandroidtest.domain.usecases.paymentMethods;

import android.os.Build;
import androidx.annotation.RequiresApi;
import com.uticodes.payoneerandroidtest.presentation.paymentMenthodAdapter.PaymentMethod;
import com.uticodes.payoneerandroidtest.remote.core.AppApi;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import io.reactivex.rxjava3.core.Single;

public class GetPaymentMethodsImpl implements GetPaymentMethods {

    AppApi api;

    @Inject
    public GetPaymentMethodsImpl(AppApi api) {
        this.api = api;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public Single<List<PaymentMethod>> execute() {
        return api.getPaymentMethods()
                .map(paymentMethodsResponse -> paymentMethodsResponse.getNetworks().getApplicable())
                .map(response -> {
                            List<PaymentMethod> list = new ArrayList<>();
                    response.forEach(applicable -> list.add(new PaymentMethod(
                                    applicable.getCode(),
                                    applicable.getLabel(),
                                    applicable.getLinks().getLogo()
                            )));

                            return list;
                        }
                );
    }
}
