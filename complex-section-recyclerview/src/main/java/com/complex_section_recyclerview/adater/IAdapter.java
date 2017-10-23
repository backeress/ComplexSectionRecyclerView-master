package com.complex_section_recyclerview.adater;

import android.view.ViewGroup;

import com.complex_section_recyclerview.holer.BaseViewHolder;


/**
 * Created by sung on 2017-10-12.
 */

public interface IAdapter<HEADER,ITEM,FOOTER> {

    //[아답터/섹션 헤더/아이템/푸터 뷰]
    BaseViewHolder onCreateAdapterHeaderViewHolder(ViewGroup parent, int viewType);
    BaseViewHolder onCreateAdapterFooterViewHolder(ViewGroup parent, int viewType);
    BaseViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType);
    BaseViewHolder onCreateSectionItemViewHolder(ViewGroup parent, int viewType);
    BaseViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType);
    void onBindAdapterHeaderViewHolder(BaseViewHolder viewHolder, Object adapterHeader);
    void onBindAdapterFooterViewHolder(BaseViewHolder viewHolder, Object adapterFooter);
    void onBindSectionHeaderViewHolder(BaseViewHolder viewHolder, int sectionIndex, int headerUserType, HEADER header);
    void onBindSectionItemViewHolder(BaseViewHolder viewHolder, int sectionIndex, int itemIndex, int itemUserType, ITEM item);
    void onBindSectionFooterViewHolder(BaseViewHolder viewHolder, int sectionIndex, int footerUserType, FOOTER footer);

}
