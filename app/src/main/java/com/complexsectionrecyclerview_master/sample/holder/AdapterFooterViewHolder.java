package com.complexsectionrecyclerview_master.sample.holder;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.complex_section_recyclerview.holer.BaseViewHolder;
import com.complexsectionrecyclerview_master.DLog;
import com.complexsectionrecyclerview_master.R;
import com.complexsectionrecyclerview_master.sample.model.Footer;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sung on 2017-10-18.
 */

public class AdapterFooterViewHolder extends BaseViewHolder {

    @Nullable
    @BindView(R.id.textView) TextView mTextView;

    public static BaseViewHolder newInstance(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_adapter_footer, parent, false);
        return new AdapterFooterViewHolder(itemView);
    }


    public AdapterFooterViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void onBindView(Object item) {

        if( null == item )
            return ;

        try{
            //mTextView.setText(item.text);
        }catch(Exception ex){
            DLog.e(ex);
        }

    }

}
