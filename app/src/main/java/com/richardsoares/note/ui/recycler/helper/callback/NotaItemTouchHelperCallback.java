package com.richardsoares.note.ui.recycler.helper.callback;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.richardsoares.note.dao.NotaDAO;
import com.richardsoares.note.ui.recycler.adapter.ListaNotasAdapter;

public class NotaItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ListaNotasAdapter adapter;

    public NotaItemTouchHelperCallback(ListaNotasAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int marcacoesDeDeslize = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int marcacoesDeArrasta = ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(marcacoesDeArrasta, marcacoesDeDeslize);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int indexInicial = viewHolder.getAdapterPosition();
        int indexFinal = target.getAdapterPosition();
        trocaNotas(indexInicial, indexFinal);
        return true;
    }

    private void trocaNotas(int indexInicial, int indexFinal) {
        new NotaDAO().troca(indexInicial, indexFinal);
        adapter.troca(indexInicial, indexFinal);
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int indexNotaDeslize = viewHolder.getAdapterPosition();
        removeNota(indexNotaDeslize);
    }

    private void removeNota(int index) {
        new NotaDAO().remove(index);
        adapter.remove(index);
    }
}
