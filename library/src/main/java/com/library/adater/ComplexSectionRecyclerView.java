package com.library.adater;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.library.decoration.StickyHeaderDecoration;
import com.library.holer.BaseViewHolder;
import com.library.model.data.BaseFooter;
import com.library.model.data.BaseHeader;
import com.library.model.data.BaseItem;
import com.library.model.section.BaseSection;

import java.util.ArrayList;


/**
 * Created by sung on 2017-10-10.
 */

public abstract class ComplexSectionRecyclerView<SECTION extends BaseSection, HEADER extends BaseHeader, ITEM extends BaseItem, FOOTER extends BaseFooter>
        extends RecyclerView.Adapter<BaseViewHolder> implements IAdapter<HEADER,ITEM,FOOTER>
{


    //섹션 뷰타입
    public static final int BASE_SECTION_VIEW_TYPE_HEADER = 0;
    public static final int BASE_SECTION_VIEW_TYPE_ITEM = 1;
    public static final int BASE_SECTION_VIEW_TYPE_FOOTER = 2;

    //컨텍스트
    Context context;
    //리사이클뷰
    RecyclerView recyclerView;
    //섹션 목록
    private ArrayList<SECTION> sections;
    //스티키 데코레이션
    StickyHeaderDecoration stickyHeaderDecoration;


    /**
     * 생성자
     * @param context : 컨텍스트
     * @param sections : 섹션 목록
     */
    public ComplexSectionRecyclerView(Context context, RecyclerView recyclerView, ArrayList<SECTION> sections)
    {
        this.context = context;
        this.recyclerView = recyclerView;
        this.sections = sections;

        //
        //Log.e("Dlog","ComplexSectionRecyclerView() sections.size() : "+sections.size());

        /*
        섹션을 위치를 배열에 미리 기억 시킬 경우
        속도나 성능적으로 좋을 수 있으나 유지 관리는 별로인 겻 같다.
        사용하지 않고, 이런 방법도 가능하다는 정도의 주석으로 남겨 둔다.

        private int[] arrSectionPositionByAdapterPosition;
        private int[] arrSectionItemPositionByAdapterPosition;
        this.arrSectionPositionByAdapterPosition = new int[totalCount];
        this.arrSectionItemPositionByAdapterPosition = new int[totalCount];

         int totalCount = 0;
        for ( SECTION section : this.sections)
            totalCount += section.getSectionItemsTotal();

        int sectionNumbers = 0;
        for (int sectionIndex = 0; sectionIndex < this.sections.size(); sectionIndex++) {
            SECTION section = sections.get(sectionIndex);

            //섹션 하위의 모든 아이템은 부모의 섹션 인덱스를 가진다.
            for (int itemIndex = 0; itemIndex < section.getSectionItemsTotal(); itemIndex++) {
                this.arrSectionPositionByAdapterPosition[sectionNumbers + itemIndex] = sectionIndex;
                this.arrSectionItemPositionByAdapterPosition[sectionNumbers + itemIndex] = itemIndex;
            }
            sectionNumbers += section.getSectionItemsTotal();
        }*/

    }



    /**
     * 뷰홀더 생성
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        try{

            //베이스 타입
            // - 섹션 헤더/아이템/푸터
            int baseViewType = unmaskBaseViewType(viewType);
            //유저 타입
            // - 세션 헤더/아이템/푸터의 뷰타입을 유저 뷰타입으로 출력 한다.
            int userViewType = unmaskUserViewType(viewType);

            //Log.e("Dlog","onCreateViewHolder() viewType : "+viewType);
            //Log.e("Dlog","onCreateViewHolder() userViewType : "+userViewType);

            switch (baseViewType) {
                case BASE_SECTION_VIEW_TYPE_HEADER:
                    //DLog.e("헤더 뷰 생성");
                    return onCreateHeaderViewHolder(parent, userViewType);
                case BASE_SECTION_VIEW_TYPE_ITEM:
                    //DLog.e("아이템 뷰 생성");
                    return onCreateItemViewHolder(parent, userViewType);
                case BASE_SECTION_VIEW_TYPE_FOOTER:
                    return onCreateFooterViewHolder(parent, userViewType);
            }

        }catch(Exception ex){
            throw ex;
        }

        throw new IndexOutOfBoundsException("알수없는 타입 : " + viewType + " 헤더/아이템/푸터 타입이 아닙니다.");
    }


    /**
     * 뷰홀더 데이터 반인딩
     * @param holder
     * @param adapterPosition
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int adapterPosition) {

        try{

            //Log.e("Dlog","onBindViewHolder() adapterPosition : "+adapterPosition);


            //섹션 포지션
            int sectionIndex = this.getPositionInSectionForAdapterPosition(adapterPosition);
            //뷰홀더 타입(헤더/아이템/푸터)
            int baseType = this.unmaskBaseViewType(holder.getItemViewType());
            //뷰홀더 유저 타입
            //헤더/아이템/푸터의 위치에 별도의 뷰타입을 지정한 경우
            int userType = this.unmaskUserViewType(holder.getItemViewType());

            switch (baseType)
            {
                //섹션 헤더
                case BASE_SECTION_VIEW_TYPE_HEADER:
                    onBindHeaderViewHolder(holder, sectionIndex, userType, this.getHeaderInSection(sectionIndex));
                    break;
                //섹션 아이템
                case BASE_SECTION_VIEW_TYPE_ITEM:
                    //섹션 아아템 포지션
                    int sectionItemIndex = this.getPositionOfItemInSectionForAdapterPosition(adapterPosition);
                    ITEM item = this.getItemInSection(sectionIndex, sectionItemIndex);
                    onBindItemViewHolder(holder, sectionIndex, sectionItemIndex, userType, item);
                    break;
                //섹션 푸터
                case BASE_SECTION_VIEW_TYPE_FOOTER:
                    onBindFooterViewHolder(holder, sectionIndex, userType, this.getFooterInSection(sectionIndex));
                    break;
                default:
                    throw new IndexOutOfBoundsException("알수없는 타입 : " + baseType + " 헤더/아이템/푸터 타입이 아닙니다.");
            }

        }catch(Exception ex){
            throw ex;
        }


    }


    /**
     * 뷰타입 반환
     * @param adapterPosition
     * @return
     */
    @Override
    public int getItemViewType(int adapterPosition) {

        //Log.e("Dlog","getItemViewType() adapterPosition : "+adapterPosition);

        int baseType = 0;
        int userType = 0;
        try{

            if (adapterPosition < 0) {
                throw new IndexOutOfBoundsException("아답터 포지션 (" + adapterPosition + ") cannot be < 0");
            } else if (adapterPosition >= getItemCount()) {
                throw new IndexOutOfBoundsException("아답터 (" + adapterPosition + ")  cannot be > getItemCount() (" + getItemCount() + ")");
            }

            int sectionIndex = this.getPositionInSectionForAdapterPosition(adapterPosition);
            SECTION section =  this.getSection(sectionIndex);
            //아답터 포지션과 연결 된 섹션의 로컬(아이템+헤더+푸터) 포지션
            int sectionLocalFlatItemPosition = this.getPositionOfLocalFlatInSectionForAdapterPosition(adapterPosition);
            //아답터 포지션과 연결 된 섹션의 로컬(아이템+헤더+푸터) 타입
            baseType = this.getItemViewBaseType(section, sectionLocalFlatItemPosition);
            //Log.e("Dlog","getItemViewType() baseType : "+baseType);

            switch (baseType)
            {
                //헤더탑입
                case BASE_SECTION_VIEW_TYPE_HEADER:
                    userType = getSectionHeaderUserType(sectionIndex);
                    if (userType < 0 || userType > 0xFF) {
                        throw new IllegalArgumentException("커스텀 헤더 뷰 타입 (" + userType + ") 범위여야 합니다. [0,255]");
                    }
                    break;
                //아이템타입
                case BASE_SECTION_VIEW_TYPE_ITEM:
                    //섹션 아이템 포지션
                    int sectionItemPosition = this.getPositionOfItemInSectionForAdapterPosition(adapterPosition);
                    userType = getSectionItemUserType(sectionIndex, sectionItemPosition);
                    if (userType < 0 || userType > 0xFF) {
                        throw new IllegalArgumentException("커스텀 아이템 뷰 타입 (" + userType + ") 범위여야 합니다. [0,255]");
                    }
                    break;
                //푸터타입
                case BASE_SECTION_VIEW_TYPE_FOOTER:
                    userType = getSectionFooterUserType(sectionIndex);
                    if (userType < 0 || userType > 0xFF) {
                        throw new IllegalArgumentException("커스텀 푸터 뷰 타입 (" + userType + ") 범위여야 합니다. [0,255]");
                    }
                    break;
            }

        }catch(Exception ex)
        {
            throw ex;
        }

        //밑면 8 비트, 사용자 유형 다음 8 비트
        return ((userType & 0xFF) << 8) | (baseType & 0xFF);

    }

    /**
     * 아답터 아이템 수 반환
     * @return
     */
    @Override
    public int getItemCount() {


        int count = 0;
        if( null != this.getSections() )
        {
            for ( SECTION section : this.getSections())
            {
                //섹션에 포함 된 모든 아이템(헤더+아이템+푸터) 수
                count += section.getSectionItemsTotal();
            }
        }
        //Log.e("Dlog","getItemCount() count : "+count);
        return count;

    }




    /**
     * 섹션 목록 반환
     * @return
     */
    public ArrayList<SECTION> getSections() {

        return this.sections;
    }


    /**
     * 섹션의 헤더뷰 반환
     * @param sectionIndex
     * @return
     */
    public HEADER getHeaderInSection(int sectionIndex)
    {
        if( null != this.getSections() && this.getSections().get(sectionIndex).hasHeader())
        {
            return (HEADER)this.getSections().get(sectionIndex).getHeader();
        }
        //throw new IndexOutOfBoundsException("Invalid position");
        return null;
    }

    /**
     * 섹션의 푸터 반환
     * @param sectionIndex
     * @return
     */
    public FOOTER getFooterInSection(int sectionIndex)
    {
        if( null != this.getSections() && this.getSections().get(sectionIndex).hasFooter())
        {
            return (FOOTER)this.getSections().get(sectionIndex).getFooter();
        }
        //throw new IndexOutOfBoundsException("Invalid position");
        return null;
    }

    /**
     * 섹션의 아이템 반환
     * @param sectionIndex
     * @param sectionItemPosition
     * @return
     */
    public ITEM getItemInSection(int sectionIndex, int sectionItemPosition)
    {
        if( null != this.getSections() && this.getSections().size() > sectionIndex)
        {
            if(null != this.getSections().get(sectionIndex).getItems() && this.getSections().get(sectionIndex).getItems().size() > sectionItemPosition)
            {
                return (ITEM)this.getSections().get(sectionIndex).getItems().get(sectionItemPosition);
            }
        }

        //throw new IndexOutOfBoundsException("Invalid position");
        return null;
    }

    /**
     * 아답터 포지션에 해당하는 섹션의 인덱스 반환
     * @param adapterPosition
     * @return
     */
    public SECTION getSectionForPositionForAdapterPosition(int adapterPosition) {

        int currentPos = 0;

        for (SECTION section : this.getSections()) {

            //보이지 않는 섹션은 무시한다.
            //if (!section.isVisible())
            // continue;

            int sectionTotal = section.getSectionItemsTotal();

            //아답터 포지션에 해당하는 섹션을 반환 한다.
            if (adapterPosition >= currentPos && adapterPosition <= (currentPos + sectionTotal - 1)) {
                return section;
            }

            currentPos += sectionTotal;
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }


    /**
     * 섹션반환
     * @param sectionIndex
     * @return
     */
    public SECTION getSection(int sectionIndex) {

        if( null != this.getSections() && this.getSections().size() > sectionIndex)
            return this.getSections().get(sectionIndex);

        throw new IndexOutOfBoundsException("Invalid position");
    }

    /**
     * 섹션의 헤더뷰 타입인가
     * @param viewType
     * @return
     */
    public boolean isSectionHeaderViewType(int viewType)
    {
        int baseViewType = this.unmaskBaseViewType(viewType);
        return (baseViewType == BASE_SECTION_VIEW_TYPE_HEADER);
    }


    /**
     * 아답터 포지션에 해당하는 섹션의 인덱스를 반환 한다.
     * @param adapterPosition
     * @return
     */
    public int getPositionInSectionForAdapterPosition(int adapterPosition) {

        int runningTotal = 0;
        final int size = this.getSections().size();
        for (int sectionIndex = 0; sectionIndex < size; sectionIndex++)
        {
            //섹션 전체 아이템 카운트(헤더+헤더+푸더)
            int sectionTotal = this.getSections().get(sectionIndex).getSectionItemsTotal();
            if (adapterPosition < runningTotal + sectionTotal) {
                return sectionIndex;
            }
            runningTotal += sectionTotal;
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }


    /**
     * 아답타 포지션에 해당하는 섹션의 아이템 인덱스을 반환 한다.
     * @param adapterPosition
     * @return
     */
    public int getPositionOfItemInSectionForAdapterPosition(int adapterPosition)
    {

        int currentPos = 0;

        for (SECTION section : this.getSections()) {


            //보이지 않는 섹션은 무시한다.
            //if (!section.isVisible())
            // continue;

            int sectionTotal = section.getSectionItemsTotal();

            //아답터 포지션에 해당하는 섹션
            if (adapterPosition >= currentPos && adapterPosition <= (currentPos + sectionTotal - 1))
            {
                //섹션 데이터 아이템 포지션
                int sectionItemPosition = (adapterPosition - currentPos)-(section.hasHeader()?1:0);
                return sectionItemPosition;

            }

            currentPos += sectionTotal;
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }

    /**
     * 아답터 포지션에 해당하는 세션의 로컬 인덱스을 반환 한다.
     * 아답터 포지션과 섹션의 로컬(헤더+아이템+푸터) 평면 인덱스를 반환 한다.
     * 예)
     *  데이터 : 헤더+아이템(1,2,3)+푸터 각각 존재
     *  - 아답터 포지션 '0' 이라면, 헤더 위치
     *  - 아답터 포지션 '1' 이라면, 아이템 1 위치
     *  - 아답터 포지션 '4' 이라면, 푸터 위치
     *
     * @param adapterPosition
     * @return
     */
    public int getPositionOfLocalFlatInSectionForAdapterPosition(int adapterPosition)
    {
        int currentPos = 0;

        for (SECTION section : this.getSections()) {

            //보이지 않는 섹션은 무시한다.
            //if (!section.isVisible())
            // continue;

            int sectionTotal = section.getSectionItemsTotal();

            //아답터 포지션에 해당하는 섹션
            if (adapterPosition >= currentPos && adapterPosition <= (currentPos + sectionTotal - 1))
            {
                //섹션에 포함 된 지역 아이템(헤더+아이템+푸터) 포지션
                return (adapterPosition - currentPos);
            }

            currentPos += sectionTotal;
        }

        throw new IndexOutOfBoundsException("Invalid position");
    }


    /**
     * 섹션 객체로 섹션의 인덱스을 반환 한다.
     * @param section
     * @return
     */
    public int getSectionPositionForAdapterPosition(SECTION section) {
        int currentPos = 0;

        for (SECTION loopSection : this.getSections()) {

            //보이지 않는 섹션은 무시한다.
            //if (!loopSection.isVisible())
            // continue;

            if (loopSection == section) {
                return currentPos;
            }

            int sectionTotal = loopSection.getSectionItemsTotal();

            currentPos += sectionTotal;
        }

        throw new IllegalArgumentException("Invalid section");
    }


    /**
     * 섹션 기본 타입 마스크
     * base view type (HEADER/ITEM/FOOTER) is lower 8 bits
     * @param itemViewTypeMask
     * @return
     */
    public static int unmaskBaseViewType(int itemViewTypeMask) {
        return itemViewTypeMask & 0xFF;
    }

    /**
     * 섹션 유저 타입 마스크
     * use type is in 0x0000FF00 segment
     *
     * @param itemViewTypeMask
     * @return
     */
    public static int unmaskUserViewType(int itemViewTypeMask) {
        return (itemViewTypeMask >> 8) & 0xFF;
    }

    /**
     * 섹션의 로컬 타입을 반환 한다.
     *
     * sectionLocalPosition 인덱스는 섹션의 로컬(헤더+아이템+푸터) 평면 포지션이다.
     * 예)
     *  섹션 : 헤더+아이템(1,2,3)+푸터 존재
     *  - sectionLocalPosition '0' 이라면, 헤더 위치
     *  - sectionLocalPosition '1' 이라면, 아이템 1 위치
     *  - sectionLocalPosition '4' 이라면, 푸터 위치
     * @param section
     * @param sectionLocalPosition
     * @return
     */
    int getItemViewBaseType(SECTION section, int sectionLocalPosition)
    {
        if (section.hasHeader() && section.hasFooter()) {
            if (sectionLocalPosition == 0) {
                return BASE_SECTION_VIEW_TYPE_HEADER;
            } else if (sectionLocalPosition == section.getSectionItemsTotal() - 1) {
                return BASE_SECTION_VIEW_TYPE_FOOTER;
            } else {
                return BASE_SECTION_VIEW_TYPE_ITEM;
            }
        } else if (section.hasHeader()) {
            if (sectionLocalPosition == 0) {
                return BASE_SECTION_VIEW_TYPE_HEADER;
            } else {
                return BASE_SECTION_VIEW_TYPE_ITEM;
            }
        } else if (section.hasFooter()) {
            if (sectionLocalPosition == section.getSectionItemsTotal() - 1) {
                return BASE_SECTION_VIEW_TYPE_FOOTER;
            } else {
                return BASE_SECTION_VIEW_TYPE_ITEM;
            }
        } else {
            // 섹션에 헤더와 푸터가 없는 경우
            return BASE_SECTION_VIEW_TYPE_ITEM;
        }
    }

    /**
     * 스티키 헤더 데코레이션 Show
     */
    public void showStickyHeaderDecoration()
    {
        if(null == this.stickyHeaderDecoration)
            this.stickyHeaderDecoration = new StickyHeaderDecoration();

        //스티키 데코레이션
        this.recyclerView.setLayoutManager(new LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false));
        this.recyclerView.addItemDecoration(this.stickyHeaderDecoration);
    }

    /**
     * 스티키 헤더 데코레이션 Hide
     */
    public void hideStickyHeaderDecoration()
    {
        if(null != this.stickyHeaderDecoration)
        {
            //스티키 데코레이션
            this.recyclerView.removeItemDecoration(this.stickyHeaderDecoration);
        }
    }


    /**
     * 기본 뷰타입(헤더/아이템/푸터)을 사용하지 않고, 커스텀 뷰타입을 사용한 경우 커스텀 뷰 번호를 반환 한다.
     * @param sectionIndex
     * @return
     */
    public int getSectionHeaderUserType(int sectionIndex) {

        HEADER header =  this.getHeaderInSection(sectionIndex);
        if( null == header )
            return ComplexSectionRecyclerView.BASE_SECTION_VIEW_TYPE_HEADER;

        return header.getViewType();

    }

    /**
     * 기본 뷰타입(헤더/아이템/푸터)을 사용하지 않고, 커스텀 뷰타입을 사용한 경우 커스텀 뷰 번호를 반환 한다.
     * @param sectionIndex
     * @return
     */
    public int getSectionItemUserType(int sectionIndex, int itemIndex) {

        ITEM item = this.getItemInSection(sectionIndex, itemIndex);
        if(null == item)
            return ComplexSectionRecyclerView.BASE_SECTION_VIEW_TYPE_ITEM;

        return item.getViewType();

    }


    /**
     * 기본 뷰타입(헤더/아이템/푸터)을 사용하지 않고, 커스텀 뷰타입을 사용한 경우 커스텀 뷰 번호를 반환 한다.
     * @param sectionIndex
     * @return
     */
    public int getSectionFooterUserType(int sectionIndex) {

        FOOTER footer =  this.getFooterInSection(sectionIndex);
        if( null == footer )
            return ComplexSectionRecyclerView.BASE_SECTION_VIEW_TYPE_FOOTER;

        return footer.getViewType();
    }



}
