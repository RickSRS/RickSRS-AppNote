package com.richardsoares.note.ui.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.richardsoares.note.R;
import com.richardsoares.note.dao.NotaDAO;
import com.richardsoares.note.model.Nota;
import com.richardsoares.note.ui.recycler.adapter.ListaNotasAdapter;

import java.util.List;

public class ListaNotasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);
        List<Nota> todasNotas = notasExemplo();
        configuracaRecyclerView(todasNotas);
    }

    private List<Nota> notasExemplo() {
        NotaDAO dao = new NotaDAO();
        for (int i = 1; i < 10000; i++) {
            dao.insere(new Nota("Titulo" + i, "Descrição" + i));
        }
        List<Nota> todasNotas = dao.todos();
        return todasNotas;
    }

    private void configuracaRecyclerView(List<Nota> todasNotas) {
        RecyclerView listaNotas = findViewById(R.id.lista_notas_recyclerview);
        configuraAdapter(todasNotas, listaNotas);
    }

    private void configuraAdapter(List<Nota> todasNotas, RecyclerView listaNotas) {
        listaNotas.setAdapter(new ListaNotasAdapter(this, todasNotas));
    }
}