package com.uticodes.payoneerandroidtest.presentation;


import static com.uticodes.payoneerandroidtest.utils.TestLiveDataUtil.getOrAwaitValue;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Observer;

import com.uticodes.payoneerandroidtest.common.Scheduler;
import com.uticodes.payoneerandroidtest.domain.usecases.paymentMethods.GetPaymentMethods;
import com.uticodes.payoneerandroidtest.presentation.paymentMenthodAdapter.PaymentMethod;
import com.uticodes.payoneerandroidtest.presentation.viewModel.PaymentMethodsViewModel;
import com.uticodes.payoneerandroidtest.scheduler.TestScheduler;
import com.uticodes.payoneerandroidtest.utils.Results;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import io.reactivex.rxjava3.schedulers.Schedulers;

@RunWith(MockitoJUnitRunner.class)
public class TestPaymentMethodsViewModel {


    @Mock
    GetPaymentMethods getPaymentMethods;

    private final Scheduler scheduler = new TestScheduler();

    private PaymentMethodsViewModel sut;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        RxAndroidPlugins.setMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
        sut = new PaymentMethodsViewModel(getPaymentMethods, scheduler);
    }

    @Test
    public void WHEN_GettingPaymentMethodsIsSuccessful_THEN_returnPaymentMethods() throws InterruptedException {
        List<PaymentMethod> items = getTestPaymentMethods();

        Mockito.when(getPaymentMethods.execute()).thenReturn(Single.just(items));

        sut.getPaymentMethods();

        Mockito.verify(getPaymentMethods).execute();
        Assert.assertEquals(items, getOrAwaitValue(sut.paymentMethodsObserver()).getData());
        Assert.assertEquals(items.get(0).getId(), getOrAwaitValue(sut.paymentMethodsObserver()).getData().get(0).getId());
        Assert.assertEquals(items.size(), getOrAwaitValue(sut.paymentMethodsObserver()).getData().size());
    }

    @Test
    public void WHEN_GettingPaymentMethods_THEN_verifyLoadingStates() throws InterruptedException {
        List<PaymentMethod> items = getTestPaymentMethods();
        Observer<Boolean> loadingObserver = Mockito.mock(Observer.class);
        sut.loadingObserver().observeForever(loadingObserver);

        Mockito.when(getPaymentMethods.execute()).thenReturn(Single.just(items));

        sut.getPaymentMethods();

        Mockito.verify(loadingObserver).onChanged(true);
        Mockito.verify(loadingObserver).onChanged(false);
    }

    @Test
    public void GIVEN_NoInternet_WHEN_GettingPaymentMethods_THEN_returnErrorIOException() throws InterruptedException {
        String errorMsg = "Unable to connection with server, kindly check your internet connection and try again";
        ArgumentCaptor<Results<List<PaymentMethod>>> captor = ArgumentCaptor.forClass(Results.class);
        Observer<Results<List<PaymentMethod>>> resultObserver = Mockito.mock(Observer.class);
        sut.paymentMethodsObserver().observeForever(resultObserver);

        Mockito.when(getPaymentMethods.execute()).thenReturn(Single.error(new IOException()));

        sut.getPaymentMethods();

        Mockito.verify(resultObserver).onChanged(captor.capture());
        Assert.assertNull(captor.getValue().getData());
        Assert.assertNotNull(captor.getValue().getMessage());
        Assert.assertEquals(captor.getValue().getMessage(), errorMsg);
    }


    private List<PaymentMethod> getTestPaymentMethods() {
        List<PaymentMethod> list = new ArrayList<>();
        list.add(new PaymentMethod("AMEX", "American Express", ""));
        list.add(new PaymentMethod("PAYFAST", "PayFast", ""));
        list.add(new PaymentMethod("PAYPAL", "PayPal", ""));
        return list;
    }


}