package id.dekz.retrofitexample.data;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by DEKZ on 8/23/2017.
 */

public class WeatherContract {
    public static final String AUTHORITY = "id.dekz.retrofitexample";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    public static final String PATH = "weather";

    public static final class WeatherEntry implements BaseColumns {
        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH).build();

        public static final String TABLE_NAME = "weather";

        public static final String WEATHER_ID = "id";
        public static final String WEATHER_DESC = "desc";
        public static final String WEATHER_DATE = "date";
        public static final String WEATHER_TEMP = "temp";
    }
}
