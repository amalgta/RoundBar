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
    private Paint sPaint1,sPaint2,sPaint3;

    private float sStrokeWidth;

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
    private Paint makePaint(int sColor){
        return makePaint(sColor,255);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        sStrokeWidth=(.04f)*getMeasuredWidth();
        sPaint1=makePaint(color1);
        sPaint2=makePaint(color2);
        sPaint3=makePaint(color3);
    }
    private Paint makePaint(int sColor, int sShadow){
        Paint sPaint=new Paint(Paint.ANTI_ALIAS_FLAG);
        sPaint.setStyle(Paint.Style.STROKE);
        sPaint.setStrokeWidth(sStrokeWidth);
        sPaint.setStrokeCap(Paint.Cap.ROUND);
        sPaint.setColor(sColor);
        float sRadius=340;
        sPaint.setAlpha(sShadow);
        sPaint.setShader(new LinearGradient(getMeasuredWidth()/2-(sRadius*4),getMeasuredHeight()/2-(sRadius*4),getMeasuredWidth()/2+(sRadius*4),getMeasuredHeight()/2+(sRadius*4), sColor, sColor+360, Shader.TileMode.MIRROR));
        return sPaint;
    }
    private void init(){
    }
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Outer Circle
        float sRadius=getMeasuredWidth()/2;
        canvas.drawArc(new RectF(getMeasuredWidth()/2-sRadius,getMeasuredHeight()/2-sRadius,getMeasuredWidth()/2+sRadius,getMeasuredHeight()/2+sRadius), 0, 360, false, makePaint(Color.BLACK,40));
        canvas.drawArc(new RectF(getMeasuredWidth()/2-sRadius,getMeasuredHeight()/2-sRadius,getMeasuredWidth()/2+sRadius,getMeasuredHeight()/2+sRadius), 270, 70+90, false, sPaint1);
        sRadius-=sStrokeWidth;
        canvas.drawArc(new RectF(getMeasuredWidth()/2-sRadius,getMeasuredHeight()/2-sRadius,getMeasuredWidth()/2+sRadius,getMeasuredHeight()/2+sRadius), 0, 360, false, makePaint(Color.BLACK,40));
        canvas.drawArc(new RectF(getMeasuredWidth()/2-sRadius,getMeasuredHeight()/2-sRadius,getMeasuredWidth()/2+sRadius,getMeasuredHeight()/2+sRadius), 270, 80+90, false, sPaint2);
        sRadius-=sStrokeWidth;
        canvas.drawArc(new RectF(getMeasuredWidth()/2-sRadius,getMeasuredHeight()/2-sRadius,getMeasuredWidth()/2+sRadius,getMeasuredHeight()/2+sRadius), 0, 360, false, makePaint(Color.BLACK,40));
        canvas.drawArc(new RectF(getMeasuredWidth()/2-sRadius,getMeasuredHeight()/2-sRadius,getMeasuredWidth()/2+sRadius,getMeasuredHeight()/2+sRadius), 270, 60+90, false, sPaint3);

    }
}
