package baway.com.mypropertyanimation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class ThreeActivity extends AppCompatActivity {

    private WebView web;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);
        btn = (Button) findViewById(R.id.btn);
        web = (WebView) findViewById(R.id.web);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                WebSettings webSettings = web.getSettings();
                //设置WebView属性，能够执行Javascript脚本
                webSettings.setJavaScriptEnabled(true);
                //设置可以访问文件
                webSettings.setAllowFileAccess(true);
                //设置支持缩放
                webSettings.setBuiltInZoomControls(true);
                //加载需要显示的网页
                web.loadUrl("http://www.baidu.com");
                //设置Web视图
                web.setWebViewClient(new webViewClient ());
            }
        });


    }
    //Web视图
    private class webViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
