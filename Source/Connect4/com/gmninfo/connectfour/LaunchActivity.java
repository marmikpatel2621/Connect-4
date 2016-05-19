package com.gmninfo.connectfour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class LaunchActivity extends Activity {
    float f11H;
    float f12W;
    float hLogo;
    Logo f13l;
    Bitmap logo;
    float wLogo;

    public class Logo extends SurfaceView implements Runnable {
        Canvas f10c;
        boolean check;
        Typeface font;
        SurfaceHolder holder;
        boolean isRunning;
        Paint paint;
        Thread thread;

        public Logo(Context context) {
            super(context);
            this.isRunning = false;
            this.check = true;
            this.thread = null;
            this.paint = new Paint(1);
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
                    this.f10c = this.holder.lockCanvas();
                    this.paint.setColor(ViewCompat.MEASURED_STATE_MASK);
                    this.f10c.drawRect(0.0f, 0.0f, LaunchActivity.this.f12W, LaunchActivity.this.f11H, this.paint);
                    LaunchActivity.this.f12W = (float) this.f10c.getWidth();
                    LaunchActivity.this.f11H = (float) this.f10c.getHeight();
                    LaunchActivity.this.wLogo = (float) LaunchActivity.this.logo.getWidth();
                    LaunchActivity.this.hLogo = (float) LaunchActivity.this.logo.getHeight();
                    this.f10c.drawBitmap(LaunchActivity.this.logo, (LaunchActivity.this.f12W - LaunchActivity.this.wLogo) / 2.0f, (LaunchActivity.this.f11H - LaunchActivity.this.hLogo) / 2.0f, this.paint);
                    this.holder.unlockCanvasAndPost(this.f10c);
                    go();
                }
            }
        }

        public void go() {
            try {
                Thread.sleep(3000);
                LaunchActivity.this.startActivity(new Intent("android.intent.action.HOMEACTIVITY"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                LaunchActivity.this.finish();
            }
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        setRequestedOrientation(1);
        this.logo = BitmapFactory.decodeResource(getResources(), C0048R.drawable.logo);
        this.f13l = new Logo(this);
        setContentView(this.f13l);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0048R.menu.launch, menu);
        return true;
    }

    protected void onPause() {
        super.onPause();
        this.f13l.pause();
    }

    protected void onResume() {
        super.onResume();
        this.f13l.resume();
    }

    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
