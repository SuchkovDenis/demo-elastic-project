package ru.gb.demoelastic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.gb.demoelastic.doc.SubTitlesDoc;
import ru.gb.demoelastic.services.SubTitlesService;

@Controller
@RequestMapping("/index")
public class IndexController {

  private final SubTitlesService subTitlesService;

  public IndexController(SubTitlesService subTitlesService) {
    this.subTitlesService = subTitlesService;
  }

  @GetMapping
  public String indexPage() {
    return "index";
  }

  @PostMapping
  public String index(SubTitlesDoc doc) throws Exception {
    subTitlesService.indexSubTitles(doc);
    return "index";
  }
}
