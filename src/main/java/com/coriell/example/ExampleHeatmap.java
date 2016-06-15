package com.coriell.example;

import java.util.Random;

import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.HeatSeries;
import com.vaadin.addon.charts.model.PlotOptionsHeatmap;
import com.vaadin.addon.charts.model.Stop;
import com.vaadin.addon.charts.model.style.SolidColor;

public class ExampleHeatmap extends Chart {
	
	
	public ExampleHeatmap() {
		super(ChartType.HEATMAP);
		getConfiguration().disableCredits();
		getConfiguration().setTitle("");
		getConfiguration().setSubTitle("");
		setCaption("");

		PlotOptionsHeatmap opt = new PlotOptionsHeatmap();
		opt.setBorderColor(SolidColor.WHITE);
		opt.setBorderWidth(1);
		getConfiguration().setPlotOptions(opt);

		getConfiguration().getyAxis().setTitle("");
		getConfiguration().getxAxis().setTitle("");
		getConfiguration().getColorAxis().setMin(0);
		
		getConfiguration().getLegend().setLabelFormat(" ");
		getConfiguration().getColorAxis().getLabels().setFormat(" ");
		
		getConfiguration().getColorAxis().setStops(
				new Stop(0.0f, SolidColor.GREEN),
				new Stop(0.2f, SolidColor.GREENYELLOW),
				new Stop(0.4f, SolidColor.YELLOW),
				new Stop(0.6f, SolidColor.ORANGE),
				new Stop(0.8f, SolidColor.RED),
				new Stop(1.0f, SolidColor.PURPLE));
		
		Random rnd = new Random();
		
		HeatSeries series = new HeatSeries();
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 5; j++) {
				series.addHeatPoint(i, j, rnd.nextInt(50));
			}
		}
		getConfiguration().addSeries(series);
	}
}
