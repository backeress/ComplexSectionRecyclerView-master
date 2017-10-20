package com.complexsectionrecyclerview_master.sample.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.complexsectionrecyclerview_master.DLog;
import com.complexsectionrecyclerview_master.R;
import com.complexsectionrecyclerview_master.sample.adapter.ComplexSectionRecyclerViewAdapter;
import com.complexsectionrecyclerview_master.sample.model.Footer;
import com.complexsectionrecyclerview_master.sample.model.Header;
import com.complexsectionrecyclerview_master.sample.model.Item;
import com.complexsectionrecyclerview_master.sample.model.Section;
import com.complexsectionrecyclerview_master.sample.model.custom_item.ItemCustomObject;

import java.util.ArrayList;

/**
 * Created by sung on 2017-10-19.
 */

public class SampleActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        this.mContext = this;
        //레이아웃 초기화
        setContentView(R.layout.layout_recyclerview);

        try {

            //레이아웃 바인드
            //ButterKnife.bind(this);
            this.recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

            //리사이클뷰 초기화
            this.initRecyclerView();

            //리사이클뷰
            this.initRecyclerView();

        } catch (Exception ex) {
            DLog.e(ex);
        }
    }



    /**
     * 리사이클뷰 초기화
     * - 포인트 전체 사용내역 목록
     */
    void initRecyclerView() {

        try {


            //리사이클 뷰 아답터
            ComplexSectionRecyclerViewAdapter mRecyclerVieAdapter;

            //콘텐츠 레이아웃 크기를 변경하지 않는다.
            this.recyclerView.setHasFixedSize(true);

            //기본 레이아웃
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            this.recyclerView.setLayoutManager(layoutManager);



            //섹션 리스트
            ArrayList<Section> sections = new ArrayList<>();;
            //섹션 생성 후 아이템 추가
            Section section1 = new Section();
            section1.setHeader(new Header("[header1]"));
            section1.setFooter(new Footer("[1footer]"));
            //기본 아이템
            section1.addItem(new Item("1:1"));
            //커스텀뷰 아이템
            section1.addItem(new Item(ComplexSectionRecyclerViewAdapter.USER_ITEM_TYPE_CUSTOM_STRING,"커스텀 문자열"));
            section1.addItem(new Item(ComplexSectionRecyclerViewAdapter.USER_ITEM_TYPE_CUSTOM_NUMBER,15));
            section1.addItem(new Item(ComplexSectionRecyclerViewAdapter.USER_ITEM_TYPE_CUSTOM_OBJECT, new ItemCustomObject("커스텀 오브젝트")));
            section1.addItem(new Item("1:2"));
            //섹션 리스트에 추가
            sections.add(section1);

            /*


            Section section2 = new Section();
            section2.setHeader(new Header("[header2]"));
            section2.setFooter(new Footer("[2footer]"));
            section2.addItem(new Item("2:1"));
            section2.addItem(new Item("2:2"));
            section2.addItem(new Item("2:1"));
            section2.addItem(new Item("2:2"));
            section2.addItem(new Item("2:1"));
            section2.addItem(new Item("2:2"));
            section2.addItem(new Item("2:1"));
            section2.addItem(new Item("2:2"));
            section2.addItem(new Item("2:1"));
            section2.addItem(new Item("2:2"));
            sections.add(section2);*/


            //아답터 연동
            mRecyclerVieAdapter = new ComplexSectionRecyclerViewAdapter(this.mContext, this.recyclerView, sections);
            this.recyclerView.setAdapter(mRecyclerVieAdapter);

            //애니메이션 효과
            this.recyclerView.setItemAnimator(new DefaultItemAnimator());

            //스티키 데코레이션
            mRecyclerVieAdapter.showStickyHeaderDecoration();
            //mRecyclerVieAdapter.hideStickyHeaderDecoration();


        } catch (Exception ex) {

            DLog.e(ex);
        }

    }

}
