package com.gmninfo.connectfour;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import java.lang.reflect.Array;

public class MainActivity extends Activity implements OnTouchListener {
    float f15H;
    float H3;
    float f16W;
    MediaPlayer back;
    float col;
    boolean colFlag;
    int colIn;
    int counter;
    int[][] field;
    Game f17g;
    float gapH;
    float gapV;
    int f18i;
    int f19j;
    float[] lineH;
    float[] lineV;
    float row;
    boolean rowFlag;
    int rowIn;
    int sc1;
    int sc2;
    int[] top;
    MediaPlayer touch;
    int turn;
    float tx;
    float ty;

    public class Game extends SurfaceView implements Runnable {
        Canvas f14c;
        boolean check;
        Typeface font;
        SurfaceHolder holder;
        boolean isRunning;
        Paint paint;
        Paint paint1;
        Paint paint2;
        Thread thread;

        public Game(Context context) {
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
                    this.f14c = this.holder.lockCanvas();
                    this.paint.setColor(-1);
                    this.f14c.drawRect(0.0f, 0.0f, MainActivity.this.f16W, MainActivity.this.f15H, this.paint);
                    MainActivity.this.f16W = (float) this.f14c.getWidth();
                    MainActivity.this.f15H = (float) this.f14c.getHeight();
                    MainActivity.this.H3 = (3.0f * MainActivity.this.f15H) / 8.0f;
                    MainActivity.this.gapH = MainActivity.this.f16W / 8.0f;
                    MainActivity.this.gapV = MainActivity.this.f15H / 8.0f;
                    if (MainActivity.this.counter >= 6) {
                        check();
                    }
                    if (MainActivity.this.turn % 2 != 0) {
                        this.paint1.setColor(SupportMenu.CATEGORY_MASK);
                        this.paint.setColor(-16776961);
                        drawGameArea(this.f14c, this.paint1, this.paint);
                        player1(this.f14c, this.paint1);
                    } else {
                        this.paint1.setColor(-16776961);
                        this.paint.setColor(SupportMenu.CATEGORY_MASK);
                        drawGameArea(this.f14c, this.paint1, this.paint);
                        player2(this.f14c, this.paint1);
                    }
                    if (MainActivity.this.counter >= 6) {
                        check();
                    }
                    this.holder.unlockCanvasAndPost(this.f14c);
                }
            }
        }

        private void drawGameArea(Canvas canvas, Paint myPaint, Paint sPaint) {
            MainActivity.this.lineH[0] = 0.0f;
            MainActivity.this.lineV[0] = 0.0f;
            myPaint.setStrokeWidth(3.0f);
            myPaint.setShadowLayer(3.0f, 2.0f, 2.0f, sPaint.getColor());
            MainActivity.this.f18i = 1;
            while (MainActivity.this.f18i < 8) {
                MainActivity.this.lineV[MainActivity.this.f18i] = MainActivity.this.lineV[MainActivity.this.f18i - 1] + MainActivity.this.gapH;
                MainActivity mainActivity = MainActivity.this;
                mainActivity.f18i++;
            }
            MainActivity.this.f18i = 1;
            while (MainActivity.this.f18i < 8) {
                MainActivity.this.lineH[MainActivity.this.f18i] = MainActivity.this.lineH[MainActivity.this.f18i - 1] + MainActivity.this.gapV;
                mainActivity = MainActivity.this;
                mainActivity.f18i++;
            }
            MainActivity.this.f18i = 0;
            while (MainActivity.this.f18i < 8) {
                canvas.drawLine(MainActivity.this.lineV[MainActivity.this.f18i], 0.0f, MainActivity.this.lineV[MainActivity.this.f18i], MainActivity.this.f15H, myPaint);
                mainActivity = MainActivity.this;
                mainActivity.f18i++;
            }
            canvas.drawLine(MainActivity.this.f16W, 0.0f, MainActivity.this.f16W, MainActivity.this.f15H, myPaint);
            MainActivity.this.f18i = 0;
            while (MainActivity.this.f18i < 8) {
                canvas.drawLine(0.0f, MainActivity.this.lineH[MainActivity.this.f18i], MainActivity.this.f16W, MainActivity.this.lineH[MainActivity.this.f18i], myPaint);
                mainActivity = MainActivity.this;
                mainActivity.f18i++;
            }
            canvas.drawLine(0.0f, MainActivity.this.f15H - 2.0f, MainActivity.this.f16W, MainActivity.this.f15H - 2.0f, myPaint);
        }

        private void player1(Canvas c, Paint p) {
            MainActivity mainActivity;
            int i = 1;
            MainActivity.this.f18i = 0;
            while (MainActivity.this.f18i < 8) {
                if (MainActivity.this.tx > MainActivity.this.lineV[MainActivity.this.f18i]) {
                    MainActivity.this.col = MainActivity.this.lineV[MainActivity.this.f18i];
                    MainActivity.this.colIn = MainActivity.this.f18i;
                    MainActivity.this.colFlag = true;
                }
                MainActivity mainActivity2 = MainActivity.this;
                mainActivity2.f18i++;
            }
            MainActivity.this.f18i = 0;
            while (MainActivity.this.f18i < 8) {
                if (MainActivity.this.ty > MainActivity.this.lineH[MainActivity.this.f18i]) {
                    MainActivity.this.row = MainActivity.this.lineH[MainActivity.this.f18i];
                    MainActivity.this.rowIn = MainActivity.this.f18i;
                    MainActivity.this.rowFlag = true;
                }
                mainActivity2 = MainActivity.this;
                mainActivity2.f18i++;
            }
            if (MainActivity.this.colFlag && MainActivity.this.rowFlag && MainActivity.this.field[MainActivity.this.rowIn][MainActivity.this.colIn] == 0) {
                MainActivity.this.field[MainActivity.this.top[MainActivity.this.colIn]][MainActivity.this.colIn] = MainActivity.this.turn;
                int[] iArr = MainActivity.this.top;
                int i2 = MainActivity.this.colIn;
                iArr[i2] = iArr[i2] - 1;
                mainActivity2 = MainActivity.this;
                if (MainActivity.this.turn == 1) {
                    i = 2;
                }
                mainActivity2.turn = i;
                mainActivity = MainActivity.this;
                mainActivity.counter++;
            }
            mainActivity = MainActivity.this;
            MainActivity.this.rowFlag = false;
            mainActivity.colFlag = false;
            mainActivity = MainActivity.this;
            MainActivity.this.ty = 0.0f;
            mainActivity.tx = 0.0f;
            putCircle(c, p);
        }

        private void player2(Canvas c, Paint p) {
            MainActivity mainActivity;
            int i = 1;
            MainActivity.this.f18i = 0;
            while (MainActivity.this.f18i < 8) {
                if (MainActivity.this.tx > MainActivity.this.lineV[MainActivity.this.f18i]) {
                    MainActivity.this.col = MainActivity.this.lineV[MainActivity.this.f18i];
                    MainActivity.this.colIn = MainActivity.this.f18i;
                    MainActivity.this.colFlag = true;
                }
                MainActivity mainActivity2 = MainActivity.this;
                mainActivity2.f18i++;
            }
            MainActivity.this.f18i = 0;
            while (MainActivity.this.f18i < 8) {
                if (MainActivity.this.ty > MainActivity.this.lineH[MainActivity.this.f18i]) {
                    MainActivity.this.row = MainActivity.this.lineH[MainActivity.this.f18i];
                    MainActivity.this.rowIn = MainActivity.this.f18i;
                    MainActivity.this.rowFlag = true;
                }
                mainActivity2 = MainActivity.this;
                mainActivity2.f18i++;
            }
            if (MainActivity.this.colFlag && MainActivity.this.rowFlag && MainActivity.this.field[MainActivity.this.rowIn][MainActivity.this.colIn] == 0) {
                MainActivity.this.field[MainActivity.this.top[MainActivity.this.colIn]][MainActivity.this.colIn] = MainActivity.this.turn;
                int[] iArr = MainActivity.this.top;
                int i2 = MainActivity.this.colIn;
                iArr[i2] = iArr[i2] - 1;
                mainActivity2 = MainActivity.this;
                if (MainActivity.this.turn == 1) {
                    i = 2;
                }
                mainActivity2.turn = i;
                mainActivity = MainActivity.this;
                mainActivity.counter++;
            }
            mainActivity = MainActivity.this;
            MainActivity.this.rowFlag = false;
            mainActivity.colFlag = false;
            mainActivity = MainActivity.this;
            MainActivity.this.ty = 0.0f;
            mainActivity.tx = 0.0f;
            putCircle(c, p);
        }

        private void putCircle(Canvas c, Paint p) {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (MainActivity.this.field[i][j] == 1) {
                        p.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
                        p.setColor(SupportMenu.CATEGORY_MASK);
                        c.drawCircle(MainActivity.this.lineV[j] + (MainActivity.this.gapH / 2.0f), MainActivity.this.lineH[i] + (MainActivity.this.gapV / 2.0f), (MainActivity.this.gapH / 2.0f) - 6.0f, p);
                    } else if (MainActivity.this.field[i][j] == 2) {
                        p.setShadowLayer(0.0f, 0.0f, 0.0f, 0);
                        p.setColor(-16776961);
                        c.drawCircle(MainActivity.this.lineV[j] + (MainActivity.this.gapH / 2.0f), MainActivity.this.lineH[i] + (MainActivity.this.gapV / 2.0f), (MainActivity.this.gapH / 2.0f) - 6.0f, p);
                    }
                }
            }
        }

        private void check() {
            int i;
            int k;
            for (i = 0; i < 8; i++) {
                int j;
                for (j = 0; j < 5; j++) {
                    Intent ourIntent;
                    boolean b1 = true;
                    boolean b2 = true;
                    for (k = 0; k < 4; k++) {
                        if (MainActivity.this.field[i][j + k] != 1) {
                            b1 = false;
                        }
                        if (MainActivity.this.field[i][j + k] != 2) {
                            b2 = false;
                        }
                    }
                    if (b1) {
                        MainActivity mainActivity = MainActivity.this;
                        mainActivity.sc1++;
                    }
                    if (b2) {
                        mainActivity = MainActivity.this;
                        mainActivity.sc2++;
                    }
                    if (b1 || b2) {
                        if (b1) {
                            try {
                                ourIntent = new Intent(MainActivity.this.getApplicationContext(), Class.forName("com.gmninfo.connectfour.ResultActivity"));
                                ourIntent.putExtra("res", "Red");
                                ourIntent.putExtra("score1", MainActivity.this.sc1);
                                ourIntent.putExtra("score2", MainActivity.this.sc2);
                                ourIntent.putExtra("player", 1);
                                MainActivity.this.back.stop();
                                MainActivity.this.startActivity(ourIntent);
                            } catch (ClassNotFoundException e) {
                                e.printStackTrace();
                            }
                        } else {
                            try {
                                ourIntent = new Intent(MainActivity.this.getApplicationContext(), Class.forName("com.gmninfo.connectfour.ResultActivity"));
                                ourIntent.putExtra("res", "Blue");
                                ourIntent.putExtra("score1", MainActivity.this.sc1);
                                ourIntent.putExtra("score2", MainActivity.this.sc2);
                                ourIntent.putExtra("player", 2);
                                MainActivity.this.back.stop();
                                MainActivity.this.startActivity(ourIntent);
                            } catch (ClassNotFoundException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                }
            }
            for (i = 0; i < 5; i++) {
                for (j = 0; j < 8; j++) {
                    b1 = true;
                    b2 = true;
                    for (k = 0; k < 4; k++) {
                        if (MainActivity.this.field[i + k][j] != 1) {
                            b1 = false;
                        }
                        if (MainActivity.this.field[i + k][j] != 2) {
                            b2 = false;
                        }
                    }
                    if (b1) {
                        mainActivity = MainActivity.this;
                        mainActivity.sc1++;
                    }
                    if (b2) {
                        mainActivity = MainActivity.this;
                        mainActivity.sc2++;
                    }
                    if (b1 || b2) {
                        if (b1) {
                            try {
                                ourIntent = new Intent(MainActivity.this.getApplicationContext(), Class.forName("com.gmninfo.connectfour.ResultActivity"));
                                ourIntent.putExtra("res", "Red");
                                ourIntent.putExtra("score1", MainActivity.this.sc1);
                                ourIntent.putExtra("score2", MainActivity.this.sc2);
                                ourIntent.putExtra("player", 1);
                                MainActivity.this.back.stop();
                                MainActivity.this.startActivity(ourIntent);
                            } catch (ClassNotFoundException e22) {
                                e22.printStackTrace();
                            }
                        } else {
                            try {
                                ourIntent = new Intent(MainActivity.this.getApplicationContext(), Class.forName("com.gmninfo.connectfour.ResultActivity"));
                                ourIntent.putExtra("res", "Blue");
                                ourIntent.putExtra("score1", MainActivity.this.sc1);
                                ourIntent.putExtra("score2", MainActivity.this.sc2);
                                ourIntent.putExtra("player", 2);
                                MainActivity.this.back.stop();
                                MainActivity.this.startActivity(ourIntent);
                            } catch (ClassNotFoundException e222) {
                                e222.printStackTrace();
                            }
                        }
                    }
                }
            }
            for (i = 0; i < 5; i++) {
                for (j = 0; j < 5; j++) {
                    b1 = true;
                    b2 = true;
                    for (k = 0; k < 4; k++) {
                        if (MainActivity.this.field[i + k][j + k] != 1) {
                            b1 = false;
                        }
                        if (MainActivity.this.field[i + k][j + k] != 2) {
                            b2 = false;
                        }
                    }
                    if (b1) {
                        mainActivity = MainActivity.this;
                        mainActivity.sc1++;
                    }
                    if (b2) {
                        mainActivity = MainActivity.this;
                        mainActivity.sc2++;
                    }
                    if (b1 || b2) {
                        if (b1) {
                            try {
                                ourIntent = new Intent(MainActivity.this.getApplicationContext(), Class.forName("com.gmninfo.connectfour.ResultActivity"));
                                ourIntent.putExtra("res", "Red");
                                ourIntent.putExtra("score1", MainActivity.this.sc1);
                                ourIntent.putExtra("score2", MainActivity.this.sc2);
                                ourIntent.putExtra("player", 1);
                                MainActivity.this.back.stop();
                                MainActivity.this.startActivity(ourIntent);
                            } catch (ClassNotFoundException e2222) {
                                e2222.printStackTrace();
                            }
                        } else {
                            try {
                                ourIntent = new Intent(MainActivity.this.getApplicationContext(), Class.forName("com.gmninfo.connectfour.ResultActivity"));
                                ourIntent.putExtra("res", "Blue");
                                ourIntent.putExtra("score1", MainActivity.this.sc1);
                                ourIntent.putExtra("score2", MainActivity.this.sc2);
                                ourIntent.putExtra("player", 2);
                                MainActivity.this.back.stop();
                                MainActivity.this.startActivity(ourIntent);
                            } catch (ClassNotFoundException e22222) {
                                e22222.printStackTrace();
                            }
                        }
                    }
                }
            }
            for (i = 0; i < 5; i++) {
                for (j = 3; j < 8; j++) {
                    b1 = true;
                    b2 = true;
                    for (k = 0; k < 4; k++) {
                        if (MainActivity.this.field[i + k][j - k] != 1) {
                            b1 = false;
                        }
                        if (MainActivity.this.field[i + k][j - k] != 2) {
                            b2 = false;
                        }
                    }
                    if (b1) {
                        mainActivity = MainActivity.this;
                        mainActivity.sc1++;
                    }
                    if (b2) {
                        mainActivity = MainActivity.this;
                        mainActivity.sc2++;
                    }
                    if (b1 || b2) {
                        if (b1) {
                            try {
                                ourIntent = new Intent(MainActivity.this.getApplicationContext(), Class.forName("com.gmninfo.connectfour.ResultActivity"));
                                ourIntent.putExtra("res", "Red");
                                ourIntent.putExtra("score1", MainActivity.this.sc1);
                                ourIntent.putExtra("score2", MainActivity.this.sc2);
                                ourIntent.putExtra("player", 1);
                                MainActivity.this.back.stop();
                                MainActivity.this.startActivity(ourIntent);
                            } catch (ClassNotFoundException e222222) {
                                e222222.printStackTrace();
                            }
                        } else {
                            try {
                                ourIntent = new Intent(MainActivity.this.getApplicationContext(), Class.forName("com.gmninfo.connectfour.ResultActivity"));
                                ourIntent.putExtra("res", "Blue");
                                ourIntent.putExtra("score1", MainActivity.this.sc1);
                                ourIntent.putExtra("score2", MainActivity.this.sc2);
                                ourIntent.putExtra("player", 2);
                                MainActivity.this.back.stop();
                                MainActivity.this.startActivity(ourIntent);
                            } catch (ClassNotFoundException e2222222) {
                                e2222222.printStackTrace();
                            }
                        }
                    }
                }
            }
            if (MainActivity.this.counter == 64) {
                try {
                    ourIntent = new Intent(MainActivity.this.getApplicationContext(), Class.forName("com.gmninfo.connectfour.ResultActivity"));
                    ourIntent.putExtra("player", 3);
                    MainActivity.this.back.stop();
                    MainActivity.this.startActivity(ourIntent);
                } catch (ClassNotFoundException e22222222) {
                    e22222222.printStackTrace();
                }
            }
        }
    }

    public MainActivity() {
        this.f18i = 0;
        this.f19j = 0;
        this.counter = 0;
    }

    protected void onCreate(Bundle savedInstanceState) {
        int i;
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        setRequestedOrientation(1);
        this.f17g = new Game(this);
        this.f17g.setOnTouchListener(this);
        this.turn = 1;
        this.touch = MediaPlayer.create(this, C0048R.raw.touch);
        this.back = MediaPlayer.create(this, C0048R.raw.back);
        this.lineH = new float[8];
        this.lineV = new float[8];
        this.field = (int[][]) Array.newInstance(Integer.TYPE, new int[]{8, 8});
        this.top = new int[8];
        for (i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.field[i][j] = 0;
            }
        }
        for (i = 0; i < 8; i++) {
            this.top[i] = 7;
        }
        this.back.start();
        this.back.setLooping(true);
        setContentView(this.f17g);
    }

    public boolean onTouch(View v, MotionEvent event) {
        this.touch.start();
        this.tx = event.getX();
        this.ty = event.getY();
        return false;
    }

    protected void onPause() {
        super.onPause();
        this.f17g.pause();
    }

    protected void onResume() {
        super.onResume();
        this.f17g.resume();
    }

    public void onBackPressed() {
        super.onBackPressed();
        this.back.stop();
        try {
            startActivity(new Intent(this, Class.forName("com.gmninfo.connectfour.HomeActivity")));
            finish();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finish();
    }
}
