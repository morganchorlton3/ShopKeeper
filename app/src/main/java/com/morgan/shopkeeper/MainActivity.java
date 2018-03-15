package com.morgan.shopkeeper;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
    implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private DrawerLayout mDrawerLayout;
    boolean isLoggedIn = true;
    LayoutInflater layoutInflater;
    LinearLayout Popular_linearLayout, Recomended_linearLayout;
    HorizontalScrollView PopularCarouselView, RecomendedCarouselView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
        mDrawerLayout = findViewById(R.id.drawer_layout);
        layoutInflater=(LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        PopularCarouselView=(HorizontalScrollView)findViewById(R.id.Popular_Carousel_View);
        RecomendedCarouselView=(HorizontalScrollView)findViewById(R.id.Recomended_Carousel_View);
        Popular_linearLayout = (LinearLayout) findViewById(R.id.Popular_img_gallery);
        Recomended_linearLayout = (LinearLayout) findViewById(R.id.Recomended_img_gallery);


        setUpPopularCarousel();
        setUpRecomendedCarousel();


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
    /*@Override
    public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem signout = menu.findItem(R.id.nav_SignOut);
        MenuItem account = menu.findItem(R.id.nav_Account);
        MenuItem login = menu.findItem(R.id.nav_Login);
        MenuItem register = menu.findItem(R.id.nav_Register);
        if(userStatus){
            login.setVisible(true);
            register.setVisible(true);
            signout.setVisible(false);
            account.setVisible(false);

        }
        return true;
    }

*/
    /*@Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;
        if (id == R.id.nav_History) {

        } else if (id == R.id.nav_Basket) {
            setContentView(R.layout.content_basket);
        } else if (id == R.id.nav_Prices) {

        } else if (id == R.id.nav_Discounts) {

        }else if (id == R.id.nav_Login) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        }else if(id == R.id.nav_Register){
            setContentView(R.layout.activity_register);
        } else if (id == R.id.nav_SignOut) {
            signOut();
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        Fragment fragment = null;
        Class fragmentClass;
        switch(menuItem.getItemId()) {
            case R.id.nav_History:
                fragmentClass = BasketActivity.class;
                break;
            case R.id.nav_Basket:
                fragmentClass = BasketActivity.class;
                break;
            case R.id.nav_Discounts:
                fragmentClass = BasketActivity.class;
                break;
            default:
                fragmentClass = FirstFragment.class;
        }

        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Highlight the selected item has been done by NavigationView
        menuItem.setChecked(true);
        // Set action bar title
        setTitle(menuItem.getTitle());
        // Close the navigation drawer
        mDrawerLayout.closeDrawers();
    }

    /*
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuItem login = menu.findItem(R.id.nav_Login);
        MenuItem Register = menu.findItem(R.id.nav_Register);
        MenuItem Signout = menu.findItem(R.id.nav_SignOut);
        if(isLoggedIn){
            System.out.println("TEsting");
            login.setVisible(false);
            Register.setVisible(false);
            Signout.setVisible(true);
        }else{
            Signout.setVisible(false);
            login.setVisible(true);
            Register.setVisible(true);
        }
        getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        return true;
    }*/

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
    public void signOut() {
        Toast.makeText(getApplicationContext(), "You have been signed out", Toast.LENGTH_SHORT).show();
        auth.signOut();
        updateUI();
    }
}
