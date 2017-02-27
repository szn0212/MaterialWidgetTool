package tools.study.com.materialwidgettool.drawerlayout;

import android.support.design.widget.TabLayout;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import tools.study.com.materialwidgettool.R;
import tools.study.com.materialwidgettool.drawerlayout.library.BaseLazyFragment;
import tools.study.com.materialwidgettool.drawerlayout.library.CVPFragmentAdapter;
import tools.study.com.materialwidgettool.drawerlayout.library.XViewPager;
import tools.study.com.materialwidgettool.utils.EventCenter;

/**
 * @author suzhuning
 * @date 2016/11/7.
 * Description:
 */
public class NewsContainerFragment extends BaseLazyFragment {

    @BindView(R.id.news_tablayout)
    TabLayout news_tablayout;
    @BindView(R.id.news_container)
    XViewPager news_container;

    private String[] newsCategoryArrayIds;

    @Override
    protected void onFirstUserVisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {
        newsCategoryArrayIds = getResources().getStringArray(R.array.news_category_list_ids);

        if(newsCategoryArrayIds != null && newsCategoryArrayIds.length > 0){
            List<NavigationEntity> mCategoryList = new ArrayList<>();
            for (int i = 0; i < newsCategoryArrayIds.length; i++){
                mCategoryList.add(new NavigationEntity(newsCategoryArrayIds[i], newsCategoryArrayIds[i], 0));
            }
//            news_container.setAdapter(new CVPFragmentAdapter(getChildFragmentManager(), getContext(), NewsListFragment.class.getName(), mCategoryList));
//            news_container.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(news_tablayout){
//                @Override
//                public void onPageSelected(int position) {
//                    super.onPageSelected(position);
//                    NewsListFragment fragment = (NewsListFragment) news_container.getAdapter().instantiateItem(news_container, position);
//                    fragment.setText(newsCategoryArrayNames[position]);
//                }
//            });

            news_container.setAdapter(new CVPFragmentAdapter(getChildFragmentManager(), NewsListFragment.class, mCategoryList));
            news_container.setOffscreenPageLimit(newsCategoryArrayIds.length);
            news_container.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(news_tablayout));
            news_tablayout.setupWithViewPager(news_container);
        }
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.drawerlayout_news_container_layout;
    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }
}
