package gcarroll.com.irishroadwatchlive.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import gcarroll.com.irishroadwatchlive.R;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment must implement the {@link DublinCamFragment
 * .OnFragmentInteractionListener} interface to handle interaction events. Use the
 */
public class DublinCamFragment extends Fragment {

  // private OnFragmentInteractionListener mListener;

  public DublinCamFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(final Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override
  public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {

    if (container == null) {
      return null;
    }

    // Inflate the layout for this fragment
    return inflater.inflate(R.layout.fragment_dublin_cam, container, false);
  }

  // @Override
  // public void onAttach(final Context context) {
  // super.onAttach(context);
  // if (context instanceof OnFragmentInteractionListener) {
  // mListener = (OnFragmentInteractionListener) context;
  // }
  // else {
  // throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
  // }
  // }
  //
  // @Override
  // public void onDetach() {
  // super.onDetach();
  // mListener = null;
  // }

  /**
   * This interface must be implemented by activities that contain this fragment to allow an interaction in this
   * fragment to be communicated to the activity and potentially other fragments contained in that activity.
   * <p/>
   * See the Android Training lesson
   * <a href= "http://developer.android.com/training/basics/fragments/communicating.html" >Communicating with Other
   * Fragments</a> for more information.
   */
  // public interface OnFragmentInteractionListener {
  // // TODO: Update argument type and name
  // void onFragmentInteraction(Uri uri);
  // }
}
