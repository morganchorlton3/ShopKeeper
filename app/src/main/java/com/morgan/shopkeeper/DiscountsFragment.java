package com.morgan.shopkeeper;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by morga on 16/03/2018.
 */

public class DiscountsFragment extends Fragment {
    View DiscountsView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        DiscountsView = inflater.inflate(R.layout.activity_discounts, container, false);
        return DiscountsView;
    }
}
