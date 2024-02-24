package sn.esmt.scolarite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import sn.esmt.scolarite.http.Api;
import sn.esmt.scolarite.http.EtudiantResponse;

public class ListeEtudiantActivity extends AppCompatActivity {
    private ListView listEtu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_etudiant);

        listEtu = findViewById(R.id.listEtu);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.12:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<List<EtudiantResponse>> call = api.getAllEtudiants();
        call.enqueue(new Callback<List<EtudiantResponse>>() {
            @Override
            public void onResponse(Call<List<EtudiantResponse>> call, Response<List<EtudiantResponse>> response) {
                if (response.isSuccessful()) {
                    // Récupération de la liste des étudiants depuis la réponse
                    List<EtudiantResponse> etudiants = response.body();

                    // Création d'un adaptateur personnalisé pour afficher la liste des étudiants dans la ListView
                    EtudiantListAdapter adapter = new EtudiantListAdapter(ListeEtudiantActivity.this, etudiants);

                    // Liaison de l'adaptateur à la ListView
                    listEtu.setAdapter(adapter);
                } else {
                    // Gérer les réponses d'erreur ici
                    Toast.makeText(ListeEtudiantActivity.this, "Erreur lors de la récupération des étudiants", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<EtudiantResponse>> call, Throwable t) {
                Toast.makeText(ListeEtudiantActivity.this, "Impossible de contacter le serveur", Toast.LENGTH_SHORT).show();
            }
        });

    }
}