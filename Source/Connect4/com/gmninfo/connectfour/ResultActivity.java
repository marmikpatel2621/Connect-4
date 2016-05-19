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
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.CursorAdapter;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.TextView;

public class ResultActivity extends Activity implements OnTouchListener {
    float f21H;
    float f22W;
    String color;
    TextView display;
    Bitmap draw;
    int f23h;
    int hMenu;
    Bitmap menu;
    MediaPlayer p1;
    MediaPlayer p2;
    int player;
    Bitmap player1;
    Bitmap player2;
    Result f24r;
    int score1;
    int score2;
    MediaPlayer touch;
    float tx;
    float ty;
    int f25w;
    int wMenu;

    public class Result extends SurfaceView implements Runnable {
        Canvas f20c;
        boolean check;
        Typeface font;
        SurfaceHolder holder;
        boolean isRunning;
        Paint paint;
        Paint paint1;
        Paint paint2;
        Thread thread;

        public Result(Context context) {
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
                    this.f20c = this.holder.lockCanvas();
                    this.paint.setColor(-1);
                    this.f20c.drawRect(0.0f, 0.0f, ResultActivity.this.f22W, ResultActivity.this.f21H, this.paint);
                    ResultActivity.this.f22W = (float) this.f20c.getWidth();
                    ResultActivity.this.f21H = (float) this.f20c.getHeight();
                    switch (ResultActivity.this.player) {
                        case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                            this.paint.setColor(SupportMenu.CATEGORY_MASK);
                            ResultActivity.this.f25w = ResultActivity.this.player1.getWidth();
                            ResultActivity.this.f23h = ResultActivity.this.player1.getHeight();
                            ResultActivity.this.wMenu = ResultActivity.this.menu.getWidth();
                            ResultActivity.this.hMenu = ResultActivity.this.menu.getHeight();
                            this.f20c.drawBitmap(ResultActivity.this.player1, (ResultActivity.this.f22W - ((float) ResultActivity.this.f25w)) / 2.0f, (ResultActivity.this.f21H - ((float) ResultActivity.this.f23h)) / 2.0f, this.paint);
                            this.f20c.drawBitmap(ResultActivity.this.menu, (ResultActivity.this.f22W - ((float) ResultActivity.this.wMenu)) / 2.0f, ((ResultActivity.this.f21H + ((float) ResultActivity.this.f23h)) / 2.0f) + 28.0f, this.paint);
                            break;
                        case CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER /*2*/:
                            this.paint.setColor(-16776961);
                            ResultActivity.this.f25w = ResultActivity.this.player2.getWidth();
                            ResultActivity.this.f23h = ResultActivity.this.player2.getHeight();
                            ResultActivity.this.wMenu = ResultActivity.this.menu.getWidth();
                            ResultActivity.this.hMenu = ResultActivity.this.menu.getHeight();
                            this.f20c.drawBitmap(ResultActivity.this.player2, (ResultActivity.this.f22W - ((float) ResultActivity.this.f25w)) / 2.0f, (ResultActivity.this.f21H - ((float) ResultActivity.this.f23h)) / 2.0f, this.paint);
                            this.f20c.drawBitmap(ResultActivity.this.menu, (ResultActivity.this.f22W - ((float) ResultActivity.this.wMenu)) / 2.0f, ((ResultActivity.this.f21H + ((float) ResultActivity.this.f23h)) / 2.0f) + 28.0f, this.paint);
                            break;
                        case FragmentManagerImpl.ANIM_STYLE_CLOSE_ENTER /*3*/:
                            this.paint.setColor(-16776961);
                            ResultActivity.this.f25w = ResultActivity.this.draw.getWidth();
                            ResultActivity.this.f23h = ResultActivity.this.draw.getHeight();
                            ResultActivity.this.wMenu = ResultActivity.this.menu.getWidth();
                            ResultActivity.this.hMenu = ResultActivity.this.menu.getHeight();
                            this.f20c.drawBitmap(ResultActivity.this.draw, (ResultActivity.this.f22W - ((float) ResultActivity.this.f25w)) / 2.0f, (ResultActivity.this.f21H - ((float) ResultActivity.this.f23h)) / 2.0f, this.paint);
                            this.f20c.drawBitmap(ResultActivity.this.menu, (ResultActivity.this.f22W - ((float) ResultActivity.this.wMenu)) / 2.0f, ((ResultActivity.this.f21H + ((float) ResultActivity.this.f23h)) / 2.0f) + 28.0f, this.paint);
                            break;
                    }
                    eventCheck();
                    this.holder.unlockCanvasAndPost(this.f20c);
                }
            }
        }

        private void eventCheck() {
            if (ResultActivity.this.tx > (ResultActivity.this.f22W - ((float) ResultActivity.this.wMenu)) / 2.0f && ResultActivity.this.ty > ((ResultActivity.this.f21H + ((float) ResultActivity.this.f23h)) / 2.0f) + 20.0f && ResultActivity.this.tx < (ResultActivity.this.f22W + ((float) ResultActivity.this.wMenu)) / 2.0f && ResultActivity.this.ty < (((ResultActivity.this.f21H + ((float) ResultActivity.this.f23h)) / 2.0f) + 20.0f) + ((float) ResultActivity.this.hMenu)) {
                ResultActivity.this.touch.start();
                ResultActivity resultActivity = ResultActivity.this;
                ResultActivity.this.ty = 0.0f;
                resultActivity.tx = 0.0f;
                try {
                    ResultActivity.this.startActivity(new Intent(ResultActivity.this.getApplicationContext(), Class.forName("com.gmninfo.connectfour.HomeActivity")));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public ResultActivity() {
        this.f25w = 0;
        this.f23h = 0;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        setRequestedOrientation(1);
        this.f24r = new Result(this);
        this.f24r.setOnTouchListener(this);
        Intent in = getIntent();
        this.touch = MediaPlayer.create(this, C0048R.raw.touch);
        this.player = in.getExtras().getInt("player");
        this.ty = 0.0f;
        this.tx = 0.0f;
        this.p1 = MediaPlayer.create(this, C0048R.raw.playerone);
        this.p2 = MediaPlayer.create(this, C0048R.raw.playertwo);
        this.player1 = BitmapFactory.decodeResource(getResources(), C0048R.drawable.player1);
        this.player2 = BitmapFactory.decodeResource(getResources(), C0048R.drawable.player2);
        this.draw = BitmapFactory.decodeResource(getResources(), C0048R.drawable.draw);
        this.menu = BitmapFactory.decodeResource(getResources(), C0048R.drawable.menu);
        switch (this.player) {
            case CursorAdapter.FLAG_AUTO_REQUERY /*1*/:
                this.p1.start();
                break;
            case CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER /*2*/:
                this.p2.start();
                break;
        }
        setContentView(this.f24r);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0048R.menu.result, menu);
        return true;
    }

    public boolean onTouch(View v, MotionEvent event) {
        this.tx = event.getX();
        this.ty = event.getY();
        return false;
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.p1.stop();
        this.p2.stop();
        try {
            startActivity(new Intent(this, Class.forName("com.gmninfo.connectfour.HomeActivity")));
            finish();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected void onPause() {
        super.onPause();
        this.f24r.pause();
    }

    protected void onResume() {
        super.onResume();
        this.f24r.resume();
    }
}
