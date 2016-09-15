package webapi;


import com.google.android.gms.analytics.ecommerce.Product;
import com.google.gson.JsonObject;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.OkClient;
import retrofit.client.Response;


public class VidmateApiService {

    private static final EnvironmentConfig CONFIG = new EnvironmentConfig();
    private static VidmateApiService vidmateApiService = new VidmateApiService();
    private VidmateApiClient service;

    public static VidmateApiService getInstance() {
        if (vidmateApiService == null)
            return new VidmateApiService();
        else
            return vidmateApiService;
    }



    private VidmateApiClient getService() {
        if (service == null) {
            RestAdapter retrofit = new RestAdapter.Builder()
                    .setEndpoint(CONFIG.getBaseUrl())
                    .setLogLevel(CONFIG.getRetrofitLogLevel())
                    .setClient(new OkClient(new OkHttpClient()))
                    .build();

            service = retrofit.create(VidmateApiClient.class);
        }
        return service;
    }

}
