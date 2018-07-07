package com.android.paskahlis.yogaapp.utility;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;

public class OrderedListHelper {
    private Context context;

    public OrderedListHelper(Context context) {
        this.context = context;
    }

    public LinearLayout buildRow(int num, String string) {
        LinearLayout stepRowContainer = new LinearLayout(context);
        TextView numText = new TextView(context);
        numText.setText(num + ".");
        numText.setWidth(getDp(20));

        TextView stepText = new TextView(context);
        stepText.setText(string);
        stepText.setWidth(0);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        p.weight = 1;
        stepText.setLayoutParams(p);
        stepText.setText(string);

        stepRowContainer.addView(numText);
        stepRowContainer.addView(stepText);
        stepRowContainer.setPadding(0, 0, 0, getDp(10));

        return stepRowContainer;
    }

    private int getDp(int size) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (size * scale + 0.5f);
    }
}
