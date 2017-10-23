package com.complexsectionrecyclerview_master.sample.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.complex_section_recyclerview.adater.ComplexSectionRecyclerView;
import com.complex_section_recyclerview.holer.BaseViewHolder;
import com.complexsectionrecyclerview_master.DLog;
import com.complexsectionrecyclerview_master.sample.holder.AdapterFooterViewHolder;
import com.complexsectionrecyclerview_master.sample.holder.AdapterHeaderViewHolder;
import com.complexsectionrecyclerview_master.sample.holder.FooterViewHolder;
import com.complexsectionrecyclerview_master.sample.holder.HeaderViewHolder;
import com.complexsectionrecyclerview_master.sample.holder.ItemViewHolder;
import com.complexsectionrecyclerview_master.sample.holder.custom.ItemCustomNumberViewHolder;
import com.complexsectionrecyclerview_master.sample.holder.custom.ItemCustomObjectViewHolder;
import com.complexsectionrecyclerview_master.sample.holder.custom.ItemCustomStringViewHolder;
import com.complexsectionrecyclerview_master.sample.model.Footer;
import com.complexsectionrecyclerview_master.sample.model.Header;
import com.complexsectionrecyclerview_master.sample.model.Item;
import com.complexsectionrecyclerview_master.sample.model.Section;

import java.util.ArrayList;

/**
 * Created by sung on 2017-10-18.
 */

public class ComplexSectionRecyclerViewAdapter extends ComplexSectionRecyclerView<Section,Header,Item, Footer> {

    //커스텀 아이템 뷰 번호
    public static final int  USER_ITEM_TYPE_CUSTOM_STRING = 100;
    public static final int  USER_ITEM_TYPE_CUSTOM_NUMBER = 101;
    public static final int  USER_ITEM_TYPE_CUSTOM_OBJECT = 102;

    /**
     * 생성자
     *
     * @param context  : 컨텍스트
     * @param sections : 섹션 목록
     */
    public ComplexSectionRecyclerViewAdapter(Context context, RecyclerView recyclerView, ArrayList<Section> sections) {
        super(context, recyclerView, sections);
    }

    /**
     * 아답터 헤더뷰 생성
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateAdapterHeaderViewHolder(ViewGroup parent, int viewType) {
        return AdapterHeaderViewHolder.newInstance(parent);
    }

    /**
     * 아답터 푸터뷰 생성
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateAdapterFooterViewHolder(ViewGroup parent, int viewType) {
        return AdapterFooterViewHolder.newInstance(parent);
    }



    /**
     * 섹션 헤더뷰 생성
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {

        try{

            //기본 뷰 생성
            if( viewType == super.getBaseViewTypeBySection() )
            {
                return HeaderViewHolder.newInstance(parent);
            }
            else{

                //커스텀 뷰 생성
                switch (viewType)
                {
                    //일치하는 뷰가 존재하지 않는다면, 예외를 발생 시킨다.
                    default:
                        throw new IllegalArgumentException("[에러] onCreateHeaderViewHolder 인식할 수 없는 viewType: " + viewType);
                }
            }

        }
        catch(Exception ex)
        {
            DLog.e(ex);
        }

        return null;
    }

    /**
     * 섹션 아이템뷰 생성
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateSectionItemViewHolder(ViewGroup parent, int viewType) {
        //DLog.d("아이템뷰 생성 : "+viewType);

        try{

            //기본 뷰 생성
            if( viewType == super.getBaseViewTypeBySection() )
            {
                return ItemViewHolder.newInstance(parent);
            }
            else{

                //기본 뷰 및 커스텀 생성
                switch (viewType)
                {
                    //커스텀 아이템 뷰
                    case USER_ITEM_TYPE_CUSTOM_STRING:
                        return ItemCustomStringViewHolder.newInstance(parent);
                    case USER_ITEM_TYPE_CUSTOM_NUMBER:
                        return ItemCustomNumberViewHolder.newInstance(parent);
                    case USER_ITEM_TYPE_CUSTOM_OBJECT:
                        return ItemCustomObjectViewHolder.newInstance(parent);

                    //일치하는 뷰가 존재하지 않는다면, 예외를 발생 시킨다.
                    default:
                        throw new IllegalArgumentException("[에러] onCreateItemViewHolder 인식할 수 없는 viewType: " + viewType);
                }
            }

        }
        catch(Exception ex)
        {
            DLog.e(ex);
        }
        return null;

    }

    /**
     * 섹션 푸터뷰 생성
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        //DLog.d("푸터 생성");

        try{

            //기본 뷰 생성
            if( viewType == super.getBaseViewTypeBySection() )
            {
                return FooterViewHolder.newInstance(parent);
            }
            else{

                //커스텀 뷰 생성
                switch (viewType)
                {
                    //일치하는 뷰가 존재하지 않는다면, 예외를 발생 시킨다.
                    default:
                        throw new IllegalArgumentException("[에러] onCreateFooterViewHolder 인식할 수 없는 viewType: " + viewType);
                }
            }

        }
        catch(Exception ex)
        {
            DLog.e(ex);
        }
        return null;

    }


    /**
     * 아답터 헤더뷰 바인드
     *
     * @param viewHolder
     * @param adapterHeader
     */
    @Override
    public void onBindAdapterHeaderViewHolder(BaseViewHolder viewHolder, Object adapterHeader) {
        //헤더뷰 바인드
        viewHolder.onBindView(adapterHeader);
    }


    /**
     * 아답터 푸터뷰 바인드
     * @param viewHolder
     * @param adapterFooter
     */
    @Override
    public void onBindAdapterFooterViewHolder(BaseViewHolder viewHolder, Object adapterFooter) {
        viewHolder.onBindView(adapterFooter);
    }

    /**
     * 섹션 헤더뷰 바인드
     * @param viewHolder
     * @param sectionIndex
     * @param headerUserType
     * @param header
     */
    @Override
    public void onBindSectionHeaderViewHolder(BaseViewHolder viewHolder, int sectionIndex, int headerUserType, Header header) {
        viewHolder.onBindView(header);
    }

    /**
     * 섹션 아이템뷰 바인드
     * @param viewHolder
     * @param sectionIndex
     * @param itemIndex
     * @param itemUserType
     * @param item
     */
    @Override
    public void onBindSectionItemViewHolder(BaseViewHolder viewHolder, int sectionIndex, int itemIndex, int itemUserType, Item item) {
        viewHolder.onBindView(item);

    }

    /**
     * 섹션 푸터뷰 바인드
     * @param viewHolder
     * @param sectionIndex
     * @param footerUserType
     * @param footer
     */
    @Override
    public void onBindSectionFooterViewHolder(BaseViewHolder viewHolder, int sectionIndex, int footerUserType, Footer footer) {
        viewHolder.onBindView(footer);

    }
}
