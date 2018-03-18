package com.morgan.shopkeeper;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class PriceFragment extends Fragment {
    View PriceView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        PriceView = inflater.inflate(R.layout.activity_price, container, false);
        return PriceView;
    }
}
