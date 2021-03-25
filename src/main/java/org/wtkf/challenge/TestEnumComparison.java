package org.wtkf.challenge;

import com.google.gson.Gson;

public class TestEnumComparison {
  public static void main(String[] args) {

    System.out.println(StateEnum.NO_DATA.value < StateEnum.CLEAR.value);

    //serializing

    final String s = new Gson().toJson(StateEnum.NO_DATA);

    System.out.println(s);

  }
}
