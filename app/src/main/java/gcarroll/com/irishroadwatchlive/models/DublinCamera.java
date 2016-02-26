package gcarroll.com.irishroadwatchlive.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by gcarroll on 23/02/2016.
 */
public class DublinCamera implements Parcelable {

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

  public DublinCamera() {}

  public DublinCamera(Parcel in) {
    Id = in.readInt();
    Area = in.readString();
    Junction = in.readString();
    Url = in.readString();
    Fav = in.readByte() != 0; // Fav == true if byte != 0
  }

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

  @Override
  public String toString() {
    return Id + "\n" + Junction;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeInt(Id);
    dest.writeString(Area);
    dest.writeString(Junction);
    dest.writeString(Url);
    dest.writeByte((byte) (Fav
        ? 1
        : 0));
  }

  @SuppressWarnings("rawtypes")
  public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
    public DublinCamera createFromParcel(Parcel in) {
      return new DublinCamera(in);
    }

    public DublinCamera[] newArray(int size) {
      return new DublinCamera[size];
    }
  };
}
