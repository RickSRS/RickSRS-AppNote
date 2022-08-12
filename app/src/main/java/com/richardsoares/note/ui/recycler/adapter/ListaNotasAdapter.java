package com.richardsoares.note.ui.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.richardsoares.note.R;
import com.richardsoares.note.model.Nota;

import java.util.List;

public class ListaNotasAdapter extends RecyclerView.Adapter<ListaNotasAdapter.NotaViewHolder> {

    private List<Nota> notas;
    private Context context;

    public ListaNotasAdapter(Context context, List<Nota> notas) {
        this.context = context;
        this.notas = notas;
    }

    @Override
    public ListaNotasAdapter.NotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false);
        return new NotaViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(ListaNotasAdapter.NotaViewHolder holder, int index) {
        Nota nota = notas.get(index);
        NotaViewHolder notaViewHolder = (NotaViewHolder) holder;
        notaViewHolder.vincula(nota);
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    class NotaViewHolder extends RecyclerView.ViewHolder {
        private final TextView titulo;
        private final TextView descricao;

        public NotaViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_nota_titulo);
            descricao = itemView.findViewById(R.id.item_nota_descricao);
        }

        public void vincula(Nota nota) {
            titulo.setText(nota.getTitulo());
            descricao.setText(nota.getDescricao());
        }
    }
}
