package com.zqc.opencc.android;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;

import com.zqc.opencc.android.lib.ChineseConverter;
import com.zqc.opencc.android.lib.ConversionType;

public class MainActivity extends AppCompatActivity {

    private ConversionType currentConversionType = ConversionType.TW2SP;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.show();
        new Thread(() -> {
            ChineseConverter.initializeConverters(
                    MainActivity.this, ConversionType.values());
            runOnUiThread(() -> {
                if (progressDialog != null && progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            });
        }).start();

        AppCompatSpinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.conversion_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(12);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentConversionType = ConversionType.values()[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final AppCompatEditText textView = findViewById(R.id.text);

        findViewById(R.id.btn).setOnClickListener(v -> {
            CharSequence text = textView.getText();
            if (text != null && !TextUtils.isEmpty(text)) {
                textView.setText(ChineseConverter.convert(text.toString(),
                        currentConversionType, getApplicationContext()));
            }
        });
    }
}
