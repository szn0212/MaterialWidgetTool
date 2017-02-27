package tools.study.com.materialwidgettool.drawerlayout.library;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import java.util.List;

/**
 * @author suzhuning
 * @date 2016/12/1.
 * Description:
 */
public class MyViewPager extends ViewPager {

    public MyViewPager(Context context) {
        super(context);
    }

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void addFragemntList(FragmentManager fragmentManager, List<Fragment> fragments){
        if(fragments != null && fragments.size()  > 0){
            setOffscreenPageLimit(fragments.size());
            setAdapter(new MyViewPagerAdapter(fragmentManager, fragments));
        }
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
    }

    class MyViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> mListFragemnts;

        public MyViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            mListFragemnts = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            if(mListFragemnts != null && position > -1 && position < mListFragemnts.size()){
                return mListFragemnts.get(position);
            }else {
                return null;
            }
        }

        @Override
        public int getCount() {
            return mListFragemnts == null ? 0 : mListFragemnts.size();
        }

    }

}
