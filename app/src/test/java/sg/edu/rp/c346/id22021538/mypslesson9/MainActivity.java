package sg.edu.rp.c346.id22021538.mypslesson9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button insert, show;
    EditText sTitle, singers, yor;
    RadioGroup rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = findViewById(R.id.insert);
        show = findViewById(R.id.display);
        sTitle = findViewById(R.id.title);
        singers = findViewById(R.id.singer);
        yor = findViewById(R.id.year);
        rating = findViewById(R.id.star);

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper db = new DBHelper(MainActivity.this);
                int ratingID = rating.getCheckedRadioButtonId();
                String star = "";
                if(ratingID == R.id.one){
                    star = "1";
                }else if(ratingID == R.id.two){
                    star = "2";
                }else if(ratingID == R.id.three){
                    star = "3";
                }else if(ratingID == R.id.four){
                    star = "4";
                }else{
                    star = "5";
                }
                db.insertSong(sTitle.getText().toString(), singers.getText().toString(), Integer.parseInt(yor.getText().toString()), star);
                db.close();
                Toast.makeText(MainActivity.this, "New song inserted successfully", Toast.LENGTH_SHORT).show();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, list.class);
                startActivity(intent);
            }
        });


    }

}