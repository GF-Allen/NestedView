package com.andlau.nestedview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private VerticalViewPager mRvMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mRvMain = findViewById(R.id.rv_main);
        mRvMain.setVertical(true);
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            datas.add(String.format("第%d页", i));
        }
        VpMainAdapter adapter = new VpMainAdapter(this, datas);
        mRvMain.setAdapter(adapter);
    }
}
