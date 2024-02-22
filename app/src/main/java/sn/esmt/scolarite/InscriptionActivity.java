package sn.esmt.scolarite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sn.esmt.scolarite.http.Api;
import sn.esmt.scolarite.http.EtudiantResponse;

public class InscriptionActivity extends AppCompatActivity {

    private EditText matrtxt;
    private EditText nametxt;
    private EditText prenomtxt;
    private EditText addrtxt;
    private EditText teltxt;
    private EditText fraistxt;
    private Button registerstbt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscription);

        matrtxt = (EditText) findViewById(R.id.matrtxt);
        nametxt = (EditText) findViewById(R.id.nametxt);
        prenomtxt = (EditText) findViewById(R.id.prenomtxt);
        addrtxt = (EditText) findViewById(R.id.addrtxt);
        teltxt = (EditText) findViewById(R.id.teltxt);
        fraistxt = (EditText) findViewById(R.id.fraistxt);
        registerstbt = (Button) findViewById(R.id.registerstbt);

        registerstbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://192.168.137.38:8082")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                Api api = retrofit.create(Api.class);

                //Création d'un objet etudiant à insérer dans la base
                EtudiantResponse e = new EtudiantResponse();
                e.setMat(matrtxt.getText().toString());
                e.setNom(nametxt.getText().toString());
                e.setPrenom(prenomtxt.getText().toString());
                e.setAdr(addrtxt.getText().toString());
                e.setTel(Integer.parseInt(teltxt.getText().toString()));
                e.setFrais(Double.parseDouble(fraistxt.getText().toString()));

                //Appel de l'API
                Call<EtudiantResponse> callSave = api.saveEtudiant(e);

                callSave.enqueue(new Callback<EtudiantResponse>() {
                    @Override
                    public void onResponse(Call<EtudiantResponse> call, Response<EtudiantResponse> response) {
                            Toast.makeText(InscriptionActivity.this,
                                    "Etudiant inscrit avec succès",
                                    Toast.LENGTH_LONG).show();

                    }
                    @Override
                    public void onFailure(Call<EtudiantResponse> call, Throwable t) {
                        Toast.makeText(InscriptionActivity.this,
                                "Impossible d'accéder au serveur",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

}