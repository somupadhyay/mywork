package com.ourshop.it.web;

import com.ourshop.it.domain.Party;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/partys")
@Controller
@RooWebScaffold(path = "partys", formBackingObject = Party.class)
public class PartyController {
}
