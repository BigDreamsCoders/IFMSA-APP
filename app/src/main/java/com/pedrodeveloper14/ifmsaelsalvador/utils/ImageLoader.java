package com.pedrodeveloper14.ifmsaelsalvador.utils;

import android.text.TextUtils;
import android.widget.ImageView;

import com.pedrodeveloper14.ifmsaelsalvador.R;
import com.squareup.picasso.Picasso;

public class ImageLoader {

    /**
     * Method that load image with {@link Picasso}
     *
     * @param url       String that contains an url of the image
     * @param imageView View where the image will be loaded
     */
    public static void loadImage(String url, ImageView imageView) {
        try {
            if (!TextUtils.isEmpty(url)) {
                Picasso
                        .get()
                        .load(url)
                        .error(R.drawable.ic_image)
                        .into(imageView);
            } else {
                Picasso
                        .get()
                        .load(R.drawable.ic_image)
                        .error(R.drawable.ic_image)
                        .into(imageView);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
