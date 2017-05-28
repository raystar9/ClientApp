package shoeping.clientapp;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import shoeping.clientapp.DatabaseManager.LoadCompleteListener;
import shoeping.clientapp.typeDefine.ItemInfo;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    ViewPager viewPager;
    TabLayout tabs;
    //    String loginToken;    // TODO : 로그인토큰 추가 및 Intent로 토큰 교환
    ItemInfo[] dressShoesW;
    ItemInfo[] dressShoesM;
    ItemInfo[] sneakersW;
    ItemInfo[] sneakersM;
    ItemInfo[] slippers;
    int index;

    DatabaseManager databaseManager;
    DatabaseManager databaseManager2;
    DatabaseManager databaseManager3;
    DatabaseManager databaseManager4;
    DatabaseManager databaseManager5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        index = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabs = (TabLayout) findViewById(R.id.tabs);
        databaseManager = new DatabaseManager();
        databaseManager2 = new DatabaseManager();
        databaseManager3 = new DatabaseManager();
        databaseManager4 = new DatabaseManager();
        databaseManager5 = new DatabaseManager();

        databaseManager.requestGetMainInfo("DressShoes(W)");

        databaseManager.setLoadCompleteListener(new LoadCompleteListener() {
            @Override
            public void onLoadComplete() {
                dressShoesW = databaseManager.getMainInfoArray();
                databaseManager2.requestGetMainInfo("Sneakers(W)");
            }

            @Override
            public void onLoadFail() {

            }
        });
        databaseManager2.setLoadCompleteListener(new LoadCompleteListener() {
            @Override
            public void onLoadComplete() {
                sneakersW = databaseManager2.getMainInfoArray();
                databaseManager3.requestGetMainInfo("DressShoes(M)");
            }
            @Override
            public void onLoadFail() {

            }
        });
        databaseManager3.setLoadCompleteListener(new LoadCompleteListener() {
            @Override
            public void onLoadComplete() {
                dressShoesM = databaseManager3.getMainInfoArray();
                databaseManager4.requestGetMainInfo("Sneakers(M)");
            }
            @Override
            public void onLoadFail() {

            }
        });
        databaseManager4.setLoadCompleteListener(new LoadCompleteListener() {
            @Override
            public void onLoadComplete() {
                sneakersM = databaseManager4.getMainInfoArray();
                databaseManager5.requestGetMainInfo("Slippers");
            }
            @Override
            public void onLoadFail() {

            }
        });
        databaseManager5.setLoadCompleteListener(new LoadCompleteListener() {
            @Override
            public void onLoadComplete() {
                slippers = databaseManager5.getMainInfoArray();
            }
            @Override
            public void onLoadFail() {

            }
        });
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
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

        } else if (id == R.id.nav_women) {
            Adapter adapter = new Adapter(getSupportFragmentManager());
            adapter.addFragment(new CardFragments(R.array.dressShoesWoman, dressShoesW), "dressWomen");
            adapter.addFragment(new CardFragments(R.array.sneakersWoman, sneakersW), "runningWomen");
            viewPager.setAdapter(adapter);
            tabs.setupWithViewPager(viewPager);

        } else if (id == R.id.nav_men) {
            Adapter adapter = new Adapter(getSupportFragmentManager());
            adapter.addFragment(new CardFragments(R.array.dressShoesMan, dressShoesM), "dressMen");
            adapter.addFragment(new CardFragments(R.array.sneakersMan, sneakersM), "runningMen");
            viewPager.setAdapter(adapter);
            tabs.setupWithViewPager(viewPager);

        } else if (id == R.id.nav_slipper) {
            Adapter adapter = new Adapter(getSupportFragmentManager());
            adapter.addFragment(new CardFragments(R.array.slipper, slippers), "slipper");
            viewPager.setAdapter(adapter);
            tabs.setupWithViewPager(viewPager);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
            super(manager);
            if (manager.getFragments() != null)
                manager.getFragments().clear();
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
