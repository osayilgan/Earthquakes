package demos.okan.earthquakes.view.earthquakes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import demos.okan.earthquakes.R;
import demos.okan.earthquakes.repository.model.Earthquake;
import demos.okan.earthquakes.util.Constants;

@Singleton
public class EarthquakeAdapter extends RecyclerView.Adapter<EarthquakeAdapter.ViewHolder> {

    private List<Earthquake> earthquakes;
    private ClickListener clickListener;

    @Inject
    public EarthquakeAdapter() {
        earthquakes = new ArrayList<>();
    }

    public void setClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.earthquake_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setData(earthquakes.get(position));
    }

    @Override
    public int getItemCount() {
        return earthquakes.size();
    }

    /**
     * Set Entire data set in the Adapter.
     *
     * @param earthquakes
     */
    public void setData(List<Earthquake> earthquakes) {
        this.earthquakes= earthquakes;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        CardView parentView;
        TextView dateTime;
        TextView magnitude;

        ViewHolder(View itemView) {
            super(itemView);
            parentView = itemView.findViewById(R.id.earthquake_item);
            dateTime = itemView.findViewById(R.id.date_time);
            magnitude = itemView.findViewById(R.id.magnitude);

            /* Set ClickListener */
            itemView.setOnClickListener(v -> {
                if (clickListener != null && getAdapterPosition() != RecyclerView.NO_POSITION) clickListener.onClickItem(earthquakes.get(getAdapterPosition()));
            });
        }

        void setData(Earthquake earthquake) {
            dateTime.setText(earthquake.getDateTime());
            magnitude.setText(Double.toString(earthquake.getMagnitude()));

            /* Set Background Color */
            setBackgroundColor(earthquake.getMagnitude() >= Constants.EARTHQUAKE_MAGNITUDE_THRESHOLD);
        }

        private void setBackgroundColor(boolean changeColor) {
            parentView.setCardBackgroundColor(parentView.getContext().getResources().getColor((changeColor) ? R.color.highPriorityBackgroundColor : R.color.lowPriorityBackgroundColor));
        }
    }

    public interface ClickListener {

        /**
         * Called when an Earthquake Item is Clicked.
         *
         * @param earthquake
         */
        void onClickItem(Earthquake earthquake);
    }
}
