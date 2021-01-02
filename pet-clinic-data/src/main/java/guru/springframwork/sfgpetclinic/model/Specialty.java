package guru.springframwork.sfgpetclinic.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "specialties")
public class Specialty extends BaseEntity{

    @Column(name = "description")
    private String description;

}
