package com.example.myapplicationrecyclertest;

import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MultiTypeAdapter extends RecyclerView.Adapter {

    //定義三種常數,因為有三種類型
    private static final int TYPE_FULL_IMAGE = 0;
    private static final int TYPE_RIGHT_IMAGE = 1;
    private static final int TYPE_THREE_IMAGE = 2;


    private final List<MultiTypeBean> mData;

    public MultiTypeAdapter(List<MultiTypeBean> data) {
        this.mData = data;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        //根據viewType來創建條目, 條目才會不同
        View view;

        //TODO:這裡面去創建ViewHolder
        if (viewType ==TYPE_FULL_IMAGE){
            view = View.inflate( parent.getContext(),R.layout.item_type_full_image,null );
            return new FullImageHolder( view );

        }else if (viewType == TYPE_RIGHT_IMAGE){
            view = View.inflate( parent.getContext(),R.layout.item_type_right_image,null );
            return new RightImageHolder( view );

        }else {
            view = View.inflate( parent.getContext(),R.layout.item_type_three_image,null );
            return new ThreeImageHolder( view );
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //TODO:先不放數據
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }


    //覆寫方法,這個方法是根據條件來返回條目類型
    @Override
    public int getItemViewType(int position) {
        MultiTypeBean multiTypeBean = mData.get( position );
        if (multiTypeBean.type == 0) {
            return TYPE_FULL_IMAGE;
        } else if (multiTypeBean.type == 1) {
            return TYPE_RIGHT_IMAGE;
        } else {
            return TYPE_THREE_IMAGE;
        }
    }

    private class FullImageHolder extends RecyclerView.ViewHolder {

        public FullImageHolder(@NonNull View itemView) {
            super( itemView );


        }
    }

    private class RightImageHolder extends RecyclerView.ViewHolder {

        public RightImageHolder(@NonNull View itemView) {
            super( itemView );


        }
    }


    private class ThreeImageHolder extends RecyclerView.ViewHolder{

        public ThreeImageHolder(@NonNull View itemView) {
            super( itemView );
        }
    }

}