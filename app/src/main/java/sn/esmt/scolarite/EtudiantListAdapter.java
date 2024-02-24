package sn.esmt.scolarite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import sn.esmt.scolarite.http.EtudiantResponse;

public class EtudiantListAdapter extends ArrayAdapter<EtudiantResponse> {

    private List<EtudiantResponse> etudiants;
    private Context context;

    public EtudiantListAdapter(Context context, List<EtudiantResponse> etudiants) {
        super(context, R.layout.list_item_layout, etudiants);
        this.context = context;
        this.etudiants = etudiants;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_layout, parent, false);
        }

        // Récupérer les éléments de la mise en page
        TextView textViewMatricule = view.findViewById(R.id.textViewMatricule);
        TextView textViewNomPrenom = view.findViewById(R.id.textViewNomPrenom);

        // Récupérer l'étudiant actuel
        EtudiantResponse etudiant = etudiants.get(position);

        // Afficher les informations de l'étudiant dans les TextView correspondants
        textViewMatricule.setText("Matricule: " + etudiant.getMat());
        textViewNomPrenom.setText("Nom et prénom: " + etudiant.getNom() + " " + etudiant.getPrenom());

        return view;
    }



}
