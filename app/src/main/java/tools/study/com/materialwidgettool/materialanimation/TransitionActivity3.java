package tools.study.com.materialwidgettool.materialanimation;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionInflater;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import tools.study.com.materialwidgettool.R;

public class TransitionActivity3 extends BaseDetailActivity {

    private int type;
    @BindView(R.id.square_red)
    ImageView square_red;
    @BindView(R.id.title)
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition3);
        ButterKnife.bind(this);

        SampleEntity sample = (SampleEntity) getIntent().getExtras().getSerializable(EXTRA_SAMPLE);
        type = getIntent().getExtras().getInt(EXTRA_TYPE);
        title.setText(sample.getName());
        DrawableCompat.setTint(square_red.getDrawable(), ContextCompat.getColor(this, sample.getColor()));

        setupWindowAnimations();
        setupLayout();
        setupToolbar();
    }

    private void setupWindowAnimations() {
        Transition transition;

        if (type == TYPE_PROGRAMMATICALLY) {
            transition = buildEnterTransition();
        }  else {
            transition = TransitionInflater.from(this).inflateTransition(R.transition.slide_from_bottom);
        }
        getWindow().setEnterTransition(transition);
    }

    private void setupLayout() {
        findViewById(R.id.exit_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAfterTransition();
            }
        });
    }

    private Visibility buildEnterTransition() {
        Slide enterTransition = new Slide();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        enterTransition.setSlideEdge(Gravity.RIGHT);
        return enterTransition;
    }

}
