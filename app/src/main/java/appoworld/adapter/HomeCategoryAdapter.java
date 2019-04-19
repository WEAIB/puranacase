package appoworld.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import appoworld.Model.HomeCategorymodel;
import appoworld.workingapp.R;

public class HomeCategoryAdapter  extends RecyclerView.Adapter<HomeCategoryAdapter.MyViewHolder>{


    private Context mcontext;
    private List<HomeCategorymodel> item_list;
    public HomeCategoryAdapter(Context mcontext, List<HomeCategorymodel> item_list) {
        this.mcontext = mcontext;
        this.item_list = item_list;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView item_name;
        public ImageView item_image;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_name);
            item_image= itemView.findViewById(R.id.item_image);


        }
    }
    @NonNull
    @Override
    public HomeCategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_category_item,viewGroup,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeCategoryAdapter.MyViewHolder myViewHolder, int i) {
        HomeCategorymodel model = item_list.get(i);
        myViewHolder.item_image.setImageResource(model.getImage());
        myViewHolder.item_name.setText(model.getName());

    }

    @Override
    public int getItemCount() {
        return item_list.size();
    }


}
