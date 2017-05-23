package shoeping.clientapp;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Resources resources = getResources();

        int position = getIntent().getIntExtra(EXTRA_POSITION, 0);

        TypedArray placePictures = resources.obtainTypedArray(R.array.slipper);
        ImageView placePicutre = (ImageView) findViewById(R.id.detailImg);
        placePicutre.setImageDrawable(placePictures.getDrawable(position % placePictures.length()));

        placePictures.recycle();
    }
}
