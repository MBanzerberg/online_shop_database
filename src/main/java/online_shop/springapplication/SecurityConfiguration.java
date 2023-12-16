package online_shop.springapplication;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user")
                .password("user")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("admin")
                .roles("ADMIN");
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() { return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/index").permitAll()
                .antMatchers("/resources/static/**").permitAll()
                .antMatchers("/main").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/main_admin").access("hasRole('ADMIN')")
                .antMatchers("/main_user").access("hasRole('USER')")

                .antMatchers("/addresses").access("hasRole('ADMIN')")
                .antMatchers("/edit_address").access("hasRole('ADMIN')")
                .antMatchers("/new_address").access("hasRole('ADMIN')")
                .antMatchers("/update_address").access("hasRole('ADMIN')")
                .antMatchers("/save_address").access("hasRole('ADMIN')")
                .antMatchers("/delete_address").access("hasRole('ADMIN')")

                .antMatchers("/client_address").access("hasRole('ADMIN')")
                .antMatchers("/employee_address").access("hasRole('ADMIN')")
                .antMatchers("/shop_address").access("hasRole('ADMIN')")

                .antMatchers("/clients").access("hasRole('ADMIN')")
                .antMatchers("/edit_client}").access("hasRole('ADMIN')")
                .antMatchers("/new_edit_client").access("hasRole('ADMIN')")
                .antMatchers("/update_edit_client").access("hasRole('ADMIN')")
                .antMatchers("/save_edit_client").access("hasRole('ADMIN')")
                .antMatchers("/delete_client").access("hasRole('ADMIN')")

                .antMatchers("/employees").access("hasRole('ADMIN')")
                .antMatchers("/edit_employee/{id}").access("hasRole('ADMIN')")
                .antMatchers("/new_employee").access("hasRole('ADMIN')")
                .antMatchers("/update_employee").access("hasRole('ADMIN')")
                .antMatchers("/save_employee").access("hasRole('ADMIN')")
                .antMatchers("/delete_employee").access("hasRole('ADMIN')")

                .antMatchers("/products").access("hasRole('ADMIN')")
                .antMatchers("/edit_product").access("hasRole('ADMIN')")
                .antMatchers("/new_product").access("hasRole('ADMIN')")
                .antMatchers("/update_product").access("hasRole('ADMIN')")
                .antMatchers("/save_product").access("hasRole('ADMIN')")
                .antMatchers("/delete_product").access("hasRole('ADMIN')")

                .antMatchers("/shop_information").access("hasRole('ADMIN')")
                .antMatchers("/edit_shop_information/{id}").access("hasRole('ADMIN')")
                .antMatchers("/new_shop_information").access("hasRole('ADMIN')")
                .antMatchers("/update_shop_information").access("hasRole('ADMIN')")
                .antMatchers("/save_shop_information").access("hasRole('ADMIN')")
                .antMatchers("/delete_shop_information").access("hasRole('ADMIN')")

                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/main").permitAll()
                .and()
                .logout()
                .logoutUrl("/index")
                .logoutSuccessUrl("/index")
                .permitAll();
    }

}


/*@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() { return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/index")
                .permitAll();

        return http.build();
    }

}*/

