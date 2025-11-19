package com.model.service;

import com.model.domain.Member;
import com.model.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional; // Optional 사용을 위해 필요

@Service
@Transactional // 트랜잭션 처리 (데이터 저장/수정 시 필수)
@RequiredArgsConstructor
public class MemberService { // 클래스 이름을 MemberService로 변경 (언더바 제거)

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화 객체

    // 중복 회원 검증 로직
    private void validateDuplicateMember(AddMemberRequest request) {
        // 이메일로 회원을 찾음 (Optional로 반환됨)
        Member findMember = memberRepository.findByEmail(request.getEmail());

        // 만약 회원이 존재하면(isPresent) 예외 발생
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    // 회원 가입(저장) 로직
    public Member saveMember(AddMemberRequest request) {
        validateDuplicateMember(request); // 1. 중복 체크

        // 2. 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(request.getPassword());
        request.setPassword(encodedPassword); // 암호화된 비밀번호로 교체

        // 3. 저장
        return memberRepository.save(request.toEntity());
    }

    public Member loginCheck(String email, String rawPassword) {
        Member member = memberRepository.findByEmail(email); // 이메일 조회
        if (member == null) {
            throw new IllegalArgumentException("등록되지 않은 이메일입니다.");
        }
        if (!passwordEncoder.matches(rawPassword, member.getPassword())) { // 비밀번호 확인
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return member; // 인증 성공 시 회원 객체 반환
    }
}