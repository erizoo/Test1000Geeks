package com.a1000geeks.test.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.a1000geeks.test.R;
import com.a1000geeks.test.data.models.ResponseCars;
import com.a1000geeks.test.ui.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CarsAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public Context context;
    private List<ResponseCars> responseCars = new ArrayList<>();
    private Callback callback;

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CarsAdapter.CarsViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.items_cars_rv, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return responseCars.size();
    }

    public void setItems(List<ResponseCars> responseCars) {
        this.responseCars.clear();
        this.responseCars.addAll(responseCars);
        notifyDataSetChanged();
    }

    public interface Callback{

        void updateRecord(ResponseCars responseCars);

    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public class CarsViewHolder extends BaseViewHolder {

        @BindView(R.id.layout)
        LinearLayout layout;
        @BindView(R.id.name_manufacturer)
        TextView nameManufacturer;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.price)
        TextView price;

        public CarsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = itemView.getContext();
        }

        @Override
        public void onBind(int position) {
            nameManufacturer.setText(responseCars.get(position).getNameManufacturer());
            name.setText(responseCars.get(position).getName());
            String priceString = responseCars.get(position).getPrice() + " " + "$";
            price.setText(priceString);
            layout.setOnClickListener(v -> {
                callback.updateRecord(responseCars.get(position));
            });
        }
    }
}
