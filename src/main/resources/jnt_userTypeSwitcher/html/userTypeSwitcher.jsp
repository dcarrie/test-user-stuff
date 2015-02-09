<%@ page import="java.util.Locale" %>
<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="template" uri="http://www.jahia.org/tags/templateLib" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="jcr" uri="http://www.jahia.org/tags/jcr" %>
<%@ taglib prefix="ui" uri="http://www.jahia.org/tags/uiComponentsLib" %>
<%@ taglib prefix="functions" uri="http://www.jahia.org/tags/functions" %>
<%@ taglib prefix="query" uri="http://www.jahia.org/tags/queryLib" %>
<%@ taglib prefix="utility" uri="http://www.jahia.org/tags/utilityLib" %>
<%@ taglib prefix="s" uri="http://www.jahia.org/tags/search" %>
<%--@elvariable id="currentNode" type="org.jahia.services.content.JCRNodeWrapper"--%>
<%--@elvariable id="out" type="java.io.PrintWriter"--%>
<%--@elvariable id="script" type="org.jahia.services.render.scripting.Script"--%>
<%--@elvariable id="scriptInfo" type="java.lang.String"--%>
<%--@elvariable id="workspace" type="java.lang.String"--%>
<%--@elvariable id="renderContext" type="org.jahia.services.render.RenderContext"--%>
<%--@elvariable id="currentResource" type="org.jahia.services.render.Resource"--%>
<%--@elvariable id="url" type="org.jahia.services.render.URLGenerator"--%>



<c:if test="${not renderContext.liveMode}">
    <c:set var="onchange" value="window.location='${renderContext.request.requestURI}' + '?requestUserType=' + this.value"/>
  
    <select onchange="${onchange}">
        <option value=""/>
    <%
       String[][] userTypes=new String[][]{{"business_traveler", "Business Traveler"},{"potential_client","Potential Client"}, {"travel_manager","Travel Manager"}, {"media_manager", "Media Manager"},{"prospective_employee","Employee"}};
       for (String[] uType : userTypes) {

    %>
        <c:set var="code" value="<%= uType[0] %>"/>
        <c:set var="selected" value=""/>
        <c:if test="${not empty sessionUserType and sessionUserType eq code}">
            <c:set var="selected" value="selected"/>
        </c:if>
        <option value="${code}" ${selected}><%= uType[1] %></option>

    <%
        }
    %>
    </select>
</c:if>