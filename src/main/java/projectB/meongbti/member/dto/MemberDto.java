package projectB.meongbti.member.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import projectB.meongbti.member.entity.Member;

import java.util.Collection;
import java.util.Collections;

@AllArgsConstructor
@Getter
public class MemberDto implements UserDetails {
    private Long memberId;
    private String memberEmail;
    private String memberPw;
    private String memberNick;



    public static MemberDto fromEntity(Member member) {
        return new MemberDto(
            member.getMemberId(),
            member.getMemberEmail(),
            member.getMemberPw(),
            member.getMemberNick()

        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
          return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return memberPw;
    }

    @Override
    public String getUsername() {
        return memberEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}



