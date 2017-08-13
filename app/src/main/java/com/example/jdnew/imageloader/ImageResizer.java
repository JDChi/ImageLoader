package com.example.jdnew.imageloader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.FileDescriptor;

/**
 * Created by JDNew on 2017/7/3.
 */

public class ImageResizer {

    public ImageResizer(){

    }

    public Bitmap decodeSampleBitmapFromResource(Resources resources
    , int resId , int reqWidth , int reqHeight){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(resources , resId , options);

        options.inSampleSize = calculateInSampleSize(options , reqWidth , reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(resources , resId , options);
    }

    public Bitmap decodeSampleBitmapFromFileDescriptor(FileDescriptor fileDescriptor
                                                       , int reqWidth , int reqHeight
    ){
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFileDescriptor(fileDescriptor , null , options);
        options.inSampleSize = calculateInSampleSize(options , reqWidth , reqHeight);
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFileDescriptor(fileDescriptor , null , options);
    }

    private int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        if(reqWidth == 0 || reqHeight == 0){
            return 1;
        }

        final int height = options.outHeight;
        final int width = options.outWidth;

        int inSampleSize = 1;

        if(height > reqHeight || width > reqWidth){
            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while ((halfHeight / inSampleSize) >= reqHeight
                && (halfWidth / inSampleSize) >= reqWidth){
                inSampleSize = inSampleSize * 2;
            }
        }
        return inSampleSize;
    }
}
