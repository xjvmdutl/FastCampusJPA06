package com.fastcampus.jpa.FastCampusJPA06.domain.converter;

import com.fastcampus.jpa.FastCampusJPA06.repository.dto.BookStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class BookStatusConverter implements AttributeConverter<BookStatus,Integer> {//Entity의 Atribute,DataBase 컬럼 타입
    @Override
    public Integer convertToDatabaseColumn(BookStatus attribute) {
        return attribute.getCode();
    }

    @Override
    public BookStatus convertToEntityAttribute(Integer dbData) {
        return dbData != null ? new BookStatus(dbData) : null;
    }
}
