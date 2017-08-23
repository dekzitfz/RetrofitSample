package id.dekz.retrofitexample.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by DEKZ on 8/23/2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydata.db";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_WEATHER =
            "CREATE TABLE " + WeatherContract.WeatherEntry.TABLE_NAME + " (" +
                    WeatherContract.WeatherEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    WeatherContract.WeatherEntry.WEATHER_ID + " INTEGER, " +
                    WeatherContract.WeatherEntry.WEATHER_DATE + " TEXT, " +
                    WeatherContract.WeatherEntry.WEATHER_DESC + " TEXT, " +
                    WeatherContract.WeatherEntry.WEATHER_TEMP + " REAL " +
                    "); ";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_WEATHER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + WeatherContract.WeatherEntry.TABLE_NAME);
        onCreate(db);
    }
}
