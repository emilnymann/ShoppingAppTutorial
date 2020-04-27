package com.emilnymann.shoppingapp.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.emilnymann.shoppingapp.R;
import com.emilnymann.shoppingapp.persistence.DbTable;
import com.emilnymann.shoppingapp.persistence.ItemRepository;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    private List<DbTable> shoppingItems;

    public ItemAdapter(List<DbTable> items) {
        this.shoppingItems = items;
    }

    public void setData(List<DbTable> items) {
        this.shoppingItems = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DbTable dbTable = shoppingItems.get(position);

        holder.textViewItemName.setText(dbTable.item);
        holder.textViewItemCount.setText("Quantity: " + dbTable.quantity);
    }

    @Override
    public int getItemCount() {
        return shoppingItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textViewItemName;
        TextView textViewItemCount;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewItemName = itemView.findViewById(R.id.textViewItemName);
            this.textViewItemCount = itemView.findViewById(R.id.textViewItemCount);
            this.imageView = itemView.findViewById(R.id.imgDelete);

            imageView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            DbTable dbTable = shoppingItems.get(position);
            ItemRepository itemRepository = new ItemRepository(v.getContext());
            itemRepository.deleteItem(dbTable);
            Toast.makeText(v.getContext(), "Deleted!", Toast.LENGTH_SHORT).show();
        }
    }

}
