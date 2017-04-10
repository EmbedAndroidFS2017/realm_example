package pibs.swisscom.com.realmdbexample.Model;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Student extends RealmObject {
    @PrimaryKey
    private long id;
    public String name;
    public String surname;

    public Student() {
        id = UUID.randomUUID().getMostSignificantBits();
    }
}
