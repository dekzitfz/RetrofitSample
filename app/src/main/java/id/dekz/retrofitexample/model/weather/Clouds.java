package id.dekz.retrofitexample.model.weather;

import com.google.gson.annotations.SerializedName;

public class Clouds{

	@SerializedName("all")
	private int all;

	public Clouds() {
	}

	public void setAll(int all){
		this.all = all;
	}

	public int getAll(){
		return all;
	}

	@Override
 	public String toString(){
		return 
			"Clouds{" + 
			"all = '" + all + '\'' + 
			"}";
		}
}