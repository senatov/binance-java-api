package com.binance.api.client.domain.account;

import com.binance.api.client.domain.ContingencyType;
import com.binance.api.client.domain.OCOOrderStatus;
import com.binance.api.client.domain.OCOStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewOCOResponse extends OrderList {

	private Long orderListId;
	private ContingencyType contingencyType;
	private OCOStatus listStatusType;
	private OCOOrderStatus listOrderStatus;
	private String listClientOrderId;
	private Long transactionTime;
	private String symbol;
	private List<OrderReport> orderReports;

	// Getters
	public Long getOrderListId() {
		return orderListId;
	}

	// Setter
	public void setOrderListId(Long orderListId) {
		this.orderListId = orderListId;
	}

	public ContingencyType getContingencyType() {
		return contingencyType;
	}

	public void setContingencyType(ContingencyType contingencyType) {
		this.contingencyType = contingencyType;
	}

	public OCOStatus getListStatusType() {
		return listStatusType;
	}

	public void setListStatusType(OCOStatus listStatusType) {
		this.listStatusType = listStatusType;
	}

	public OCOOrderStatus getListOrderStatus() {
		return listOrderStatus;
	}

	public void setListOrderStatus(OCOOrderStatus listOrderStatus) {
		this.listOrderStatus = listOrderStatus;
	}

	public String getListClientOrderId() {
		return listClientOrderId;
	}

	public void setListClientOrderId(String listClientOrderId) {
		this.listClientOrderId = listClientOrderId;
	}

	public Long getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Long transactionTime) {
		this.transactionTime = transactionTime;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public List<OrderReport> getOrderReports() {
		return orderReports;
	}

	public void setOrderReports(List<OrderReport> orderReports) {
		this.orderReports = orderReports;
	}

}