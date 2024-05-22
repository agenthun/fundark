package com.example.xrenginesdkdemo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.xrenginesdkdemo.utils.FileUtils;
import com.silencefly96.fundark.R;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button mButton = findViewById(R.id.goto_unity);
        mButton.setOnClickListener(v -> UnityActivity.start(MainActivity.this, provideConfigJson()));

        TextView textVersion = findViewById(R.id.text_version);
        textVersion.setText("Version:" + getVersionName());
        Log.d("MainActivity", "hun.test, onServiceDisconnected called since the service is lost or stop!");
    }

    private String provideConfigJson() {
        return FileUtils.getFromAssets(this, "ar_config.json");
    }

    private String getVersionName() {
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            if (!TextUtils.isEmpty(packageInfo.versionName)) {
                versionName = packageInfo.versionName;
            }
        } catch (PackageManager.NameNotFoundException | Resources.NotFoundException e) {
            Log.e(TAG, "getVersionName: " + e.getMessage());
        }
        return versionName;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

}
