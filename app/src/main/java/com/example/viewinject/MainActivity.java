package com.example.viewinject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viewinject.anotations.OnClickEvent;
import com.example.viewinject.anotations.ViewInject;
import com.example.viewinject.reflect.ViewInjectRef;

public class MainActivity extends AppCompatActivity {

    @ViewInject(R.id.textView1)
    TextView textView1;
    @ViewInject(R.id.textView2)
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewInjectRef.init(this);

        textView1.setText("TextView 1");

        textView2.setText("TextView 2");
    }

    @OnClickEvent({R.id.textView1,R.id.textView2})
    void onClick(View v) {
        if (v.getId() == R.id.textView1)
            Toast.makeText(this, "点击了textview1", Toast.LENGTH_SHORT).show();
        if (v.getId() == R.id.textView2)
            Toast.makeText(this, "点击了textview2", Toast.LENGTH_SHORT).show();
    }
}
