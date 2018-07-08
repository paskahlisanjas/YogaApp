package com.android.paskahlis.yogaapp.fragment.history.schedule;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.paskahlis.yogaapp.R;
import com.android.paskahlis.yogaapp.activity.MenuActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class SetDateFragment extends Fragment {
    private MenuActivity activity;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity = (MenuActivity) getActivity();

        ImageView backButton = getView().findViewById(R.id.back_button);
        final SharedPreferences pref = getActivity().getSharedPreferences("YogaApp", Context.MODE_PRIVATE);

        Button setSchedule = getView().findViewById(R.id.button_set_schedule);
        setSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment timeFragment = new SetTimeFragment();
                activity.replaceFragment(timeFragment, false);
            }
        });

        final TextView strDate = getView().findViewById(R.id.text_date);
        final CalendarView calendarView = getView().findViewById(R.id.calendar);
        calendarView.setDate(pref.getLong("date", new Date().getTime()));
        DateFormat dateFormat = new SimpleDateFormat("yyyy\nEEE, dd MMM");
        strDate.setText(dateFormat.format(calendarView.getDate()));
        pref.edit().putLong("date", calendarView.getDate()).apply();
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth, 0, 0);
                calendarView.setDate(calendar.getTimeInMillis());
                DateFormat dateFormat = new SimpleDateFormat("yyyy\nEEE, dd MMM");
                strDate.setText(dateFormat.format(calendarView.getDate()));
                pref.edit().putLong("date", calendarView.getDate()).apply();
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_set_date, container, false);

        return rootView;
    }
}