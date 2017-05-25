package shoeping.clientapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText _editText_id;
    EditText _editText_pw;
    DatabaseManager databaseManager = DatabaseManager.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        String productId = getIntent().getStringExtra("productToken");

        Button loginButton = (Button) findViewById(R.id.loginBtn);

        _editText_id = (EditText) findViewById(R.id.idEdt);
        _editText_pw = (EditText) findViewById(R.id.passwordEdt);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestLoginInfo();
            }
        });

        //
        databaseManager.setLoadCompleteListener(new DatabaseManager.LoadCompleteListener() {
            @Override
            public void onLoadComplete(boolean isData) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                //intent.putExtra("id", id);
                

                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private void requestLoginInfo() {

        String _id = _editText_id.getText().toString();
        String _pw = _editText_pw.getText().toString();
        databaseManager.requestGetId(_id, _pw);
        databaseManager.requestGetPw(_id, _pw);
    }
}
