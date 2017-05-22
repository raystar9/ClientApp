package shoeping.clientapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        final DatabaseManager databaseManager = DatabaseManager.getInstance();

        TextView textView_id = (TextView) findViewById(R.id.idTxv);
        final EditText editText_address = (EditText) findViewById(R.id.addressEdt);
        final EditText editText_phone = (EditText) findViewById(R.id.phoneEdt);
        final EditText editText_comment = (EditText) findViewById(R.id.commentEdt);
        Button button_order = (Button) findViewById(R.id.orderBtn);

        textView_id.setText("id");

        button_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = editText_address.getText().toString();
                String phone = editText_phone.getText().toString();
                String comment= editText_comment.getText().toString();

             //   databaseManager.commitToDatabase(address, phone, comment);
            }
        });
    }
}
