package shoeping.clientapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText _editText_id;
    EditText _editText_pw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.loginBtn);

        _editText_id = (EditText) findViewById(R.id.idEdt);
        _editText_pw = (EditText) findViewById(R.id.passwordEdt);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    if (getLoginId() != null) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("id", getLoginId());

                        setResult(RESULT_OK);
                        finish();
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
        });
    }

    private String getLoginId() {
        DatabaseManager databaseManager = DatabaseManager.getInstance();

        databaseManager.setId(_editText_id.getText().toString());
        databaseManager.setPw(_editText_pw.getText().toString());

        databaseManager.CheckIdPw();

        if (checkPasswordIsCorrect(databaseManager.getId(), databaseManager.getPw())) {
            return databaseManager.getId();
        } else {
            Toast.makeText(getApplicationContext(), "로그인에 실패하였습니다.", Toast.LENGTH_SHORT).show();
            return null;
        }
    }

    private boolean checkPasswordIsCorrect(String id, String pw)
    {
        if (id.equals(_editText_id.getText()) && pw.equals(_editText_pw.getText())) {
            return true;
        } else
        {
            return false;
        }
    }
}
