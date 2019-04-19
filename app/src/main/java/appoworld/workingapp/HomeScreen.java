package appoworld.workingapp;

import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.yarolegovich.slidingrootnav.SlidingRootNav;
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import appoworld.Model.HomeCategorymodel;
import appoworld.adapter.DrawerAdapter;
import appoworld.adapter.DrawerItem;
import appoworld.adapter.HomeCategoryAdapter;
import appoworld.adapter.SimpleItem;
import appoworld.adapter.SpaceItem;

public class HomeScreen extends AppCompatActivity implements TabLayout.OnTabSelectedListener,DrawerAdapter.OnItemSelectedListener
         {
             private SlidingRootNav slidingRootNav;
             private static final int POS_DASHBOARD = 0;
             private static final int POS_ACCOUNT = 1;
             private static final int POS_MESSAGES = 2;
             private static final int POS_CART = 3;
             private static final int POS_LOGOUT = 5;
             private String[] screenTitles;
             private Drawable[] screenIcons;
    RecyclerView recyclerView;
    HomeCategoryAdapter adapter;
    List<HomeCategorymodel> item_list;
    HomeCategorymodel model;
    int image[] = {R.drawable.vns, R.drawable.vns, R.drawable.vns, R.drawable.vns, R.drawable.vns,
            R.drawable.vns, R.drawable.vns, R.drawable.vns};
    String name[] = {"vns", "vns", "vns", "vns", "vns", "vns", "vns", "vns"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView = findViewById(R.id.item_list);
        item_list = new ArrayList<>();
        // adding item to list

        adapter = new HomeCategoryAdapter(this, item_list);
        for (int i = 0; i < image.length; i++) {
            model = new HomeCategorymodel(image[i], name[i]);
            item_list.add(model);
            adapter.notifyDataSetChanged();
        }
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        //
        //NAVIGATION


        slidingRootNav = new SlidingRootNavBuilder(this)
                .withToolbarMenuToggle(toolbar)
                .withMenuOpened(false)
                .withContentClickableWhenMenuOpened(true)
                .withSavedState(savedInstanceState)
                .withMenuLayout(R.layout.menu_left_drawer)
                .inject();

        screenIcons = loadScreenIcons();
        screenTitles = loadScreenTitles();

        DrawerAdapter drawadapter = new DrawerAdapter(Arrays.asList(
                createItemFor(POS_DASHBOARD).setChecked(true),
                createItemFor(POS_ACCOUNT),
                createItemFor(POS_MESSAGES),
                createItemFor(POS_CART),
                new SpaceItem(48),
                createItemFor(POS_LOGOUT)));
        drawadapter.setListener(this);

        RecyclerView list = findViewById(R.id.list);
        list.setNestedScrollingEnabled(false);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(drawadapter);

        drawadapter.setSelected(POS_DASHBOARD);


        //BUTTON




    }





    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
        }

        //
        @Override
        public void onItemSelected(int position) {
            if (position == POS_LOGOUT) {
                finish();
            }
            if (position == POS_ACCOUNT

            ) {
                //     Intent i =new Intent(getApplication(), About.class);
                //    startActivity(i);
            }

            if (position == POS_CART

            ) {
                //   Intent i =new Intent(getApplication(), AdvertiseUs.class);
                //  startActivity(i);
            }

            if (position == POS_MESSAGES

            ) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT," Click to download Colors Soda app from wwww. ");
                sendIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"G E E N  B O X");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

            }

            slidingRootNav.closeMenu();
            // Fragment selectedScreen = CenteredTextFragment.createFor(screenTitles[position]);
            // showFragment(selectedScreen);
        }



             private DrawerItem createItemFor(int position) {
                 return new SimpleItem(screenIcons[position], screenTitles[position])
                         .withIconTint(color(R.color.textColorSecondary))
                         .withTextTint(color(R.color.textColorSecondary))
                         .withSelectedIconTint(color(R.color.navfun))
                         .withSelectedTextTint(color(R.color.navfun));
             }

             private String[] loadScreenTitles() {
                 return getResources().getStringArray(R.array.ld_activityScreenTitles);
             }

             private Drawable[] loadScreenIcons() {
                 TypedArray ta = getResources().obtainTypedArray(R.array.ld_activityScreenIcons);
                 Drawable[] icons = new Drawable[ta.length()];
                 for (int i = 0; i < ta.length(); i++) {
                     int id = ta.getResourceId(i, 0);
                     if (id != 0) {
                         icons[i] = ContextCompat.getDrawable(this, id);
                     }
                 }
                 ta.recycle();
                 return icons;
             }
             @ColorInt
             private int color(@ColorRes int res) {
                 return ContextCompat.getColor(this, res);
             }
             @Override
             public void onTabSelected(TabLayout.Tab tab) {

             }

             @Override
             public void onTabUnselected(TabLayout.Tab tab) {

             }

             @Override
             public void onTabReselected(TabLayout.Tab tab) {

             }
    }

