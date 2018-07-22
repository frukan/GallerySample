package net.furkanakdemir.myapplication.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import java.util.List;
import net.furkanakdemir.myapplication.GlideApp;
import net.furkanakdemir.myapplication.R;
import net.furkanakdemir.myapplication.data.model.Image;

public class RowView extends LinearLayout {

    private LinearLayout rootView;
    private int          columnCount;

    public RowView(Context context,
                   int columnCount,
                   List<Image> images) {
        super(context);
        this.columnCount = columnCount;
        initializeView(context, images);
    }

    private void initializeView(Context context,
                                List<Image> images) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        rootView = (LinearLayout) inflater.inflate(R.layout.view_grid_row, this, true);

        float density = getResources().getDisplayMetrics().density;

        int width;
        int height;

        switch (columnCount) {
            case 1:
                width = LayoutParams.MATCH_PARENT;
                height = (int) (density * 144);

                break;

            case 2:
                width = (int) (density * 144);
                height = (int) (density * 144);

                break;

            case 3:
                width = (int) (density * 128);
                height = (int) (density * 128);
                break;

            case 4:

                width = (int) (density * 96);
                height = (int) (density * 96);
                break;

            case 5:
                width = (int) (density * 64);
                height = (int) (density * 64);
                break;

            default:
                width = (int) (density * 48);
                height = (int) (density * 48);
                break;
        }

        for (int x = 0; x < columnCount; x++) {
            ImageView imageView = new ImageView(context);

            imageView.setImageResource(R.drawable.ic_image_black_48dp);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
            params.weight = 1.0f;

            int margin = (int) (density * 2);

            params.setMargins(margin, margin, margin, margin);

            imageView.setLayoutParams(params);
            ((LinearLayout) rootView.getChildAt(0)).addView(imageView);
        }

        final int childCount = ((LinearLayout) rootView.getChildAt(0)).getChildCount();

        for (int i = 0; i < childCount; i++) {
            ImageView v = (ImageView) ((LinearLayout) rootView.getChildAt(0)).getChildAt(i);

            if (images.size() > i) {
                GlideApp.with(getContext())
                    .load(images.get(i).getUrl())
                    .placeholder(R.drawable.ic_image_black_48dp)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(v);

                v.setVisibility(VISIBLE);
            } else {
                v.setVisibility(INVISIBLE);
            }
        }
    }
}
