package fr.esilv.s8.tp_note.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import fr.esilv.s8.tp_note.R;
import fr.esilv.s8.tp_note.interfaces.OnItemSelectedListener;
import fr.esilv.s8.tp_note.models.Item;

public class ItemsViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView description;
    private TextView date;
    private TextView author;
    private ImageView thumbnails;
    private OnItemSelectedListener onItemSelectedListener;

    public ItemsViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
        description = (TextView) itemView.findViewById(R.id.description);
        //date = (TextView) itemView.findViewById(R.id.date);
        author = (TextView) itemView.findViewById(R.id.author);
        thumbnails = (ImageView) itemView.findViewById(R.id.thumbnails);
    }

    public void bind(final Item item) {
        title.setText(item.getSnippet().getTitle());
        description.setText(item.getSnippet().getDescription());
        //date.setText(item.getSnippet().getPublishedAt());
        author.setText(item.getSnippet().getChannelTitle());
        Picasso.with(itemView.getContext()).load(item.getSnippet().getThumbnails().getMedium().getUrl()).into(thumbnails);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemSelectedListener == null) {
                    return;
                }
                onItemSelectedListener.onItemSelected(item);
            }
        });
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }
}

