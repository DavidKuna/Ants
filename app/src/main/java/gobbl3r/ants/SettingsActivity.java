package gobbl3r.ants;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

public class SettingsActivity extends Activity {

    private Spinner spinnerctrl;
    private Button btn;
    private Locale myLocale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set full screen view
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_settings);

        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        myLocale = conf.locale;

        spinnerctrl = (Spinner) findViewById(R.id.spinner);
        spinnerctrl.setSelection(myLocale.getLanguage() == "cs" ? 0 : 1);
        spinnerctrl.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                Log.d("LANGUAGE", myLocale.getLanguage());
                if (pos == 0 && myLocale.getLanguage() != "cs") {

                    Toast.makeText(parent.getContext(),
                            "You have selected Cs", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("cs");
                } else if (pos == 1 && myLocale.getLanguage() != "en") {

                    Toast.makeText(parent.getContext(),
                            "You have selected En", Toast.LENGTH_SHORT)
                            .show();
                    setLocale("en");
                }

            }

            public void onNothingSelected(AdapterView<?> arg0) {
                // TODO Auto-generated method stub
            }

        });
    }

    public void setLocale(String lang) {
        myLocale = new Locale(lang);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor prefEditor = prefs.edit();
        prefEditor.putString("locale_override", lang);
        prefEditor.commit();

        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, SettingsActivity.class);
        startActivity(refresh);
        finish();
    }

}
