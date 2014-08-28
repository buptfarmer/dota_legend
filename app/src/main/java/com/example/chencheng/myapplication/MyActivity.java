package com.example.chencheng.myapplication;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.qq.e.ads.AdListener;
import com.qq.e.ads.AdRequest;
import com.qq.e.ads.AdSize;
import com.qq.e.ads.AdView;
import com.qq.e.appwall.GdtAppwall;


public class MyActivity extends Activity {

    public static final String QQ_ADVERTISE_APPID = "1101813469";
    public static final String QQ_ADVERTISE_BANNER_ID = "9079537211443466180";
    public static final String QQ_ADVERTISE_WALL_ID = "8935422023367610308";
    public static final boolean TEST_AD = true;
    private Button mShowAdButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
        final GdtAppwall appwall = new GdtAppwall(this,"appid","posid", TEST_AD);
        mShowAdButton = (Button) findViewById(R.id.show_ad);
        mShowAdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                appwall.doShowAppWall();
            }
        });
        RelativeLayout l = (RelativeLayout)findViewById(R.id.adcontent);
        /* 创建Banner广告view
　　		 *  “appid”指在 http://e.qq.com/dev/ 能看到的app唯一字符串
　　		 * 	“广告位 id” 指在 http://e.qq.com/dev/ 生成的数字串，
　　		 * 	并非 appid 或者 appkey
		 */
        AdView adv = new AdView(this, AdSize.BANNER, QQ_ADVERTISE_APPID, QQ_ADVERTISE_BANNER_ID);
        l.addView(adv);
		/* 广告请求数据，可以设置广告轮播时间，默认为30s  */
        AdRequest adr = new AdRequest();
		/* 这个接口的作用是设置广告的测试模式，该模式下点击不扣费
		 * 未发布前请设置testad为true，
		 * 上线的版本请确保设置为false或者去掉这行调用
		 */
        adr.setTestAd(true);
		/* 设置广告刷新时间，为30~120之间的数字，单位为s*/
        adr.setRefresh(31);
		/* 设置空广告和首次收到广告数据回调
		 * 调用fetchAd方法后会发起广告请求，广告轮播时不会产生回调
		 */
        adv.setAdListener(new AdListener() {
            @Override
            public void onNoAd() {
                Log.i("ccc:","no");
            }
            @Override
            public void onAdReceiv() {
                Log.i("ccc:", "revc");
            }

            @Override
            public void onAdExposure() {

            }

            @Override
            public void onBannerClosed() {

            }
        });
		/* 发起广告请求，收到广告数据后会展示数据	 */
        adv.fetchAd(adr);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private WebView mWebView;
        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_my, container, false);
            mWebView = (WebView) rootView.findViewById(R.id.webview);
//            mWebView.loadUrl("http://www.smzdm.com/");
            mWebView.loadUrl("file:///android_asset/tut1.html");
            WebSettings settings = mWebView.getSettings();
            settings.setUseWideViewPort(true);
            settings.setSupportZoom(true);
            return rootView;
        }
    }
}
