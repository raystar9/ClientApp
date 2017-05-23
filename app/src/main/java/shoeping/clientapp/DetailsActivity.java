package shoeping.clientapp;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "position";
    public static final String EXTRA_SPECIES = "species";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Resources resources = getResources();
        DatabaseManager databaseManager = DatabaseManager.getInstance();

        int position = getIntent().getIntExtra(EXTRA_POSITION, 0);
        int species = getIntent().getIntExtra(EXTRA_SPECIES, 0);

        ShoesDataPack pack = databaseManager.pack(species, position);

        TypedArray placePictures = resources.obtainTypedArray(species);
        ImageView placePicutre = (ImageView) findViewById(R.id.detailImg);
        placePicutre.setImageDrawable(placePictures.getDrawable(position % placePictures.length()));

        placePictures.recycle();
    }
}
