package com.company.myapp.models;

import java.io.Serializable;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class DataModel implements Serializable {
  public String toJson() {
    GsonBuilder builder = new GsonBuilder();

    Gson gson = builder.create();

    return gson.toJson(this);
  }
}