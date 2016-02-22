package gcarroll.com.irishroadwatchlive.requests;

import java.io.UnsupportedEncodingException;
import java.util.Map;

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

  private final Gson gson = new Gson();

  private final Class<T> clazz;

  private final Map<String, String> headers;

  private final Response.Listener<T> listener;

  /**
   * Make a GET request and return a parsed object from JSON.
   *
   * @param url URL of the request to make
   * @param clazz Relevant class object, for Gson's reflection
   * @param headers Map of request headers
   */
  public GsonRequest(final String url, final Class<T> clazz, final Map<String, String> headers,
      final Response.Listener<T> listener, final Response.ErrorListener errorListener) {
    super(Method.GET, url, errorListener);
    this.clazz = clazz;
    this.headers = headers;
    this.listener = listener;
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
      return Response.success(gson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
    }
    catch (final UnsupportedEncodingException e) {
      return Response.error(new ParseError(e));
    }
    catch (final JsonSyntaxException e) {
      return Response.error(new ParseError(e));
    }
  }
}
