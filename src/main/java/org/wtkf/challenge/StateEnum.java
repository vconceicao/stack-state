package org.wtkf.challenge;

import com.google.gson.annotations.SerializedName;

public enum StateEnum {


  @SerializedName("no_data")
  NO_DATA(1),
  @SerializedName("clear")
  CLEAR(2),
  @SerializedName("warning")
  WARNING(3),
  @SerializedName("alert")
  ALERT(4);

  public final Integer value;

  private StateEnum(Integer value) {
    this.value = value;
  }


}
