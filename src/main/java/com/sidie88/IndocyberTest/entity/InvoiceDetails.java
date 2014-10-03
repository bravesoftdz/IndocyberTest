package com.sidie88.IndocyberTest.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.*;

@Entity
@Table(name = "invoicedetails")
public class InvoiceDetails implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_DETAILS")
	private Long idDetail;
	
	@Column(name = "INVOICE_NO", nullable = false)
    private String invoiceId;
	
	@Column(name = "ID_PRODUCT")
    private Long productId;
	
	@Column(name = "PRODUCT_NAME")
    private String productName;
    
	@Column(name = "PRICE")
    private BigDecimal price;
    
	@Column(name = "QTY")
    private Integer quantity;
    
	@Column(name = "SUB_TOTAL")
    private BigDecimal subTotal;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "INVOICE_NO", referencedColumnName = "INVOICE_NO", insertable=false, updatable=false)
	private Invoice invoice;

    public String getInvoiceId() {
		return invoiceId;
	}

	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}

	public Invoice getInvoice() {
		return invoice;
	}

	public void setInvoice(Invoice invoice) {
		this.invoice = invoice;
	}

	public Long getIdDetail() {
        return idDetail;
    }

    public void setIdDetail(Long idDetail) {
        this.idDetail = idDetail;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getSubTotal() {
    	BigDecimal amount = BigDecimal.ZERO;
    	if(price!=null && quantity!=null){
    		amount = price.multiply(new BigDecimal(quantity));
    	}
        return amount;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

	public InvoiceDetails(Long productId,
			String productName, BigDecimal price, Integer quantity) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.price = price;
		this.quantity = quantity;
	}
    
	public InvoiceDetails() {
		
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(getClass() != obj.getClass())
			return false;
		InvoiceDetails other = (InvoiceDetails)obj;
		if(productId == null) {
			if(other.productId!= null)
				return false;
		} else if(!productId.equals(other.productId))
			return false;
		return true;
	}
	
	
    
}
