package com.richardsoares.note.ui.recycler.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.richardsoares.note.R;
import com.richardsoares.note.model.Nota;
import com.richardsoares.note.ui.recycler.adapter.listener.OnItemClickListener;

import java.util.Collections;
import java.util.List;

public class ListaNotasAdapter extends RecyclerView.Adapter<ListaNotasAdapter.NotaViewHolder> {

    private final List<Nota> notas;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public ListaNotasAdapter(Context context, List<Nota> notas) {
        this.context = context;
        this.notas = notas;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ListaNotasAdapter.NotaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewCriada = LayoutInflater.from(context).inflate(R.layout.item_nota, parent, false);
        return new NotaViewHolder(viewCriada);
    }

    @Override
    public void onBindViewHolder(ListaNotasAdapter.NotaViewHolder holder, int index) {
        Nota nota = notas.get(index);
        holder.vincula(nota);
    }

    @Override
    public int getItemCount() {
        return notas.size();
    }

    public void altera(int index, Nota nota) {
        notas.set(index, nota);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        notas.remove(index);
        notifyItemRemoved(index);
    }

    public void troca(int indexInicial, int indexFinal) {
        Collections.swap(notas, indexInicial, indexFinal);
        notifyItemMoved(indexInicial, indexFinal);
    }

    class NotaViewHolder extends RecyclerView.ViewHolder {
        private final TextView titulo;
        private final TextView descricao;
        private Nota nota;

        public NotaViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_nota_titulo);
            descricao = itemView.findViewById(R.id.item_nota_descricao);
            itemView.setOnClickListener(view -> onItemClickListener.onItemClick(nota, getAdapterPosition()));
        }

        public void vincula(Nota nota) {
            this.nota = nota;
            preencheCampos(nota);
        }

        private void preencheCampos(Nota nota) {
            titulo.setText(nota.getTitulo());
            descricao.setText(nota.getDescricao());
        }
    }

    public void adiciona(Nota nota) {
        notas.add(nota);
        notifyDataSetChanged();
    }
}
