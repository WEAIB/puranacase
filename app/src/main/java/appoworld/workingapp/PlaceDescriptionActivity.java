package appoworld.workingapp;

import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import appoworld.adapter.ViewPagerAdapter;

public class PlaceDescriptionActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ImageView ivPlaceImage;
    private DatabaseReference imageRef;
    private KProgressHUD kProgressHUD;
    private String strPlacename;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        strPlacename=getIntent().getStringExtra("placename");
        Constants.root=strPlacename;

        tabLayout = findViewById(R.id.tablayout_id);
        viewPager =findViewById(R.id.viewpager_id);
        ivPlaceImage=(ImageView)findViewById(R.id.iv_place_image);
        kProgressHUD= KProgressHUD.create(PlaceDescriptionActivity.this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setLabel("Please wait")
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        ///Adding Fragments
        adapter.AddFragment(new FragmentItemInformation(),"Information");
        adapter.AddFragment(new FragmentItemDescription(),"Description");

        //adapter setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        FirebaseDatabase.getInstance().getReference("Hotel").child(strPlacename).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    dataSnapshot.child("imageURL").getValue();
                    Picasso.get().load(dataSnapshot.child("imageURL").getValue().toString()).into(ivPlaceImage, new Callback() {
                        @Override
                        public void onSuccess() {
                            kProgressHUD.dismiss();
                        }

                        @Override
                        public void onError(Exception e) {
                            Toast.makeText(PlaceDescriptionActivity.this, "Unable to load image", Toast.LENGTH_SHORT).show();
                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
