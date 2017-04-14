package pibs.swisscom.com.realmdbexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import io.realm.Realm;
import pibs.swisscom.com.realmdbexample.models.Student;

public class AddStudentActivity extends Activity {
    Realm realm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);
    }

    @Override
    protected void onResume() {
        realm = Realm.getDefaultInstance();
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }

    public void onClickSave(View v) {
        Student student = new Student();

        student.fullName = ((EditText) findViewById(R.id.et_name)).getText().toString();
        student.email = ((EditText) findViewById(R.id.et_email)).getText().toString();

        String strAge = ((EditText) findViewById(R.id.et_age)).getText().toString();
        student.age = strAge.isEmpty() ? 0 : Integer.parseInt(strAge);

        realm.beginTransaction();
        realm.copyToRealm(student);
        realm.commitTransaction();

        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }

    public void onClickCancel(View v) {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
