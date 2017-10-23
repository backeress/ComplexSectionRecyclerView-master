package com.complexsectionrecyclerview_master.sample.model;


import com.complex_section_recyclerview.model.data.BaseItem;

/**
 * Created by sung on 2017-10-18.
 */

public class Item extends BaseItem {
    public String text;

    public Item(String text)
    {
        this.text = text;
    }
    public Item(int layout, Object obj)
    {
        super(layout, obj);
    }
}
