package com.cocoon.entity;

import com.cocoon.enums.InvoiceStatus;
import com.cocoon.enums.InvoiceType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "invoice")
@NoArgsConstructor
@Getter
@Setter
public class Invoice extends BaseEntity{

    private String invoiceNo; //TODO - invoice number will be evaluated somewhere else...

    @Enumerated(EnumType.STRING)
    private InvoiceStatus invoiceStatus;

    @Enumerated(EnumType.STRING)
    private InvoiceType invoiceType;

    @Column(columnDefinition = "DATE")
    private LocalDate invoiceDate;

    private int enabled;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sptable_id") //TODO foreign key will be replaced with "client_vendor_id"....
    private ClientVendor clientVendor;


}