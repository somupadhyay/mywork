package com.ourshop.it.web;

import com.ourshop.it.domain.TransactionInventory;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/transactioninventorys")
@Controller
@RooWebScaffold(path = "transactioninventorys", formBackingObject = TransactionInventory.class)
public class TransactionInventoryController {
}
