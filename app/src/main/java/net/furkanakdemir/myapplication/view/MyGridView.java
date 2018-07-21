package net.furkanakdemir.myapplication.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import net.furkanakdemir.myapplication.R;
import net.furkanakdemir.myapplication.data.model.Gallery;
import net.furkanakdemir.myapplication.data.model.Image;

public class MyGridView extends LinearLayout {

    private LinearLayout rootView;

    public MyGridView(Context context) {
        super(context);
        initializeView(context);
    }

    public MyGridView(Context context,
                      @Nullable AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    public MyGridView(Context context,
                      @Nullable AttributeSet attrs,
                      int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView(context);
    }

    private void initializeView(Context context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = (LinearLayout) inflater.inflate(R.layout.view_grid, this, true);

        setOrientation(VERTICAL);
    }

    public void setGallery(@NonNull Gallery gallery) {

        if (rootView != null) {

            for (int x = 0; x < 10; x++) {

                Image[] images = new Image[2];
                images[0] = gallery.getImages().get(2 * x);
                images[1] = gallery.getImages().get(2 * x + 1);

                RowView rowView = new RowView(getContext(), images);

                LayoutParams lpView = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

                rowView.setLayoutParams(lpView);

                rootView.addView(rowView);
            }
        }
    }
}
