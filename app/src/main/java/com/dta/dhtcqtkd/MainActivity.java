package com.dta.dhtcqtkd;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import android.widget.ImageView;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;
import android.net.Uri;
import java.util.Timer;
import java.util.TimerTask;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class MainActivity extends AppCompatActivity {
	private Timer _timer = new Timer();
	
	private FloatingActionButton _fab;
	
	private LinearLayout linear3;
	private LinearLayout linear4;
	private LinearLayout linear6;
	private LinearLayout linear8;
	private LinearLayout linear9;
	private ImageView icon_ufba;
	private Button nut_main_ufba;
	private Button thuvien_ufba;
	private Button congsinhvien;
	private Button thoatungdung;
	private TextView textview1;
	
	private Intent thu_vien_ufba = new Intent();
	private Intent web_chinh_thuc_ufba = new Intent();
	private TimerTask main_web_ufba;
	private RequestNetwork kiemtramang;
	private RequestNetwork.RequestListener _kiemtramang_request_listener;
	private AlertDialog.Builder thong_bao_internet;
	private TimerTask thuvien;
	private TimerTask congsinhvien_ufba;
	private Intent congsinhvien_ufba_i = new Intent();
	private TimerTask thoat_ung_dung;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		_fab = (FloatingActionButton) findViewById(R.id._fab);
		
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		linear4 = (LinearLayout) findViewById(R.id.linear4);
		linear6 = (LinearLayout) findViewById(R.id.linear6);
		linear8 = (LinearLayout) findViewById(R.id.linear8);
		linear9 = (LinearLayout) findViewById(R.id.linear9);
		icon_ufba = (ImageView) findViewById(R.id.icon_ufba);
		nut_main_ufba = (Button) findViewById(R.id.nut_main_ufba);
		thuvien_ufba = (Button) findViewById(R.id.thuvien_ufba);
		congsinhvien = (Button) findViewById(R.id.congsinhvien);
		thoatungdung = (Button) findViewById(R.id.thoatungdung);
		textview1 = (TextView) findViewById(R.id.textview1);
		kiemtramang = new RequestNetwork(this);
		thong_bao_internet = new AlertDialog.Builder(this);
		
		nut_main_ufba.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				main_web_ufba = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								web_chinh_thuc_ufba.setClass(getApplicationContext(), MainChinhThucActivity.class);
								startActivity(web_chinh_thuc_ufba);
							}
						});
					}
				};
				_timer.schedule(main_web_ufba, (int)(500));
			}
		});
		
		thuvien_ufba.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				thuvien = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								thu_vien_ufba.setClass(getApplicationContext(), ThuvienActivity.class);
								startActivity(thu_vien_ufba);
							}
						});
					}
				};
				_timer.schedule(thuvien, (int)(500));
			}
		});
		
		congsinhvien.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				congsinhvien_ufba = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								congsinhvien_ufba_i.setClass(getApplicationContext(), CongsinhvienActivity.class);
								startActivity(congsinhvien_ufba_i);
							}
						});
					}
				};
				_timer.schedule(congsinhvien_ufba, (int)(500));
			}
		});
		
		thoatungdung.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				thoat_ung_dung = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								_thoat_va_xoa_du_lieu();
							}
						});
					}
				};
				_timer.schedule(thoat_ung_dung, (int)(1500));
			}
		});
		
		_kiemtramang_request_listener = new RequestNetwork.RequestListener() {
			@Override
			public void onResponse(String _param1, String _param2, HashMap<String, Object> _param3) {
				final String _tag = _param1;
				final String _response = _param2;
				final HashMap<String, Object> _responseHeaders = _param3;
				
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
		kiemtramang.startRequestNetwork(RequestNetworkController.GET, "https://www.google.com/", "Test_Internet", _kiemtramang_request_listener);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		super.onActivityResult(_requestCode, _resultCode, _data);
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _thoat_va_xoa_du_lieu () {
		((ActivityManager)MainActivity.this.getSystemService("activity")).clearApplicationUserData();
	}
	
}