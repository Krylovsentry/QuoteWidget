package com.telaistudio.www.quotewidget;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by Anton on 24.12.2016.
 */

public class ConfigActivity extends Activity {

    int widgetID = AppWidgetManager.INVALID_APPWIDGET_ID;
    Intent resultValue;

    public static final String WIDGET_LANGUAGE = "widget_lang";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Extract id for widget
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        
    }
}
