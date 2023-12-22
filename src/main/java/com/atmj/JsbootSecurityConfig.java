package com.atmj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.atmj.jsboot.services.users.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class JsbootSecurityConfig {

	@Autowired
	private UserDetailServiceImpl userDetailsService;

	private static final String ROLEUSER = "USER";
	private static final String ROLEAR = "AR";
	private static final String ROLENA = "NA";
	private static final String ROLEADMIN = "ADMIN";

	@Bean
	public SecurityFilterChain employeeFilterChain(HttpSecurity http) throws Exception {
		http.securityMatcher("/employee/**")
				.authorizeHttpRequests(authorize -> authorize
						.requestMatchers(AntPathRequestMatcher.antMatcher("/employee/admin"),
								AntPathRequestMatcher.antMatcher("/employee/savePawn"),
								AntPathRequestMatcher.antMatcher("/employee/newPawn"),
								AntPathRequestMatcher.antMatcher("/employee/daily"),
								AntPathRequestMatcher.antMatcher("/employee/newsale"),
								AntPathRequestMatcher.antMatcher("/employee/newshopping"),
								AntPathRequestMatcher.antMatcher("/employee/removesale"),
								AntPathRequestMatcher.antMatcher("/employee/newadjustment"),
								AntPathRequestMatcher.antMatcher("/employee/saveShopping"),
								AntPathRequestMatcher.antMatcher("/employee/newconcept"),
								AntPathRequestMatcher.antMatcher("/employee/cancelparcialsale"),
								AntPathRequestMatcher.antMatcher("/employee/removeparcialsale"),
								AntPathRequestMatcher.antMatcher("/employee/removePawn"),
								AntPathRequestMatcher.antMatcher("/employee/newentrymoney"),
								AntPathRequestMatcher.antMatcher("/employee/searchdaily"),
								AntPathRequestMatcher.antMatcher("/employee/newincident"),
								AntPathRequestMatcher.antMatcher("/employee/searchclientpawn"),
								AntPathRequestMatcher.antMatcher("/employee/myincidents"),
								AntPathRequestMatcher.antMatcher("/employee/beforeday*"),
								AntPathRequestMatcher.antMatcher("/employee/againday*"),
								AntPathRequestMatcher.antMatcher("/employee/newdiscount"),
								AntPathRequestMatcher.antMatcher("/employee/newpayroll"),
								AntPathRequestMatcher.antMatcher("/employee/othersales"),
								AntPathRequestMatcher.antMatcher("/employee/newsalepostponed"),
								AntPathRequestMatcher.antMatcher("/employee/addinstallment"),
								AntPathRequestMatcher.antMatcher("/employee/getsptimeout"),
								AntPathRequestMatcher.antMatcher("/employee/searchsalepostponed"),
								AntPathRequestMatcher.antMatcher("/employee/searchclientreturnpawn"))
						.hasAnyRole(ROLEUSER, ROLEAR, ROLENA)
						.requestMatchers(AntPathRequestMatcher.antMatcher("/employee/localrental")).hasRole(ROLEUSER)
						.requestMatchers(AntPathRequestMatcher.antMatcher("/employee/renewPawn"),
								AntPathRequestMatcher.antMatcher("/employee/searchrenovations"),
								AntPathRequestMatcher.antMatcher("/employee/resultRenovationsPawns"))
						.hasAnyRole(ROLEUSER, ROLENA).anyRequest().authenticated())
				.formLogin(formLogin -> formLogin.loginPage("/employee/login").permitAll()
						.defaultSuccessUrl("/employee/admin", true).failureForwardUrl("/403"))
				.exceptionHandling(eH -> eH.accessDeniedPage("/403"))
				.logout(logout -> logout.logoutUrl("/employee/j_spring_security_logout")
						.logoutSuccessUrl("/employee/login").invalidateHttpSession(true));
		return http.build();
	}

	@Bean
	public SecurityFilterChain adminFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests(authorize -> authorize.requestMatchers(AntPathRequestMatcher.antMatcher("/admin"),
				AntPathRequestMatcher.antMatcher("/newJewel"), AntPathRequestMatcher.antMatcher("/saveJewel"),
				AntPathRequestMatcher.antMatcher("/updateJewels"), AntPathRequestMatcher.antMatcher("/searchJewels"),
				AntPathRequestMatcher.antMatcher("/newCategory"), AntPathRequestMatcher.antMatcher("/newPayment"),
				AntPathRequestMatcher.antMatcher("/newPawn"), AntPathRequestMatcher.antMatcher("/savePayment"),
				AntPathRequestMatcher.antMatcher("/searchclients"), AntPathRequestMatcher.antMatcher("/disablejewel"),
				AntPathRequestMatcher.antMatcher("/searchtodisablejewel"),
				AntPathRequestMatcher.antMatcher("/saveCategory"), AntPathRequestMatcher.antMatcher("/addset"),
				AntPathRequestMatcher.antMatcher("/newSet"),
				AntPathRequestMatcher.antMatcher("/resultSearchUpdateJewels"),
				AntPathRequestMatcher.antMatcher("/searchUpdateJewels"),
				AntPathRequestMatcher.antMatcher("/searchShoppings"), AntPathRequestMatcher.antMatcher("/allpayments"),
				AntPathRequestMatcher.antMatcher("/searchPawns"), AntPathRequestMatcher.antMatcher("/newHoliday"),
				AntPathRequestMatcher.antMatcher("/allHolidays"), AntPathRequestMatcher.antMatcher("/dailyplace"),
				AntPathRequestMatcher.antMatcher("/searchquarterpawns"), AntPathRequestMatcher.antMatcher("/quarter"),
				AntPathRequestMatcher.antMatcher("/searchbill"), AntPathRequestMatcher.antMatcher("/bill"),
				AntPathRequestMatcher.antMatcher("/searchSalesCard"), AntPathRequestMatcher.antMatcher("/searchSales"),
				AntPathRequestMatcher.antMatcher("/searchDaily"), AntPathRequestMatcher.antMatcher("/searchHolidays"),
				AntPathRequestMatcher.antMatcher("/searchNumMissing"),
				AntPathRequestMatcher.antMatcher("/searchByReference"),
				AntPathRequestMatcher.antMatcher("/searchPosibleRepeated"),
				AntPathRequestMatcher.antMatcher("/searchrenovations"),
				AntPathRequestMatcher.antMatcher("/resultpawns"), AntPathRequestMatcher.antMatcher("/newuser"),
				AntPathRequestMatcher.antMatcher("/allincidents"), AntPathRequestMatcher.antMatcher("/pendingissues"),
				AntPathRequestMatcher.antMatcher("/searchquartermaterial"),
				AntPathRequestMatcher.antMatcher("/searchincident"),
				AntPathRequestMatcher.antMatcher("/searchcalculatedailies"),
				AntPathRequestMatcher.antMatcher("/outofdate"), AntPathRequestMatcher.antMatcher("/searchEntries"),
				AntPathRequestMatcher.antMatcher("/upload"), AntPathRequestMatcher.antMatcher("/investedMoney"),
				AntPathRequestMatcher.antMatcher("/checkinventory"),
				AntPathRequestMatcher.antMatcher("/searchgramsnull"),
				AntPathRequestMatcher.antMatcher("/searchsumadjustments"),
				AntPathRequestMatcher.antMatcher("/searchadjustment"), AntPathRequestMatcher.antMatcher("/tomelloso"),
				AntPathRequestMatcher.antMatcher("/exceltomelloso"), AntPathRequestMatcher.antMatcher("/newsale"),
				AntPathRequestMatcher.antMatcher("/searchsalepostponed"), AntPathRequestMatcher.antMatcher("/allcoins"),
				AntPathRequestMatcher.antMatcher("/searchRegisterEmployees"),
				AntPathRequestMatcher.antMatcher("/newcoin"), AntPathRequestMatcher.antMatcher("/beforeday*"),
				AntPathRequestMatcher.antMatcher("/againday*"), AntPathRequestMatcher.antMatcher("/searchByPrice"))
				.hasRole(ROLEADMIN).anyRequest().authenticated())
				.formLogin(formLogin -> formLogin.loginPage("/login").permitAll().defaultSuccessUrl("/admin", true)
						.failureForwardUrl("/403admin"))
				.exceptionHandling(eH -> eH.accessDeniedPage("/403admin")).logout(logout -> logout
						.logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/login").invalidateHttpSession(true));
		return http.build();
	}

	/**
	 * Así es como funcionan los resources, hay que poner configuration arriba en la
	 * clase sino no funciona
	 * 
	 * @return
	 */
	@Bean
	public WebSecurityCustomizer webSecurityCostumizer() {
		return web -> web.ignoring().requestMatchers(AntPathRequestMatcher.antMatcher("/css/**"),
				AntPathRequestMatcher.antMatcher("/img/**"), AntPathRequestMatcher.antMatcher("/js/**"));
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// Setting Service to find User in the database.
		// And Setting PassswordEncoder
		auth.userDetailsService(userDetailsService);
	}
}