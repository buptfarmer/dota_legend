package com.example.chencheng.myapplication;



import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.qq.e.ads.AdListener;
import com.qq.e.ads.AdRequest;
import com.qq.e.ads.AdSize;
import com.qq.e.ads.AdView;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AboutFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class AboutFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private AdView mAdView;
    private AdRequest mAdRequest;


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AboutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    public AboutFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_about, container, false);
        RelativeLayout l = (RelativeLayout)rootView.findViewById(R.id.adcontent);
        /* 创建Banner广告view
　　		 *  “appid”指在 http://e.qq.com/dev/ 能看到的app唯一字符串
　　		 * 	“广告位 id” 指在 http://e.qq.com/dev/ 生成的数字串，
　　		 * 	并非 appid 或者 appkey
		 */
        mAdView = new AdView(getActivity(), AdSize.BANNER, Constants.QQ_ADVERTISE_APPID, Constants.QQ_ADVERTISE_BANNER_ID);
        l.addView(mAdView);
		/* 广告请求数据，可以设置广告轮播时间，默认为30s  */
        mAdRequest = new AdRequest();
		/* 这个接口的作用是设置广告的测试模式，该模式下点击不扣费
		 * 未发布前请设置testad为true，
		 * 上线的版本请确保设置为false或者去掉这行调用
		 */
        mAdRequest.setTestAd(true);
		/* 设置广告刷新时间，为30~120之间的数字，单位为s*/
        mAdRequest.setRefresh(31);
		/* 设置空广告和首次收到广告数据回调
		 * 调用fetchAd方法后会发起广告请求，广告轮播时不会产生回调
		 */
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onNoAd() {
                Log.i("ccc:", "no");
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
        mAdView.fetchAd(mAdRequest);
        return rootView;
    }


}
