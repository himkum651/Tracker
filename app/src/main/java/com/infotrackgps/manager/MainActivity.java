package com.infotrackgps.manager;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private WebView mywebView;

    //variables
    Animation topAnim, bottomAnim;
    ImageView logo;
    TextView text1, text2;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this, R.anim.logo_animation);
        bottomAnim = AnimationUtils.loadAnimation(this, R.anim.text_animation);

        //Hooks
        logo=findViewById(R.id.logo);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        progressBar=findViewById(R.id.progressbar);

        logo.setAnimation(topAnim);
        text1.setAnimation(bottomAnim);
        text2.setAnimation(bottomAnim);

        mywebView=(WebView) findViewById(R.id.webview);
        mywebView.setWebViewClient(new WebViewClient());
        mywebView.loadUrl("http://infotechs.in");
        progressBar.setVisibility(View.VISIBLE);
        WebSettings webSettings=mywebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mywebView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                logo.setVisibility(View.INVISIBLE);
                text1.setVisibility(View.INVISIBLE);
                text2.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
    }

    public class mywebClient extends WebViewClient{
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view,url,favicon);
        }
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,String url){
            view.loadUrl(url);
            return true;
        }
    }
    @Override
    public void onBackPressed(){
        if(mywebView.canGoBack()) {
            mywebView.goBack();
        }
        else{
            super.onBackPressed();
        }
    }
}