package com.complex_section_recyclerview.decoration;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.Region;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.complex_section_recyclerview.adater.ComplexSectionRecyclerView;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by sung on 2017-09-28.
 */

public class StickyHeaderDecoration extends RecyclerView.ItemDecoration {
    private final static String TAG = StickyHeaderDecoration.class.getSimpleName();

    //리사이클뷰
    ComplexSectionRecyclerView mAdapter = null;

    //캐시 고정 해더 뷰
    View mPinnedHeaderView = null;
    int mHeaderPosition = -1;
    //뷰 타입
    Map<Integer, Boolean> mPinnedViewTypes = new HashMap<Integer, Boolean>();

    private int mPinnedHeaderTop;
    private Rect mClipBounds;

    //onDraw함수를 통해서 아이템이 그려지기 전에 먼저 그릴 수 있다
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {


        createPinnedHeader(parent);


        if (this.mPinnedHeaderView != null) {


            //체크한다. 오버랩 섹션뷰인지
            //TODO support only vertical header currently.
            final int headerEndAt = this.mPinnedHeaderView.getTop() + this.mPinnedHeaderView.getHeight();
            final View v = parent.findChildViewUnder(c.getWidth() / 2, headerEndAt + 1);

            if (this.isHeaderView(parent, v)) {
                this.mPinnedHeaderTop = v.getTop() - mPinnedHeaderView.getHeight();
            } else {
                this.mPinnedHeaderTop = 0;
            }

            this.mClipBounds = c.getClipBounds();
            this.mClipBounds.top = this.mPinnedHeaderTop + this.mPinnedHeaderView.getHeight();
            c.clipRect(this.mClipBounds);
        }
    }

    //onDrawOver함수를 통해서 아이템 위에 덮어서 그릴 수 있다.
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {


        if (this.mPinnedHeaderView != null) {
            c.save();

            this.mClipBounds.top = 0;
            c.clipRect(this.mClipBounds, Region.Op.UNION);
            c.translate(0, this.mPinnedHeaderTop);
            this.mPinnedHeaderView.draw(c);

            c.restore();
        }
    }

    private void createPinnedHeader(RecyclerView parent) {
        checkCache(parent);

        //레이아웃 매니저를 가져온다.
        final LinearLayoutManager linearLayoutManager;
        final RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            linearLayoutManager = (LinearLayoutManager) layoutManager;
        } else {
            return;
        }

        final int firstVisiblePosition = linearLayoutManager.findFirstVisibleItemPosition();
        final int headerPosition = findPinnedHeaderPosition(firstVisiblePosition);

        if (headerPosition >= 0 && this.mHeaderPosition != headerPosition) {
            this.mHeaderPosition = headerPosition;
            final int viewType = this.mAdapter.getItemViewType(headerPosition);

            final RecyclerView.ViewHolder pinnedViewHolder = this.mAdapter.createViewHolder(parent, viewType);
            mAdapter.bindViewHolder(pinnedViewHolder, headerPosition);
            mPinnedHeaderView = pinnedViewHolder.itemView;

            // read layout parameters
            ViewGroup.LayoutParams layoutParams = mPinnedHeaderView.getLayoutParams();
            if (layoutParams == null) {
                layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mPinnedHeaderView.setLayoutParams(layoutParams);
            }

            int heightMode = View.MeasureSpec.getMode(layoutParams.height);
            int heightSize = View.MeasureSpec.getSize(layoutParams.height);

            if (heightMode == View.MeasureSpec.UNSPECIFIED) {
                heightMode = View.MeasureSpec.EXACTLY;
            }

            final int maxHeight = parent.getHeight() - parent.getPaddingTop() - parent.getPaddingBottom();
            if (heightSize > maxHeight) {
                heightSize = maxHeight;
            }

            // measure & layout
            final int ws = View.MeasureSpec.makeMeasureSpec(parent.getWidth() - parent.getPaddingLeft() - parent.getPaddingRight(), View.MeasureSpec.EXACTLY);
            final int hs = View.MeasureSpec.makeMeasureSpec(heightSize, heightMode);
            mPinnedHeaderView.measure(ws, hs);
            mPinnedHeaderView.layout(0, 0, mPinnedHeaderView.getMeasuredWidth(), mPinnedHeaderView.getMeasuredHeight());
        }
    }


    private int findPinnedHeaderPosition(int fromPosition) {

        if( this.mAdapter.hasHeader() && 0 == fromPosition)
        {
            return -1;
        }
        else if (fromPosition > this.mAdapter.getItemCount()) {
            return -1;
        }

        for (int position = fromPosition; position >= 0; position--) {
            final int viewType = this.mAdapter.getItemViewType(position);
            if (isSectionViewType(viewType)) {
                return position;
            }
        }

        return -1;
    }

    //섹션 타입인가
    private boolean isSectionViewType(int viewType) {
        if (!this.mPinnedViewTypes.containsKey(viewType)) {
            this.mPinnedViewTypes.put(viewType, this.mAdapter.isSectionHeaderViewType(viewType));
        }

        return mPinnedViewTypes.get(viewType);
    }

    //파라미터 뷰가 헤더뷰인가
    private boolean isHeaderView(RecyclerView parent, View v) {
        final int position = parent.getChildPosition(v);
        if( this.mAdapter.hasHeader() && 0 == position)
        {
            return false;
        }
        else if (position == RecyclerView.NO_POSITION) {
            return false;
        }
        final int viewType = this.mAdapter.getItemViewType(position);

        return isSectionViewType(viewType);
    }

    private void checkCache(RecyclerView parent) {
        ComplexSectionRecyclerView adapter = (ComplexSectionRecyclerView)parent.getAdapter();
        if (this.mAdapter != adapter) {
            disableCache();
            if (adapter instanceof ComplexSectionRecyclerView) {
                this.mAdapter = adapter;
            } else {
                this.mAdapter = null;
            }
        }
    }

    private void disableCache() {
        this.mPinnedHeaderView = null;
        this.mHeaderPosition = -1;
        this.mPinnedViewTypes.clear();
    }
}
