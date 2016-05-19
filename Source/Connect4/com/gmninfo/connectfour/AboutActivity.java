package com.gmninfo.connectfour;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.Menu;
import android.widget.TextView;

public class AboutActivity extends Activity {
    TextView f1t;
    TextView t2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        setRequestedOrientation(1);
        setContentView(C0048R.layout.activity_about);
        this.f1t = (TextView) findViewById(C0048R.id.text1);
        this.f1t.setTextColor(-7829368);
        this.f1t.setText("We are group of three students of L. D. College of Engineering studying Computer Engineering. This is our first application in Play Store. Hope you will like this.");
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(C0048R.menu.about, menu);
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
