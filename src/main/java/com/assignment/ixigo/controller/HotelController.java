package com.assignment.ixigo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.assignment.ixigo.matcher.HotelMatcher;
import com.assignment.ixigo.model.Hotel;

/**
 * @author kamna
 *
 */

@Controller
public class HotelController {

	@Autowired
	HotelMatcher hotelMatcher;

	@RequestMapping(value = "/hotel", method = RequestMethod.GET)
	public String hotel() {

//		return new ModelAndView("hotel", "command", new Hotel());
		return "hotel";
	}

	@RequestMapping(value = "/addHotel", method = RequestMethod.POST)
	public String addHotel(@ModelAttribute("ixigoAssignment-1T") Hotel hotel, RedirectAttributes redirectAttributes) {
		if (hotelMatcher.getHotel1() == null) {
			hotelMatcher.setHotel1(hotel);
		} else if (hotelMatcher.getHotel2() == null) {
			hotelMatcher.setHotel2(hotel);
		}
		if (hotelMatcher.getHotel1() != null && hotelMatcher.getHotel2() != null) {
			boolean b = hotelMatcher.sameEntitiesorNot();
			hotelMatcher.setHotel1(null);
			hotelMatcher.setHotel2(null);
			
			redirectAttributes.addAttribute("matchScore", String.valueOf("matching result is " + b));
			return "redirect:/hotel";
			
		}
//		return "Details saved. Please enter other entity for comparing two entities";
		redirectAttributes.addAttribute("isHotel1Saved", "Details saved. Please enter other entity for comparing two entities");
		return "redirect:/hotel";
	}

}
