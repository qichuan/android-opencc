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
public class ChineseConverter {

    /***
     * @param text           the text to be converted to
     * @param conversionType the conversion type
     * @param context        android context
     * @return the converted text
     */
    public static String convert(String text, ConversionType conversionType, Context context) {
        File lastDataFile = new File(context.getFilesDir() + "/openccdata/zFinished2");
        if (!lastDataFile.exists()) {
            initialize(context);
        }
        File dataFolder = new File(context.getFilesDir() + "/openccdata");
        return convert(text, conversionType.getValue(), dataFolder.getAbsolutePath());
    }

    /***
     * Clear the dictionary data folder, only call this method when update the dictionary data.
     * @param context
     */
    public static void clearDictDataFolder(Context context){
        File dataFolder = new File(context.getFilesDir() + "/openccdata");
        deleteRecursive(dataFolder);
    }

    private static void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
    }

    private static native String convert(String text, String configFile, String absoluteDataFolderPath);

    private static void initialize(Context context) {
        copyFolder("openccdata", context);
    }

    private static void copyFolder(String folderName, Context context) {
        File fileFolderOnDisk = new File(context.getFilesDir() + "/" + folderName);
        AssetManager assetManager = context.getAssets();
        String[] files = null;
        try {
            files = assetManager.list(folderName);
        } catch (IOException e) {
            Log.e("tag", "Failed to get asset file list.", e);
        }
        if (files != null) {
            for (String filename : files) {
                InputStream in = null;
                OutputStream out = null;
                try {
                    in = assetManager.open(folderName + "/" + filename);
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
        }
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
