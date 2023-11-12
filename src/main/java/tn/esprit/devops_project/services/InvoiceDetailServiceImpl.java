package tn.esprit.devops_project.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.devops_project.entities.InvoiceDetail;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.services.Iservices.IInvoiceDetailService;

import java.util.List;
@Service
@Slf4j
@AllArgsConstructor
public class InvoiceDetailServiceImpl implements IInvoiceDetailService {
   @Autowired
    InvoiceDetailRepository invoiceDetailRepository;
   @Autowired
    InvoiceRepository invoiceRepository;

    @Override
    public List<InvoiceDetail> retriveAllInvoicesDetail() {
        return invoiceDetailRepository.findAll();
    }

    @Override
    public InvoiceDetail getInvoiceDetail(long idInvoiceDetail) {
        return invoiceDetailRepository.findById(idInvoiceDetail).orElseThrow(() -> new NullPointerException("Invoice detail not found"));

    }



    @Override
    public InvoiceDetail addInvoiceDetail(InvoiceDetail invoiceDetail) {
        return invoiceDetailRepository.save(invoiceDetail);
    }

    @Override
    public void deleteInvoiceDetail(long idInvoiceDetail) {
        invoiceDetailRepository.deleteById(idInvoiceDetail);
    }


}
