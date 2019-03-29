<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="cn.big.gvk.util.Page"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	  <link href="/gvk/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
		<base href="<%=basePath%>">
		<script language="javascript" type="text/javascript">
		function setPageSize(url) {
			var size = document.getElementById("listpage").value;
			var tail = "&pageSize=" + size;
			var tar = url + tail;
			window.location = tar;
		}
		function goPage(url) {
			var gono = document.getElementById("gono").value;
			var tail = "&pageNo=" + gono;
			var tar = url + tail;
			window.location = tar;
		}
		</script>
	</head>

	<body>
		<%
			String myUrl = (String) request.getAttribute("myUrl");
			if(request.getAttribute("myPage")  != null ){
			
			
			Page myPage = (Page) request.getAttribute("myPage");
			if(myPage != null){

			int pageSize = myPage.getPageSize();
			int pageNo = myPage.getPageNo();
			int rowCount = myPage.getRowCount();
			int firstPageNo = myPage.getFirstPageNo();
			int lastPageNo = myPage.getLastPageNo();
			
			
			int nextPageNo = pageNo + 1;
			int prevPageNo = pageNo - 1;

			String nextUrl = myUrl + "&pageNo=" + nextPageNo + "&pageSize="
					+ pageSize + "&rowCount=" + rowCount+"&isFirstSearchFlag=0";
			String prevUrl = myUrl + "&pageNo=" + prevPageNo + "&pageSize="
					+ pageSize + "&rowCount=" + rowCount+"&isFirstSearchFlag=0";
			
			String lastPage =  myUrl + "&pageNo=" + lastPageNo + "&pageSize="
					+ pageSize + "&rowCount=" + rowCount+"&isFirstSearchFlag=0";
			String firstPage = myUrl + "&pageNo=" + firstPageNo + "&pageSize="
					+ pageSize + "&rowCount=" + rowCount+"&isFirstSearchFlag=0";

			String pageSizeLink = myUrl + "&pageNo=" + pageNo + "&rowCount="
					+ rowCount+"&isFirstSearchFlag=0";
			String goToPageLink = myUrl + "&pageSize=" + pageSize
					+ "&rowCount=" + rowCount+"&isFirstSearchFlag=0";
		%> 
		
			<div class="fixed-table-pagination clearfix">
				<div class="pull-left pagination-detail">
					<span class="pagination-info">
					Showing <s:if test="page.rowCount>0">
							<s:property value="page.rowFrom" />
						</s:if>
						<s:else>0</s:else> to <s:property value="page.rowTo" /> of <s:property value="page.rowCount" /> rows
					</span><span class="page-list"><select name="pageSize" id="listpage"
						onchange="setPageSize('<%=pageSizeLink%>')" style="width:70px">
						<option value="10"
							<s:if test="page.pageSize==10">selected="selected"</s:if>>
							10
						</option>
						<option value="15"
							<s:if test="page.pageSize==15">selected="selected"</s:if>>
							15
						</option>
						<option value="20"
							<s:if test="page.pageSize==20">selected="selected"</s:if>>
							20
						</option>
						<option value="30"
							<s:if test="page.pageSize==30">selected="selected"</s:if>>
							30
						</option>
					</select> rows per page</span>
				</div>
				<div class="pull-right pagination"><ul class="pagination"><li class="page-item"><a href="<%=firstPage%>" id="idhreffirst">First</a></li>
					<s:if test="page.isHasPreviousPage==1">
						<li class="page-item page-pre"><a href="<%=prevUrl%>">Prev</a></li>
					</s:if>
					<s:else>
						<li class="page-item page-pre"><a disabled="disabled">Prev</a></li>
					</s:else>
					
					<s:if test="page.isHasNextPage==1">
						<li class="page-item page-next"><a href="<%=nextUrl%>">Next</a></li>
					</s:if>
					<s:else>
						<li class="page-item page-next"><a disabled="disabled">Next</a></li>
					</s:else>
					<li class="page-item"><a href="<%=lastPage %>">Last</a></li>
					</ul></div>
			</div>
		
		<!--
				<table>
					<tr>
					  <td>Items&nbsp;
						<s:if test="page.rowCount>0">
							<s:property value="page.rowFrom" />
						</s:if>
						<s:else>0</s:else>
					&nbsp;-&nbsp;<s:property value="page.rowTo" />&nbsp;of <s:property value="page.rowCount" /></td>
					<td>&nbsp;&nbsp;&nbsp;&nbsp;
					<select name="pageSize" id="listpage" -->
						<!--onchange="setPageSize('<--%=pageSizeLink%>')" style="width:70px"-->
						<!--option value="10"
							<s:if test="page.pageSize==10">selected="selected"</s:if>>
							10
						</option-->
						<!--option value="15"
							<s:if test="page.pageSize==15">selected="selected"</s:if>>
							15
						</option-->
					<!--option value="20"
							<s:if test="page.pageSize==20">selected="selected"</s:if>>
							20
						</option-->
						<!--option value="30"
							<s:if test="page.pageSize==30">selected="selected"</s:if>>
							30
						</option-->
					<!--/select>&nbsp;Items per page&nbsp;</td>
					<td>
					<a href="<--%=firstPage%>">First</a>
					<s:if test="page.isHasPreviousPage==1">
						<a href="<--%=prevUrl%>">Prev</a>
					</s:if>
					<s:else>
						<a disabled="disabled">Prev</a>
					</s:else>
					<input type="text" size="3" id="gono" name="gono" value="<s:property value='page.pageNo'/>">of
					<s:property value="page.lastPageNo" />
					<s:if test="page.isHasNextPage==1">
						<a href="<--%=nextUrl%>">Next</a>
					</s:if>
					<s:else>
						<a disabled="disabled">Next</a>
					</s:else>
					<a href="<--%=lastPage %>">Last</a>
					<input type="button" value="GOTO" onClick="goPage('<--%=goToPageLink%>')" style="margin-left:10px"/>
					</td></tr>
				</table>
				-->
			
	
		<%
			}
			}
		%>
	</body>
</html>
