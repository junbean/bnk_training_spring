package com.example.jpaEx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.jpaEx.entity.Player;
import com.example.jpaEx.repository.PlayerRepository;

@Controller
public class MyController {

	@Autowired
	private PlayerRepository playerRepositry;
	
	@GetMapping("/players")
	public String getPlayerList(Model model) {
		List<Player> list = playerRepositry.findAll();
		model.addAttribute("list",list);
		
		return "players/list";
	}
}
