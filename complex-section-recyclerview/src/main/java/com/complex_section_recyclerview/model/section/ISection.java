package com.complex_section_recyclerview.model.section;

import java.util.List;

/**
 * Created by sung on 2017-09-27.
 */

public interface ISection<HEADER,ITEM,FOOTER> {

    boolean hasHeader();
    boolean hasFooter();
    HEADER getHeader();
    FOOTER getFooter();
    void setHeader(HEADER header);
    void setFooter(FOOTER footer);

    //섹션에 포함 된 아이템 수
    int getItemCount();
    //섹션에 포함 된 포든 아이템 토탈(헤더+아이템+푸터)
    int getSectionItemsTotal();
    ITEM getItem(int itemPosition);
    List<ITEM> getItems();
    void addItem(ITEM item);
    void addItems(ITEM... items);
    void addItems(List<ITEM> items);
    void removeItem(int position);
    int removeItem(ITEM item);

}
