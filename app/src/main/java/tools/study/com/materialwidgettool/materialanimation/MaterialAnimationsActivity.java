package tools.study.com.materialwidgettool.materialanimation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.Gravity;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemClickListener;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tools.study.com.materialwidgettool.R;

/**
 * @author suzhuning
 * @date 2016/11/22.
 * Description:
 */
public class MaterialAnimationsActivity extends AppCompatActivity {

    private List<SampleEntity> samples;

    @BindView(R.id.material_animation_toolbar)
    Toolbar toolbar;
    @BindView(R.id.material_animations_list)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_animation_main_layout);
        ButterKnife.bind(this);

        setupWindowAnimations();
        setupSamples();
        setupToolbar();
        setupLayout();
    }

    private void setupWindowAnimations() {
        Slide slide = new Slide();
        slide.setSlideEdge(Gravity.LEFT);
        slide.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        getWindow().setReenterTransition(slide);
        getWindow().setExitTransition(slide);
    }

    private void setupSamples() {
        samples = Arrays.asList(new SampleEntity(R.color.sample_red, "Transitions"),
                new SampleEntity(R.color.sample_blue, "Shared Elements"),
                new SampleEntity(R.color.sample_green, "View animations"),
                new SampleEntity(R.color.sample_yellow, "Circular Reveal Animation"));
    }

    private void setupToolbar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void setupLayout(){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        SampleRecyclerViewAdapter adapter = new SampleRecyclerViewAdapter(samples);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {
            @Override
            public void SimpleOnItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                switch (i){
                    case 0:
                        transitionToActivity(TransitionActivity1.class, samples.get(i));
                        break;

                    case 1:
                        transitionToActivity(SharedElementActivity.class, view, samples.get(i));
                        break;

                    case 2:
                        transitionToActivity(AnimationsActivity1.class, samples.get(i));
                        break;

                    case 3:
                        transitionToActivity(RevealActivity.class, view, samples.get(i), R.string.transition_reveal1);
                        break;
                }
            }
        });
    }

    private void transitionToActivity(Class target, SampleEntity sample){
        Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this,true);
        startActivity(target, pairs, sample);
    }

    private void transitionToActivity(Class target, View contentView, SampleEntity sample, int transitionName){
        Pair<View, String>[] pais = TransitionHelper.createSafeTransitionParticipants(this, false,
                new Pair<>(contentView.findViewById(R.id.sample_icon), getString(transitionName)));
        startActivity(target, pais,sample);
    }

    private void transitionToActivity(Class target, View contentView, SampleEntity sample){
        Pair<View, String>[] pais = TransitionHelper.createSafeTransitionParticipants(this, false,
                new Pair<>(contentView.findViewById(R.id.sample_icon), getString(R.string.square_blue_name)),
                new Pair<>(contentView.findViewById(R.id.sample_text), getString(R.string.sample_blue_title)));
        startActivity(target, pais,sample);
    }

    private void startActivity(Class target, Pair<View, String>[] pairs, SampleEntity sample){
        Intent intent = new Intent(this, target);
        ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        intent.putExtra("sample", sample);
        startActivity(intent, activityOptionsCompat.toBundle());
    }

}
