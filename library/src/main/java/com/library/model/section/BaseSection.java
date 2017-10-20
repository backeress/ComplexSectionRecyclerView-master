package com.library.model.section;

import com.library.model.data.BaseFooter;
import com.library.model.data.BaseHeader;
import com.library.model.data.BaseItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



/**
 * Created by sung on 2017-10-19.
 */

public class BaseSection implements ISection<BaseHeader,BaseItem,BaseFooter> {

    BaseHeader header;
    List<BaseItem> items = new ArrayList<>();
    BaseFooter footer;

    @Override
    public boolean hasHeader() {
        return (null != this.header);
    }

    @Override
    public boolean hasFooter() {
        return (null != this.footer);
    }

    @Override
    public BaseHeader getHeader() {
        return this.header;
    }

    @Override
    public BaseFooter getFooter() {return this.footer;}

    @Override
    public void setHeader(BaseHeader header) {
        this.header = header;
    }

    @Override
    public void setFooter(BaseFooter footer) {
        this.footer = footer;
    }


    @Override
    public int getItemCount() {
        if( null == this.getItems() )
            return 0;
        return this.getItems().size();
    }

    @Override
    public List<BaseItem> getItems() {
        return this.items;
    }

    @Override
    public void addItem(BaseItem item) {

        if( null != this.getItems() )
            this.getItems().add(item);
    }

    @Override
    public void addItems(BaseItem... baseItems) {
        this.addItems(Arrays.asList(baseItems));
    }

    @Override
    public void addItems(List<BaseItem> baseItems) {
        this.getItems().addAll(baseItems);

    }

    @Override
    public void removeItem(int position) {
        if( null != this.getItems() )
            this.getItems().remove(position);

    }

    @Override
    public int removeItem(BaseItem item) {
        if( null != this.getItems() ){

            int index = this.getItems().indexOf(item);
            if (index >= 0) {
                removeItem(index);
                return index;
            }
        }

        return -1;
    }

    @Override
    public int getSectionItemsTotal() {

        int totalCount = 0;

        //아이템
        totalCount+= this.getItemCount();
        //헤더
        totalCount+=(this.hasHeader()? 1 : 0);
        //푸터
        totalCount+=(this.hasFooter()? 1 : 0);

        return totalCount;
    }

    @Override
    public BaseItem getItem(int itemPosition) {

        if( this.getItemCount() > itemPosition )
            return this.getItems().get(itemPosition);

        return null;
    }
}
