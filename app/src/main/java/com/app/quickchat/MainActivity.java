package com.app.quickchat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import java.util.prefs.PreferenceChangeEvent;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private ImageView refresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         refresh = findViewById(R.id.refresh);
         webView = findViewById(R.id.webView);
         loadUrl();



         refresh.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 loadUrl();
             }
         });
    }

    private void loadUrl(){
        if(refresh.getVisibility() == View.VISIBLE){
            refresh.setVisibility(View.GONE);
        }
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.loadUrl("");
    }

    private  class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {

            webView.loadUrl(url);
            return true;
        }

        @Override
        public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
            super.onReceivedError(view, request, error);
            refresh.setVisibility(View.VISIBLE);
        }
    }
}

