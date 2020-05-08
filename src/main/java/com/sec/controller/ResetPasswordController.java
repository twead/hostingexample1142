package com.sec.controller;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sec.entity.User;
import com.sec.service.EmailServiceImpl;
import com.sec.service.UserService;

@Controller
public class ResetPasswordController {

	@Autowired
	private UserService userService;

	@Autowired
	private EmailServiceImpl emailService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	
	@RequestMapping(value = "/forgot", method = RequestMethod.GET)
	public String displayForgotPasswordPage() {
		return "forgotPassword";
	}

	// form feldolgozása forgotPassword oldalon
	@RequestMapping(value = "/forgot", method = RequestMethod.POST)
	public String processForgotPasswordForm(@RequestParam("email") String userEmail, HttpServletRequest request,
			Model model) {

		// Adatbázisból User lekérése
		Optional<User> optional = userService.findUserByEmail(userEmail);

		if (!optional.isPresent()) {
			model.addAttribute("emailNotFound", "Nem találtunk felhasználót ehhez az emailhez!");
		} else {
			model.addAttribute("emailHasFound", "Kérlek ellenőrizd a beérkezett leveleidet!");

			// 36 karakter hosszú String legenerálása tokennek
			User user = optional.get();
			user.getUserProfile().setResetToken(UUID.randomUUID().toString());

			// Token lementése az adatbázisba
			userService.save(user);

			String appUrl = request.getScheme() + "://" + request.getServerName();

			// Email message
			emailService.sendForgotPasswordEmail(user.getEmail(), "Elfelejtett jelszó",
					"Hogy megújítsd a jelszavad, kattints az alábbi linkre:\n" + appUrl + "/reset?token="
							+ user.getUserProfile().getResetToken());

		}

		return "forgotPassword";

	}

	// resetPassword form megjelenítése
	@RequestMapping(value = "/reset", method = RequestMethod.GET)
	public String displayResetPasswordPage(ModelAndView modelAndView, @RequestParam("token") String token) {

		Optional<User> user = userService.findUserByResetToken(token);

		if (user.isPresent()) { // Megtalálható a Token az adatbázisban
			modelAndView.addObject("resetToken", token);
		} else { // Nem található meg
			modelAndView.addObject("errorMessage", "Oops! Ez nem egy valós link.");
		}

		return "resetPassword";
	}

	// resetPassword form feldolgozása
	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public String setNewPassword(ModelAndView modelAndView, @RequestParam Map<String, String> requestParams,
			RedirectAttributes redir) {

		Optional<User> user = userService.findUserByResetToken(requestParams.get("token"));

		if (user.isPresent()) {

			User resetUser = user.get();

			// Új jelszó beállítása
			resetUser.setPassword(bCryptPasswordEncoder.encode(requestParams.get("password")));

			// 0-ra állítjuk be, hogy ne tudjuk ismételten használni
			resetUser.getUserProfile().setResetToken(null);

			userService.save(resetUser);

			return "auth/login";

		} else {
			modelAndView.addObject("errorMessage", "Oops! Ez nem egy valós link.");
			modelAndView.setViewName("resetPassword");
		}

		return "resetPassword";
	}

	// Az oldal visszaállítása, token nélkül átirányít a bejelentkezési oldalra
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ModelAndView handleMissingParams(MissingServletRequestParameterException ex) {
		return new ModelAndView("redirect:login");
	}
}
