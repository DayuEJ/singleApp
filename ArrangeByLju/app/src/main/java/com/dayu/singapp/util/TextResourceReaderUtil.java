package com.dayu.singapp.util;

import android.content.Context;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Ljy on 2018/4/12.
 */

public class TextResourceReaderUtil {
    public static String readTextFileFromRecource(Context context, int recourceId){
        StringBuilder sb = new StringBuilder();
        try {
            //InputStream --> InputStreamReader --> BufferedReader
            InputStream input = context.getResources().openRawResource(recourceId);
            InputStreamReader reader = new InputStreamReader(input);
            BufferedReader bufferReader = new BufferedReader(reader);
            String nextLine;
            while ((nextLine = bufferReader.readLine()) != null){
                sb.append(nextLine);
                sb.append("\n");
            }
        }catch (Exception e){

        }
        return sb.toString();
    }
}
