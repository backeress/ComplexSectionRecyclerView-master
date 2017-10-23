package com.complexsectionrecyclerview_master.sample.holder;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.complex_section_recyclerview.holer.BaseViewHolder;
import com.complexsectionrecyclerview_master.DLog;
import com.complexsectionrecyclerview_master.R;
import com.complexsectionrecyclerview_master.sample.model.Header;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sung on 2017-10-18.
 */

public class HeaderViewHolder extends BaseViewHolder<Header> {

    @Nullable @BindView(R.id.textView) TextView mTextView;

    public HeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static BaseViewHolder newInstance(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_header, parent, false);
        return new HeaderViewHolder(itemView);
    }

    @Override
    public void onBindView(Header header) {

        if( null == header || null == mTextView )
            return ;
        try{
            mTextView.setText(header.text);
        }catch(Exception ex){
            DLog.e(ex);
        }

    }



}
