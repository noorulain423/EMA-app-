package com.example.survey;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class MyAdapter extends FirebaseRecyclerAdapter <Locations, MyAdapter.MyViewHolder> {

    @Override
    public int getItemCount() {
        return super.getItemCount( );
    }

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MyAdapter(@NonNull FirebaseRecyclerOptions<Locations> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MyViewHolder holder,int position,@NonNull Locations earlierMoments) {
        holder.address.setText(earlierMoments.getAddress());
        holder.steps.setText(earlierMoments.getSteps());
        holder.mobile_usage.setText(earlierMoments.getMobile_usage());




    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent,int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.locationlist,parent,false);
        return new MyViewHolder(view);

    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView address;
        TextView steps;
        TextView mobile_usage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            address = itemView.findViewById(R.id.txt_loc);
            steps = itemView.findViewById(R.id.txt_steps);
            mobile_usage = itemView.findViewById(R.id.txt_usage);

        }
    }
}
