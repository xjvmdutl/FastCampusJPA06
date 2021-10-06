package com.fastcampus.jpa.FastCampusJPA06.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable//임베디드를 할수있는 클래스로 표기
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String city;     //시
    private String district; //구
    @Column(name = "address_detail")
    private String detail;   //상세주소
    private String zipCode;  //우편번호
}
