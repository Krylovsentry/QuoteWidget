package com.telaistudio.www.quotewidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.concurrent.ExecutionException;

/**
 * Created by Anton on 24.12.2016.
 */

public class QuoteWidget extends AppWidgetProvider {

    /**
     * Calls when first example of widget creates
     *
     * @param context some context
     */
    @Override
    public void onEnabled(Context context) {
        updateWidget(context);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        updateWidget(context);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        updateWidget(context);
    }

    @Override
    public void onRestored(Context context, int[] oldWidgetIds, int[] newWidgetIds) {
        updateWidget(context);
    }

    private void updateWidget(Context context) {
        RemoteViews widgetView = new RemoteViews(context.getPackageName(), R.layout.widget);
        Quote quote;
        try {
            quote = new QuoteTask(context).execute().get();
            widgetView.setTextViewText(R.id.quote_author, quote.getAuthor());
            widgetView.setTextViewText(R.id.quote_text, quote.getQuote());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        ComponentName componentName = new ComponentName(context, QuoteWidget.class);
        AppWidgetManager.getInstance(context).updateAppWidget(componentName, widgetView);
    }

}
