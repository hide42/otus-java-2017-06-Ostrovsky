package dataSets;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class AddressDataSet extends DataSet{

    @Column(name = "street")
    private String street;

    @OneToOne
    @JoinColumn(name = "userf_id")
    private User user;

    public AddressDataSet(String street) {
        this.street = street;
    }

    public AddressDataSet() {
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "AddressDataSet{" +
                "street='" + street + '\'' +
                '}';
    }
}
