package com.company.myapp;

import com.company.myapp.lib.ApplicationPattern;
import com.company.myapp.lib.HTTPManager;
import android.content.Context;
import android.util.DisplayMetrics;
import android.graphics.drawable.Drawable;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Canvas;
import android.os.Environment;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Application extends ApplicationPattern {
  private static final HTTPManager httpManager = new HTTPManager();

  @Override
  public void onCreate() {
    super.onCreate();

    context = getApplicationContext();

    char[] secret = {
      '3', 'd', '4', 'e', 'b', '0', 'd', 'f', '7', 'b', 'f', '0', '3', 'c', '4', '7', '4', '1', '8',
      'e', '4', 'e', '1', '6', '5', '2', 'a', '3', '2', '0', 'e', 'd'
    };

    setSharedPrefs(secret);
  }

  public static HTTPManager getHttpManager() {
    return httpManager;
  }

  public static float dpToPx(float dp, Context context) {
    return dp
        * ((float) context.getResources().getDisplayMetrics().densityDpi
            / DisplayMetrics.DENSITY_DEFAULT);
  }

  public static float pxToDp(float px, Context context) {
    return px
        / ((float) context.getResources().getDisplayMetrics().densityDpi
            / DisplayMetrics.DENSITY_DEFAULT);
  }

  public static File drawableToFile(Drawable drawable, String fileName) {
    if (drawable == null || fileName == null) {
      Log.i("DRAWABLE-TO-FILE", "Given Drawable was null, so returning null File");
      return null;
    }

    Bitmap bitmap = null;

    if (drawable instanceof BitmapDrawable) {
      BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
      if (bitmapDrawable.getBitmap() != null) {
        bitmap = bitmapDrawable.getBitmap();
      }
    } else {
      // Create a Bitmap from the drawable
      bitmap =
          Bitmap.createBitmap(
              drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
      Canvas canvas = new Canvas(bitmap);
      drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
      drawable.draw(canvas);
    }

    Bitmap.CompressFormat format;

    if (fileName.length() >= 5 && fileName.substring(fileName.length() - 3) == "jpg") {
      format = Bitmap.CompressFormat.JPEG;
    } else {
      format = Bitmap.CompressFormat.PNG;
    }

    File directory =
        new File(Environment.getExternalStorageDirectory() + File.separator + "images");

    File file = new File(directory, fileName);

    FileOutputStream outputStream = null;

    try {
      outputStream = new FileOutputStream(file);
      bitmap.compress(format, 100, outputStream);
      outputStream.close();
      return file;
    } catch (IOException e) {
      Log.e("DRAWABLE-TO-FILE", "Unable to save the provided drawable to disk.", e);
      if (outputStream != null) {
        try {
          outputStream.close();
        } catch (IOException e1) {
          Log.e("DRAWABLE-TO-FILE", "Unable to close file output stream.", e);
        }
      }
      return null;
    }
  }
}