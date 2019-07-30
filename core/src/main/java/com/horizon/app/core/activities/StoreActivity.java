package com.horizon.app.core.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.horizon.app.core.R;

public class StoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.layout.bottom_silent,R.layout.bottom_out);
    }
}
