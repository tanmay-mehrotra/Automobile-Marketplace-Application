package com.bean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;

import com.exception.NoJobsAssignedException;
import com.manager.EnquiryManager;
import com.TO.EnquiryTO;



public class FollowUpMB {

	private List<EnquiryTO> enquiryTOList;
	private List<SelectItem> requestIdList;
	private int requestId;
	private String message;

	/**
	 * @return the enquiryTOList
	 */
	public List<EnquiryTO> getEnquiryTOList() {
		return enquiryTOList;
	}
	/**
	 * @param enquiryTOList the enquiryTOList to set
	 */
	public void setEnquiryTOList(List<EnquiryTO> enquiryTOList) {
		this.enquiryTOList = enquiryTOList;
	}
	/**
	 * @return the requestIdList
	 */
	public List<SelectItem> getRequestIdList() {
		return requestIdList;
	}
	/**
	 * @param requestIdList the requestIdList to set
	 */
	public void setRequestIdList(List<SelectItem> requestIdList) {
		this.requestIdList = requestIdList;
	}
	/**
	 * @return the requestId
	 */
	public int getRequestId() {
		return requestId;
	}
	/**
	 * @param requestId the requestId to set
	 */
	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * getEnquiryDetails will set the requestId in session Attribute
	 * @param null
	 * @return String
	 *
	 */
	public String getEnquiryDetails(){

		FacesContext ctx = FacesContext.getCurrentInstance();
		ExternalContext etx = ctx.getExternalContext();
		HttpSession session = (HttpSession) etx.getSession(true);
		session.setAttribute("requestId",this.requestId);
		return "success";
	}



	/**
	 * FollowUpMB will retrieve the requestId List  
	 * prospective Customer
	 * @param null
	 * @return null
	 * @throws Exception 
	 *
	 */

	public FollowUpMB() throws Exception{
		this.requestIdList=new ArrayList<SelectItem>();
		try
		{

			Integer employeeId;
			FacesContext ctx = FacesContext.getCurrentInstance();
			ExternalContext etx = ctx.getExternalContext();
			HttpSession session = (HttpSession) etx.getSession(true);
			employeeId=(Integer) session.getAttribute("user");

			this.enquiryTOList=new EnquiryManager().getFollowUpEnquiry(employeeId);

			List<EnquiryTO> list=this.enquiryTOList;
			Iterator<EnquiryTO> Iter = list.iterator();
			while(Iter.hasNext()){
				EnquiryTO enquiry = Iter.next();
				SelectItem item = new SelectItem(enquiry.getRequestId());
				requestIdList.add(item);
			}

		}
		catch(NoJobsAssignedException e){
			this.setMessage(e.getMessage());
		}
	}

}
