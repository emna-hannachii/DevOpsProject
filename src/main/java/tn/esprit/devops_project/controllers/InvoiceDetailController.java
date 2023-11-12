package tn.esprit.devops_project.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.devops_project.entities.InvoiceDetail;
import tn.esprit.devops_project.services.Iservices.IInvoiceDetailService;

import java.util.List;


@RestController
@AllArgsConstructor
public class InvoiceDetailController {

    IInvoiceDetailService invoiceDetailService;

    @GetMapping("/invoicesDetail")
    public List<InvoiceDetail> getInvoicesDetail() {
        return invoiceDetailService.retriveAllInvoicesDetail();
    }

    @GetMapping("/invoiceDetail/{idInvoiceDetail}")
    public InvoiceDetail getInvoiceDetail(@PathVariable long idInvoiceDetail){
        return  invoiceDetailService.getInvoiceDetail(idInvoiceDetail);
    }
    @PostMapping("/invoiceDetailAdd")
    public InvoiceDetail addInvoiceDetail(@RequestBody InvoiceDetail invoiceDetail){
        return invoiceDetailService.addInvoiceDetail(invoiceDetail);
    }
    @DeleteMapping("/invoiceDetail/{idDetail}")
    public void deleteInvoiceDetail(@PathVariable long idDetail){
        invoiceDetailService.deleteInvoiceDetail(idDetail);
    }

}
