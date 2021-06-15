package com.je.jsboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

import com.je.jsboot.services.users.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class JsbootSecurityConfig {

	PasswordEncoder pbkdf2Encoder;

	@Autowired
	private UserDetailServiceImpl userDetailsService;
	private static String[] resources = new String[] { "/css/**", "/img/**", "/js/**" };

	@Configuration
	@Order(1)
	public static class WorkshopSecurityConfig extends WebSecurityConfigurerAdapter {
		private static final String ROLEJORGE = "JORGE";
		private static final String LOGINURL = "/workshop/login";
		private static final String ADMINURL = "/workshop/admin";

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/workshop/**").authorizeRequests().anyRequest().hasRole(ROLEJORGE).and().formLogin()
					.loginPage(LOGINURL).permitAll().defaultSuccessUrl(ADMINURL, true)
					.loginProcessingUrl("/workshop/jspringsecuritycheckws").failureForwardUrl(LOGINURL).and()
					.exceptionHandling().accessDeniedPage("/403wks").and().logout()
					.logoutUrl("/workshop/j_spring_security_logout").logoutSuccessUrl(LOGINURL)
					.invalidateHttpSession(true);
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers(HttpMethod.GET, resources);
		}
	}

	@Configuration
	@Order(2)
	public static class EmployeeSecurityConfig extends WebSecurityConfigurerAdapter {
		private static final String ROLEUSER = "USER";
		private static final String ROLEAR = "AR";
		private static final String ROLENA = "NA";
		private static final String LOGINURL = "/employee/login";
		private static final String ADMINURL = "/employee/admin";

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.antMatcher("/employee/**").authorizeRequests()
					.antMatchers(ADMINURL, "/employee/savePawn", "/employee/newPawn", "/employee/daily",
							"/employee/newsale", "/employee/newshopping", "/employee/removesale",
							"/employee/newadjustment", "/employee/saveShopping", "/employee/newconcept",
							"/employee/cancelparcialsale", "/employee/removeparcialsale", "/employee/removePawn",
							"/employee/newentrymoney", "/employee/searchdaily", "/employee/newincident",
							"/employee/searchclientpawn", "/employee/myincidents", "/employee/beforeday*",
							"/employee/againday*", "/employee/newdiscount", "/employee/newpayroll",
							"/employee/othersales", "/employee/newsalepostponed", "/employee/addinstallment",
							"/employee/getsptimeout", "/employee/searchsalepostponed",
							"/employee/searchclientreturnpawn")
					.hasAnyRole(ROLEUSER, ROLEAR, ROLENA).antMatchers("/employee/localrental").hasRole(ROLEUSER)
					.antMatchers("/employee/renewPawn", "/employee/searchrenovations",
							"/employee/resultRenovationsPawns")
					.hasAnyRole(ROLEUSER, ROLENA).anyRequest().authenticated().and().formLogin().loginPage(LOGINURL)
					.permitAll().defaultSuccessUrl(ADMINURL, true).loginProcessingUrl("/employee/jspringsecuritycheck")
					.failureForwardUrl(LOGINURL).and().exceptionHandling().accessDeniedPage("/403").and().logout()
					.logoutUrl("/employee/j_spring_security_logout").logoutSuccessUrl(LOGINURL)
					.invalidateHttpSession(true);
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers(HttpMethod.GET, resources);
		}
	}

	@Configuration
	@Order(3)
	public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
		private static final String ROLEADMIN = "ADMIN";
		private static final String LOGINURL = "/login";
		private static final String ADMINURL = "/admin";

		@Override
		protected void configure(HttpSecurity http) throws Exception {
			http.authorizeRequests()
					.antMatchers(ADMINURL, "/newJewel", "/saveJewel", "/updateJewels", "/searchJewels", "/newCategory",
							"/newPayment", "/newPawn", "/savePayment", "/error", "/searchclients", "/disablejewel",
							"/searchtodisablejewel", "/saveCategory", "/addset", "/newSet", "/resultSearchUpdateJewels",
							"/searchUpdateJewels", "/searchShoppings", "/allpayments", "/searchPawns", "/newHoliday",
							"/allHolidays", "/dailyplace", "/searchquarterpawns", "/quarter", "/searchbill", "/bill",
							"/searchSalesCard", "/searchSales", "/searchDaily", "/searchHolidays", "/searchNumMissing",
							"/searchByReference", "/searchPosibleRepeated", "/searchrenovations", "/resultpawns",
							"/newuser", "/allincidents", "/pendingissues", "/searchquartermaterial", "/searchincident",
							"/searchcalculatedailies", "/outofdate", "/searchEntries", "/upload", "/investedMoney",
							"/checkinventory", "/searchgramsnull", "/searchsumadjustments", "/searchadjustment",
							"/tomelloso", "/exceltomelloso", "/newsale", "/searchsalepostponed", "/allcoins",
							"/searchRegisterEmployees", "/newcoin", "/beforeday*", "/againday*", "/searchByPrice")
					.hasRole(ROLEADMIN).anyRequest().authenticated().and().formLogin().loginPage(LOGINURL).permitAll()
					.defaultSuccessUrl(ADMINURL, true).failureForwardUrl(LOGINURL).and().exceptionHandling()
					.accessDeniedPage("/403admin").and().logout().logoutUrl("/j_spring_security_logout")
					.logoutSuccessUrl(LOGINURL).invalidateHttpSession(true);
		}

		@Override
		public void configure(WebSecurity web) throws Exception {
			web.ignoring().antMatchers(HttpMethod.GET, resources);
		}
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {

		// Setting Service to find User in the database.
		// And Setting PassswordEncoder
		auth.userDetailsService(userDetailsService).passwordEncoder(pbkdf2Encoder());
	}

	@Bean
	public PasswordEncoder pbkdf2Encoder() {
		pbkdf2Encoder = new Pbkdf2PasswordEncoder();
		return pbkdf2Encoder;
	}
}