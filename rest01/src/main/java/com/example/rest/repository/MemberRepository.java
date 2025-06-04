package com.example.rest.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.rest.dto.MemberDTO;

@Repository
public class MemberRepository {
	
    private final List<MemberDTO> list = new ArrayList<>();


    // 전체 리스트 반환
    public List<MemberDTO> findAll() {
        return new ArrayList<>(list); // 불변 컬렉션 반환
    }

    
    // 이름으로 검색
    public Optional<MemberDTO> findByName(String name) {
        return list.stream()
                .filter(m -> m.getName().equalsIgnoreCase(name))
                .findFirst();
    }
    

    // 이메일로 검색
    public Optional<MemberDTO> findByEmail(String email) {
        return list.stream()
                .filter(m -> m.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    
    // 저장
    public boolean save(MemberDTO member) {
        return list.add(member);
    }

    
    // 초기화 (테스트용)
    public void clear() {
        list.clear();
    }
}
