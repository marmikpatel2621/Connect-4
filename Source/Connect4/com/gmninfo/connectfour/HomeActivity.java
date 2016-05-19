package com.gmninfo.connectfour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

public class HomeActivity extends Activity implements OnTouchListener {
    float f3H;
    float f4W;
    Bitmap about;
    Bitmap connect4;
    Draw f5d;
    private boolean exit;
    Bitmap exit1;
    float gapH;
    float gapV;
    int f6h;
    int hConnect;
    int hPlay;
    Bitmap help;
    int f7i;
    int f8j;
    float[] lineH;
    float[] lineV;
    Bitmap play;
    MediaPlayer touch;
    float tx;
    float ty;
    int f9w;
    int wConnect;
    int wPlay;

    /* renamed from: com.gmninfo.connectfour.HomeActivity.1 */
    class C00471 implements Runnable {
        C00471() {
        }

        public void run() {
            HomeActivity.this.exit = false;
        }
    }

    public class Draw extends SurfaceView implements Runnable {
        Canvas f2c;
        boolean check;
        Typeface font;
        SurfaceHolder holder;
        boolean isRunning;
        Paint paint;
        Paint paint1;
        Paint paint2;
        Thread thread;

        public Draw(Context context) {
            super(context);
            this.isRunning = false;
            this.check = true;
            this.thread = null;
            this.paint = new Paint(1);
            this.paint1 = new Paint(1);
            this.paint2 = new Paint(1);
            this.holder = getHolder();
        }

