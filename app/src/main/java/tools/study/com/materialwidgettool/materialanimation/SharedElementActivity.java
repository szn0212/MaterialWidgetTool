package tools.study.com.materialwidgettool.materialanimation;

import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.Slide;
import android.view.Gravity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tools.study.com.materialwidgettool.R;

public class SharedElementActivity extends BaseDetailActivity {

    @BindView(R.id.title)
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sharedelement);
        ButterKnife.bind(this);

        SampleEntity sample = (SampleEntity) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        title.setText(sample.getName());

        setupWindowAnimations();
        setupLayout(sample);
        setupToolbar();
    }

    private void setupWindowAnimations() {
        // We are not interested in defining a new Enter Transition. Instead we change default transition duration
        getWindow().getEnterTransition().setDuration(getResources().getInteger(R.integer.anim_duration_long));
    }

    private void setupLayout(SampleEntity sample) {
        // Transition for fragment1
        Slide slideTransition = new Slide(Gravity.LEFT);
        slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        // Create fragment and define some of it transitions
        SharedElementFragment1 sharedElementFragment1 = SharedElementFragment1.newInstance(sample);
        sharedElementFragment1.setReenterTransition(slideTransition);
        sharedElementFragment1.setExitTransition(slideTransition);
        sharedElementFragment1.setSharedElementEnterTransition(new ChangeBounds());

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.sample2_content, sharedElementFragment1)
                .commit();
    }
}
