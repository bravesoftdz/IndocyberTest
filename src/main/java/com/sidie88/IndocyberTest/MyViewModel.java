package com.sidie88.IndocyberTest;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;

import com.sidie88.IndocyberTest.entity.Invoice;
import com.sidie88.IndocyberTest.entity.InvoiceDetails;
import com.sidie88.IndocyberTest.entity.Product;
import com.sidie88.IndocyberTest.services.InvoiceService;
import com.sidie88.IndocyberTest.services.ProductService;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class MyViewModel {


	@WireVariable
	private ProductService productService;

	@WireVariable
	private InvoiceService invoiceService;

	private ListModelList<Product> productListModel;
	private ListModelList<InvoiceDetails> invoiceListModel;
	private String message;
	private String invoiceNo;
	private Date transDate;
	private String customer;
	private String searchBox;
	private String total;

	public String getSearchBox() {
		return searchBox;
	}

	public void setSearchBox(String searchBox) {
		this.searchBox = searchBox;
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

	@Init
	public void init() {
		initForm();

	}

	public ListModelList<Product> getProductListModel() {
		return productListModel;
	}

	public void setProductListModel(ListModelList<Product> productListModel) {
		this.productListModel = productListModel;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	@Command
	public void searchProduct() {
		productListModel.clear();
		List<Product> list = productService.getListSearchProduct(searchBox);
		productListModel.addAll(list);

	}

	@Command @NotifyChange({"total"})
	public void addToCart(@BindingParam("p") Product p) {
		if (p == null) {
			return;
		}
		InvoiceDetails ids = new InvoiceDetails(p.getProductId(),
				p.getProductName(), p.getPrice(), 1);
		if (invoiceListModel.contains(ids)) {
			InvoiceDetails invoiceDetails = invoiceListModel
					.get(invoiceListModel.indexOf(ids));
			invoiceListModel.remove(invoiceDetails);
			invoiceDetails.setQuantity(invoiceDetails.getQuantity() + 1);
			invoiceDetails.setSubTotal(invoiceDetails.getPrice().multiply(
					new BigDecimal(invoiceDetails.getQuantity())));
			invoiceListModel.add(invoiceDetails);
		} else {
			ids.setSubTotal(ids.getPrice().multiply(
					new BigDecimal(ids.getQuantity())));
			invoiceListModel.add(ids);
		}

	}

	@Command
	public void removeFromCart(@BindingParam("inv") InvoiceDetails invD) {
		if (invD == null) {
			return;
		}
		if (invoiceListModel.contains(invD)) {
			InvoiceDetails invoiceDetails = invoiceListModel
					.get(invoiceListModel.indexOf(invD));
			invoiceListModel.remove(invoiceDetails);
			if (invoiceDetails.getQuantity() > 1) {
				invoiceDetails.setQuantity(invoiceDetails.getQuantity() - 1);
				invoiceListModel.add(invoiceDetails);
			}

		} else {
			invoiceListModel.remove(invD);
		}

	}

	@Command @NotifyChange({"invoiceNo","total"})
	public void saveInvoice() throws Exception{
		try {
			
			if(invoiceListModel.size()==0){
				throw new Exception("Please Add Invoice Items First");
			}
			if(transDate==null){
				throw new Exception("Please Set Transaction Date Fist");
			}

			Invoice invoice = new Invoice();
			invoice.setInvoiceNo(invoiceNo);
			invoice.setCustomer(customer);
			invoice.setTransDate(transDate);
			invoice.setInvoiceDetails(invoiceListModel);

			invoiceService.addInvoice(invoice);
			Messagebox.show("Invoice save succesfully");
			reset();
		} catch (Exception e) {
			Messagebox.show(e.getMessage());
			e.printStackTrace();
		}

	}

	public ListModelList<InvoiceDetails> getInvoiceListModel() {
		return invoiceListModel;
	}

	public void setInvoiceListModel(
			ListModelList<InvoiceDetails> invoiceListModel) {
		this.invoiceListModel = invoiceListModel;
	}

	public String getTotal() {
		BigDecimal amount = BigDecimal.ZERO;
		for (InvoiceDetails i : invoiceListModel) {
			amount = amount.add(i.getSubTotal());
			
		}
		return amount.toString();
	}

	public void setTotal(String total) {
		this.total = total;
	}

	private void initForm() {
		List<Product> prodList = productService.getListProduct();
		productListModel = new ListModelList<Product>(prodList);
		invoiceListModel = new ListModelList<InvoiceDetails>();
		reset();
	}
	
	private void reset(){
		invoiceListModel.clear();
		invoiceNo = generateInvoiceNo();
		customer = "";
	}
	
	private String generateInvoiceNo(){
		DateTime jodaTime = new DateTime();
		DateTimeFormatter formatter = DateTimeFormat.forPattern("YYYYMMddHHmm");
		Integer sequence = invoiceService.getCountInvoice()+1;
		return String.format("%s%s", formatter.print(jodaTime), sequence.toString());
	}

}
