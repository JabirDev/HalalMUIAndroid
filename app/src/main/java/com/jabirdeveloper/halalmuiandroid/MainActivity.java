package com.jabirdeveloper.halalmuiandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

//import com.jabirdeveloper.halalmui.HalalListener;
//import com.jabirdeveloper.halalmui.HalalMui;
//import com.jabirdeveloper.halalmui.HalalData;
//import com.squareup.picasso.Picasso;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView tv = findViewById(R.id.textView);
        String n = "indomie";
//        HalalMui.byNamaProduk(n, new HalalListener() {
//            @Override
//            public void ketikaSukses(List<HalalData> halalData) {
//                StringBuilder sb = new StringBuilder();
//                for (int i = 0; i < halalData.size(); i++) {
//                    sb.append(halalData.get(i).getNamaProduk());
//                    sb.append("\n");
//                }
//                tv.setText(sb.toString());
//                Log.d(TAG, sb.toString());
//            }
//
//            @Override
//            public void ketikaTidakAdaData(String s) {
//                tv.setText(s);
//                Log.e(TAG, s);
//            }
//
//            @Override
//                public void ketikaGagal(Throwable t) {
//                tv.setText(t.getMessage());
//                Log.e(TAG, "ketikaGagal: ", t);
//            }
//        });
    }
}
