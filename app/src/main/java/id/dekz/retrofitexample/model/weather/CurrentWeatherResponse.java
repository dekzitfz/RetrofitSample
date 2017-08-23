package id.dekz.retrofitexample.model.weather;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CurrentWeatherResponse{

	@SerializedName("dt")
	private long dt;

	@SerializedName("coord")
	private Coord coord;

	@SerializedName("visibility")
	private long visibility;

	@SerializedName("weather")
	private List<WeatherItem> weather;

	@SerializedName("name")
	private String name;

	@SerializedName("cod")
	private int cod;

	@SerializedName("main")
	private Main main;

	@SerializedName("clouds")
	private Clouds clouds;

	@SerializedName("id")
	private long id;

	@SerializedName("sys")
	private Sys sys;

	@SerializedName("base")
	private String base;

	@SerializedName("wind")
	private Wind wind;

	public CurrentWeatherResponse() {
	}

	public void setDt(long dt){
		this.dt = dt;
	}

	public long getDt(){
		return dt;
	}

	public void setCoord(Coord coord){
		this.coord = coord;
	}

	public Coord getCoord(){
		return coord;
	}

	public void setVisibility(long visibility){
		this.visibility = visibility;
	}

	public long getVisibility(){
		return visibility;
	}

	public void setWeather(List<WeatherItem> weather){
		this.weather = weather;
	}

	public List<WeatherItem> getWeather(){
		return weather;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setCod(int cod){
		this.cod = cod;
	}

	public int getCod(){
		return cod;
	}

	public void setMain(Main main){
		this.main = main;
	}

	public Main getMain(){
		return main;
	}

	public void setClouds(Clouds clouds){
		this.clouds = clouds;
	}

	public Clouds getClouds(){
		return clouds;
	}

	public void setId(long id){
		this.id = id;
	}

	public long getId(){
		return id;
	}

	public void setSys(Sys sys){
		this.sys = sys;
	}

	public Sys getSys(){
		return sys;
	}

	public void setBase(String base){
		this.base = base;
	}

	public String getBase(){
		return base;
	}

	public void setWind(Wind wind){
		this.wind = wind;
	}

	public Wind getWind(){
		return wind;
	}

	public String getResolvedLocation(){
		return name+", "+sys.getCountry();
	}

	public String getReadableDate(){
		Date date = new Date(dt * 1000L);
		@SuppressLint("SimpleDateFormat")
		DateFormat format = new SimpleDateFormat("EEE");
		return format.format(date);
	}

	@Override
 	public String toString(){
		return 
			"CurrentWeatherResponse{" + 
			"dt = '" + dt + '\'' + 
			",coord = '" + coord + '\'' + 
			",visibility = '" + visibility + '\'' + 
			",weather = '" + weather + '\'' + 
			",name = '" + name + '\'' + 
			",cod = '" + cod + '\'' + 
			",main = '" + main + '\'' + 
			",clouds = '" + clouds + '\'' + 
			",id = '" + id + '\'' + 
			",sys = '" + sys + '\'' + 
			",base = '" + base + '\'' + 
			",wind = '" + wind + '\'' + 
			"}";
		}
}