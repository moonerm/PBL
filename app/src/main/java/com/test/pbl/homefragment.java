package com.test.pbl;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class homefragment extends Fragment {
    @Nullable
    private BarChart barChart;
    private Button button;

    static public Calendar cal = Calendar.getInstance();
    static public int nDay = cal.get(Calendar.DAY_OF_WEEK);  //일요일 = 1, 월요일 = 2 …….
    static public int dayData[] = {0, 0, 0, 0, 0, 0, 0};

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, container, false);

        barChart = (BarChart) view.findViewById(R.id.BarChart);
        button = (Button) view.findViewById(R.id.dataButton);

        if (nDay == 1) nDay = 8;

        button.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                dayData[nDay-2]++; }
        });

        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setBackgroundColor(Color.WHITE); //백그라운드는 흰색으로
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false); //지저분한 x축 y축 grid를 없애봅시다
        barChart.getXAxis().setEnabled(false);
        barChart.getAxisRight().setEnabled(false); //top, right에 나오는 데이터는 숨겨주고
        barChart.setNoDataText(""); //데이터가 없을 때 나오는 text
        barChart.fitScreen(); //그래프를 Layout에 맞춥시다
        barChart.getDescription().setEnabled(false); //오른쪽 코너에 성가신 text 치우기
        barChart.getLegend().setEnabled(false); //그래프 아래에 성가신 data set 정보도 치우기
        barChart.setScaleEnabled(false);
        barChart.getAxisLeft().setAxisMinimum(0f);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        if (nDay == 2) {  // 월요일마다 데이터 초기화
            barEntries.add(new BarEntry(2, 0));
            barEntries.add(new BarEntry(3, 0));
            barEntries.add(new BarEntry(4, 0));
            barEntries.add(new BarEntry(5, 0));
            barEntries.add(new BarEntry(6, 0));
            barEntries.add(new BarEntry(7, 0));
            barEntries.set(nDay-1, new BarEntry(nDay-1, dayData[nDay-2]));
        } else {
            for (int i = 0; i < 7; i++){
                barEntries.add(new BarEntry(i+1, dayData[i]));
            }
        }

        AxisBase axis;
        BarDataSet dataSet = new BarDataSet(barEntries, "Chart Data Set");
        dataSet.setDrawValues(false); //그래프 위에 데이터가 안 뜨게 함다
        dataSet.setColors(Color.parseColor("#FAD064"));
        BarData chartData = new BarData(dataSet);
        barChart.setData(chartData);

        return view;
    }
}