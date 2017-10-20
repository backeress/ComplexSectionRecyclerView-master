package com.complex_section_recyclerview.adater;

import android.view.ViewGroup;

import com.complex_section_recyclerview.holer.BaseViewHolder;


/**
 * Created by sung on 2017-10-12.
 */

public interface IAdapter<HEADER,ITEM,FOOTER> {

    //뷰 생성
    BaseViewHolder onCreateHeaderViewHolder(ViewGroup parent, int viewType);
    BaseViewHolder onCreateItemViewHolder(ViewGroup parent, int viewType);
    BaseViewHolder onCreateFooterViewHolder(ViewGroup parent, int viewType);
    //뷰 바인드
    void onBindHeaderViewHolder(BaseViewHolder viewHolder, int sectionIndex, int headerUserType, HEADER item);
    void onBindItemViewHolder(BaseViewHolder viewHolder, int sectionIndex, int itemIndex, int itemUserType, ITEM item);
    void onBindFooterViewHolder(BaseViewHolder viewHolder, int sectionIndex, int footerUserType, FOOTER item);

}
