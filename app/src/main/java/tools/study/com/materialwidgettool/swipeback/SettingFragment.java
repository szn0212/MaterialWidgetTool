package tools.study.com.materialwidgettool.swipeback;

import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.PreferenceFragment;
import android.widget.Toast;

import tools.study.com.materialwidgettool.R;

/**
 * @author suzhuning
 * @date 2016/11/18.
 * Description:
 */
public class SettingFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.swipeback_setting_preference);

        String name = ((EditTextPreference)findPreference("name")).getText();
        Toast.makeText(getActivity(), name, Toast.LENGTH_SHORT).show();
    }
}
