package com.uticodes.payoneerandroidtest.presentation.paymentMenthodAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethod {
    private String id;
    private String label;
    private String logo;

    public PaymentMethod(String id, String label, String logo) {
        this.id = id;
        this.label = label;
        this.logo = logo;
    }

    public static DiffUtil.ItemCallback<PaymentMethod> DIFF_CALLBACK = new DiffUtil.ItemCallback<PaymentMethod>() {
        @Override
        public boolean areItemsTheSame(@NonNull PaymentMethod oldItem, @NonNull PaymentMethod newItem) {
            return oldItem.getId().equals(newItem.getId());
        }

        @Override
        public boolean areContentsTheSame(@NonNull PaymentMethod oldItem, @NonNull PaymentMethod newItem) {
            return oldItem.equals(newItem);
        }
    };
}
