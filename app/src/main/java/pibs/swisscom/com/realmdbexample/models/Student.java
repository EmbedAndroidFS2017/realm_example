package pibs.swisscom.com.realmdbexample.models;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Student extends RealmObject {
    @PrimaryKey
    private long id;
    public String fullName;
    public String email;
    public int age;

    public Student() {
        id = UUID.randomUUID().getMostSignificantBits();
    }
}
