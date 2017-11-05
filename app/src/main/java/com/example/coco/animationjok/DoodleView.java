package com.example.coco.animationjok;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by coco on 2017/11/3.
 */

public class DoodleView extends AppCompatImageView {
    private final String TAG = "DoodleView";
    private Paint mPaint;
    private float paintYold;
    private float paintXold;
    Canvas canvas;
    Bitmap bitmap;

    public DoodleView(Context context) {
        super(context);
        init(null, 0);
    }

    public DoodleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public DoodleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {

        mPaint = new Paint();
        mPaint.setARGB(255, 255, 0, 0);
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(20);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStyle(Paint.Style.STROKE);

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            Log.d(TAG, "MotionEvent:" + "ACTION_DOWN");
            paintXold = event.getX();
            paintYold = event.getY();
            canvas.drawPoint(paintXold, paintYold, mPaint);
            setImageBitmap(bitmap);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {

            Log.d(TAG, "MotionEvent:" + "ACTION_MOVE");
            float paintX = event.getX();
            float paintY = event.getY();
            Log.d(TAG, "paintY:" + paintY);
            Log.d(TAG, "paintX:" + paintX);
            if (paintX != paintXold || paintY != paintYold) {
                canvas.drawLine(paintXold, paintYold, paintX, paintY, mPaint);
                Log.d(TAG, "draw:" + "line");
                setImageBitmap(bitmap);
            }
            paintXold = event.getX();
            paintYold = event.getY();
        } else {
            super.onTouchEvent(event);
        }


        return true;
    }
}
