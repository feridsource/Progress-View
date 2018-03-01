package com.ferid.lib.progressview;

import android.content.Context;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

/**
 * Created by ferid.cafer on 2/19/2018.
 */
public class ProgressView extends View {

    private View mProgressBackground;
    private View mProgressRatio;
    private TextView mTextValue;

    private double mPercentage;

    public ProgressView(Context context, ViewGroup viewGroup) {
        super(context);

        inflateView(context, viewGroup);
    }

    private void inflateView(Context context, ViewGroup viewGroup) {
        LayoutInflater inflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rootView = inflater.inflate(R.layout.view_progress,
                viewGroup, false);

        mProgressBackground = rootView.findViewById(R.id.progressBackground);
        mProgressRatio = rootView.findViewById(R.id.progressRatio);
        mTextValue = rootView.findViewById(R.id.textValue);

        viewGroup.addView(rootView);
    }

    /**
     * Set colors
     * @param progressBackgroundColor background color
     * @param progressRatioColor front color
     * @param textValueColor text color
     * @param textSize text size
     */
    public void setColors(int progressBackgroundColor, int progressRatioColor,
                          int textValueColor, int textSize) {
        if (progressBackgroundColor > 0) {
            mProgressBackground.setBackgroundTintList(
                    ColorStateList.valueOf(getResources().getColor(progressBackgroundColor)));
        }

        if (progressRatioColor > 0) {
            mProgressRatio.setBackgroundTintList(
                    ColorStateList.valueOf(getResources().getColor(progressRatioColor)));
        }

        if (textValueColor > 0) {
            mTextValue.setTextColor(getResources().getColor(textValueColor));
        }

        if (textSize > 0) {
            mTextValue.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        }
    }

    /**
     * Set progress with percentage. Shows percentage with its sign.
     * @param minValue minimum value
     * @param maxValue maximum value
     * @param currentValue current value
     */
    public void setProgressWithPercentage(double minValue,
                                          double maxValue,
                                          double currentValue) {

        if (maxValue >= currentValue) {
            setProgressViewPercentage(minValue, maxValue, currentValue);
        }
    }

    /**
     * Set progress with given unit. Shows unit value.
     * @param minValue minimum value
     * @param maxValue maximum value
     * @param currentValue current value
     * @param unit unit to show besides value
     */
    public void setProgressWithUnit(double minValue,
                                    double maxValue,
                                    double currentValue,
                                    String unit) {
        if (maxValue >= currentValue && !TextUtils.isEmpty(unit)) {

            setProgressViewUnit(minValue, maxValue, currentValue, unit);
        }
    }

    private void setProgressViewPercentage(final double minValue,
                                           final double maxValue,
                                           final double currentValue) {

        mProgressRatio.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams params = mProgressRatio.getLayoutParams();
                double range = maxValue - minValue;
                mPercentage = (currentValue - minValue) / range * 100;
                params.width = (int) (mProgressRatio.getWidth() * mPercentage / 100);
                mProgressRatio.setLayoutParams(params);

                mTextValue.setText(String.format(Locale.ENGLISH, "%%%,.0f", mPercentage));
            }
        });
    }

    private void setProgressViewUnit(final double minValue,
                                     final double maxValue,
                                     final double currentValue,
                                     final String unit) {

        mProgressRatio.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams params = mProgressRatio.getLayoutParams();
                double range = maxValue - minValue;
                mPercentage = (currentValue - minValue) / range * 100;
                params.width = (int) (mProgressRatio.getWidth() * mPercentage / 100);
                mProgressRatio.setLayoutParams(params);

                mTextValue.setText(String.format(Locale.ENGLISH,
                        "%,.0f %s", currentValue, unit));
            }
        });
    }

}