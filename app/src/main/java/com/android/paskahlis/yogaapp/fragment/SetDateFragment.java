package com.android.paskahlis.yogaapp.fragment;

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
import com.android.paskahlis.yogaapp.activity.SetDateActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class SetDateFragment extends Fragment {
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final SetDateActivity setDateActivity = (SetDateActivity) getActivity();
        ImageView backButton = setDateActivity.findViewById(R.id.back_button);
        final Bundle bundle = new Bundle();
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDateActivity.onBackPressed();
            }
        });

        Button setSchedule = getView().findViewById(R.id.button_set_schedule);
        setSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment timeFragment = new SetTimeFragment();
                timeFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.date_container, timeFragment).addToBackStack("TimeFragment").commit();
            }
        });

        final TextView strDate = getView().findViewById(R.id.text_date);
        final CalendarView calendarView = getView().findViewById(R.id.calendar);
        DateFormat dateFormat = new SimpleDateFormat("yyyy\nEEE, dd MMM");
        strDate.setText(dateFormat.format(calendarView.getDate()));
        bundle.putLong("date", calendarView.getDate());
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, dayOfMonth, 0, 0);
                calendarView.setDate(calendar.getTimeInMillis());
                DateFormat dateFormat = new SimpleDateFormat("yyyy\nEEE, dd MMM");
                strDate.setText(dateFormat.format(calendarView.getDate()));
                bundle.putLong("date", calendarView.getDate());
            }
        });
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_set_date, container, false);

        return rootView;
    }
}
