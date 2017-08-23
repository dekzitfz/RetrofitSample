package id.dekz.retrofitexample.data;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by DEKZ on 8/23/2017.
 */

public class DataProvider extends ContentProvider {

    private static final int WEATHER = 100;
    private static final UriMatcher uriMatcher = buildUriMatcher();

    private DBHelper dbHelper;

    public static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(WeatherContract.AUTHORITY, WeatherContract.PATH, WEATHER);

        return matcher;
    }

    @Override
    public boolean onCreate() {
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor result = null;
        final SQLiteDatabase db = dbHelper.getReadableDatabase();

        int match = uriMatcher.match(uri);
        switch (match){
            case WEATHER:
                result = db.query(
                        WeatherContract.WeatherEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                result.setNotificationUri(getContext().getContentResolver(), uri);
                break;
            default:
                Log.w("warning", "Unknown URI: " + uri);
        }

        return result;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        Uri result = null;
        long id;

        final SQLiteDatabase db = dbHelper.getWritableDatabase();
        int match = uriMatcher.match(uri);
        switch (match){
            case WEATHER:
                id = db.insert(WeatherContract.WeatherEntry.TABLE_NAME, null, contentValues);
                if(id > 0){
                    result = ContentUris.withAppendedId(uri, id);
                    getContext().getContentResolver().notifyChange(uri, null);
                }else{
                    Log.w("warning", "inserting data was failed!");
                }
                break;
            default:
                Log.w("warning", "unknown URI: "+uri);
        }

        return result;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {
        int result = 0;
        final SQLiteDatabase db = dbHelper.getWritableDatabase();

        int match = uriMatcher.match(uri);
        switch (match){
            case WEATHER:
                result = db.delete(WeatherContract.WeatherEntry.TABLE_NAME, null, null);
                break;
            default:
                Log.w("warning", "Unknown URI: " + uri);
        }

        if(result > 0) getContext().getContentResolver().notifyChange(uri, null);

        return result;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }
}
