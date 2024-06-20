package com.nik.pr8savichev;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.IntStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void OpenNote(View view)
    {
        setContentView(R.layout.note);
    }
    public class notes
    {
        public String name;
        public String text;
        public String date;
    }
    public List<notes> list_nites = new ArrayList<>();

    public void AddNote(View view){
        notes new_notes = new notes();
        EditText e_name = findViewById(R.id.rditTextTextPersonName);
        new_notes.name = e_name.getText().toString();
        MultiAutoCompleteTextView e_text = findViewById(R.id.multiAutoCompleteTextView);
        new_notes.text = e_text.getText().toString();

        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd");
        new_notes.date = formatForDateNow.format(dateNow);

        list_nites.add(new_notes);
        setContentView(R.layout.activity_main);
        onLoad();
    }

    public void OpenNotes(View view)
    {
        int id = (int) view.getTag();
        setContentView(R.layout.note);

        EditText e_name = findViewById(R.id.rditTextTextPersonName);
        e_name.setText(list_nites.get(id).name);

        MultiAutoCompleteTextView e_text = findViewById(R.id.multiAutoCompleteTextView);
        e_text.setText(list_nites.get(id).text);
    }

    public void onLoad()
    {
        LinearLayout parrent = findViewById(R.id.parrent);
        parrent.removeAllViews();
        System.out.print(list_nites.size());
        for(int i = 0 ; i < list_nites.size(); i++) {
            final int finalI = i;
            LinearLayout ll = new LinearLayout(this);
            ll.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            ll.setLayoutParams(params);
            ll.setTag(i);
            ll.setOnClickListener(this::OpenNotes);

            ImageView iv = new ImageView(this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);
            iv.setLayoutParams(layoutParams);
            iv.setPadding(20, 20, 20, 20);

            LinearLayout ll_ver = new LinearLayout(this);
            ll_ver.setOrientation(LinearLayout.VERTICAL);
            ll_ver.setLayoutParams(params);

            TextView tv_name = new TextView(this);
            tv_name.setText(list_nites.get(i).name);
            LinearLayout.LayoutParams params_tv = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            tv_name.setLayoutParams(params_tv);
            tv_name.setTextColor(Color.BLACK);
            tv_name.setTextSize(18);

            TextView tv_data = new TextView(this);
            tv_data.setText(list_nites.get(i).date);
            tv_data.setLayoutParams(params_tv);
            tv_data.setTextColor(Color.GRAY);

            Button btn_delete = new Button(this);
            btn_delete.setText("Удалить");
            LinearLayout.LayoutParams btn_params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            btn_delete.setLayoutParams(btn_params);
            btn_delete.setOnClickListener(v -> {
                list_nites.remove(list_nites.get(finalI));
                onLoad();
            });

            parrent.addView(ll);
            ll.addView(iv);
            ll.addView(ll_ver);
            ll_ver.addView(tv_name);
            ll_ver.addView(tv_data);
            ll_ver.addView(btn_delete);
        }
    }
}