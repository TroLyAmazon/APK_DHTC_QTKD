package com.dta.dhtcqtkd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.app.AlertDialog;
import android.content.DialogInterface;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class MainChinhThucActivity extends AppCompatActivity {
	
	private LinearLayout linear1;
	private ScrollView vscroll1;
	private WebView webview;
	
	private RequestNetwork kiemtramang;
	private RequestNetwork.RequestListener _kiemtramang_request_listener;
	private AlertDialog.Builder thong_bao_internet;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main_chinh_thuc);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		vscroll1 = (ScrollView) findViewById(R.id.vscroll1);
		webview = (WebView) findViewById(R.id.webview);
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setSupportZoom(true);
		kiemtramang = new RequestNetwork(this);
		thong_bao_internet = new AlertDialog.Builder(this);
		
		webview.setWebViewClient(new WebViewClient() {
			@Override
			public void onPageStarted(WebView _param1, String _param2, Bitmap _param3) {
				final String _url = _param2;
				
				super.onPageStarted(_param1, _param2, _param3);
			}
			
			@Override
			public void onPageFinished(WebView _param1, String _param2) {
				final String _url = _param2;
				
				super.onPageFinished(_param1, _param2);
			}
		});
		
		_kiemtramang_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				SketchwareUtil.showMessage(getApplicationContext(), _response);
			}
			
			@Override
			public void onErrorResponse(String _param1, String _param2) {
				final String _tag = _param1;
				final String _message = _param2;
				thong_bao_internet.setTitle("Hệ Thống:");
				thong_bao_internet.setMessage("Vui Lòng Kiểm Tra Internet");
				thong_bao_internet.setPositiveButton("Thoát Ứng Dụng", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						_thoat_va_xoa_du_lieu();
					}
				});
				thong_bao_internet.setCancelable(false);
				thong_bao_internet.create().show();
			}
		};
	}
	
	private void initializeLogic() {
		webview.getSettings().setLoadWithOverviewMode(true); webview.getSettings().setUseWideViewPort(true); final WebSettings webSettings = webview.getSettings(); final String newUserAgent; newUserAgent = "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2228.0 Safari/537.36"; webSettings.setUserAgentString(newUserAgent);
		kiemtramang.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com/", "Test_Internet", _kiemtramang_request_listener);
		webview.loadUrl("https://ufba.edu.vn/");
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	@Override
	public void onBackPressed() {
		if (webview.canGoBack()) {
			webview.goBack();
		}
		else {
			finish();
		}
	}
	public void _thoat_va_xoa_du_lieu () {
		((ActivityManager)MainChinhThucActivity.this.getSystemService("activity")).clearApplicationUserData();
	}
	
}