package com.uticodes.payoneerandroidtest.di;

import com.uticodes.payoneerandroidtest.domain.usecases.paymentMethods.GetPaymentMethods;
import com.uticodes.payoneerandroidtest.domain.usecases.paymentMethods.GetPaymentMethodsImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public abstract class PaymentModule {
    @Binds
    public abstract GetPaymentMethods bindGetPaymentMethods(GetPaymentMethodsImpl getPaymentMethodsImpl);
}
