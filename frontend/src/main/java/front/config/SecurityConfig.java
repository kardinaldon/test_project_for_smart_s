package front.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;

    protected void configureEndpointAccessForStaticResources(final ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry requests) {
        requests
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations())
                    .permitAll();
        requests
                .antMatchers("/resources/**")
                    .permitAll()
                .antMatchers("/static/**", "*/index.html")
                    .permitAll();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {

        http
                .csrf()
                    .disable()
                .rememberMe()
                    .disable()
                .httpBasic()
                .and()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                    .maximumSessions(2);
        http
                .authorizeRequests()
                    .antMatchers("/static/**", "/index.html", "/auth/authenticator")
                        .permitAll()
                    .antMatchers(HttpMethod.OPTIONS)
                        .permitAll()
                    .anyRequest()
                        .authenticated();
        http
                .logout()
                    .logoutUrl("/exit")
                    .logoutSuccessUrl("/index.html")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "Authorization", "id")
                    .clearAuthentication(true);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.jdbcAuthentication().dataSource(dataSource)
                .authoritiesByUsernameQuery("select USERNAME, USER_PASS from APP_CREDENTIALS where USERNAME=?")
                .usersByUsernameQuery("select USERNAME, USER_PASS, 1 as enabled from APP_CREDENTIALS where USERNAME=?")
                .passwordEncoder(passwordEncoder());
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
