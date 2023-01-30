package com.example.filterandinterceptor.dto;

import lombok.*;

// Lombok
@Data   // @ToString,@EqualsAndHashCode, @Getter, @Setter, @RequiredArgsConstructor 자동생성
//@Getter // 게터 자동생성
//@Setter // 세터 자동생성
@NoArgsConstructor  // 기본생성자 자동생성
@AllArgsConstructor // 생성자 자동생성
public class User {

    private String name;
    private int age;

}
