package com.library.util;

import android.content.Context;
import android.util.Log;

/**
 * Created by sung on 2017-10-20.
 */

public class DLog {
    static final String TAG = "Dlog";
    static Context mContext=null;

    public static void setContext(Context context) {
        mContext = context;
    }

    /**
     * Log Level Error
     **/
    public static final void e(String message) {

        String log = buildLogMsg(message);
        Log.e(TAG, log);
    }

    /**
     * Log Level Error
     **/
    public static final void e(Exception e) {
        String log = buildLogMsg(e);
        Log.e(TAG, log);

    }

    /**
     * Log Level Warning
     **/
    public static final void w(String message) {
        String log = buildLogMsg(message);
        Log.w(TAG, log);

    }

    /**
     * Log Level Information
     **/
    public static final void i(String message) {
        String log = buildLogMsg(message);
        Log.i(TAG, log);

    }

    /**
     * Log Level Debug
     **/
    public static final void d(String message) {
        String log = buildLogMsg(message);
        Log.d(TAG, log);


    }
    /**
     * Log Level Error
     **/
    public static final void d(Exception e) {
        String log = buildLogMsg(e);
        Log.d(TAG, log);

    }


    //
    public static String buildLogMsg(String message) {

        try {
            StackTraceElement ste = Thread.currentThread().getStackTrace()[4];
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(ste.getFileName().replace(".java", ""));
            sb.append("::");
            sb.append(ste.getMethodName());
            sb.append("] ");
            sb.append(message);
            sb.append("(line:" + ste.getLineNumber() + ")");
            return sb.toString();

        } catch (Exception e) {
            Log.e(TAG, " : " + e.toString());
        }

        return " :: " + message;
    }

    //
    public static String buildLogMsg(Exception e) {

        try {
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append(e.getStackTrace()[0].getFileName().replace(".java", ""));
            sb.append("::");
            sb.append(e.getStackTrace()[0].getMethodName());
            sb.append("] ");
            sb.append(e.toString());
            sb.append("(line:" + e.getStackTrace()[0].getLineNumber() + ")");
            return sb.toString();

        } catch (Exception ee) {
            Log.e(TAG, " : " + ee.toString());
        }

        return "::" + e.toString();
    }
}
