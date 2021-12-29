package com.uticodes.payoneerandroidtest.presentation.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.uticodes.payoneerandroidtest.common.Scheduler;
import com.uticodes.payoneerandroidtest.domain.usecases.paymentMethods.GetPaymentMethods;
import com.uticodes.payoneerandroidtest.presentation.paymentMenthodAdapter.PaymentMethod;
import com.uticodes.payoneerandroidtest.utils.ErrorUtils;
import com.uticodes.payoneerandroidtest.utils.Results;
import java.util.List;
import javax.inject.Inject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class PaymentMethodsViewModel extends ViewModel {

    private final Scheduler scheduler;
    private final GetPaymentMethods getPaymentMethods;

    private final CompositeDisposable container = new CompositeDisposable();

    private final MutableLiveData<Results<List<PaymentMethod>>> paymentMethods = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    public LiveData<Results<List<PaymentMethod>>> paymentMethodsObserver() {
        return paymentMethods;
    }

    public LiveData<Boolean> loadingObserver() {
        return loading;
    }

    @Inject
    public PaymentMethodsViewModel(GetPaymentMethods getPaymentMethods, Scheduler scheduler) {
        this.getPaymentMethods = getPaymentMethods;
        this.scheduler = scheduler;
    }

    public void getPaymentMethods() {
        Disposable disposable = getPaymentMethods
                .execute()
                .subscribeOn(scheduler.io())
                .observeOn(scheduler.main())
                .doOnSubscribe(disposable1 -> loading.postValue(true))
                .doOnTerminate(() -> loading.postValue(false))
                .subscribe(paymentMethodList -> paymentMethods.postValue(Results.success(paymentMethodList)), throwable -> {
                    String message = ErrorUtils.handleErrorMessage(throwable);
                    paymentMethods.postValue(Results.error(message));
                });

        container.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        container.clear();
    }
}
