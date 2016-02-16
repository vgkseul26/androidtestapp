package com.usv.androidtestapp.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.usv.androidtestapp.R;
import com.usv.androidtestapp.model.Record;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {
    private List<Record> dataSet;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView textView;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.cardView);
            textView = (TextView) itemView.findViewById(R.id.text_rec);
            imageView = (ImageView) itemView.findViewById(R.id.photo);
        }
    }

    public PostAdapter(List<Record> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public PostAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.imageView.setImageResource(dataSet.get(position).getPhotoId());
        holder.imageView.setImageURI(dataSet.get(position).getPhoto());
        holder.textView.setText(dataSet.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
