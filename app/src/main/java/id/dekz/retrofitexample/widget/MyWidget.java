package id.dekz.retrofitexample.widget;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.widget.RemoteViews;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import id.dekz.retrofitexample.MainActivity;
import id.dekz.retrofitexample.R;
import id.dekz.retrofitexample.data.WeatherContract;

/**
 * Implementation of App Widget functionality.
 */
public class MyWidget extends AppWidgetProvider {

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId) {

        Cursor cursor = getDataFromDB(context);
        cursor.moveToFirst();
        String when = cursor.getString(cursor.getColumnIndex(WeatherContract.WeatherEntry.WEATHER_DATE));
        String readableDate = longToReadableDate(when);
        double temp = cursor.getDouble(cursor.getColumnIndex(WeatherContract.WeatherEntry.WEATHER_TEMP));
        String desc = cursor.getString(cursor.getColumnIndex(WeatherContract.WeatherEntry.WEATHER_DESC));

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.my_widget);
        views.setTextViewText(R.id.widget_date, readableDate+", ");
        views.setTextViewText(R.id.widget_desc, desc+", ");
        views.setTextViewText(R.id.widget_temp, String.valueOf(temp));

        Intent main = new Intent(context, MainActivity.class);
        PendingIntent pendingMain = PendingIntent.getActivity(context, 0, main, PendingIntent.FLAG_UPDATE_CURRENT);
        views.setOnClickPendingIntent(R.id.widget_root, pendingMain);

        // Instruct the widget manager to update the widget
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            Log.i("onUpdate", appWidgetId+" widget updated");
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    private static Cursor getDataFromDB(Context c){
        return c.getContentResolver()
                .query(
                        WeatherContract.WeatherEntry.CONTENT_URI,
                        null,null,null,null
                );
    }

    private static String longToReadableDate(String longStr){
        long dt = Long.parseLong(longStr);
        Date date = new Date(dt * 1000L);
        @SuppressLint("SimpleDateFormat")
        DateFormat format = new SimpleDateFormat("EEE");
        return format.format(date);
    }
}

