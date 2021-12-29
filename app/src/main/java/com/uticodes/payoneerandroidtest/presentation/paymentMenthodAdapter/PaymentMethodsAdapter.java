package com.uticodes.payoneerandroidtest.presentation.paymentMenthodAdapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.uticodes.payoneerandroidtest.databinding.PaymentMethodItemBinding;

public class PaymentMethodsAdapter extends ListAdapter<PaymentMethod, PaymentMethodsAdapter.ViewHolder> {

    public PaymentMethodsAdapter() {
        super(PaymentMethod.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        PaymentMethodItemBinding binding = PaymentMethodItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(getItem(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final PaymentMethodItemBinding binding;

        public ViewHolder(@NonNull PaymentMethodItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bindData(PaymentMethod model) {
            binding.label.setText(model.getLabel());
            Glide.with(this.itemView.getContext())
                    .load(model.getLogo())
                    .into(binding.icon);
        }
    }
}
