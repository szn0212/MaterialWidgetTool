package tools.study.com.materialwidgettool.drawerlayout.library;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import tools.study.com.materialwidgettool.drawerlayout.NavigationEntity;

/**
 * @author suzhuning
 * @date 2016/11/8.
 * Description:
 */
public class CVPFragmentAdapter extends FragmentPagerAdapter {

    private Class<?> fragmentClass;
    private List<NavigationEntity> mCategoryList;

    public CVPFragmentAdapter(FragmentManager fm, Class<?> fragmentClass, List<NavigationEntity> mCategoryList) {
        super(fm);
        this.fragmentClass = fragmentClass;
        this.mCategoryList = mCategoryList;
    }

    @Override
    public Fragment getItem(int position) {
        try {
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            Bundle bundle = new Bundle();
            bundle.putString("categoryId", mCategoryList.get(position).getId());
            fragment.setArguments(bundle);
            return fragment;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int getCount() {
        return mCategoryList == null ? 0 : mCategoryList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mCategoryList == null ? null : mCategoryList.get(position).getName();
    }
}
