package id.sch.smktelkom_mlg.project.xiirpl506162636.mamacatering.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.project.xiirpl506162636.mamacatering.R;
import id.sch.smktelkom_mlg.project.xiirpl506162636.mamacatering.model.Food;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    IFoodAdapter mIFoodAdapter;

    ArrayList<Food> foodList;

    public FoodAdapter(Context context, ArrayList<Food> foodList) {
        this.foodList = foodList;
        mIFoodAdapter = (IFoodAdapter) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.tvJudul.setText(food.judul);
        holder.tvDeskripsi.setText(food.deskripsi);
        holder.ivFoto.setImageURI(Uri.parse(food.foto));
    }

    @Override
    public int getItemCount() {
        if (foodList != null)
            return foodList.size();
        return 0;

    }

    public interface IFoodAdapter {
        void doClick(int pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivFoto;
        TextView tvJudul;
        TextView tvDeskripsi;

        public ViewHolder(View itemView) {
            super(itemView);
            ivFoto = (ImageView) itemView.findViewById(R.id.imageView);
            tvJudul = (TextView) itemView.findViewById(R.id.textViewJudul);
            tvDeskripsi = (TextView) itemView.findViewById(R.id.textViewDeskripsi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIFoodAdapter.doClick(getAdapterPosition());
                }
            });
        }
    }
}
