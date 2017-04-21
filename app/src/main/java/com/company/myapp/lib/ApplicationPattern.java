package com.company.myapp.lib;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Set;
import com.google.gson.Gson;

public class ApplicationPattern extends Application {
  private static final String TAG = "APPLICATIONPATTERN";

  protected static ObscuredSharedPreferences sharedPrefs;

  protected static Context context;

  public void onCreate() {
    super.onCreate();
  }

  protected void setSharedPrefs(char[] secret) {
    sharedPrefs =
        new ObscuredSharedPreferences(
            secret, this, PreferenceManager.getDefaultSharedPreferences(this));
  }

  public static void purgeSharedPreferences() {
    ObscuredSharedPreferences.Editor editor = sharedPrefs.edit();

    editor.clear().apply();
  }

  public static void deleteSharedPreference(String key) {
    ObscuredSharedPreferences.Editor editor = sharedPrefs.edit();

    editor.remove(key).apply();
  }

  public static String getSharedPreferenceString(String key) {
    return sharedPrefs.getString(key, "");
  }

  public static JSONObject getSharedPreferenceJSONObject(String key) {
    String stringified = sharedPrefs.getString(key, null);

    try {
      JSONObject objectifiedJSON = new JSONObject(stringified);
      return objectifiedJSON;
    } catch (JSONException e) {
      Log.e(TAG, "OBJECT IN SHAREDPREFS COULD NOT BE PARSED INTO JSON");
    }

    return null;
  }

  public static long getSharedPreferenceLong(String key) {
    return sharedPrefs.getLong(key, Long.MIN_VALUE);
  }

  public static int getSharedPreferenceInt(String key) {
    return sharedPrefs.getInt(key, Integer.MIN_VALUE);
  }

  public static boolean getSharedPreferenceBoolean(String key) {
    return sharedPrefs.getBoolean(key, false);
  }

  public static float getSharedPreferenceFloat(String key) {
    return sharedPrefs.getFloat(key, Float.NaN);
  }

  public static double getSharedPreferenceDouble(String key) {
    return Double.longBitsToDouble(sharedPrefs.getLong(key, Double.doubleToLongBits(0d)));
  }

  public static Set<String> getSharedPreferenceStringSet(String key) {
    return sharedPrefs.getStringSet(key, null);
  }

  public static void putSharedPreferenceString(String key, String value) {
    ObscuredSharedPreferences.Editor editor = sharedPrefs.edit();

    editor.putString(key, value);

    editor.apply();
  }

  public static void putSharedPreferenceJSONObject(String key, JSONObject value) {
    ObscuredSharedPreferences.Editor editor = sharedPrefs.edit();

    String stringified = value.toString();

    editor.putString(key, stringified);

    editor.apply();
  }

  public static void putSharedPreferencesObject(String key, Object value) {
    Gson gson = new Gson();

    String item = gson.toJson(value);

    putSharedPreferenceString(key, item);
  }

  public static void putSharedPreferenceLong(String key, long value) {
    ObscuredSharedPreferences.Editor editor = sharedPrefs.edit();

    editor.putLong(key, value);

    editor.apply();
  }

  public static void putSharedPreferenceInt(String key, int value) {
    ObscuredSharedPreferences.Editor editor = sharedPrefs.edit();

    editor.putInt(key, value);

    editor.apply();
  }

  public static void putSharedPreferenceBoolean(String key, boolean value) {
    ObscuredSharedPreferences.Editor editor = sharedPrefs.edit();

    editor.putBoolean(key, value);

    editor.apply();
  }

  public static void putSharedPreferenceFloat(String key, float value) {
    ObscuredSharedPreferences.Editor editor = sharedPrefs.edit();

    editor.putFloat(key, value);

    editor.apply();
  }

  public static void putSharedPreferenceStringSet(String key, Set<String> value) {
    ObscuredSharedPreferences.Editor editor = sharedPrefs.edit();

    editor.putStringSet(key, value);

    editor.apply();
  }

  public static void putSharedPreferenceDouble(String key, double value) {
    ObscuredSharedPreferences.Editor editor = sharedPrefs.edit();

    editor.putLong(key, Double.doubleToRawLongBits(value));

    editor.apply();
  }
}