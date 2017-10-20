package com.complexsectionrecyclerview_master.sample.holder.custom;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.complexsectionrecyclerview_master.DLog;
import com.complexsectionrecyclerview_master.R;
import com.complexsectionrecyclerview_master.sample.model.Footer;
import com.complexsectionrecyclerview_master.sample.model.Item;
import com.complexsectionrecyclerview_master.sample.model.custom_item.ItemCustomObject;
import com.library.holer.BaseViewHolder;

/**
 * Created by sung on 2017-10-19.
 */

public class ItemCustomObjectViewHolder extends BaseViewHolder<Item> {

    private TextView mTextView;
    public ItemCustomObjectViewHolder(View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.textView);
    }

    public static BaseViewHolder newInstance(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_custom, parent, false);
        return new ItemCustomObjectViewHolder(itemView);
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
            //변환이 가능한 타입인지 체크 할 수 있다.
            DLog.d("obj instanceof ItemCustomObject : " + (obj instanceof ItemCustomObject));
            if(obj instanceof ItemCustomObject)
            {
                ItemCustomObject customObject = (ItemCustomObject)obj;
                mTextView.setText(customObject.text);
            }

        }

    }

}