package shoeping.clientapp;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RecyclerView recyclerView =
                (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);

        recyclerView.setPadding(8, 8, 8, 8);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView picture;
        public TextView name;
        public TextView price;
        public TextView size;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.each_shoes, parent, false));
            picture = (ImageView) itemView.findViewById(R.id.pictureImv);
            name = (TextView) itemView.findViewById(R.id.shoesName);
            price = (TextView) itemView.findViewById(R.id.shoesPrice);
            size = (TextView) itemView.findViewById(R.id.shoesSize);
        }
    }

    private class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {
        private static final int LENGTH = 18;

        private final Drawable[] sliper;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            TypedArray a = resources.obtainTypedArray(R.array.sliper);
            sliper = new Drawable[a.length()];
            for (int i = 0; i < sliper.length; i++) {
                sliper[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.picture.setImageDrawable(sliper[position % sliper.length]);
        }

        @Override
        public int getItemCount() {
            return 0;
        }
    }
}
