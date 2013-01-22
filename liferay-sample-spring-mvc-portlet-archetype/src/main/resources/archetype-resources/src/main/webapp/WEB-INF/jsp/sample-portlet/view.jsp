#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
<%@ include file="init.jsp"%>

<liferay-ui:success key="success"
	message="ui-request-processed-successfully" />
<liferay-ui:error key="error"
	message="ui-request-processed-error" />
<portlet:actionURL name="action" var="actionUrl" />
<portlet:resourceURL var="resourceUrl" />
	
This is a sample portlet<br/>
<a href="${symbol_dollar}{actionUrl}">Action Link</a><br/>
<br/>
<a href="${symbol_dollar}{resourceUrl}">Resource Link</a><br/>
