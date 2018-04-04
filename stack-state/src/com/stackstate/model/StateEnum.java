package com.stackstate.model;

import com.google.gson.annotations.SerializedName;

public enum StateEnum {


	 @SerializedName("no_data")
	 NO_DATA,
	 @SerializedName("clear")
	 CLEAR,
	 @SerializedName("warning")
	 WARNING, 
	 @SerializedName("alert")
	 ALERT;
	


	
	
	
	
}
