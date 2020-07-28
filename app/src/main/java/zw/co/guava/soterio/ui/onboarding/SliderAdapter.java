package zw.co.guava.soterio.ui.onboarding;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import zw.co.guava.soterio.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){
        this.context = context;
    }

    public int[] slide_images = {
        R.drawable.car1,
        R.drawable.car1,
        R.drawable.car1
    };

    public String[] slide_headings = {
            "COVID-19 Cases",
            "Exposure notifications",
            "COVID-19 Red-zones"
    };

    public String[] slide_description = {
            "Get alerted if you have been in contact\n" + "with someone who has tested positive for\n" + "COVID-19",
            "Get alerted if you have been in contact\n" + "with someone who has tested positive for\n" + "COVID-19",
            "Get alerted if you have been in contact\n" + "with someone who has tested positive for\n" + "COVID-19"
    };

    @Override
    public int getCount() {
        return slide_headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (RelativeLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.content_onboarding, container, false);

        ImageView slideImageVew = (ImageView) view.findViewById(R.id.slideImage);
        TextView slideHeading = (TextView) view.findViewById(R.id.slideHeading);
        TextView slideDescription = (TextView) view.findViewById(R.id.slideDescription);

        slideImageVew.setImageResource(slide_images[position]);
        slideHeading.setText(slide_headings[position]);
        slideDescription.setText(slide_description[position]);

        container.addView(view);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
        ((ViewPager) container).removeView((View) object);
    }
}
