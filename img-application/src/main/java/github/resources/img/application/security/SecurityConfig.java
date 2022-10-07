package github.resources.img.application.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author pfxuchn@gmail.com
 * @date 2022/9/26 22:09
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private TokenFilter tokenFilter;

    @Bean( name="authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/api/user/login","/img/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .csrf().disable()
                .sessionManagement()
                // 因为使用了JWT，所以这里不管理Session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .cors();
        http.addFilterBefore(tokenFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
