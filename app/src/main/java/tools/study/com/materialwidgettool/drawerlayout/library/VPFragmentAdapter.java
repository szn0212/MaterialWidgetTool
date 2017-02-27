package tools.study.com.materialwidgettool.drawerlayout.library;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import tools.study.com.materialwidgettool.drawerlayout.library.BaseLazyFragment;

/**
 * @author suzhuning
 * @date 2016/11/7.
 * Description:
 */
public class VPFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseLazyFragment> mListFragemnts;

    public VPFragmentAdapter(FragmentManager fm, List<BaseLazyFragment> fragments) {
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
