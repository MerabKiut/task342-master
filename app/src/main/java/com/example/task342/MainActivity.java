package com.example.task342;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v4.os.ConfigurationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Spinner langSelector;
    private Button btnOk;
    private Spinner marginSelector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_main);
        langSelector = findViewById(R.id.langSelector);
        btnOk = findViewById(R.id.btnOk);
        marginSelector = findViewById(R.id.marginSelector);
        btnOk.setOnClickListener(this);
        initSpinner();
        initSpinnerMargin();
    }

    private void initSpinner() {
        ArrayAdapter<CharSequence> adapterLanguage = ArrayAdapter.createFromResource(this,
                R.array.language, android.R.layout.simple_spinner_item);
        adapterLanguage.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        langSelector.setAdapter(adapterLanguage);
        langSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String[] language = getResources().getStringArray(R.array.language);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }


    private void initSpinnerMargin() {
        ArrayAdapter<CharSequence> adapterMargin = ArrayAdapter.createFromResource(this,
                R.array.margin, android.R.layout.simple_spinner_item);
        adapterMargin.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        marginSelector.setAdapter(adapterMargin);
        marginSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                String[] margin = getResources().getStringArray(R.array.margin);
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
    }


    @Override
    public void onClick(View v) {
        String selectLanguage = langSelector.getSelectedItem().toString();
        switch (selectLanguage) {
            case "Русский":
                Locale localeRu = new Locale("ru");
                Configuration configRu = new Configuration();
                configRu.setLocale(localeRu);
                getResources().updateConfiguration(configRu, getBaseContext().getResources().getDisplayMetrics());
                recreate();
                break;
            case "English":
                Locale localeEn = new Locale("en");
                Configuration configEn = new Configuration();
                configEn.setLocale(localeEn);
                getResources().updateConfiguration(configEn, getBaseContext().getResources().getDisplayMetrics());
                recreate();
                break;
        }

        String selectMargin = marginSelector.getSelectedItem().toString();
        switch (selectMargin) {
            case "Крупный": // возможно ли выбрать две темы одновременно? и если нет то как менять
            case "Large":   // цвета и отступы независимо друг от друга?
                Utils.changeToTheme(this, Utils.THEME_LARGE);
                break;
            case "Средний":
            case "Medium":
                Utils.changeToTheme(this, Utils.THEME_MEDIUM);
                break;
            case "Мелкий":
            case "Small":
                Utils.changeToTheme(this, Utils.THEME_SMALL);
                break;
        }
    }
}
