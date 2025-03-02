package com.example.onlinestoreapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestoreapp.Interface.ItemClickDeleteListener;
import com.example.onlinestoreapp.R;
import com.example.onlinestoreapp.model.DonHang;
import com.example.onlinestoreapp.utils.Utils;

import java.util.List;

public class DonHangAdapter extends RecyclerView.Adapter<DonHangAdapter.MyViewHolder> {
    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    Context context;
    List<DonHang> listdonhang;
    ItemClickDeleteListener deleteListener;

    public DonHangAdapter(Context context, List<DonHang> listdonhang, ItemClickDeleteListener itemClickDeleteListener) {
        this.context = context;
        this.listdonhang = listdonhang;
        this.deleteListener = itemClickDeleteListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_donhang, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DonHang donHang = listdonhang.get(position);
        holder.txtdonhang.setText("Order: " + donHang.getId());
        holder.txttrangthai.setText(Utils.statusOrder(donHang.getTrangthai()));
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                deleteListener.onClickDelete(donHang.getId());
                return false;
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.rechitiet.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(donHang.getItems().size());
        //adapter chitiet
        ChiTietAdapter chiTietAdapter = new ChiTietAdapter(context, donHang.getItems());
        holder.rechitiet.setLayoutManager(layoutManager);
        holder.rechitiet.setAdapter(chiTietAdapter);
        holder.rechitiet.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return listdonhang.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtdonhang, txttrangthai;
        RecyclerView rechitiet;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtdonhang = itemView.findViewById(R.id.iddonhang);
            txttrangthai = itemView.findViewById(R.id.trangthaidon);
            rechitiet = itemView.findViewById(R.id.recycleview_chitiet);
        }
    }
}
