package com.dayu.singapp.util;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.airbnb.lottie.BuildConfig;

import static android.opengl.GLES20.*;
import static android.opengl.GLUtils.*;
import static android.opengl.Matrix.*;

/**
 * Created by Ljy on 2018/6/5.
 */

public class TextureHelper {
    private static final String TAG = "TextureHelper";
    public static int loadTexture(Context context, int resourceId){
        final int[] textureObjectIds = new int[1];
        glGenTextures(1, textureObjectIds, 0);//创建一个纹理对象
        if (textureObjectIds[0] == 0){
            if (BuildConfig.DEBUG){
                LogUtil.d(TAG, "Could not generate a new OpenGl texture object");
            }
            return 0;
        }

        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inScaled = false;//告诉android我们想要原始图像数据 而不是这个图像的缩放版本

        //openGl 不能读取
        final Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), resourceId, options);
        if (bitmap == null){
            if (BuildConfig.DEBUG){
                LogUtil.d(TAG, "could not be decoded");
            }

            glDeleteTextures(1, textureObjectIds, 0);
            return 0;
        }
        //GL_TEXTURE2D 表示一个2d纹理  textureObjectIds[0]表示OpenGl 要绑定的纹理对象的id
        glBindTexture(GL_TEXTURE_2D, textureObjectIds[0]);
        //对于缩小的情况使用三线性过滤
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR_MIPMAP_LINEAR);
        //对于放大的情况使用双线性过滤
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        //加载位图数据到openGl 复制到当前绑定的纹理对象
        texImage2D(GL_TEXTURE_2D, 0, bitmap, 0);
        bitmap.recycle();
        glGenerateMipmap(GL_TEXTURE_2D);//生成mip 贴图
        glBindTexture(GL_TEXTURE_2D, 0);//解除绑定
        return textureObjectIds[0];
    }
}
