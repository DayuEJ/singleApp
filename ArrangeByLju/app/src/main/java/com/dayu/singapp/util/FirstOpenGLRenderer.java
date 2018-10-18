package com.dayu.singapp.util;
import static android.opengl.GLES20.*;

import android.content.Context;
import android.opengl.GLSurfaceView;

import com.airbnb.lottie.BuildConfig;
import com.dayu.singapp.R;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Ljy on 2018/4/12.
 */

public class FirstOpenGLRenderer implements GLSurfaceView.Renderer {
    private static final int BYTES_PER_FLOAT = 4;
    private static final int POSITION_COMPONENT_COUNT = 2;
    private static FloatBuffer vertexData;
    private static final String U_COLOR = "u_color";
    private static final String A_POSITION = "a_Position";
    private int aPositionLocation;
    private int uColorLocation;
    private Context mContext;
    private int mProgram;
    private static float[] tablePoint = {
            //Triangle 1
            0f, 0f,
            9f, 14f,
            0f, 14f,
            //Triangle 2
            0f, 0f,
            9f, 0f,
            9f, 14f,
            //line 1
            0f, 7f,
            9f, 7f,
            //mallets
            4.5f, 2f,
            4.5f, 12f
    };

    public FirstOpenGLRenderer(Context context){
        this.mContext = context;
        vertexData = ByteBuffer
                .allocate(tablePoint.length * BYTES_PER_FLOAT)//分配一块本地内存 不会被垃圾回收机制回收  每个浮点数占4个字节
                .order(ByteOrder.nativeOrder())//保证本地字节序组织内容
                .asFloatBuffer();
        vertexData.put(tablePoint);//把数据从虚拟机复制到本地内存
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        //当Surface被创建时 GLSurfaceView会调用这个方法
        //当设备被唤醒或者从其他Activity切换回来 onResume()也会被调用 这意味着当程序运行时该方法可能会被多次调用
        glClearColor(1.0f, 0f, 0f, 0f);// red green blue alpha
        importShaderCode();
        if (BuildConfig.DEBUG){
            ShaderHelper.checkProgramVaild(mProgram);
        }
        glUseProgram(mProgram);
        uColorLocation = glGetUniformLocation(mProgram, U_COLOR);
        aPositionLocation = glGetAttribLocation(mProgram, A_POSITION);

        vertexData.position(0);//设置缓冲区的指针
        glVertexAttribPointer(aPositionLocation, POSITION_COMPONENT_COUNT, GL_FLOAT, false, 0, vertexData);
        glEnableVertexAttribArray(aPositionLocation);
    }

    private void importShaderCode(){
        String vertexShaderSource = TextResourceReaderUtil.readTextFileFromRecource(mContext, R.raw.simple_vertex_shader);
        String fragmentShaderSource = TextResourceReaderUtil.readTextFileFromRecource(mContext, R.raw.simple_fragment_shader);
        int vertexShader = ShaderHelper.complieVertexShader(vertexShaderSource);
        int fragmentShader = ShaderHelper.complieFragmentShader(fragmentShaderSource);
        mProgram = ShaderHelper.linkProgram(vertexShader, fragmentShader);
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //每次Surface 尺寸发生变化时 会被调用
        glViewport(0, 0, width, height);//告诉opengl Surface渲染尺寸的大小  （0， 0）代表视口左上角点的坐标 width  height  代表视口宽度和高度
    }

    @Override
    public void onDrawFrame(GL10 gl){
        //当绘制一帧时  会被调用 这个方法返回后 渲染的缓冲区会被交换并显示到屏幕上 如果什么都没有画  可能会出现闪烁效果
        glClear(GL_COLOR_BUFFER_BIT);//清空屏幕  并用之前glClear() 定义的颜色去填充
        glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);//设置颜色 red green blue alpha
        glDrawArrays(GL_LINES, 6, 2);//第6个顶点以后的两个点

        glUniform4f(uColorLocation, 0.0f, 0.0f, 1.0f, 1.0f);
        glDrawArrays(GL_POINTS, 8, 1);//第8个顶点以后的一个点

        glUniform4f(uColorLocation, 1.0f, 0.0f, 0.0f, 1.0f);
        glDrawArrays(GL_POINTS, 9, 1);//第9个顶点以后的一个点

    }
}
