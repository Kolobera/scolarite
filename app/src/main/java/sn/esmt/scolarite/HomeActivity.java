package sn.esmt.scolarite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity {

    private Button registerbt;
    private Button viewbt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        registerbt = (Button) findViewById(R.id.registerbt);
        viewbt = (Button) findViewById(R.id.viewbt);

        registerbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,
                        InscriptionActivity.class);
                startActivity(i);
            }
        });

        viewbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomeActivity.this,
                        ListeEtudiantActivity.class);
                startActivity(i);

            }
        });
    }
}