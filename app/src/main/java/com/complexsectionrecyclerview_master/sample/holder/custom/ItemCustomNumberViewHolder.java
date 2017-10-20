package com.complexsectionrecyclerview_master.sample.holder.custom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.complex_section_recyclerview.holer.BaseViewHolder;
import com.complexsectionrecyclerview_master.DLog;
import com.complexsectionrecyclerview_master.R;
import com.complexsectionrecyclerview_master.sample.model.Item;


/**
 * Created by sung on 2017-10-18.
 */

public class ItemCustomNumberViewHolder extends BaseViewHolder<Item> {

    private TextView mTextView;

    public ItemCustomNumberViewHolder(View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.textView);
    }

    public static BaseViewHolder newInstance(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_custom, parent, false);
        return new ItemCustomNumberViewHolder(itemView);
    }

    @Override
    public void onBindView(Item item) {

        if( null == item )
            return ;

        Object obj = item.getCustomData();
        if(null != obj )
        {
            //변환 할 타입 이름을 확인 할 수 있다
            DLog.d("obj.getClass().getName() : "+obj.getClass().getName());
            mTextView.setText("커스텀 숫자 "+(int)obj);
        }


    }

}