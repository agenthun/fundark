package com.example.xrenginesdkdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;

import com.sensetime.xrengine.XREngineActivity;
import com.unity3d.player.UnityPlayer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class UnityActivity extends XREngineActivity {

    private static final String TAG = UnityActivity.class.getSimpleName();
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private static UnityActivity currentActivity;

    public static void start(Context context, @Nullable String configJson) {
        Intent intent = new Intent(context, UnityActivity.class);
        intent.putExtra("configJson", configJson);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentActivity = this;
        InitSdkConfigFromJson(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Window window = getWindow();
        if (window != null) {
            window.getDecorView().post(() -> InitSdkConfigFromJson(getIntent()));
        }
    }

    @Override
    protected void showNativeActivity(String activity) {
        super.showNativeActivity(activity);
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

//    public void showNativeActivityFmap(String activity) {
//        this.showNativeActivity(activity);
//    }

    /**
     * 初始化config基本配置
     *
     * @param intent
     */
    private void InitSdkConfigFromJson(@Nullable Intent intent) {
        if (intent == null) return;
        String configJson = intent.getStringExtra("configJson");
        if (configJson == null || TextUtils.isEmpty(configJson)) return;
        SendUnityMessage("GameLogic", "InitSdkConfigFromJson", configJson);
    }

    /**
     * 设置用户信息
     *
     * @param json
     */
    public static void SetUserInfoFromJson(@NonNull String json) {
        SendUnityMessage("GameLogic", "SetUserInfoFromJson", json);
    }

    private static void SendUnityMessage(String gameObject, String func, String param) {
        try {
            UnityPlayer.UnitySendMessage(gameObject, func, param);
        } catch (Exception e) {
            Log.e(TAG, "SendUnityMessage: " + e.getMessage());
        }
    }

    @Override
    public void onUnityPlayerUnloaded() {
        super.onUnityPlayerUnloaded();
    }

    @Override
    public void onXREngineQuit() {
        super.onXREngineQuit();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }


}
