package net.furkanakdemir.myapplication.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import net.furkanakdemir.myapplication.R;
import net.furkanakdemir.myapplication.data.model.Image;

public class RowView extends LinearLayout {

    private LinearLayout rootView;
    private View         firstView;
    private View         secondView;
    private View         thirdView;

    public RowView(Context context,
                   Image[] images) {
        super(context);
        initializeView(context);

        setImages(images);
    }

    public RowView(Context context,
                   @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);

        setImages(null);
    }

    public RowView(Context context,
                   @Nullable AttributeSet attrs,
                   int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView(context);

        setImages(null);
    }

    private void setImages(Image[] images) {

        firstView.setBackgroundColor(Color.rgb(0, 255, 0));
        secondView.setBackgroundColor(Color.rgb(0, 0, 255));
        thirdView.setBackgroundColor(Color.rgb(255, 0, 0));
    }

    private void initializeView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = (LinearLayout) inflater.inflate(R.layout.view_grid_cell, this, true);

        firstView = findViewById(R.id.imageview_row_first);
        secondView = findViewById(R.id.imageview_row_second);
        thirdView = findViewById(R.id.imageview_row_third);
    }
}
