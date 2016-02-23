package gcarroll.com.irishroadwatchlive.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gcarroll on 23/02/2016.
 */
public class DublinCamera {

  @SerializedName("Id")
  @Expose
  private Integer Id;

  @SerializedName("Area")
  @Expose
  private String Area;

  @SerializedName("Junction")
  @Expose
  private String Junction;

  @SerializedName("Url")
  @Expose
  private String Url;

  @SerializedName("Fav")
  @Expose
  private Boolean Fav;

  /**
   * @return The Id
   */
  public Integer getId() {
    return Id;
  }

  /**
   * @param Id The Id
   */
  public void setId(final Integer Id) {
    this.Id = Id;
  }

  /**
   * @return The Area
   */
  public String getArea() {
    return Area;
  }

  /**
   * @param Area The Area
   */
  public void setArea(final String Area) {
    this.Area = Area;
  }

  /**
   * @return The Junction
   */
  public String getJunction() {
    return Junction;
  }

  /**
   * @param Junction The Junction
   */
  public void setJunction(final String Junction) {
    this.Junction = Junction;
  }

  /**
   * @return The Url
   */
  public String getUrl() {
    return Url;
  }

  /**
   * @param Url The Url
   */
  public void setUrl(final String Url) {
    this.Url = Url;
  }

  /**
   * @return The Fav
   */
  public Boolean getFav() {
    return Fav;
  }

  /**
   * @param Fav The Fav
   */
  public void setFav(final Boolean Fav) {
    this.Fav = Fav;
  }
}
