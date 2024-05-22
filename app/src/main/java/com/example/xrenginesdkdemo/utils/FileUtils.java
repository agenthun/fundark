package com.example.xrenginesdkdemo.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @project XREngineSDKDemo
 * @authors Henry Hu
 * @date 2022/4/22 14:59.
 */
public class FileUtils {
    private static final String TAG = FileUtils.class.getSimpleName();

    public static @Nullable
    String getFromAssets(@NonNull Context context, @NonNull String fileName) {
        InputStreamReader inputReader = null;
        BufferedReader bufReader = null;
        try {
            inputReader = new InputStreamReader(context.getResources().getAssets().open(fileName));
            bufReader = new BufferedReader(inputReader);
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = bufReader.readLine()) != null)
                result.append(line);
            return result.toString();
        } catch (Exception e) {
            Log.e(TAG, "getFromAssets: " + e.getMessage());
            return null;
        } finally {
            if (inputReader != null) {
                try {
                    inputReader.close();
                } catch (IOException e) {
                    Log.e(TAG, "getFromAssets: " + e.getMessage());
                }
            }
            if (bufReader != null) {
                try {
                    bufReader.close();
                } catch (IOException e) {
                    Log.e(TAG, "getFromAssets: " + e.getMessage());
                }
            }
        }
    }
}
