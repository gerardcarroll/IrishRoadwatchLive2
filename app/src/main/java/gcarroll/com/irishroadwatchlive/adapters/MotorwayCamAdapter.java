package gcarroll.com.irishroadwatchlive.adapters;

import java.util.List;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import gcarroll.com.irishroadwatchlive.R;
import gcarroll.com.irishroadwatchlive.models.DublinCamera;

/**
 * Created by Gerard on 25/02/2016.
 */
public class MotorwayCamAdapter extends RecyclerView.Adapter<MotorwayCamAdapter.MotorwayCamViewHolder> {

  private List<DublinCamera> mMotorwayCams;

  public MotorwayCamAdapter(List<DublinCamera> motorwayCams) {
    this.mMotorwayCams = motorwayCams;
  }

  @Override
  public MotorwayCamViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.motorway_cam_textview, parent, false);
    return new MotorwayCamViewHolder(view);
  }

  @Override
  public void onBindViewHolder(MotorwayCamViewHolder holder, int position) {
    holder.textViewJunction.setText(mMotorwayCams.get(position).getJunction());
  }

  @Override
  public int getItemCount() {
    return mMotorwayCams.size();
  }

  public class MotorwayCamViewHolder extends RecyclerView.ViewHolder {

    public TextView textViewJunction;

    public MotorwayCamViewHolder(View v) {
      super(v);
      textViewJunction = (TextView) v.findViewById(R.id.motorway_cam_junction);
    }
  }
}
