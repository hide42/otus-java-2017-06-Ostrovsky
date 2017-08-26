package Entity;

import javax.persistence.*;
@Entity
@Table(name = "user")
public abstract class DataSet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false)
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public DataSet(long id) {
        this.id = id;
    }

    public DataSet() {
    }

    public long getId(){
        return id;}

}
