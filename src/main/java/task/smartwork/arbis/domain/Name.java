package task.smartwork.arbis.domain;

import javax.persistence.*;

@Entity
@Table(name = "Name")
public class Name {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int nameId;
    private String firstName;
    private String lastName;

    public int getNameId() {
        return nameId;
    }

    public void setNameId(int nameId) {
        this.nameId = nameId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
