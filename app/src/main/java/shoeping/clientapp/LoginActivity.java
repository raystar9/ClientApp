package shoeping.clientapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText _editText_id;
    EditText _editText_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.btn_login);
        _editText_id = (EditText) findViewById(R.id.edt_id);
        _editText_pw = (EditText) findViewById(R.id.edt_pw);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkLoginInfoCorrect();
            }
        });
    }

    private String checkLoginInfoCorrect() {

        DatabaseManager databaseManager = DatabaseManager.getInstance();

        String string_id = databaseManager.getId();
        String string_pw = databaseManager.getPw();

        if(string_id.equals(_editText_id.getText()) && string_pw.equals(_editText_pw.getText())) {
                return string_id;
        }
        else {
            return null;
        }
    }
}
