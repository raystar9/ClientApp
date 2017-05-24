package shoeping.clientapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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
        Button orderButton = (Button) findViewById(R.id.directOrderBtn);

        int position = getIntent().getIntExtra(EXTRA_POSITION, 0);
        int species = getIntent().getIntExtra(EXTRA_SPECIES, 0);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, LoginActivity.class);
                context.startActivity(intent);
            }
        });
//        ShoesDataPack pack = databaseManager.packShoesData(species, position);

        TypedArray placePictures = resources.obtainTypedArray(species);
        ImageView placePicutre = (ImageView) findViewById(R.id.detailImg);
        placePicutre.setImageDrawable(placePictures.getDrawable(position % placePictures.length()));

        placePictures.recycle();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }
}
