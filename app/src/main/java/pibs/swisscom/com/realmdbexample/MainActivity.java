package pibs.swisscom.com.realmdbexample;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import pibs.swisscom.com.realmdbexample.Model.Student;

public class MainActivity extends ListActivity {
    ArrayList<Student> students;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
    }

    @Override
    protected void onResume() {
        realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        for(int i = 0; i < 10; i++) {
            Student st = new Student();
            st.name = "Name " + i;
            st.surname = "Surname " + i;
            realm.copyToRealmOrUpdate(st);
        }
        realm.commitTransaction();

        // Es macht keinen Sinn die Liste zuerst in der DB zu speichern, dann auszulesen und anzuzeigen
        // Hier nur Demo-Zweck

        RealmResults<Student> result = realm.where(Student.class).findAll();
        students = new ArrayList<>(result);

        ArrayAdapter<Student> adapter = new ArrayAdapter<Student>(this, android.R.layout.simple_list_item_1, students);
        this.setListAdapter(adapter);
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
