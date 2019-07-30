package com.example.programmer.fakeapi;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.example.programmer.fakeapi.adapters.view_pager.ViewPagerAdapter;
import com.example.programmer.fakeapi.fragments.AlbumFragment;
import com.example.programmer.fakeapi.fragments.PostFragment;
import com.example.programmer.fakeapi.fragments.UsersFragment;
import com.example.programmer.fakeapi.models.Users;
import com.example.programmer.fakeapi.view_models.MainViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    // ui


    private ViewPager viewPager;
    private List<Fragment> fragmentList;
    private BottomNavigationView navView;

    // var

    private Boolean isBack = false;
    private ViewPagerAdapter viewPagerAdapter;
    private MenuItem item;
    private MainViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createViewPager();

        createBottomNaviagte();


        viewModel= ViewModelProviders.of(this).get(MainViewModel.class);
        viewModel.getLiveData().observe(this, new Observer<List<Users>>() {
            @Override
            public void onChanged(List<Users> users) {
                for(int i=0;i<users.size();i++)
                {
                    Log.d("uuuuuuu",users.get(i).getAddress().getCity());
                }
            }
        });


    }

    private void createViewPager() {
        fragmentList = new ArrayList<>();
        viewPager = findViewById(R.id.view_pager);

        PostFragment postFragment = new PostFragment();
        fragmentList.add(postFragment);

        AlbumFragment albumFragment = new AlbumFragment();
        fragmentList.add(albumFragment);

        UsersFragment todosFragment = new UsersFragment();
        fragmentList.add(todosFragment);

        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), fragmentList);

        viewPager.setAdapter(viewPagerAdapter);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (item != null) {
                    item.setChecked(false);
                } else
                    navView.getMenu().getItem(0).setChecked(false);

                navView.getMenu().getItem(position).setChecked(true);
                item = navView.getMenu().getItem(position);



            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void createBottomNaviagte() {
        navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:

                    viewPager.setCurrentItem(0);

                    return true;
                case R.id.navigation_dashboard:

                    viewPager.setCurrentItem(1);

                    return true;
                case R.id.navigation_notifications:

                    viewPager.setCurrentItem(2);

                    return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {
        if (isBack) {
            super.onBackPressed();
        } else {
            Toast.makeText(MainActivity.this, "press back again to exit", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isBack = false;
                }
            }, 1000);
            isBack = true;
        }

    }
}
