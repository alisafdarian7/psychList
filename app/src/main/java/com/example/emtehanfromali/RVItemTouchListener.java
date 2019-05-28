package com.example.emtehanfromali;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class RVItemTouchListener implements RecyclerView.OnItemTouchListener {
    private MyClickListener clickListener;
    private GestureDetector gestureDetector;
    public RVItemTouchListener(Context context, final RecyclerView recyclerView, final MyClickListener clickListener){
        this.clickListener = clickListener;
        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View childView = recyclerView.findChildViewUnder(e.getX(), e.getY());
                if(childView != null && clickListener != null){
                    clickListener.onLongClick(
                            childView,
                            recyclerView.getChildAdapterPosition(childView));
                }
                super.onLongPress(e);
            }
        });

    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View childView = rv.findChildViewUnder(e.getX(), e.getY());
        if(childView!= null && clickListener != null && gestureDetector.onTouchEvent(e)){
            clickListener.onClick(childView, rv.getChildAdapterPosition(childView));
        }
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }
}
