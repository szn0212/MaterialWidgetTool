package tools.study.com.materialwidgettool.materialanimation;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author suzhuning
 * @date 2016/11/22.
 * Description:
 */
public class TransitionHelper {

    public static Pair<View, String>[] createSafeTransitionParticipants
            (@NonNull Activity activity, boolean includeStatusBar, @NonNull Pair... otherParticipants){
        View decor = activity.getWindow().getDecorView();
        View statusBar = null;
        if(includeStatusBar){
            statusBar = decor.findViewById(android.R.id.statusBarBackground);
        }
        View navBar = decor.findViewById(android.R.id.navigationBarBackground);

        List<Pair> participants = new ArrayList<>();
        addNonNullViewToTransitionParticipants(statusBar, participants);
        addNonNullViewToTransitionParticipants(navBar, participants);

        if(otherParticipants != null && !(otherParticipants.length == 1 && otherParticipants[0] == null)){
            participants.addAll(Arrays.asList(otherParticipants));
        }
        return participants.toArray(new Pair[participants.size()]);
    }

    public static void addNonNullViewToTransitionParticipants(View view, List<Pair> pairList){
        if(view == null){
            return;
        }
        pairList.add(new Pair<>(view, view.getTransitionName()));
    }

}
