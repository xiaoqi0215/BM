package com.qixiao.bm.test;

import android.animation.TypeEvaluator;


public class PointEvaluator implements TypeEvaluator {
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
       MyPoint sPoint = (MyPoint)startValue;
       MyPoint ePoint = (MyPoint) endValue;


        int x = (int) (sPoint.getX() + fraction * (ePoint.getX() - sPoint.getX()));
        int y = (int )(sPoint.getY()+fraction * (ePoint.getY() - sPoint.getY()));

        MyPoint point = new MyPoint(x,y);
        return point;
    }
}
