package com.a5vh.assistant;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .requestProfile()
                .build();

        GoogleSignInClient gsc = GoogleSignIn.getClient(this, gso);


        loadFragment(new HomeFragment());
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null)
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Fragment fragment = null;

        switch (menuItem.getItemId()) {

            case R.id.navigation_goals:
                fragment = new GoalsFragment();
                break;

            case R.id.navigation_routes:
                fragment = new RoutesFragment();
                break;

            case R.id.navigation_home:
                fragment = new HomeFragment();
                HomeFragment homeFragment = (HomeFragment)getSupportFragmentManager()
                        .findFragmentById(R.id.navigation_home);

                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
                String personGivenName = acct.getGivenName();

                TextView nameText = findViewById(R.id.nameBox);
                nameText.setText("Hello, " + personGivenName);

                break;
        }

        return loadFragment(fragment);
    }
}
