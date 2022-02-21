package ru.gb.demoelastic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.gb.demoelastic.services.SubTitlesService;

@Controller
@RequestMapping("/search")
public class SearchController {

  private final SubTitlesService subTitlesService;

  public SearchController(SubTitlesService subTitlesService) {
    this.subTitlesService = subTitlesService;
  }

  @GetMapping
  public String searchPage(@RequestParam(defaultValue = "") String search, Model model) throws Exception {
    model.addAttribute("results", subTitlesService.search(search));
    return "search";
  }
}
