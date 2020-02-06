package com.zqc.opencc.android;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.zqc.opencc.android.lib.ChineseConverter;
import com.zqc.opencc.android.lib.ConversionType;

public class MainActivity extends AppCompatActivity {

    private ConversionType currentConversionType = ConversionType.TW2SP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.conversion_type_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        currentConversionType = ConversionType.TW2SP;
                        break;
                    case 1:
                        currentConversionType = ConversionType.S2HK;
                        break;
                    case 2:
                        currentConversionType = ConversionType.S2T;
                        break;
                    case 3:
                        currentConversionType = ConversionType.S2TW;
                        break;
                    case 4:
                        currentConversionType = ConversionType.S2TWP;
                        break;
                    case 5:
                        currentConversionType = ConversionType.T2HK;
                        break;
                    case 6:
                        currentConversionType = ConversionType.T2S;
                        break;
                    case 7:
                        currentConversionType = ConversionType.T2TW;
                        break;
                    case 8:
                        currentConversionType = ConversionType.TW2S;
                        break;
                    case 9:
                        currentConversionType = ConversionType.HK2S;
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final EditText textView = findViewById(R.id.text);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String originalText = textView.getText().toString();
                textView.setText(ChineseConverter.convert(originalText,
                        currentConversionType, getApplicationContext()));
            }
        });
    }
}
