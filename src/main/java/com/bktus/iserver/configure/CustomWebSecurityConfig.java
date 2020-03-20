package com.bktus.iserver.configure;

import com.bktus.iserver.configure.security.IServerPasswordEncoder;
import com.bktus.iserver.configure.security.IServerSecurityAuthenticationProvider;
import com.bktus.iserver.configure.security.IServerUsernamePasswordAuthenticationFilter;
import com.bktus.iserver.service.IServerUserDetailService;
import com.bktus.iserver.service.IUserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.annotation.Resource;

@Configuration
public class CustomWebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private IServerUserDetailService userDetailService;

    @Resource
    private IServerSecurityAuthenticationProvider authenticationProvider;

    @Resource
    private IServerPasswordEncoder passwordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
                authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin().loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .successForwardUrl("/")
                .failureForwardUrl("/login?error")
                .and()
                .logout().permitAll();

        http.addFilterAt(authenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(authenticationProvider)
                .userDetailsService(userDetailService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers(
                        "/",
                        "/build/**",
                        "/dist/**",
                        "/plugins/**",
                        "/login/**",
                        "/register/**");
    }

    //注册自定义的UsernamePasswordAuthenticationFilter
    @Bean
    IServerUsernamePasswordAuthenticationFilter authenticationFilter() throws Exception {
        IServerUsernamePasswordAuthenticationFilter filter = new IServerUsernamePasswordAuthenticationFilter();
        filter.setAllowSessionCreation(true);
        filter.setRequiresAuthenticationRequestMatcher(
                new AntPathRequestMatcher("/login", "POST"));

        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

}
