package com.zqc.opencc.android.lib;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by zhangqichuan on 29/2/16.
 */
@SuppressWarnings("ResultOfMethodCallIgnored")
public class ChineseConverter {
    private static final String DATA_FOLDER = "openccdata";
    private static final String LAST_DATA_FILE = "zFinished3";
    private static boolean initialized = false;

    /***
     * @param text           the text to be converted to
     * @param conversionType the conversion type
     * @param context        android context
     * @return the converted text
     */
    public static String convert(String text, ConversionType conversionType, Context context) {
        initialize(context);
        return convert(text, conversionType.getValue(), conversionType.ordinal(), getDataFolder(context).getAbsolutePath());
    }

    /***
     * Initialize converters to make later uses fast
     * @param context         android context
     * @param force           force initialize
     * @param conversionTypes the converters to be initialize
     */
    public static void initializeConverters(Context context, boolean force, ConversionType... conversionTypes) {
        initialize(context);
        for (ConversionType type : conversionTypes) {
            initializeConvert(type.getValue(), type.ordinal(), getDataFolder(context).getAbsolutePath(), force);
        }
    }

    /***
     * Initialize converters to make later uses fast
     * @param context         android context
     * @param conversionTypes the converters to be initialize
     */
    public static void initializeConverters(Context context, ConversionType... conversionTypes) {
        initializeConverters(context, false, conversionTypes);
    }

    private static File getDataFolder(Context context) {
        return new File(context.getFilesDir() + File.separator + DATA_FOLDER);
    }

    /***
     * Clear the dictionary data folder, only call this method when update the dictionary data.
     * @param context android context
     */
    public static void clearDictDataFolder(Context context) {
        try {
            deleteRecursive(getDataFolder(context));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory()) {
            File[] files = fileOrDirectory.listFiles();
            if (files != null) {
                for (File child : files) {
                    deleteRecursive(child);
                }
            }
        }
        fileOrDirectory.delete();
    }

    private static native String convert(String text, String configFile, int ordinal, String absoluteDataFolderPath);

    private static native void initializeConvert(String configFile, int ordinal, String absoluteDataFolderPath, boolean force);

    public static void initialize(Context context) {
        if (initialized) {
            return;
        }
        File lastDataFile = new File(context.getFilesDir() + DATA_FOLDER + File.separator + LAST_DATA_FILE);
        if (lastDataFile.exists()) {
            return;
        }
        if (!copyFolder(context)) {
            clearDictDataFolder(context);
            initialize(context);
        } else {
            initialized = true;
        }
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private static boolean copyFolder(Context context) {
        File fileFolderOnDisk = new File(context.getFilesDir() + File.separator + DATA_FOLDER);
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list(DATA_FOLDER);
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        if (files != null) {
            for (String filename : files) {
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = assetManager.open(DATA_FOLDER + File.separator + filename);
                    if (!fileFolderOnDisk.exists()) {
                        fileFolderOnDisk.mkdirs();
                    }
                    File outFile = new File(fileFolderOnDisk.getAbsolutePath(), filename);
                    if (!outFile.exists()) {
                        outFile.createNewFile();
                    }
                    out = new FileOutputStream(outFile);
                    copyFile(in, out);
                } catch (IOException e) {
                    Log.e("tag", "Failed to copy asset file: " + filename, e);
                    return false;
                } finally {
                    if (in != null) {
                        try {
                            in.close();
                        } catch (IOException e) {
                            // NOOP
                        }
                    }
                    if (out != null) {
                        try {
                            out.close();
                        } catch (IOException e) {
                            // NOOP
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }


    private static void copyFile(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);
        }
    }

    static {
        System.loadLibrary("ChineseConverter");
    }
}
