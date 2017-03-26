package fr.esilv.s8.tp_note.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.esilv.s8.tp_note.R;
import fr.esilv.s8.tp_note.interfaces.OnItemSelectedListener;
import fr.esilv.s8.tp_note.models.Items;
import fr.esilv.s8.tp_note.viewholders.ItemsViewHolder;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsViewHolder> {
    private final Items items;
    private OnItemSelectedListener onItemSelectedListener;

    public ItemsAdapter(Items items) {
        this.items = items;
    }

    @Override
    public ItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_item, parent, false);
        return new ItemsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsViewHolder holder, int position) {
        holder.setOnItemSelectedListener(onItemSelectedListener);
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items != null ? items.size() : 0;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }
}
