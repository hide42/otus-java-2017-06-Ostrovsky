package dataSets;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "users")
public class User extends DataSet {

    @Column(name = "name")
    private String name;

    @OneToOne(cascade = CascadeType.ALL, targetEntity = AddressDataSet.class, mappedBy = "user", orphanRemoval = true)
    private AddressDataSet address;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = PhoneDataSet.class, mappedBy = "user", orphanRemoval = true)
    private List<PhoneDataSet> phones;

    public User() {
    }

    public User(String name, AddressDataSet address, PhoneDataSet... phones) {
        this.setId(-1);
        this.setName(name);
        this.setAddress(address);
        address.setUser(this);
        this.setPhones(Arrays.asList(phones));
        Arrays.asList(phones).forEach((p) -> p.setUser(this));

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private void setAddress(AddressDataSet address) {
        this.address = address;
    }

    public List<PhoneDataSet> getPhones() {
        return phones;
    }

    private void setPhones(List<PhoneDataSet> phones) {
        this.phones = phones;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", address=" + address +
                ", phones=" + phones +
                '}';
    }
}

