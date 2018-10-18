package com.dayu.singapp.util;
import android.os.Build;

import com.airbnb.lottie.BuildConfig;
import com.dayu.singapp.activeandroid.Log;

import static android.opengl.GLES20.*;
import static android.opengl.GLUtils.*;
import static android.opengl.Matrix.*;
/**
 * Created by Ljy on 2018/4/12.
 */

public class ShaderHelper {
    private static final String TAG = "ShaderHelper";

    public static int complieVertexShader(String shaderCode){
        return compileShader(GL_VERTEX_SHADER, shaderCode);
    }

    public static int complieFragmentShader(String shaderCode){
        return compileShader(GL_FRAGMENT_SHADER, shaderCode);
    }

    private static int compileShader(int type, String shaderCode){
        final int shaderObjectId = glCreateShader(type);//创建一个新的着色器对象
        if (shaderObjectId == 0){
            glDeleteShader(shaderObjectId);
            return 0;//0相当于java null
        }
        glShaderSource(shaderObjectId, shaderCode);
        glCompileShader(shaderObjectId);
        final int [] compileStatus = new int[1];
        glGetShaderiv(shaderObjectId, GL_COMPILE_STATUS, compileStatus, 0);//读取与shaderObjectId关联的编译状态 并把它写入compileStatus的第0个元素
        if (BuildConfig.DEBUG){
            LogUtil.d("ljy", "results of compiling source" + "\n" + shaderCode + "\n:" + glGetShaderInfoLog(shaderObjectId));
        }
        return shaderObjectId;
    }

    //将着色器链接到OpenGl
    public static int linkProgram(int vertexShaderId, int fragmentShaderId){
        final  int programObjectId = glCreateProgram();
        if (programObjectId == 0){
            if (BuildConfig.DEBUG){
                LogUtil.d("ljy", "Could not create new program");
            }
            return 0;
        }
        //将顶点着色器和片段着色器附加到程序上
        glAttachShader(programObjectId, vertexShaderId);
        glAttachShader(programObjectId, fragmentShaderId);

        glLinkProgram(programObjectId);
        final int[] linkStatus = new int[1];
        glGetProgramiv(programObjectId, GL_LINK_STATUS, linkStatus, 0);
        if (BuildConfig.DEBUG){
            LogUtil.d("ljy", "Result of linking program：\n" + glGetProgramInfoLog(programObjectId));
        }

        if (linkStatus[0] == 0){
            glDeleteProgram(programObjectId);
        }

        return programObjectId;
    }

    public static boolean checkProgramVaild(int programId){
        glValidateProgram(programId);
        final int[] vaildStatus = new int[1];
        glGetProgramiv(programId, GL_VALIDATE_STATUS, vaildStatus, 0);
        if (BuildConfig.DEBUG){
            LogUtil.d("ljy", "Results of vaildating program" + vaildStatus[0] + "\n" + glGetProgramInfoLog(programId));
        }
        return vaildStatus[0] != 0;
    }
}
