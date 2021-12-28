package com.uticodes.payoneerandroidtest.presentation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.uticodes.payoneerandroidtest.databinding.PaymentMethodsFragmentBinding;
import dagger.hilt.android.AndroidEntryPoint;


@AndroidEntryPoint
public class PaymentMethodsFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        PaymentMethodsFragmentBinding binding;
        binding = PaymentMethodsFragmentBinding.inflate(inflater);
        binding.materialToolbar.setNavigationOnClickListener(view -> requireActivity().finish());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}