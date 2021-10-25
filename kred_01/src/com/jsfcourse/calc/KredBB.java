package com.jsfcourse.calc;

import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named
@RequestScoped
//@SessionScoped
public class KredBB {
	private String amount;
	private String years;
	private String percentage;
	private Double result;

	@Inject
	FacesContext ctx;

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getYears() {
		return years;
	}

	public void setYears(String years) {
		this.years = years;
	}
	
	public String getPercentage() {
		return percentage;
	}

	public void setPercentage(String percentage) {
		this.percentage = percentage;
	}

	public Double getResult() {
		return result;
	}

	public void setResult(Double result) {
		this.result = result;
	}

	public boolean doTheMath() {
		try {
			double amount = Double.parseDouble(this.amount);
			double years = Double.parseDouble(this.years);
			double percentage = Double.parseDouble(this.percentage);

			result = amount/(years*12) + (amount/(years*12))*percentage/100;
			
			result = Math.round(result * 100.0)/100.0;

			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Wyliczono miesiêczn¹ ratê kredytu", null));
			return true;
		} catch (Exception e) {
			ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Wyliczono miesiêczn¹ ratê kredytu", null));
			return false;
		}
	}

	// Go to "showresult" if ok
	public String calc() {
		if (doTheMath()) {
			return "showresult";
		}
		return null;
	}
}
