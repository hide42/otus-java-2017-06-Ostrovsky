package Entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class UserDataSet extends DataSet {

    @Column(name = "name")
    private String name;

    @Column(name = "age", nullable = false)
    private int age;

    //for instance
    public UserDataSet() {
        super();
    }

    public UserDataSet(long id, String name, int age) {
        super(id);
        this.age = age;
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserDataSet (id: " + getId() + ", name: " + name + ", age: " + age + ")";
    }
}
