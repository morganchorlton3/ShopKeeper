package com.morgan.shopkeeper;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DecimalFormat;
import java.util.ArrayList;

import in.goodiebag.carouselpicker.CarouselPicker;

public class DiscountsActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth.AuthStateListener authListener;
    public static ArrayList<ShoppingList> ShoppingList = new ArrayList<ShoppingList>();
    private FirebaseAuth auth;
    DrawerLayout drawer;
    NavigationView navigationView;
    android.support.v7.widget.Toolbar toolbar=null;

    LayoutInflater PopularlayoutInflater, OfferslayoutInflater;
    LinearLayout PopularlinearLayout, OfferslinearLayout;
    HorizontalScrollView PopularhorizontalScrollView, OffershorizontalScrollView;

    boolean isLoggedIn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discounts);
        android.support.v7.widget.Toolbar toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();

        //get current user
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                if (user == null) {
                    finish();
                    isLoggedIn = false;
                } else {
                    isLoggedIn = true;
                }
            }
        };
        PopularlayoutInflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        PopularhorizontalScrollView=(HorizontalScrollView)findViewById(R.id.horizontal_scroll_view);
        PopularlinearLayout=(LinearLayout)findViewById(R.id.img_gallery);
        OfferslayoutInflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        OffershorizontalScrollView=(HorizontalScrollView)findViewById(R.id.Offers_horizontal_scroll_view);
        OfferslinearLayout=(LinearLayout)findViewById(R.id.Offers_img_gallery);


        setUpPopular();
        setUpOffers();
    }
    private void setUpPopular()
    {
        final String name[]={"Ferrero Rocher","Heinz Baked Beanz", "Beef Mince 500g","Bananas","Sugar 1KG","Light Mayonnaise","Heinz Tomato Ketchup","Pringles Originals"};
        final String price[]={"£5.99","£1.99","£3.70","£1.78","£3.20","£2.10","£1.78","£1.35"};
        final String Dicount_price[]={"£4.98","£1.80","£3.00","£1.76","£2.89","£1.89","£1.35","£1.05"};
        Integer thumbnail[]={R.drawable.ferrero_rocher_giant_525g, R.drawable.heinz_beanz,R.drawable.beef_500g, R.drawable.bananas,  R.drawable.sugar_1kg, R.drawable.heinz_mayonnaise_light, R.drawable.heinz_tomatoketchup,
                R.drawable.pringles_originila};

        for (int i=0;i<name.length; i++) {
            View view = PopularlayoutInflater.inflate(R.layout.scroll_view_item, PopularlinearLayout, false);
            LinearLayout scroll_item_layout=(LinearLayout) view.findViewById(R.id.scrol_item_Layout);
            ImageView imageView = (ImageView) view.findViewById(R.id.scroll_image_view);
            TextView item_name=(TextView)view.findViewById(R.id.scroll_item_name);
            TextView old_price=(TextView)view.findViewById(R.id.scroll_old_price);
            TextView new_price=(TextView)view.findViewById(R.id.scroll_new_price);
            final TextView Discount_price=(TextView)view.findViewById(R.id.scroll_new_Discount);
            Button add_Btn=(Button)view.findViewById(R.id.scroll_add_btn);

            imageView.setImageResource(thumbnail[i]);
            item_name.setText(name[i]);
            new_price.setText(price[i]);
            Discount_price.setText(Dicount_price[i]);

            if (i==(name.length)-1)
            {
                scroll_item_layout.setBackgroundResource(android.R.color.transparent);
            }
            PopularlinearLayout.addView(view);
            final int finalI = i;
            add_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), name[finalI] + " added to List", Toast.LENGTH_SHORT).show();
                    ShoppingList newShoppingList = new ShoppingList();
                    newShoppingList.setItemName(name[finalI]);
                    newShoppingList.setItemPrice(price[finalI]);
                    ShoppingList.add(newShoppingList);
                    System.out.println(newShoppingList);
                }
            });


        }
    }
    private void setUpOffers()
    {
        final String name[]={"Sugar","Self Raising Flour", "Fair Trade Bananas","Heinz Tomato Ketchup","Actimel Clear 4 pack","Heinz Mayonnaise","Free Range Eggs","Pringles Original"};
        String price[]={"£2.30","£2.56","£3.10","£2.30","£3.45","£2.22","£1.70","£1.99"};
        final String Dicount_price[]={"£1.98","£2.30","£3.00","£1.79","£2.89","£1.89","£1.35","£1.75"};
        Integer thumbnail[]={R.drawable.sugar_1kg, R.drawable.tesco_selfraisingflour_1kg,R.drawable.bananas, R.drawable.heinz_tomatoketchup, R.drawable.actimel_clear_4pk, R.drawable.heinz_mayonnaise_seriouslygood,
                R.drawable.eggs_15pk, R.drawable.pringles_originila,};

        for (int i=0;i<name.length; i++) {
            View view = OfferslayoutInflater.inflate(R.layout.scroll_view_item, OfferslinearLayout, false);
            LinearLayout scroll_item_layout=(LinearLayout) view.findViewById(R.id.scrol_item_Layout);
            ImageView imageView = (ImageView) view.findViewById(R.id.scroll_image_view);
            final TextView item_name=(TextView)view.findViewById(R.id.scroll_item_name);
            TextView old_price=(TextView)view.findViewById(R.id.scroll_old_price);
            TextView new_price=(TextView)view.findViewById(R.id.scroll_new_price);
            final TextView Discount_price=(TextView)view.findViewById(R.id.scroll_new_Discount);
            Button add_Btn=(Button)view.findViewById(R.id.scroll_add_btn);

            imageView.setImageResource(thumbnail[i]);
            item_name.setText(name[i]);
            new_price.setText(price[i]);
            Discount_price.setText(Dicount_price[i]);

            if (i==(name.length)-1)
            {
                scroll_item_layout.setBackgroundResource(android.R.color.transparent);
            }
            OfferslinearLayout.addView(view);

            final int finalI = i;
            add_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), name[finalI] + " added to List", Toast.LENGTH_SHORT).show();
                    String item_price = Dicount_price[finalI];
                    ShoppingList newShoppingList = new ShoppingList();
                    newShoppingList.setItemName(name[finalI]);
                    newShoppingList.setItemPrice(Dicount_price[finalI]);
                    ShoppingList.add(newShoppingList);
                    System.out.println(newShoppingList);
                }
            });


        }
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        updateUI();
    }
    private void updateUI() {
        System.out.println(isLoggedIn);
        FirebaseUser currentUser = auth.getCurrentUser();
        if (currentUser != null) {
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            View header=navigationView.getHeaderView(0);
            String email = currentUser.getEmail();
            TextView emaillabel = (TextView)header.findViewById(R.id.emailLabel);
            emaillabel.setText(email);
        }else{
            NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
            navigationView.setNavigationItemSelectedListener(this);
            View header=navigationView.getHeaderView(0);
            ImageView avatar = (ImageView) header.findViewById(R.id.avatarImage);
            TextView emailLabel = (TextView)header.findViewById(R.id.emailLabel);
            emailLabel.setVisibility(View.INVISIBLE);
            avatar.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //here is the main place where we need to work on.
        int id=item.getItemId();
        switch (id) {

            case R.id.nav_History:
                Intent h = new Intent(DiscountsActivity.this, MainActivity.class);
                startActivity(h);
                break;
            case R.id.nav_Basket:
                Intent i = new Intent(DiscountsActivity.this, BasketActivity.class);
                startActivity(i);
                break;
            case R.id.nav_Discounts:
                Intent g= new Intent(DiscountsActivity.this,DiscountsActivity.class);
                startActivity(g);
                break;
            case R.id.nav_Login:
                Intent t= new Intent(DiscountsActivity.this,SignupActivity.class);
                startActivity(t);
                break;
            case R.id.nav_Register:
                Intent e= new Intent(DiscountsActivity.this,RegisterActivity.class);
                startActivity(e);
                break;
            case R.id.nav_SignOut:
                signOut();
                break;
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void signOut() {
        Toast.makeText(getApplicationContext(), "You have been signed out", Toast.LENGTH_SHORT).show();
        auth.signOut();
        updateUI();
    }
}

