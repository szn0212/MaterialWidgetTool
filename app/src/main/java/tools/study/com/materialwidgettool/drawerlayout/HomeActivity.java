package tools.study.com.materialwidgettool.drawerlayout;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tools.study.com.materialwidgettool.R;
import tools.study.com.materialwidgettool.WidgetToolApplication;
import tools.study.com.materialwidgettool.drawerlayout.library.BaseLazyFragment;
import tools.study.com.materialwidgettool.drawerlayout.library.VPFragmentAdapter;
import tools.study.com.materialwidgettool.drawerlayout.library.XViewPager;

/**
 * @author suzhuning
 * @date 2016/11/16.
 * Description:
 */
public class HomeActivity extends AppCompatActivity {

    private ActionBarDrawerToggle mActionBarDrawerToggle;

    @BindView(R.id.main_toolbar)
    Toolbar main_toolbar;
    @BindView(R.id.main_container)
    XViewPager main_container;
    @BindView(R.id.main_drawer)
    DrawerLayout main_drawer;
    @BindView(R.id.mian_menu_layout)
    LinearLayout mian_menu_layout;
    @BindView(R.id.main_menu_list)
    RecyclerView main_menu_list;

    private BaseQuickAdapter<NavigationEntity, BaseViewHolder> mMenuAdapter;
    private int mCheckedListItemColorResIds[] = {
            R.color.navigation_checked_news_text_color,
            R.color.navigation_checked_picture_text_color,
            R.color.navigation_checked_video_text_color,
            R.color.navigation_checked_music_text_color,
    };

    private int mCurentMenuCheckPos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawerlayout_home_activity);
        ButterKnife.bind(this);

        setSupportActionBar(main_toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this,main_drawer,main_toolbar,R.string.drawer_open,R.string.drawer_close){

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                setTitle(R.string.app_name);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if(mMenuAdapter != null){
                    setTitle(mMenuAdapter.getItem(mCurentMenuCheckPos).getName());
                }
            }
        };
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mActionBarDrawerToggle.syncState();
        main_drawer.setDrawerListener(mActionBarDrawerToggle);

        main_menu_list.setLayoutManager(new LinearLayoutManager(this));

        List<NavigationEntity> navigationEntities = new ArrayList<>();
        String[] navigationArrays = getResources().getStringArray(R.array.navigation_list);
        navigationEntities.add(new NavigationEntity("", navigationArrays[0], R.drawable.ic_news));
        navigationEntities.add(new NavigationEntity("", navigationArrays[1], R.drawable.ic_picture));
        navigationEntities.add(new NavigationEntity("", navigationArrays[2], R.drawable.ic_video));
        navigationEntities.add(new NavigationEntity("", navigationArrays[3], R.drawable.ic_music));

        setTitle(navigationArrays[0]);

        mMenuAdapter = new BaseQuickAdapter<NavigationEntity, BaseViewHolder>(R.layout.navigation_menu_item_view, navigationEntities){

            @Override
            protected void convert(BaseViewHolder baseViewHolder, NavigationEntity entity) {
                baseViewHolder.setImageResource(R.id.navigation_menu_item_image, entity.getIconRes())
                        .setText(R.id.navigation_menu_item_text, entity.getName())
                        .setTextColor(R.id.navigation_menu_item_text, (baseViewHolder.getAdapterPosition() == mCurentMenuCheckPos) ?
                                (mContext.getColor(mCheckedListItemColorResIds[baseViewHolder.getAdapterPosition()]))
                                : mContext.getColor(android.R.color.black));
            }
        };
        main_menu_list.setAdapter(mMenuAdapter);
        main_menu_list.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int position) {
                mCurentMenuCheckPos = position;
                mMenuAdapter.notifyDataSetChanged();
                main_drawer.closeDrawer(Gravity.LEFT);
                main_container.setCurrentItem(mCurentMenuCheckPos, false);
            }
        });

        List<BaseLazyFragment> fragments = new ArrayList<>();
        fragments.add(new NewsContainerFragment());
//        fragments.add(new NewsContainerFragment());
//        fragments.add(new NewsContainerFragment());
//        fragments.add(new NewsContainerFragment());
        if(fragments != null && !fragments.isEmpty()){
            main_container.setEnableScroll(false);
            main_container.setOffscreenPageLimit(fragments.size());
            main_container.setAdapter(new VPFragmentAdapter(getSupportFragmentManager(), fragments));
        }

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(mActionBarDrawerToggle != null){
            mActionBarDrawerToggle.syncState();
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if(mActionBarDrawerToggle != null){
            mActionBarDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.drawerlayout_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(mActionBarDrawerToggle != null && mActionBarDrawerToggle.onOptionsItemSelected(item)){
            return true;
        }
        switch (item.getItemId()){
            case R.id.action_capture:

                break;

            case R.id.action_about:
                break;

            case R.id.action_feedback:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_MENU){
            if(main_drawer.isDrawerOpen(Gravity.LEFT)){
                main_drawer.closeDrawer(Gravity.LEFT);
            }else {
                main_drawer.openDrawer(Gravity.LEFT);
            }
        }else if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            if(main_drawer.isDrawerOpen(Gravity.LEFT)){
                main_drawer.closeDrawer(Gravity.LEFT);
            }else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
