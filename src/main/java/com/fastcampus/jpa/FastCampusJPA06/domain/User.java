package com.fastcampus.jpa.FastCampusJPA06.domain;

import com.fastcampus.jpa.FastCampusJPA06.domain.listener.Auditable;
import com.fastcampus.jpa.FastCampusJPA06.domain.listener.UserEntityListener;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Builder
@Entity
@EntityListeners(value = { UserEntityListener.class})
@ToString(callSuper = true)//toString 재정의
@EqualsAndHashCode(callSuper = true)
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;
    @NonNull
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @Builder.Default
    @OneToMany(fetch = FetchType.EAGER)//LAZY는 필요할 때 데이터를 가지고 온다(영속성 컨텍스트가 해당 데이터를 가지고 있을때)
    @JoinColumn(name = "user_id",insertable = false,updatable = false)
    @ToString.Exclude
    private List<UserHistory> userHistories = new ArrayList<>();//1,2,3,4,5,6 값을 배열 데이터

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "user_id")
    @ToString.Exclude
    private List<Review> reviews = new ArrayList<>();

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
