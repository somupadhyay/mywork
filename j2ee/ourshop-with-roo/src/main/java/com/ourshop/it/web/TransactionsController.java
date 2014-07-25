package com.ourshop.it.web;

import com.ourshop.it.domain.Transactions;
import org.springframework.roo.addon.web.mvc.controller.scaffold.RooWebScaffold;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/transactionses")
@Controller
@RooWebScaffold(path = "transactionses", formBackingObject = Transactions.class)
public class TransactionsController {
}
