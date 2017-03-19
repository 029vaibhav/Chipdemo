package com.vaibhav.chipdemo.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MultiAutoCompleteTextView;

import com.vaibhav.chipdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.chipsView)
    MultiAutoCompleteTextView chipsView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        MainPresenter.getInstance(this).getAndAddData(chipsView);
    }

    @Override
    protected void onDestroy() {
        MainPresenter.destroy();
        super.onDestroy();
    }

}
