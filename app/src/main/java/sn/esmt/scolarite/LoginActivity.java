package sn.esmt.scolarite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {


    private EditText emailtxt;
    private EditText passwordtxt;
    private Button longinbt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Récupération des valeurs saisies par l'utilisateur
        emailtxt = (EditText) findViewById(R.id.emailtxt);
        passwordtxt = (EditText) findViewById(R.id.passwordtxt);

        //Récupération de l'Id du bouton login
        longinbt = (Button) findViewById(R.id.loginbt);

        longinbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailtxt.getText().toString();
                String pwd = passwordtxt.getText().toString();

                if (email.isEmpty() || pwd.isEmpty()) {
                    Toast.makeText(LoginActivity.this,
                            "Veuillez renseigner les champs",
                            Toast.LENGTH_LONG).show();
                }
                else {
                    if (email.equals("esmt@esmt.sn") && pwd.equals("123")) {
                        Intent i = new Intent(LoginActivity.this,
                                HomeActivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(LoginActivity.this,
                                "Email ou mot de passe incorrect",
                                Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

}