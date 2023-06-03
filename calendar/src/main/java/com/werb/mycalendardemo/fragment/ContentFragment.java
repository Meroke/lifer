package com.werb.mycalendardemo.fragment;

import com.google.android.material.navigation.NavigationView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.drawerlayout.widget.DrawerLayout;


import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.werb.mycalendardemo.CalendarMainActivity;
import com.werb.mycalendardemo.R;
import com.werb.mycalendardemo.R2;
import com.werb.mycalendardemo.database.AlarmDBSupport;
import com.werb.mycalendardemo.models.CalendarEvent;
import com.werb.mycalendardemo.pager.AboutMePager;
import com.werb.mycalendardemo.pager.BasePager;
import com.werb.mycalendardemo.pager.DayPager;
import com.werb.mycalendardemo.pager.HomePager;
import com.werb.mycalendardemo.pager.WeekPager;
import com.werb.mycalendardemo.utils.BusProvider;
import com.werb.mycalendardemo.utils.CalendarManager;
import com.werb.mycalendardemo.utils.Events;

import java.util.ArrayList;
import java.util.List;


import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by acer-pc on 2016/3/11.
 */
public class ContentFragment extends BaseFragment {

    @BindView(R2.id.vp_content)
    ViewPager vpContent;
    private List<BasePager> mPageList;
    private NavigationView navigationView;//菜单栏
    private DrawerLayout drawerLayout;//DrawerLayout


    private  List<CalendarEvent> eventList;
    private AlarmDBSupport support;
    private HomePager homePager;
    private DayPager dayPager;
    private WeekPager weekPager;
    private AboutMePager aboutMePager;



    @Override
    public View initView() {

        View view=View.inflate(mActivity, R.layout.fragment_content,null);

        ButterKnife.bind(this,view);

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void initDate() {

        homePager = new HomePager(mActivity);
        dayPager = new DayPager(mActivity);
        weekPager = new WeekPager(mActivity);
        aboutMePager = new AboutMePager(mActivity);

        //主界面添加数据
        mPageList= new ArrayList<>();

        mPageList.add(homePager);
        mPageList.add(dayPager);
        mPageList.add(weekPager);
        mPageList.add(aboutMePager);

        vpContent.setAdapter(new VpContentAdapter());

        //获取侧边栏
        CalendarMainActivity mainUi= (CalendarMainActivity) mActivity;
        navigationView = mainUi.getNavigationView();
        navigationView.setCheckedItem(R.id.schedule);
        buildHomePager();
        drawerLayout=mainUi.getDrawerLayout();
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                int itemId = item.getItemId();
                if (itemId == R.id.schedule) {
                    vpContent.setCurrentItem(0, false);//设置当前的页面，取消平滑滑动
                    buildHomePager();
                } else if (itemId == R.id.day) {
                    vpContent.setCurrentItem(1, false);
                    dayPager.initData();
                } else if (itemId == R.id.week) {
                    vpContent.setCurrentItem(2, false);
                    weekPager.initData();
                } else if (itemId == R.id.aboutMe) {
                    vpContent.setCurrentItem(3, false);
                    aboutMePager.initData();
                }
                item.setChecked(true);//点击了设置为选中状态
                drawerLayout.closeDrawers();
                return true;
            }
        });

    }

    /**
     * 主界面设置
     */
    private void buildHomePager(){
        homePager.initData();
        BusProvider.getInstance().toObserverable().subscribe(event ->{
            if(event instanceof Events.GoBackToDay){
                homePager.agenda_view.getAgendaListView().scrollToCurrentDate(CalendarManager.getInstance().getToday());
            }
        });
    }




    /**
     * viewPager数据适配器
     */
    class VpContentAdapter extends PagerAdapter{

        @Override
        public int getCount() {
            return mPageList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BasePager pager =mPageList.get(position);
            container.addView(pager.mRootView);
            return pager.mRootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mPageList.get(position).mRootView);
        }
    }

}
