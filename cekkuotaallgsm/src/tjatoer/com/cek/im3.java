package tjatoer.com.cek;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by hacklana on 12/8/13.
 */
public class im3 extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.im3);

        WebView mainWebView = (WebView) findViewById(R.id.im3);

        WebSettings webSettings = mainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mainWebView.setWebViewClient(new MyCustomWebViewClient());
        mainWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        mainWebView.loadUrl("http://m.instant.indosat.com");
    }

    private class MyCustomWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            findViewById(R.id.progBar).setVisibility(View.VISIBLE);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url){
            findViewById(R.id.progBar).setVisibility(View.GONE);
        }
    }
}