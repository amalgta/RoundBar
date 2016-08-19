package com.styx.gta.roundbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fly on 19/8/16.
 */
public class RoundBar extends View {
    private int color1,color2,color3;
    private float sMaxRadius;
    private Paint sPaint1,sPaint2,sPaint3;

    private float sStrokeWidth,sPaddingBetweenArcs,sRadius,sPadding;

    private int DEFAULT_COLOR= Color.BLUE;
    public RoundBar(Context context, AttributeSet attrs){
        super(context,attrs);
        TypedArray sAttributes=context.getTheme().obtainStyledAttributes(attrs,R.styleable.RoundBar,0,0);
        try{
            color1=sAttributes.getColor(R.styleable.RoundBar_color1,DEFAULT_COLOR);
            color2=sAttributes.getColor(R.styleable.RoundBar_color2,DEFAULT_COLOR);
            color3=sAttributes.getColor(R.styleable.RoundBar_color3,DEFAULT_COLOR);
        }finally {
            sAttributes.recycle();
        }
        init();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        sStrokeWidth=(.02f)*getMeasuredWidth();
        sPaddingBetweenArcs=40;
        sPadding=sStrokeWidth*2;
        sMaxRadius=getMeasuredWidth()/2-sPadding;
    }
    private Paint makePaint(int sColor, int sAlpha,float sStrokeWidth){
        Paint sPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        sPaint.setStyle(Paint.Style.STROKE);
        sPaint.setStrokeWidth(sStrokeWidth);
        sPaint.setStrokeCap(Paint.Cap.ROUND);
        sPaint.setColor(sColor);
        sRadius=340;
        sPaint.setAlpha(sAlpha);
        sPaint.setShader(new LinearGradient(getMeasuredWidth()/2-(sRadius*4),getMeasuredHeight()/2-(sRadius*4),getMeasuredWidth()/2+(sRadius*4),getMeasuredHeight()/2+(sRadius*4), sColor, sColor+360, Shader.TileMode.MIRROR));
        return sPaint;
    }

    private void init(){
        ;
    }
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(new RectF(getMeasuredWidth()/2-sMaxRadius,getMeasuredHeight()/2-sMaxRadius,getMeasuredWidth()/2+sMaxRadius,getMeasuredHeight()/2+sMaxRadius), 0, 120, false, makePaint(color1,255,sStrokeWidth));
    }
}
