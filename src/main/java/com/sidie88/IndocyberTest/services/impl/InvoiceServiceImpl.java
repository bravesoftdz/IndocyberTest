package com.sidie88.IndocyberTest.services.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.zkoss.zhtml.Big;

import com.sidie88.IndocyberTest.entity.Invoice;
import com.sidie88.IndocyberTest.entity.InvoiceDetails;
import com.sidie88.IndocyberTest.services.InvoiceService;

@Service("invoiceService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class InvoiceServiceImpl implements InvoiceService {
	
	@Autowired
	InvoiceDao invoiceDao;
	
	@Autowired
	InvoiceDetailsDao invoiceDetailsDao;

	@Override
	public void addInvoice(Invoice invoice) {
		BigDecimal total = BigDecimal.ZERO;
		for (InvoiceDetails iDetails : invoice.getInvoiceDetails()) {
			iDetails.setInvoiceId(invoice.getInvoiceNo());
			total = total.add(iDetails.getSubTotal());
		}
		invoice.setTotal(total);
		invoiceDao.save(invoice);
		
	}

	@Override
	public int getCountInvoice() {
		int size = invoiceDao.queryAll().size();
		return size;
	}

}
