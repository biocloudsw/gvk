<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 
  <link href="/gvk/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
  <link href="/gvk/bootstrap/css/bootstrap-select.min.css" rel="stylesheet" />
  <link href="/gvk/bootstrap/css/bootstrap-table.min.css" rel="stylesheet" />
  <link href="/gvk/css/common.css" rel="stylesheet" />
       <link href="/gvk/js/jquery-ui.css" rel="stylesheet" />
	    
  <script src="/gvk/js/jquery-3.2.1.min.js"></script>
  <script src="/gvk/bootstrap/js/bootstrap.min.js"></script>
  <script src="/gvk/bootstrap/js/bootstrap-select.min.js"></script>
 
  <script src="/gvk/js/common.js"></script>
 <script src="/gvk/js/jquery-ui.js"></script>
 
     <script src="/gvk/js/export.js"></script>
  <title>Search Results</title>
  
</head>
  
<body>
<div class="container">
	
  	<jsp:include page="/inc/header.jsp" />
	<div id="main-info" class="row">
		<div class="col-md-4">
			<!-- You are now at... -->
			<ol class="breadcrumb">
				<li><a href="/gvk/index.jsp">GWAS Atlas</a></li>
				<li class="active">Search</li>
			</ol>
		</div>
		<div class="col-md-8">
			<jsp:include page="/inc/quickSearch.jsp" flush="true" />
		</div>
	</div>
  
	<!-- Content -->
	<div class="row">
		<div id="filter" class="col-md-3" style="padding-left: 1%;padding-top: 15px; padding-right: 0px">
           <div class="panel panel-default">
				<div class="panel-heading search_condition" data-toggle="collapse" data-target="#idResults"> 
				 	<h4 class="panel-title" style="font-size:14px; font-weight:bold;">Search Results</h4>
				 </div>
				<div id="idResults" class="panel-collapse collapse in">
					 <div class="panel-body" style="font-size:14px;" >
					 		<h5>Traits: <s:if test="mtraitCount>0">
							
							<a href="/gvk/fuzzyTraitSearch?searchParam=<s:property value='searchParam'/>&searchSpecies=<s:property value='searchSpecies'/>&mtraitCount=<s:property value='mtraitCount' />&mgeneCount=<s:property value='mgeneCount' />&searchTrait=<s:property value='searchTrait' />&psitu=<s:property value='psitu' />&pvalue=<s:property value='pvalue' />"><s:property value="mtraitCount" /></a>
							</s:if>
							
							
							
							<s:else>0</s:else></h5>
							<h5>Genes: <s:if test="mgeneCount>0"><a href="/gvk/fuzzyGeneSearch?searchParam=<s:property value='searchParam'/>&searchSpecies=<s:property value='searchSpecies'/>&mgeneCount=<s:property value='mgeneCount' />&mtraitCount=<s:property value='mtraitCount' />&searchTrait=<s:property value='searchTrait' />&psitu=<s:property value='psitu' />&pvalue=<s:property value='pvalue' />"><s:property value="mgeneCount" /></a></s:if>
							<s:elseif test="mmapGeneCount>0"><a href="/gvk/fuzzyGeneRangeSearch?searchParam=<s:property value='searchParam'/>&searchSpecies=<s:property value='searchSpecies'/>&mtraitCount=<s:property value='mtraitCount' />&mmapGeneCount=<s:property value='mmapGeneCount' />&searchTrait=<s:property value='searchTrait' />&psitu=<s:property value='psitu' />&pvalue=<s:property value='pvalue' />"><s:property value="mmapGeneCount" /></a></s:elseif>
							<s:else>0</s:else></h5>
					 </div>
				</div>
			</div> 
			
			<div class="panel panel-default">
				<jsp:include page="/inc/filter.jsp" />
			</div> 
        </div>
        
        <div id="result" class="col-md-9" style="padding-top: 15px">
		<div class="clearfix"></div>
		  <div class="panel panel-default">
		  <div class="panel-body" style="padding-top:0px;padding-bottom:0px;" >
		  	<div >
					<div style="float:left;">
						<jsp:include page="/inc/page.jsp" />
					</div>
		
					<div style="float:right; padding-top:10px;">											
						<div class="export btn-group">
							<button class="btn btn-default btn-undefined dropdown-toggle" aria-label="export type" title="Export data" data-toggle="dropdown" type="button">
								<i class="glyphicon glyphicon-export icon-share"></i>
								<span class="caret"></span>
							</button>
							<ul class="dropdown-menu" role="menu">
								<!--<li role="menuitem" data-type="excel"><a href="javascript:exportmstrait()">MS-Excel</a></li>
								<li role="menuitem" data-type="csv"><a href="javascript:exportcsvtrait()">CSV</a></li>
								
								-->
								<li role="menuitem" data-type="txt"><a href="javascript:exporttxttrait()">TXT</a></li>
							</ul>
						</div>
					</div>
				 </div>
				</div>
				<div style="clear:both"></div>
			</div>
			<s:if test="mapGeneBeanList != null ">
			<s:iterator id="mapGeneBean" value="mapGeneBeanList">
			<div class="panel panel-default">
				 <div class="panel-body" style=" padding-bottom: 10px;">
					<h4><a href="/gvk/browse/getMapGene?param=<s:property value='#mapGeneBean.gid'/>"><s:property value="#mapGeneBean.mapGeneId"/></a> (Gene)</h4>
					<h5>Description:&nbsp;&nbsp;<s:property value="#mapGeneBean.description"/></h5>
					<h5>Genomic Location:&nbsp;&nbsp;<s:property value="#mapGeneBean.mapGeneChrom"/>:<s:property value="#mapGeneBean.mapGeneStart"/>-<s:property value="#mapGeneBean.mapGeneEnd"/></h5>
					<h5>Associations:<span class="label label-default"><s:property value="#mapGeneBean.traitCount"/></span>&nbsp;&nbsp;&nbsp;&nbsp;Studies:<span class="label label-default"><s:property value="#mapGeneBean.studyCount"/></span></h5>
				</div>
			</div>
			</s:iterator>
			</s:if>
			<s:else>
			<div class="panel panel-default">
				 <div class="panel-body">
					There is no results
				</div>
			</div>	
			</s:else>
		</div>
	</div>
    
</div>
  <script src="/gvk/js/filter.js"></script>
  <script type="text/javascript">
		showTrait();
	</script>
</body>
</html>
