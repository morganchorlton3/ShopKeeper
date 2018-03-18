package com.morgan.shopkeeper;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class HistoryFragment extends Fragment {
    View HistoryView;

    LayoutInflater layoutInflater;
    LinearLayout Popular_linearLayout, Recomended_linearLayout;
    HorizontalScrollView PopularCarouselView, RecomendedCarouselView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setUpPopularCarousel();
        setUpRecomendedCarousel();

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        HistoryView = inflater.inflate(R.layout.activity_history, container, false);
        return HistoryView;
    }

    private void setUpPopularCarousel() {
        String name[]={"Item 1","Item 2", "Item 3","Item 4","Item 5","Item 6","Item 7","Item 8"};
        String price[]={"$5.99","$5.99","$5.99","$5.99","$5.99","$5.99","$5.99","£5.99"};
        Integer thumbnail[]={R.drawable.potatoes, R.drawable.potatoes,R.drawable.potatoes, R.drawable.potatoes,
                R.drawable.potatoes, R.drawable.potatoes, R.drawable.potatoes, R.drawable.potatoes};

        for (int i=0;i<name.length; i++) {
            View view = layoutInflater.inflate(R.layout.scroll_view_item, Popular_linearLayout, false);
            LinearLayout scroll_item_layout=(LinearLayout) view.findViewById(R.id.scrol_item_Layout);
            ImageView imageView = (ImageView) view.findViewById(R.id.scroll_image_view);
            TextView item_name=(TextView)view.findViewById(R.id.scroll_item_name);
            TextView old_price=(TextView)view.findViewById(R.id.scroll_old_price);
            TextView new_price=(TextView)view.findViewById(R.id.scroll_new_price);

            imageView.setImageResource(thumbnail[i]);
            item_name.setText(name[i]);
            new_price.setText(price[i]);

            if (i==(name.length)-1)
            {
                scroll_item_layout.setBackgroundResource(android.R.color.transparent);
            }
            Popular_linearLayout.addView(view);
        }
    }
    private void setUpRecomendedCarousel() {
        String name[]={"Item 1","Item 2", "Item 3","Item 4","Item 5","Item 6","Item 7","Item 8"};
        String price[]={"$5.99","$5.99","$5.99","$5.99","$5.99","$5.99","$5.99","£5.99"};
        Integer thumbnail[]={R.drawable.potatoes, R.drawable.potatoes,R.drawable.potatoes, R.drawable.potatoes,
                R.drawable.potatoes, R.drawable.potatoes, R.drawable.potatoes, R.drawable.potatoes};

        for (int i=0;i<name.length; i++) {
            View view = layoutInflater.inflate(R.layout.scroll_view_item, Recomended_linearLayout, false);
            LinearLayout scroll_item_layout=(LinearLayout) view.findViewById(R.id.scrol_item_Layout);
            ImageView imageView = (ImageView) view.findViewById(R.id.scroll_image_view);
            TextView item_name=(TextView)view.findViewById(R.id.scroll_item_name);
            TextView old_price=(TextView)view.findViewById(R.id.scroll_old_price);
            TextView new_price=(TextView)view.findViewById(R.id.scroll_new_price);

            imageView.setImageResource(thumbnail[i]);
            item_name.setText(name[i]);
            new_price.setText(price[i]);

            if (i==(name.length)-1)
            {
                scroll_item_layout.setBackgroundResource(android.R.color.transparent);
            }
            Recomended_linearLayout.addView(view);
        }
    }
}
