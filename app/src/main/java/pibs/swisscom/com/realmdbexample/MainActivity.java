package pibs.swisscom.com.realmdbexample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import pibs.swisscom.com.realmdbexample.models.Student;

public class MainActivity extends ListActivity {
    Realm realm;
    ArrayList<Student> students;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        // Realm has to be initialized before this (see RealmDbExampleApplication.java)
        realm = Realm.getDefaultInstance();

        RealmResults<Student> result = realm.where(Student.class).findAll();
        students = new ArrayList<>(result);

        ArrayAdapter<Student> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
        this.setListAdapter(adapter);
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_add) {
            Intent intent = new Intent(this, AddStudentActivity.class);
            startActivity(intent);
        }
        return true;
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