        public void pause() {
            this.isRunning = false;
            try {
                this.thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.thread = null;
        }

        public void resume() {
            this.isRunning = true;
            synchronized ("accessibility") {
                this.thread = new Thread(this);
            }
            this.thread.start();
        }

        public void run() {
            while (this.isRunning) {
                if (this.holder.getSurface().isValid()) {
                    this.f2c = this.holder.lockCanvas();
                    this.paint.setColor(-1);
                    this.f2c.drawRect(0.0f, 0.0f, HomeActivity.this.f4W, HomeActivity.this.f3H, this.paint);
                    HomeActivity.this.f4W = (float) this.f2c.getWidth();
                    HomeActivity.this.f3H = (float) this.f2c.getHeight();
                    HomeActivity.this.gapH = HomeActivity.this.f4W / 8.0f;
                    HomeActivity.this.gapV = HomeActivity.this.f3H / 8.0f;
                    drawGameArea(this.f2c, this.paint);
                    HomeActivity.this.wPlay = HomeActivity.this.play.getWidth();
                    HomeActivity.this.hPlay = HomeActivity.this.play.getHeight();
                    HomeActivity.this.hConnect = HomeActivity.this.connect4.getHeight();
                    HomeActivity.this.wConnect = HomeActivity.this.connect4.getWidth();
                    this.f2c.drawBitmap(HomeActivity.this.connect4, (HomeActivity.this.f4W - ((float) HomeActivity.this.wConnect)) / 2.0f, (HomeActivity.this.gapV * 2.0f) - ((float) (HomeActivity.this.hConnect / 2)), this.paint);
                    this.f2c.drawBitmap(HomeActivity.this.play, (HomeActivity.this.f4W - ((float) HomeActivity.this.wPlay)) / 2.0f, (HomeActivity.this.f3H - ((float) HomeActivity.this.hPlay)) / 2.0f, this.paint);
                    this.f2c.drawBitmap(HomeActivity.this.help, (HomeActivity.this.f4W - ((float) HomeActivity.this.wPlay)) / 2.0f, ((HomeActivity.this.f3H - ((float) HomeActivity.this.hPlay)) / 2.0f) + HomeActivity.this.gapV, this.paint);
                    this.f2c.drawBitmap(HomeActivity.this.about, (HomeActivity.this.f4W - ((float) HomeActivity.this.wPlay)) / 2.0f, ((HomeActivity.this.f3H - ((float) HomeActivity.this.hPlay)) / 2.0f) + (HomeActivity.this.gapV * 2.0f), this.paint);
                    this.f2c.drawBitmap(HomeActivity.this.exit1, (HomeActivity.this.f4W - ((float) HomeActivity.this.wPlay)) / 2.0f, ((HomeActivity.this.f3H - ((float) HomeActivity.this.hPlay)) / 2.0f) + (3.0f * HomeActivity.this.gapV), this.paint);
                    eventCheck();
                    this.holder.unlockCanvasAndPost(this.f2c);
                }
            }
        }

        private void drawGameArea(Canvas canvas, Paint myPaint) {
            this.paint.setStrokeWidth(3.0f);
            this.paint.setShadowLayer(3.0f, 3.0f, 3.0f, -16776961);
            this.paint.setColor(SupportMenu.CATEGORY_MASK);
            HomeActivity.this.lineH[0] = 0.0f;
            HomeActivity.this.lineV[0] = 0.0f;
            HomeActivity.this.f7i = 1;
            while (HomeActivity.this.f7i < 8) {
                HomeActivity.this.lineV[HomeActivity.this.f7i] = HomeActivity.this.lineV[HomeActivity.this.f7i - 1] + HomeActivity.this.gapH;
                HomeActivity homeActivity = HomeActivity.this;
                homeActivity.f7i++;
            }
            HomeActivity.this.f7i = 1;
            while (HomeActivity.this.f7i < 8) {
                HomeActivity.this.lineH[HomeActivity.this.f7i] = HomeActivity.this.lineH[HomeActivity.this.f7i - 1] + HomeActivity.this.gapV;
                homeActivity = HomeActivity.this;
                homeActivity.f7i++;
            }
            HomeActivity.this.f7i = 0;
            while (HomeActivity.this.f7i < 8) {
                canvas.drawLine(HomeActivity.this.lineV[HomeActivity.this.f7i], 0.0f, HomeActivity.this.lineV[HomeActivity.this.f7i], HomeActivity.this.f3H, myPaint);
                homeActivity = HomeActivity.this;
                homeActivity.f7i++;
            }
            canvas.drawLine(HomeActivity.this.f4W, 0.0f, HomeActivity.this.f4W, HomeActivity.this.f3H, myPaint);
            HomeActivity.this.f7i = 0;
            while (HomeActivity.this.f7i < 8) {
                canvas.drawLine(0.0f, HomeActivity.this.lineH[HomeActivity.this.f7i], HomeActivity.this.f4W, HomeActivity.this.lineH[HomeActivity.this.f7i], myPaint);
                homeActivity = HomeActivity.this;
                homeActivity.f7i++;
            }
            canvas.drawLine(0.0f, HomeActivity.this.f3H - 2.0f, HomeActivity.this.f4W, HomeActivity.this.f3H - 2.0f, myPaint);
        }

        private void eventCheck() {
            if (HomeActivity.this.tx > (HomeActivity.this.f4W - ((float) HomeActivity.this.wPlay)) / 2.0f && HomeActivity.this.ty > (HomeActivity.this.f3H - ((float) HomeActivity.this.hPlay)) / 2.0f && HomeActivity.this.tx < (HomeActivity.this.f4W + ((float) HomeActivity.this.wPlay)) / 2.0f && HomeActivity.this.ty < (HomeActivity.this.f3H + ((float) HomeActivity.this.hPlay)) / 2.0f) {
                HomeActivity.this.touch.start();
                HomeActivity homeActivity = HomeActivity.this;
                HomeActivity.this.ty = 0.0f;
                homeActivity.tx = 0.0f;
                try {
                    HomeActivity.this.startActivity(new Intent(HomeActivity.this.getApplicationContext(), Class.forName("com.gmninfo.connectfour.MainActivity")));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (HomeActivity.this.tx > (HomeActivity.this.f4W - ((float) HomeActivity.this.wPlay)) / 2.0f && HomeActivity.this.ty > ((HomeActivity.this.f3H - ((float) HomeActivity.this.hPlay)) / 2.0f) + (HomeActivity.this.gapV * 3.0f) && HomeActivity.this.tx < (HomeActivity.this.f4W + ((float) HomeActivity.this.wPlay)) / 2.0f && HomeActivity.this.ty < ((HomeActivity.this.f3H + ((float) HomeActivity.this.hPlay)) / 2.0f) + (HomeActivity.this.gapV * 3.0f)) {
                HomeActivity.this.touch.start();
                homeActivity = HomeActivity.this;
                HomeActivity.this.ty = 0.0f;
                homeActivity.tx = 0.0f;
                HomeActivity.this.finish();
            }
            if (HomeActivity.this.tx > (HomeActivity.this.f4W - ((float) HomeActivity.this.wPlay)) / 2.0f && HomeActivity.this.ty > ((HomeActivity.this.f3H - ((float) HomeActivity.this.hPlay)) / 2.0f) + (HomeActivity.this.gapV * 2.0f) && HomeActivity.this.tx < (HomeActivity.this.f4W + ((float) HomeActivity.this.wPlay)) / 2.0f && HomeActivity.this.ty < ((HomeActivity.this.f3H + ((float) HomeActivity.this.hPlay)) / 2.0f) + (HomeActivity.this.gapV * 2.0f)) {
                HomeActivity.this.touch.start();
                homeActivity = HomeActivity.this;
                HomeActivity.this.ty = 0.0f;
                homeActivity.tx = 0.0f;
                try {
                    HomeActivity.this.startActivity(new Intent(HomeActivity.this.getApplicationContext(), Class.forName("com.gmninfo.connectfour.AboutActivity")));
                } catch (ClassNotFoundException e2) {
                    e2.printStackTrace();
                }
            }
            if (HomeActivity.this.tx > (HomeActivity.this.f4W - ((float) HomeActivity.this.wPlay)) / 2.0f && HomeActivity.this.ty > ((HomeActivity.this.f3H - ((float) HomeActivity.this.hPlay)) / 2.0f) + HomeActivity.this.gapV && HomeActivity.this.tx < (HomeActivity.this.f4W + ((float) HomeActivity.this.wPlay)) / 2.0f && HomeActivity.this.ty < ((HomeActivity.this.f3H + ((float) HomeActivity.this.hPlay)) / 2.0f) + HomeActivity.this.gapV) {
                HomeActivity.this.touch.start();
                homeActivity = HomeActivity.this;
                HomeActivity.this.ty = 0.0f;
                homeActivity.tx = 0.0f;
                try {
                    HomeActivity.this.startActivity(new Intent(HomeActivity.this.getApplicationContext(), Class.forName("com.gmninfo.connectfour.HelpActivity")));
                } catch (ClassNotFoundException e22) {
                    e22.printStackTrace();
                }
            }
        }
    }

    public HomeActivity() {
        this.exit = false;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        setRequestedOrientation(1);
        this.f5d = new Draw(this);
        this.f5d.setOnTouchListener(this);
        this.ty = 0.0f;
        this.tx = 0.0f;
        this.lineH = new float[8];
        this.lineV = new float[8];
        this.play = BitmapFactory.decodeResource(getResources(), C0048R.drawable.play);
        this.connect4 = BitmapFactory.decodeResource(getResources(), C0048R.drawable.connect4);
        this.help = BitmapFactory.decodeResource(getResources(), C0048R.drawable.help);
        this.about = BitmapFactory.decodeResource(getResources(), C0048R.drawable.about);
        this.exit1 = BitmapFactory.decodeResource(getResources(), C0048R.drawable.exit);
        this.touch = MediaPlayer.create(this, C0048R.raw.touch);
        setContentView(this.f5d);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0048R.menu.home, menu);
        return true;
    }

    protected void onPause() {
        super.onPause();
        this.f5d.pause();
    }

    protected void onResume() {
        super.onResume();
        this.f5d.resume();
    }

    public void onBackPressed() {
        super.onBackPressed();
        if (this.exit) {
            finish();
            return;
        }
        Toast.makeText(this, "Press Back again to Exit.", 0).show();
        this.exit = true;
        new Handler().postDelayed(new C00471(), 3000);
    }

    public boolean onTouch(View v, MotionEvent event) {
        this.tx = event.getX();
        this.ty = event.getY();
        return false;
    }
}
