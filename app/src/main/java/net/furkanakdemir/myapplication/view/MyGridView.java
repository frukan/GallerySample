package net.furkanakdemir.myapplication.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import net.furkanakdemir.myapplication.R;
import net.furkanakdemir.myapplication.data.model.Gallery;

public class MyGridView extends LinearLayout {

    private int COLUMN_COUNT = 6;

    private LinearLayout rootView;
    private Gallery      gallery;

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

    public void setColumnCount(int count) {
        if (count > 0) {
            COLUMN_COUNT = count;
        }

        refreshGallery();
    }

    private void refreshGallery() {
        if (gallery != null) {
            rootView.removeAllViews();

            setGallery(gallery);
        }
    }

    public void setGallery(@NonNull Gallery gallery) {
        this.gallery = gallery;

        if (rootView != null) {

            int gallerySize = gallery.size();
            int rowSize = gallerySize / COLUMN_COUNT;

            if (rowSize == 0) {
                RowView rowView = new RowView(getContext(), COLUMN_COUNT, gallery.getImages()
                    .subList(0, gallerySize));

                LayoutParams lpView = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

                rowView.setLayoutParams(lpView);

                rootView.addView(rowView);
            } else {
                for (int x = 0; x < rowSize; x++) {

                    int startIndex = COLUMN_COUNT * x;
                    int endIndex = COLUMN_COUNT * x + COLUMN_COUNT;

                    RowView rowView = new RowView(getContext(), COLUMN_COUNT, gallery.getImages()
                        .subList(startIndex, endIndex));

                    LayoutParams lpView = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

                    rowView.setLayoutParams(lpView);

                    rootView.addView(rowView);
                }

                int remainingRowCount = gallerySize % COLUMN_COUNT;

                RowView rowView = new RowView(getContext(), COLUMN_COUNT, gallery.getImages()
                    .subList(gallery.size() - remainingRowCount, gallery.size()));

                LayoutParams lpView = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

                rowView.setLayoutParams(lpView);

                rootView.addView(rowView);
            }


        }
    }
}
