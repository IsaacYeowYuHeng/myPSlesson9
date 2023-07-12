package sg.edu.rp.c346.id22021538.mypslesson9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class editSongs extends AppCompatActivity {

    Button update, delete, cancel;
    EditText sTitle, singers, yor, id;
    RadioGroup rating;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_songs);
        update = findViewById(R.id.update);
        delete = findViewById(R.id.delete);
        cancel = findViewById(R.id.cancel);
        id = findViewById(R.id.editSongID);
        sTitle = findViewById(R.id.editTitle);
        singers = findViewById(R.id.editSinger);
        yor = findViewById(R.id.editYear);
        rating = findViewById(R.id.editStar);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        id.setText(""+data.getId());
        sTitle.setText(data.getTitle());
        singers.setText(data.getSinger());
        yor.setText(""+data.getYear());
        String stars = data.getStars();
        if(stars.equals("1")){
            rating.check(R.id.I);
        } else if (stars.equals("2")) {
            rating.check(R.id.II);
        }else if (stars.equals("3")) {
            rating.check(R.id.III);
        }else if (stars.equals("4")) {
            rating.check(R.id.IV);
        }else {
            rating.check(R.id.V);
        }

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(editSongs.this);
                data.setSinger(singers.getText().toString());
                data.setTitle(sTitle.getText().toString());
                data.setYear(Integer.parseInt(yor.getText().toString()));
                int ratingID = rating.getCheckedRadioButtonId();
                String star = "";
                if (ratingID == R.id.I) {
                    star = "1";
                } else if (ratingID == R.id.II) {
                    star = "2";
                } else if (ratingID == R.id.III) {
                    star = "3";
                } else if (ratingID == R.id.IV) {
                    star = "4";
                } else {
                    star = "5";
                }
                data.setStars(star);
                dbh.updateSong(data);
                dbh.close();
                Toast.makeText(editSongs.this, "Update successful", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(editSongs.this, list.class);
                startActivity(i);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(editSongs.this);
                dbh.deleteSong(data.getId());
                Toast.makeText(editSongs.this, "Delete successful", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(editSongs.this, list.class);
                startActivity(i);
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(editSongs.this, list.class);
                startActivity(i);
                Toast.makeText(editSongs.this, "Cancelled", Toast.LENGTH_SHORT).show();
            }
        });
    }
}