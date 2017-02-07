package com.bright.framework.simplelist.action;

import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.bn2web.common.action.Bn2Action;
import com.bn2web.common.constant.CommonConstants;
import com.bn2web.common.exception.Bn2Exception;
import com.bright.framework.simplelist.bean.List;
import com.bright.framework.simplelist.form.ListForm;
import com.bright.framework.simplelist.service.ListManager;

/**
 * Bright Interactive, product-tracker
 *
 * Copyright 2005 Bright Interactive, All Rights Reserved.
 * ViewListValuesAction.java
 *
 * Contains the ViewListValuesAction class.
 */

/*
 Ver	Date				Who					Comments
 --------------------------------------------------------------------------------
 d1	20-Jan-2005		Martin Wilson		Created
 --------------------------------------------------------------------------------
 */

/**
 * Shows the values in a list
 * 
 * @author Bright Interactive
 * @version d1
 */
public class ViewListValuesAction extends Bn2Action
{
	protected ListManager m_listManager = null;
	
	/**
	 * The execute method of the Action.
	 *
	 * @param ActionMapping a_mapping - the action mapping [explain it here].
	 * @param ActionForm a_form - the action form- in this case containing the login info entered by user.
	 * @param HttpServletRequest a_request - the request object.
	 * @param HttpServletResponse a_response - the response object.
	 * @return ActionForward - where to forward or redirect to when completed.
	 * @throws	ServletException    Servlet Exception
	 */
	public ActionForward execute(ActionMapping a_mapping,
										  ActionForm a_form,
										  HttpServletRequest a_request,
										  HttpServletResponse a_response)
	throws Bn2Exception
	/*
	 ------------------------------------------------------------------------
	 d1	20-Jan-2005	Martin Wilson		Created
	 ------------------------------------------------------------------------
	 */
	{
		// Go to the default:
		ActionForward afForward = null;
		
		try
		{
			// Get the id of the list:
			String sListId = a_request.getParameter(CommonConstants.k_sIDParam);
			
			ListForm form = (ListForm)a_form;
			
			// Get the listL
			List list = m_listManager.getList(null, sListId);
			
			// Get all the items in the list:
			Vector vecListValues = m_listManager.getListValues(null, sListId);
			
			// Set in the form:
			form.setListItems(vecListValues);
			form.setList(list);
		
			afForward = a_mapping.findForward(CommonConstants.SUCCESS_KEY); 
		}
		catch (Bn2Exception bn2)
		{
			m_logger.error("Bn2Exception in ViewListValuesAction: " + bn2.getMessage());
			afForward = a_mapping.findForward(CommonConstants.SYSTEM_FAILURE_KEY); 
		}
		
		return (afForward);
	}
	
	/**
	 * Sets the List manager. This method is called in the baseclass when linking in components,
	 * as specified in the framework config file.
	 *
	 * @param ListManager the List manager component to use.
	 */
	public void setListManager(ListManager a_listManager)
	{
		m_listManager = a_listManager;
	}
	
}
