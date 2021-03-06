package com.morgan.shopkeeper;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DecimalFormat;
import java.util.ArrayList;

import in.goodiebag.carouselpicker.CarouselPicker;

public class BasketActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    DrawerLayout drawer;
    NavigationView navigationView;
    android.support.v7.widget.Toolbar toolbar=null;

    private ListView mShoppingList;
    private TextView mItemLabel;
    private Button mAddButton, mclearButton;
    private ArrayAdapter<String> mAdapter;
    private static ArrayList<ShoppingList> ShoppingList = MainActivity.getShoppingList();
    private static DecimalFormat df2 = new DecimalFormat(".##");

    boolean isLoggedIn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
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
                }else{
                    isLoggedIn = true;
                }
            }
        };

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        mShoppingList = (ListView) findViewById(R.id.shopping_listView);
        mAddButton = (Button) findViewById(R.id.add_button);
        mclearButton = (Button) findViewById(R.id.clear_button);
        mItemLabel = (TextView) findViewById(R.id.Item_Label);

        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        mShoppingList.setAdapter(mAdapter);


        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double ShoppingListTotal = 0.0;
                for (int i = 0; i < ShoppingList.size(); i++) {
                    ShoppingList ActiveItem = ShoppingList.get(i);
                    String Item_price = ActiveItem.getItemPrice();
                    double total = Double.parseDouble(Item_price.substring(1));
                    ShoppingListTotal = ShoppingListTotal + total;
                }
                mItemLabel.setText("Total = £" + df2.format(ShoppingListTotal));
            }
        });
        mclearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdapter.clear();
                mAdapter.notifyDataSetChanged();
                ShoppingList.clear();
            }
        });


    }




    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        updateUI();
        for (int i = 0; i < ShoppingList.size(); i++) {
            ShoppingList ActiveItem = ShoppingList.get(i);
            String Item_name = ActiveItem.getItemName();
            String Item_price = ActiveItem.getItemPrice();
            String Item_String = Item_name + ": " + Item_price;
            mAdapter.add(Item_String);
            mAdapter.notifyDataSetChanged();
        }
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
                Intent h = new Intent(BasketActivity.this, MainActivity.class);
                startActivity(h);
                break;
            case R.id.nav_Basket:
                Intent i = new Intent(BasketActivity.this, BasketActivity.class);
                startActivity(i);
                break;
            case R.id.nav_Discounts:
                Intent g= new Intent(BasketActivity.this,DiscountsActivity.class);
                startActivity(g);
                break;
            case R.id.nav_Login:
                Intent t= new Intent(BasketActivity.this,SignupActivity.class);
                startActivity(t);
                break;
            case R.id.nav_Register:
                Intent e= new Intent(BasketActivity.this,RegisterActivity.class);
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
