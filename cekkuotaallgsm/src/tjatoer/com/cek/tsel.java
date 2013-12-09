package tjatoer.com.cek;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class tsel extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tsel);

        WebView mainWebView = (WebView) findViewById(R.id.tsel);

        WebSettings webSettings = mainWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        mainWebView.setWebViewClient(new MyCustomWebViewClient());
        mainWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);

        mainWebView.loadUrl("http://mobi.telkomsel.com/profil/profileinternet");
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
