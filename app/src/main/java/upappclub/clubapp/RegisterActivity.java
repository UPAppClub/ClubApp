package upappclub.clubapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by SBEBS on 7/31/2016.
 */
public class RegisterActivity extends AppCompatActivity {

    protected EditText firstName;
    protected EditText surname;
    protected EditText email;
    protected EditText password;
    protected Button register;
    protected TextView loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = (EditText) findViewById(R.id.input_name);
        surname = (EditText) findViewById(R.id.input_surname);
        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);
        register = (Button) findViewById(R.id.btn_register);
        loginLink = (TextView) findViewById(R.id.link_login);

        register.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                signup();
            }
        });

        loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //TODO: Registration code
                finish();
            }
        });
    }

    public void signup(){
        if(!validate()){
            onSignupFailed();
            return;
        }

        register.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(RegisterActivity.this, R.style.AppTheme);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String enteredName = firstName.getText().toString();
        String enteredSurname = surname.getText().toString();
        String enteredEmail = email.getText().toString();
        String enteredPassword = password.getText().toString();

    }

    public void onSignupSuccess() {
        register.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        register.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String enteredName = firstName.getText().toString();
        String enteredEmail = email.getText().toString();
        String enteredPassword = password.getText().toString();

        if (enteredName.isEmpty() || enteredName.length() < 3) {
            firstName.setError("at least 3 characters");
            valid = false;
        } else {
            firstName.setError(null);
        }

        if (enteredEmail.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(enteredEmail).matches()) {
            email.setError("enter a valid email address");
            valid = false;
        } else {
            email.setError(null);
        }

        if (enteredPassword.isEmpty() || enteredPassword.length() < 4 || enteredPassword.length() > 10) {
            password.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            password.setError(null);
        }

        return valid;
    }
}
