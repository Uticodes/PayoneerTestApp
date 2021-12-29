package com.uticodes.payoneerandroidtest.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.uticodes.payoneerandroidtest.databinding.PaymentMethodsFragmentBinding;
import com.uticodes.payoneerandroidtest.presentation.paymentMenthodAdapter.PaymentMethodsAdapter;
import com.uticodes.payoneerandroidtest.presentation.viewModel.PaymentMethodsViewModel;
import com.uticodes.payoneerandroidtest.utils.Results;
import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class PaymentMethodsFragment extends Fragment {

    PaymentMethodsFragmentBinding binding;
    private PaymentMethodsViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = PaymentMethodsFragmentBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this).get(PaymentMethodsViewModel.class);
        viewModel.getPaymentMethods();
        binding.materialToolbar.setNavigationOnClickListener(view -> requireActivity().finish());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PaymentMethodsAdapter adapter = new PaymentMethodsAdapter();
        binding.rvPaymentMethod.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.rvPaymentMethod.setAdapter(adapter);

        viewModel.paymentMethodsObserver().observe(getViewLifecycleOwner(), result -> {
            if (result.getStatus() == Results.Status.SUCCESS) {
                adapter.submitList(result.getData());
            } else if (result.getStatus() == Results.Status.ERROR) {
                NavDirections directions = PaymentMethodsFragmentDirections.paymentMethodsFragmentToErrorDialog(result.getMessage());
                Navigation.findNavController(binding.getRoot()).navigate(directions);
            }
        });

        viewModel.loadingObserver().observe(getViewLifecycleOwner(), showProgressBar -> {
            if (showProgressBar) {
                binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}