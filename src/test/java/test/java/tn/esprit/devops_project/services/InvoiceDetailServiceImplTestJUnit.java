package test.java.tn.esprit.devops_project.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.DevOps_ProjectSpringBootApplication;
import tn.esprit.devops_project.entities.InvoiceDetail;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.services.InvoiceDetailServiceImpl;

import java.util.List;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = DevOps_ProjectSpringBootApplication.class)
@ExtendWith(MockitoExtension.class)
@Slf4j
class InvoiceDetailServiceImplTestJUnit {


    @Autowired
    InvoiceDetailServiceImpl invoiceDetailService;
   @Autowired
    InvoiceDetailRepository invoiceDetailRepository;
    InvoiceDetail  invoiceDetail;
    @BeforeEach
    void setUp() {
        invoiceDetail = new InvoiceDetail(5L, 5, 10.0F,null,null); // Create a sample InvoiceDetail

   }

  @AfterEach
   void tearDown(){
        invoiceDetailRepository.deleteAll();
   }


    @Test
    void testAddInvoiceDetail() {
        invoiceDetailService.addInvoiceDetail(invoiceDetail);
        assertNotNull(invoiceDetail.getIdInvoiceDetail());
        log.info("Added invoice detail: {}", invoiceDetail);
    }
    @Test
    void testRetrieveAllInvoiceDetails() {

        List<InvoiceDetail> invoiceDetailListTest = invoiceDetailService.retriveAllInvoicesDetail();
        assertEquals(2, invoiceDetailListTest.size());
        System.out.println("Retrieve all invoices details is working correctly...!!");
        log.info(invoiceDetailRepository.findAll().toString());
        invoiceDetailRepository.deleteAll();
    }

}
