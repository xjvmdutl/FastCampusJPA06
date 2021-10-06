package com.fastcampus.jpa.FastCampusJPA06.domain;

import com.fastcampus.jpa.FastCampusJPA06.domain.listener.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Data
@ToString(callSuper = true)//toString 재정의
@EqualsAndHashCode(callSuper = true)
public class UserHistory extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private String email;

    @ManyToOne
    @ToString.Exclude
    private User user;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Embedded
    @AttributeOverrides(//컬럼 속성값을 재정의 하겠다
            {
                    @AttributeOverride(name = "city", column = @Column(name = "home_city")),//우리가 사용하는 컬럼을 재정의해준다.
                    @AttributeOverride(name = "district", column = @Column(name = "home_district")),
                    @AttributeOverride(name = "detail", column = @Column(name = "home_address_detail")),
                    @AttributeOverride(name = "zipCode", column = @Column(name = "home_zip_code"))
            }
    )
    private Address homeAddress;

    @Embedded
    @AttributeOverrides(
            {
                    @AttributeOverride(name = "city", column = @Column(name = "company_city")),//우리가 사용하는 컬럼을 재정의해준다.
                    @AttributeOverride(name = "district", column = @Column(name = "company_district")),
                    @AttributeOverride(name = "detail", column = @Column(name = "company_address_detail")),
                    @AttributeOverride(name = "zipCode", column = @Column(name = "company_zip_code"))
            }
    )
    private Address companyAddress;
}
