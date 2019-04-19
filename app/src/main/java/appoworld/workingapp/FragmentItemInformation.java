package appoworld.workingapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import appoworld.workingapp.DataModel.PlaceInfo;

public class FragmentItemInformation extends Fragment {
    public FragmentItemInformation() {
    }

    View view;
    DatabaseReference InformationRef;
    private TextView tvaddress, tvtiming, tvphone, tvwebsiteurl;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_information, container, false);
        tvaddress = (TextView) view.findViewById(R.id.tv_address);
        tvphone = (TextView) view.findViewById(R.id.tv_phone);
        tvtiming = (TextView) view.findViewById(R.id.tv_timing);
        tvwebsiteurl = (TextView) view.findViewById(R.id.tv_websiteurl);


        FirebaseDatabase.getInstance().getReference("Hotel").child(Constants.root).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    PlaceInfo placeInfo=dataSnapshot.getValue(PlaceInfo.class);
                    tvaddress.setText(placeInfo.getAddress());
                    tvwebsiteurl.setText(placeInfo.getWebsiteURL());
                    tvphone.setText(placeInfo.getPhone());
                    tvtiming.setText(placeInfo.getTiming());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    return view;
    }





    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);





    }
}
