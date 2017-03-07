package baseProject.configs;

import baseProject.exception.handlers.UnauthorizedEndpoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableAutoConfiguration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Value("${spring.security.require-https}")
    private String requireHttps;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/**/admin/**").hasAnyRole("ADMIN")
//                .antMatchers("/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .csrf().ignoringAntMatchers("/accounts/logout")
//                .csrfTokenRepository(new HttpSessionCsrfTokenRepository())
//                .and().formLogin().loginPage("/accounts/login").failureUrl("/accounts/login?error")
//                .permitAll()
//                .and()
//                .logout()
//                    .deleteCookies("remove")
//                    .invalidateHttpSession(false)
//                    .logoutUrl("/custom-logout")
//                    .logoutSuccessUrl("/logout-success")
//                    .and()
//                .headers();
                http
                .formLogin().loginPage("/login").permitAll()
                .and()
                .formLogin().defaultSuccessUrl("/home")
                .and()
                .authorizeRequests().antMatchers("/").permitAll()
                .and()
                .requestMatchers().antMatchers(
                "/login",
                "/",
                "/home")
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and().exceptionHandling()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/")
                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                .exceptionHandling().authenticationEntryPoint(new UnauthorizedEndpoint());

        if (requireHttps.equals("yes")) {
            http
                    .requiresChannel()
                    .anyRequest()
                    .requiresSecure();
        }
    }


}
