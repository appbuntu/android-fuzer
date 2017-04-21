package com.company.myapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.graphics.Color;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import com.company.myapp.R;
import android.webkit.WebViewClient;
import android.annotation.TargetApi;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.URLUtil;
import android.content.Intent;
import android.net.Uri;
import com.company.myapp.activities.WebviewActivity;

public class WebviewActivity extends AppCompatActivity {
  public LinearLayout mMainLayout;

  public Toolbar appBar;

  public WebView webView1;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.webview);

    mMainLayout = (LinearLayout) findViewById(R.id.webview);

    this.setup();
  }

  @Override
  protected void onResume() {
    super.onResume();

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
      getWindow().setStatusBarColor(Color.parseColor("#FF4828A6"));
    }
  }

  @Override
  protected void onStop() {
    super.onStop();
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
  }

  public WebviewActivity getContext() {
    return this;
  }

  private void setup() {
    appBar = (Toolbar) findViewById(R.id.appbar);

    WebviewActivity.this.setSupportActionBar(appBar);

    webView1 = (WebView) findViewById(R.id.webview1);

    webView1.setWebViewClient(
        new WebViewClient() {
          @TargetApi(Build.VERSION_CODES.LOLLIPOP)
          @Override
          public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            String url = request.getUrl().toString();
            if (URLUtil.isNetworkUrl(url)) {
              return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            ((WebviewActivity) getContext()).startActivity(intent);
            return true;
          }

          @SuppressWarnings("deprecation")
          @Override
          public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (URLUtil.isNetworkUrl(url)) {
              return false;
            }
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            ((WebviewActivity) getContext()).startActivity(intent);
            return true;
          }
        });

    webView1.loadUrl("https://bitfuzer.github.io/fuzer/#/");

    webView1.getSettings().setUseWideViewPort(true);

    webView1.getSettings().setLoadWithOverviewMode(true);

    webView1.getSettings().setSupportZoom(false);

    webView1.getSettings().setJavaScriptEnabled(true);
  }
}