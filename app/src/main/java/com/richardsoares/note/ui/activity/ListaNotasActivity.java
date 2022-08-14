package com.richardsoares.note.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.richardsoares.note.R;
import com.richardsoares.note.dao.NotaDAO;
import com.richardsoares.note.model.Nota;
import com.richardsoares.note.ui.recycler.adapter.ListaNotasAdapter;

import java.util.List;

public class ListaNotasActivity extends AppCompatActivity {

    private ListaNotasAdapter adapter;
    private List<Nota> todasNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_notas);
        todasNotas = notasExemplo();
        configuracaRecyclerView(todasNotas);

        TextView insereNotaBtn = findViewById(R.id.lista_notas_insere_nota);
        insereNotaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iniciaFormularioNota = new Intent(ListaNotasActivity.this, FormularioNotaActivity.class);
                startActivity(iniciaFormularioNota);
            }
        });
    }

    @Override
    protected void onResume() {
        NotaDAO dao =  new NotaDAO();
        todasNotas.clear();
        todasNotas.addAll(dao.todos());
        adapter.notifyDataSetChanged();
        super.onResume();
    }

    private List<Nota> notasExemplo() {
        NotaDAO dao = new NotaDAO();

        dao.insere(new Nota("Titulo 1", "Descrição 1"));
        dao.insere(new Nota("Titulo 2", "Descrição 2"));
        List<Nota> todasNotas = dao.todos();
        return todasNotas;
    }

    private void configuracaRecyclerView(List<Nota> todasNotas) {
        RecyclerView listaNotas = findViewById(R.id.lista_notas_recyclerview);
        configuraAdapter(todasNotas, listaNotas);
    }

    private void configuraAdapter(List<Nota> todasNotas, RecyclerView listaNotas) {
        adapter = new ListaNotasAdapter(this, todasNotas);
        listaNotas.setAdapter(adapter);
    }
}