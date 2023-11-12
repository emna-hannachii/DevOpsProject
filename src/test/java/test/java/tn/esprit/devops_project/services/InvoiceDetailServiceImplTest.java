package test.java.tn.esprit.devops_project.services;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.devops_project.DevOps_ProjectSpringBootApplication;
import tn.esprit.devops_project.entities.InvoiceDetail;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.services.InvoiceDetailServiceImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Slf4j
 class InvoiceDetailServiceImplTest {


    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;

    @InjectMocks
    InvoiceDetailServiceImpl invoiceDetailService;



    @Test
    void testRetrieveAllInvoiceDetails() {
        List<InvoiceDetail> invoiceDetailList = new ArrayList<>() {
            {
                add(new InvoiceDetail(1L, 15, 16F,null,null));
                add(new InvoiceDetail(2L, 16, 17F,null,null));
                add(new InvoiceDetail(3L, 17, 18F,null,null));

            }
        };

        when(invoiceDetailService.retriveAllInvoicesDetail()).thenReturn(invoiceDetailList);
        List<InvoiceDetail> invoiceDetailListTest = invoiceDetailService.retriveAllInvoicesDetail();
        assertEquals(3, invoiceDetailListTest.size());
        System.out.println("Retrieve all invoices details is working correctly...!!");
    }

    
    @Test
    void testGetInvoiceDetail() {
        InvoiceDetail invoiceDetail = new InvoiceDetail(4L, 15, 16F,null,null);
        when(invoiceDetailRepository.findById(invoiceDetail.getIdInvoiceDetail())).thenReturn(Optional.of(invoiceDetail));
        InvoiceDetail retrievedInvoiceDetail = invoiceDetailService.getInvoiceDetail(invoiceDetail.getIdInvoiceDetail());
        assertNotNull(retrievedInvoiceDetail);
        assertSame(invoiceDetail, retrievedInvoiceDetail);
        verify(invoiceDetailRepository, times(1)).findById(invoiceDetail.getIdInvoiceDetail());

    }


   @Test
    void testAddInvoiceDetail() {
        InvoiceDetail invoiceDetail = new InvoiceDetail(5L, 5, 10.0F,null,null); // Create a sample InvoiceDetail
        invoiceDetailService.addInvoiceDetail(invoiceDetail);
        verify(invoiceDetailRepository, times(1)).save(invoiceDetail);
        System.out.println("invoice detail added successfully ;");

    }

    @Test
    void testDeleteInvoiceDetail() {
        long idInvoiceDetailToDelete = 1L;
        invoiceDetailService.deleteInvoiceDetail(idInvoiceDetailToDelete);
        verify(invoiceDetailRepository, times(1)).deleteById(idInvoiceDetailToDelete);
        verifyNoMoreInteractions(invoiceDetailRepository);
        System.out.println("detail invoice is deleted successfully !!");
    }


}
