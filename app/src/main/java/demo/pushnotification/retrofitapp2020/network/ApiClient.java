package demo.pushnotification.retrofitapp2020.network;

import android.content.Context;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;
    public static Context mContext;

    public static Retrofit getClient(Context context) {
        mContext = context;
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getHttpClient())
                    .build();
        }

        return retrofit;
    }

    //This is used to Store response in Cache and populate last cache data automatic
    public static OkHttpClient getHttpClient(){
        int cacheSize=10*1024*1024;
        Cache cache=new Cache(mContext.getCacheDir(),cacheSize);
        OkHttpClient okHttpClient=new OkHttpClient.Builder()
                .cache(cache)
                .addInterceptor(getInterCeptor())
                .build();
        return okHttpClient;

    }

    //This is Used to print request and response
    public static HttpLoggingInterceptor getInterCeptor(){
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return loggingInterceptor;
    }
}
