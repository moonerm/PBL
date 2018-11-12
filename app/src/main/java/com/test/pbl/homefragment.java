package com.test.pbl;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class homefragment extends Fragment {
    @Nullable
    private BarChart barChart;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homefragment, container, false);

        barChart = (BarChart) view.findViewById(R.id.BarChart);

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

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        barEntries.add(new BarEntry(1, 40f));
        barEntries.add(new BarEntry(2, 30f));
        barEntries.add(new BarEntry(3, 10f));
        barEntries.add(new BarEntry(4, 50f));
        barEntries.add(new BarEntry(5, 60f));
        barEntries.add(new BarEntry(6, 35f));
        barEntries.add(new BarEntry(7, 10f));

        AxisBase axis;
        BarDataSet dataSet = new BarDataSet(barEntries, "Chart Data Set");
        dataSet.setDrawValues(false); //그래프 위에 데이터가 안 뜨게 함다
        dataSet.setColors(Color.parseColor("#FAD064"));
        BarData chartData = new BarData(dataSet);
        barChart.setData(chartData);

        return view;
    }
}