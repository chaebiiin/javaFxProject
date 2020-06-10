package chart_pack;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.*;
import javafx.fxml.*;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.chart.*;

public class ChartController implements Initializable {
	@FXML
	PieChart pieChart;
	@FXML
	BarChart<String, Integer> barChart;
	@FXML
	AreaChart<String, Integer> areaChart;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		pieChart.setData(FXCollections.observableArrayList(new PieChart.Data("AWT", 10.0),
				new PieChart.Data("Swing", 30.0), new PieChart.Data("SWT", 25.0), new PieChart.Data("JavaFx", 25.0),
				new PieChart.Data("Others", 10.0)));

		XYChart.Series<String, Integer> series1 = new XYChart.Series<>();
		series1.setData(FXCollections.observableArrayList(
				new XYChart.Data("2015", 70), 
				new XYChart.Data("2016", 40),
				new XYChart.Data("2017", 50), 
				new XYChart.Data("2018", 30)
				
				));
		series1.setName("Cat1");
		
		XYChart.Series<String, Integer> series2 = new XYChart.Series<>();
		series2.setData(FXCollections.observableArrayList(
				new XYChart.Data("2015", 80), 
				new XYChart.Data("2016", 20),
				new XYChart.Data("2017", 90), 
				new XYChart.Data("2018", 25)
				
				));
		series2.setName("Cat2");

		barChart.setData(FXCollections.observableArrayList(series1, series2));

		XYChart.Series<String, Integer> series3 = new XYChart.Series<>();
		series3.setData(FXCollections.observableArrayList(
				new XYChart.Data("2015", 20), 
				new XYChart.Data("2016", 44),
				new XYChart.Data("2017", 56), 
				new XYChart.Data("2018", 85)
				
				));
		series3.setName("Cat3");
		areaChart.setData(FXCollections.observableArrayList(series3));

	}
}
