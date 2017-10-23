package com.complexsectionrecyclerview_master.sample.holder;

import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.complex_section_recyclerview.holer.BaseViewHolder;
import com.complexsectionrecyclerview_master.DLog;
import com.complexsectionrecyclerview_master.R;
import com.complexsectionrecyclerview_master.sample.model.custom_item.ItemCustomObject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sung on 2017-10-18.
 */

public class AdapterHeaderViewHolder extends BaseViewHolder {

    @Nullable @BindView(R.id.textView) TextView mTextView;

    public AdapterHeaderViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public static BaseViewHolder newInstance(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_adapter_header, parent, false);
        return new AdapterHeaderViewHolder(itemView);
    }

    @Override
    public void onBindView(Object obj) {

        if( null == obj || null == mTextView )
            return ;
        try{

            //변환 할 타입 이름을 확인 할 수 있다
            DLog.d("obj.getClass().getName() : "+obj.getClass().getName());
            //변환이 가능한 타입인지 체크 할 수 있다.
            DLog.d("obj instanceof ItemCustomObject : " + (obj instanceof ItemCustomObject));
            if(obj instanceof ItemCustomObject)
            {
                ItemCustomObject customObject = (ItemCustomObject)obj;
                mTextView.setText(customObject.text);
            }


        }catch(Exception ex){
            DLog.e(ex);
        }

    }



}
