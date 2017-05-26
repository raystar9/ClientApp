package shoeping.clientapp;

import android.content.Context;
import android.content.Intent;
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

/**
 * Created by test on 2017-05-24.
 */

public class CardFragments extends Fragment {

    int _speciesId;
    DatabaseManager databaseManager;
    ItemInfo[] _itemInfos;

    public CardFragments(int speciesId, ItemInfo[] itemInfos) {
        _speciesId = speciesId;
        _itemInfos = itemInfos;
    }

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
        int tilePadding = getResources().getDimensionPixelSize(R.dimen.tile_padding);
        recyclerView.setPadding(tilePadding, tilePadding, tilePadding, tilePadding);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        databaseManager = new DatabaseManager();
        databaseManager.setLoadCompleteListener(new DatabaseManager.LoadCompleteListener()
        {
            @Override
            public void onLoadComplete(boolean isData) {

            }
        });




        return recyclerView;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DetailsActivity.class);
//                    intent.putExtra(DetailsActivity.EXTRA_SERIAL_NUMBER, serialNumber);
                    context.startActivity(intent);
                }
            });
        }
    }

    private class ContentAdapter extends RecyclerView.Adapter<ViewHolder> {


        private Drawable[] drawables;
        private String[] nameArray;
        private String[] priceArray;
        private String[] sizeArray;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            TypedArray a = resources.obtainTypedArray(_speciesId);
            drawables = new Drawable[a.length()];
            nameArray = new String[_itemInfos.length];
            priceArray = new String[_itemInfos.length];
            sizeArray = new String[_itemInfos.length];
            for (int i = 0; i < _itemInfos.length; i++) {
                drawables[i] = a.getDrawable(i);
                nameArray[i] = _itemInfos[i].shoesName;
                priceArray[i] = _itemInfos[i].price;
                sizeArray[i] = _itemInfos[i].size;
            }
            a.recycle();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()), parent);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.picture.setImageDrawable(drawables[position % drawables.length]);
            holder.name.setText(nameArray[position % nameArray.length]);
            holder.price.setText(priceArray[position % priceArray.length]);
            holder.size.setText(sizeArray[position % sizeArray.length]);
        }

        @Override
        public int getItemCount() {
            return drawables.length;
        }

    }
}
