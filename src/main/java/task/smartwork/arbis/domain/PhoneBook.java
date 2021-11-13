package task.smartwork.arbis.domain;


import javax.persistence.*;

@Entity
public class PhoneBook {

@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id ;

@ManyToOne(cascade = CascadeType.ALL)
@JoinColumn
private Name name;

private long  number;

private String type;


    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Name getName() {
        return name;
    }

    public Name setName(Name name) {
        this.name = name;
        return name;
    }

    public long getNumber() {
        return number;
    }

    public long setNumber(long number) {
        this.number = number;
        return number;
    }

    public String getType() {
        return type;
    }

    public String setType(String type) {
        this.type = type;
        return type;
    }
}
