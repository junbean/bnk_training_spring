package com.example.memo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "members")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class MemberEntity extends BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
    @Column(length = 50, nullable = false, unique = true)
    @Size(min = 3, max = 50)
    private String username;
    
    @Column(length = 20, nullable = false)
    @Size(min = 4, max = 20)
    private String password;

    @Column(length = 50)
    @Size(min = 2, max = 50)
    private String name;
    
    @Column(length = 13)
    @Size(max = 13, message = "전화번호는 최대 13자리까지 입력 가능합니다")
    @Pattern(regexp = "^[0-9\\-]+$", message = "전화번호 형식이 올바르지 않습니다")
    private String phone;
}

/*


@Table(name = "members", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username")
})


*/