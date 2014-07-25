package com.ourshop.it.web;

import com.ourshop.it.domain.Inventory;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/inventorys")
@Controller
@RooWebScaffold(path = "inventorys", formBackingObject = Inventory.class)
public class InventoryController {
}
