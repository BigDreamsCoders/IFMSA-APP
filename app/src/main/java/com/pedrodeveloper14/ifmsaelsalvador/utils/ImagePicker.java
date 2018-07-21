package com.pedrodeveloper14.ifmsaelsalvador.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;

public class ImagePicker {

    public static void pickImage(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        System.out.println(getMaxSize(activity));
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("outputX", getMaxSize(activity));
        intent.putExtra("outputY", getMaxSize(activity));
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("return-data", true);
        activity.startActivityForResult(intent, 1);
    }

    private static int getMaxSize(Context context) {
        Uri uri = ContactsContract.DisplayPhoto.CONTENT_MAX_DIMENSIONS_URI;
        final String[] projection = new String[]{ContactsContract.DisplayPhoto.DISPLAY_MAX_DIM};
        try (Cursor c = context.getContentResolver().query(uri, projection, null, null, null)) {
            c.moveToFirst();
            return c.getInt(0);
        }
    }

}
