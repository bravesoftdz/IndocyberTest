package com.sidie88.IndocyberTest.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

@Entity
@Table(name = "invoice")
public class Invoice implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_INVOICE")
	private Long invoiceId;
	
	@Column(name = "INVOICE_NO")
	private String invoiceNo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "TRANS_DATE")
    private Date transDate;
    
	@Column(name = "CUSTOMER")
    private String customer;
    
	@Column(name = "TOTAL")
    private BigDecimal total;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "invoice")
	private Collection<InvoiceDetails> invoiceDetails;
    
    public Long getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(Long invoiceId) {
		this.invoiceId = invoiceId;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getTransDate() {
        return transDate;
    }

    public void setTransDate(Date transDate) {
        this.transDate = transDate;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

	public Collection<InvoiceDetails> getInvoiceDetails() {
		return invoiceDetails;
	}

	public void setInvoiceDetails(Collection<InvoiceDetails> invoiceDetails) {
		this.invoiceDetails = invoiceDetails;
	}


}
