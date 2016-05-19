package com.gmninfo.connectfour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.internal.view.SupportMenu;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.Menu;
import android.widget.TextView;

public class HelpActivity extends Activity {
    TextView text;
    TextView text1;
    TextView text2;
    TextView text4;
    TextView text5;
    TextView text6;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        setRequestedOrientation(1);
        setContentView(C0048R.layout.activity_help);
        this.text = (TextView) findViewById(C0048R.id.text);
        this.text.setTextColor(-16776961);
        this.text.setText("How To Play");
        this.text1 = (TextView) findViewById(C0048R.id.text1);
        this.text1.setTextColor(SupportMenu.CATEGORY_MASK);
        this.text1.setText("Player 1 win by connecting vertical four red dot");
        this.text2 = (TextView) findViewById(C0048R.id.text2);
        this.text2.setTextColor(SupportMenu.CATEGORY_MASK);
        this.text2.setText("Connect4 is two player game in which one player has RED dot and another player has BLUE dot. Each player has one by one turn. When player touch the screen dot filled from bottom. For winning the game player has to connect 4 dot of same color vertically, horizontally or crossed.");
        this.text4 = (TextView) findViewById(C0048R.id.text4);
        this.text4.setTextColor(SupportMenu.CATEGORY_MASK);
        this.text4.setText("Player 1 win by connecting Horizontal four red dot");
        this.text5 = (TextView) findViewById(C0048R.id.text5);
        this.text5.setTextColor(SupportMenu.CATEGORY_MASK);
        this.text5.setText("Player 2 win by connecting four cross blue dot");
        this.text6 = (TextView) findViewById(C0048R.id.text6);
        this.text6.setTextColor(SupportMenu.CATEGORY_MASK);
        this.text6.setText("Player 1 win by connecting four cross red dot");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0048R.menu.help, menu);
        return true;
    }

    public void onBackPressed() {
        super.onBackPressed();
        try {
            startActivity(new Intent(this, Class.forName("com.gmninfo.connectfour.HomeActivity")));
            finish();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        finish();
    }
}
