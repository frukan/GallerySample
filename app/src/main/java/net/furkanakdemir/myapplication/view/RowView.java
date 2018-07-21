package net.furkanakdemir.myapplication.view;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import net.furkanakdemir.myapplication.GlideApp;
import net.furkanakdemir.myapplication.R;
import net.furkanakdemir.myapplication.data.model.Image;

public class RowView extends LinearLayout {

    private LinearLayout rootView;
    private ImageView    firstView;
    private ImageView    secondView;
    private ImageView    thirdView;

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

        GlideApp.with(getContext()).load(images[0].getUrl()).into(firstView);

        GlideApp.with(getContext()).load(images[1].getUrl()).into(secondView);



        thirdView.setBackgroundColor(Color.rgb(255, 0, 0));
    }

    private void initializeView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = (LinearLayout) inflater.inflate(R.layout.view_grid_row, this, true);

        firstView = findViewById(R.id.imageview_row_first);
        secondView = findViewById(R.id.imageview_row_second);
        thirdView = findViewById(R.id.imageview_row_third);
    }
}
