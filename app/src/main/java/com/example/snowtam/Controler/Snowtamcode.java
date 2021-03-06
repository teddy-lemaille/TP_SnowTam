package com.example.snowtam.Controler;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Response;
import com.example.snowtam.Model.DataSearchSnow;
import com.example.snowtam.Model.SnowTam;
import com.example.snowtam.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Snowtamcode#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Snowtamcode extends Fragment {
    private static String airport;

    public Snowtamcode() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static Snowtamcode newInstance(String airport) {
        Snowtamcode fragment = new Snowtamcode();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        Snowtamcode.airport = airport;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_snowtamcode, container, false);
        TextView tv = (TextView) v.findViewById(R.id.snowtamcode);
        final Response.Listener<DataSearchSnow[]> rep = response -> {
            //tv.setText(response[0].getAll());// affichage brut moche
            if(response == null)
            {
                tv.setText(getString(R.string.NoResponse));
            }
            else {
                int y = 0;
                DataSearchSnow snow = null;
                while (snow == null && y < response.length) {
                    if (response[y].getEntity().equals("")) {
                        snow = response[y];
                    }
                    y++;
                }
                if (snow == null) {
                    tv.setText(getString(R.string.NoSnowtam));
                } else {
                    String texte = snow.getAll();
                    for (int i = 65; i <= 84; i++) // de A a T
                    {
                        int j = i;
                        char lettre = (char) i;
                        int premier = texte.indexOf(lettre + ") ");
                        int deuxieme;
                        do {
                            j++;
                            if (j < 84) {
                                char lettreArret = (char) (j);
                                deuxieme = texte.indexOf(lettreArret + ")");
                            } else {
                                deuxieme = texte.indexOf("CREATED");
                            }
                        } while (deuxieme == -1);
                        i = j - 1;
                        String sub = texte.substring(premier, deuxieme);
                        tv.setText(tv.getText() + sub);
                    }
                }
            }
        };
        final Response.ErrorListener errorListener = error -> {
            Log.e("Erreur","erreur");
        };
       // SnowTam.getSnowtam(v.getContext(),airport,rep,errorListener);
        String texte = getString(R.string.SnowtamDure);
        for (int i = 65; i <= 84; i++) // de A a T
        {
            int j = i;
            char lettre = (char) i;
            int premier = texte.indexOf(lettre + ") ");
            int deuxieme;
            do {
                j++;
                if (j < 84) {
                    char lettreArret = (char) (j);
                    deuxieme = texte.indexOf(lettreArret + ")");
                } else {
                    deuxieme = texte.indexOf("CREATED");
                }
            } while (deuxieme == -1);
            i = j - 1;
            String sub = texte.substring(premier, deuxieme);
            tv.setText(tv.getText() + sub);
        }

        return v;
    }


}