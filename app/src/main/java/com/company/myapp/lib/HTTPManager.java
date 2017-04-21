package com.company.myapp.lib;

import android.support.annotation.Nullable;
import android.util.Base64;
import java.util.ArrayList;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.Date;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.http.*;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import com.company.myapp.models.*;

public class HTTPManager {
  CookieManager cookieManager;

  public HTTPManager() {
    cookieManager = new CookieManager();

    cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
  }
}