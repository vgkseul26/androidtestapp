package com.usv.androidtestapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.usv.androidtestapp.model.Record;
import com.usv.androidtestapp.model.Utils;

public class InsertActivity extends Activity {

    private Button loadData;
    private EditText editText;
    private Uri imgUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_insert);

        loadData = (Button) findViewById(R.id.load_data);
        editText = (EditText) findViewById(R.id.edit_text);
        loadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Intent.ACTION_PICK);
                i.setType("image/*");
                startActivityForResult(i, 2);
            }
        });
    }

    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == 2 && resultCode == RESULT_OK) {
            imgUri = data.getData();
            Intent intent = new Intent();
            intent.putExtra("data", new Record(imgUri, editText.getText().toString()));
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
