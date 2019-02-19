package com.qixiao.bm.test;


import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.qixiao.bm.R;


public class MyCircle extends View {
    int radius;
    Paint paint;
    MyPoint point;

    public MyCircle(Context context) {
        super(context);
    }

    public MyCircle(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }


    public MyCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint();
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.MyCircle);
        radius = typedArray.getInt(R.styleable.MyCircle_radius,1);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (point == null){
            point = new MyPoint(50,50);
            int x = point.getX();
            int y = point.getY();
            paint.setColor(Color.RED);
            canvas.drawCircle(x,y,radius,paint);
            ValueAnimator animator = ValueAnimator.ofObject(
                    new PointEvaluator(),new MyPoint(50,50),new MyPoint(1000,1000));

            animator.setDuration(1000);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    point = (MyPoint) animation.getAnimatedValue();
                    invalidate();
                }
            });
            animator.start();
        }else{
            int x = point.getX();
            int y = point.getY();
            canvas.drawCircle(x,y,radius,paint);
        }
    }
}
