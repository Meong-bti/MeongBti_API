package projectB.meongbti.member.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import projectB.meongbti.member.service.MemberService;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class AuthenticationConfig extends WebSecurityConfigurerAdapter {

    private final MemberService memberService;
    @Value("${jwt.secret-key}")
    private String secretKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
//               .csrf().disable()
         .authorizeRequests()
               .anyRequest().permitAll();
//               .antMatchers("/member/signup", "/member/login").permitAll()
//               .anyRequest().authenticated()
//               .and()
//               .sessionManagement()
//               .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//               .and()
//               .addFilterBefore(new JwtTokenFilter(memberService, secretKey), UsernamePasswordAuthenticationFilter.class)
//
//               ;


    }




}
