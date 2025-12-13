package com.model.service;

import com.model.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.model.domain.Member;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class AddMemberRequest {

    @NotBlank(message = "이름은 필수 입력 값입니다.")
    @Pattern(regexp = "^[a-zA-Z0-9가-힣]*$", message = "이름에는 특수문자와 공백을 사용할 수 없습니다.") //
    private String name;

    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    @Email(message = "이메일 형식이 올바르지 않습니다.") //
    private String email;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")

    @Pattern(regexp = "^(?=.*[A-Z]).{8,}$", message = "비밀번호는 8자 이상이어야 하며, 대문자를 포함해야 합니다.") //
    private String password;

    @NotBlank(message = "나이는 필수 입력 값입니다.")

    @Pattern(regexp = "^(19|[2-8][0-9]|90)$", message = "나이는 19세 이상 90세 이하만 가능합니다.") //
    private String age;

    @NotBlank(message = "모바일 번호는 필수 입력 값입니다.")
    private String mobile;

    @NotBlank(message = "주소는 필수 입력 값입니다.")
    private String address;

    public Member toEntity() {
        return Member.builder()
                .name(name)
                .email(email)
                .password(password)
                .age(age)
                .mobile(mobile)
                .address(address)
                .build();
    }
}