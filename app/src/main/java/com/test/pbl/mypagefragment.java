package com.test.pbl;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class mypagefragment extends Fragment {
    @Nullable
    private BarChart mChart;
    private LineChart lineChart;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mypagefragment, container, false);

        //막대그래프입니다
        mChart = (BarChart) view.findViewById(R.id.myChart);
        mChart.setMaxVisibleValueCount(50);
        mChart.setPinchZoom(false);
        mChart.setBackgroundColor(Color.WHITE); //백그라운드는 흰색으로
        mChart.getAxisRight().setDrawGridLines(false);
        mChart.getAxisLeft().setDrawGridLines(false);
        mChart.getXAxis().setDrawGridLines(false); //지저분한 x축 y축 grid를 없애봅시다
        mChart.getXAxis().setEnabled(false);
        mChart.getAxisRight().setEnabled(false); //top, right에 나오는 데이터는 숨겨주고
        mChart.setNoDataText(""); //데이터가 없을 때 나오는 text
        mChart.fitScreen(); //그래프를 Layout에 맞춥시다
        mChart.getDescription().setEnabled(false); //오른쪽 코너에 성가신 text 치우기
        mChart.getLegend().setEnabled(false); //그래프 아래에 성가신 data set 정보도 치우기
        mChart.setScaleEnabled(false);

        ArrayList<BarEntry> barEntries = new ArrayList<>();

        //여기는 데이터를 받아 올 자리
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
        mChart.setData(chartData);


        //꺾은 선 그래프입니당
        lineChart = (LineChart) view.findViewById(R.id.lineChart);

        lineChart.setMaxVisibleValueCount(100);
        lineChart.setPinchZoom(false);
        lineChart.setBackgroundColor(Color.WHITE);
        lineChart.getAxisRight().setDrawGridLines(false);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);  //여기서도 gird를 없애야 함다
        lineChart.getXAxis().setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);   // right, top에 표시되는 데이터 숨김
        lineChart.setNoDataText("");  //데이터 없을 때 나오는 text
        lineChart.getDescription().setEnabled(false);   //Description 웅앵 숨기기
        lineChart.getLegend().setEnabled(false);  //data set 숨기기
        lineChart.setScaleEnabled(false);
        lineChart.fitScreen();
        lineChart.getXAxis().setSpaceMax(0.5f);
        lineChart.getXAxis().setSpaceMin(0.5f);


        ArrayList<Entry> lineEntries = new ArrayList<>();

        lineEntries.add(new Entry(0, 60f));
        lineEntries.add(new Entry(1, 60f));
        lineEntries.add(new Entry(2, 50f));
        lineEntries.add(new Entry(3, 70f));
        lineEntries.add(new Entry(4, 30f));
        lineEntries.add(new Entry(5, 50f));
        lineEntries.add(new Entry(6, 60f));

        LineDataSet set1 = new LineDataSet(lineEntries, "LineChart Data Set");
        set1.setFillAlpha(70);
        set1.setCircleColor(Color.parseColor("#FAD064"));
        set1.setFillColor(Color.parseColor("#FAD064"));
        set1.setColor(Color.LTGRAY);
        set1.setLineWidth(2f);
        set1.setCircleSize(5f);
        set1.setDrawValues(false);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(set1);

        LineData data = new LineData(set1);
        lineChart.setData(data);

        return view;
    }
}