package id.dekz.retrofitexample.model.weather;

import com.google.gson.annotations.SerializedName;

public class Main{

	@SerializedName("temp")
	private double temp;

	@SerializedName("temp_min")
	private double tempMin;

	@SerializedName("humidity")
	private int humidity;

	@SerializedName("pressure")
	private double pressure;

	@SerializedName("temp_max")
	private double tempMax;

	public Main() {
	}

	public void setTemp(double temp){
		this.temp = temp;
	}

	public double getTemp(){
		return temp;
	}

	public void setTempMin(double tempMin){
		this.tempMin = tempMin;
	}

	public double getTempMin(){
		return tempMin;
	}

	public void setHumidity(int humidity){
		this.humidity = humidity;
	}

	public int getHumidity(){
		return humidity;
	}

	public void setPressure(double pressure){
		this.pressure = pressure;
	}

	public double getPressure(){
		return pressure;
	}

	public void setTempMax(double tempMax){
		this.tempMax = tempMax;
	}

	public double getTempMax(){
		return tempMax;
	}

	@Override
 	public String toString(){
		return 
			"Main{" + 
			"temp = '" + temp + '\'' + 
			",temp_min = '" + tempMin + '\'' + 
			",humidity = '" + humidity + '\'' + 
			",pressure = '" + pressure + '\'' + 
			",temp_max = '" + tempMax + '\'' + 
			"}";
		}

	public String getResolvedTemp(){
		return Math.round(temp)+"\u00b0";
	}

	public String getResolvedTempMin(){
		return Math.round(tempMin)+"\u00b0";
	}

	public String getResolvedTempMax(){
		return Math.round(tempMax)+"\u00b0";
	}

	public String getResolvedHumidity(){
		return humidity+"%";
	}

	public String getResolvedPressure(){
		return Math.round(pressure)+" hPa";
	}

}