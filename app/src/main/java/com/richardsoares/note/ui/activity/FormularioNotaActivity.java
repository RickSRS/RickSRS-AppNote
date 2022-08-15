package com.richardsoares.note.ui.activity;

import static com.richardsoares.note.ui.activity.NotaActivityConstantes.CHAVE_INDEX;
import static com.richardsoares.note.ui.activity.NotaActivityConstantes.CHAVE_NOTA;
import static com.richardsoares.note.ui.activity.NotaActivityConstantes.CODIGO_RESULTADO_NOTA_CRIADA;
import static com.richardsoares.note.ui.activity.NotaActivityConstantes.INDEX_INVALIDO;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.richardsoares.note.R;
import com.richardsoares.note.model.Nota;

public class FormularioNotaActivity extends AppCompatActivity {

    private int indexRecebido = INDEX_INVALIDO;
    private TextView titulo;
    private TextView descricao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);
        inicializaCampos();

        Intent dadosRecebidos = getIntent();
        if (dadosRecebidos.hasExtra(CHAVE_NOTA)) {
            Nota notaRecebida = (Nota) dadosRecebidos.getSerializableExtra(CHAVE_NOTA);
            indexRecebido = dadosRecebidos.getIntExtra(CHAVE_INDEX, INDEX_INVALIDO);
            preencheCampos(notaRecebida);
        }
    }

    private void preencheCampos(Nota nota) {
        titulo.setText(nota.getTitulo());
        descricao.setText(nota.getDescricao());
    }

    private void inicializaCampos() {
        titulo = findViewById(R.id.formulario_nota_titulo);
        descricao = findViewById(R.id.formulario_nota_descricao);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_formulario_salva_nota, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (ehMenuSalvaNota(item)) {
            Nota notaCriada = criaNota();
            retornaNota(notaCriada);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void retornaNota(Nota notaCriada) {
        Intent resultadoInsercao = new Intent();
        resultadoInsercao.putExtra(CHAVE_NOTA, notaCriada);
        resultadoInsercao.putExtra(CHAVE_INDEX, indexRecebido);
        setResult(CODIGO_RESULTADO_NOTA_CRIADA, resultadoInsercao);
    }

    @NonNull
    private Nota criaNota() {
        Nota notaCriada = new Nota(titulo.getText().toString(), descricao.getText().toString());
        return notaCriada;
    }

    private boolean ehMenuSalvaNota(@NonNull MenuItem item) {
        return item.getItemId() == R.id.menu_formulario_ic_salva_nota;
    }
}