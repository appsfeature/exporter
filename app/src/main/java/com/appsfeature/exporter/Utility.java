package com.appsfeature.exporter;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import androidx.core.content.FileProvider;

import java.io.File;

public class Utility {

    public static Uri getUriFromFile(Context context, File file) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".provider", file);
        } else {
            return Uri.fromFile(file);
        }
    }

}
