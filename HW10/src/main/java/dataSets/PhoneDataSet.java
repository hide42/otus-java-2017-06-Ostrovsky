package dataSets;

import javax.persistence.*;

@Entity
@Table(name = "phones")
public class PhoneDataSet extends DataSet{

    @Column(name = "number")
    private String number;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public PhoneDataSet() {
    }

    public PhoneDataSet(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PhoneDataSet{" +
                "number='" + number + '\'' +
                '}';
    }
}
