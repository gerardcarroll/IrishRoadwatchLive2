package gcarroll.com.irishroadwatchlive.requests;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Map;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * Created by gcarroll on 22/02/2016.
 */
public class GsonRequest<T> extends Request<T> {

  private final String LOG_TAG = GsonRequest.class.getSimpleName();

  private final Gson gson = new Gson();

  private final Map<String, String> headers;

  private final Response.Listener<T> listener;

  private final Type type;

  /**
   * Make a GET request and return a parsed object from JSON.
   *
   * @param url URL of the request to make
   * @param type Relevant Type of the object, for Gson's reflection
   * @param headers Map of request headers
   */
  public GsonRequest(final String url, Type type, final Map<String, String> headers,
      final Response.Listener<T> listener, final Response.ErrorListener errorListener) {
    super(Method.GET, url, errorListener);
    this.headers = headers;
    this.listener = listener;
    this.type = type;
  }

  @Override
  public Map<String, String> getHeaders() throws AuthFailureError {
    return headers != null
        ? headers
        : super.getHeaders();
  }

  @Override
  protected void deliverResponse(final T response) {
    listener.onResponse(response);
  }

  @Override
  protected Response<T> parseNetworkResponse(final NetworkResponse response) {
    try {
      final String json = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
      T parseObject = gson.fromJson(json, type);
      return Response.success(parseObject, HttpHeaderParser.parseCacheHeaders(response));
    }
    catch (final UnsupportedEncodingException e) {
      return Response.error(new ParseError(e));
    }
    catch (final JsonSyntaxException e) {
      Log.v(LOG_TAG, e.toString());
      return Response.error(new ParseError(e));
    }
  }
}
