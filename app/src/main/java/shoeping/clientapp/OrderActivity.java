package shoeping.clientapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        TextView textView_id = (TextView) findViewById(R.id.idTxv);
        EditText editText_adress = (EditText) findViewById(R.id.adressEdt);
        EditText editText_phone = (EditText) findViewById(R.id.phoneEdt);
        EditText editText_comment = (EditText) findViewById(R.id.commentEdt);
        Button button_order = (Button) findViewById(R.id.orderBtn);

        
    }
}
