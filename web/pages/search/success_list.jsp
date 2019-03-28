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
  
  <script src="/gvk/js/jquery-3.2.1.min.js"></script>
  <script src="/gvk/bootstrap/js/bootstrap.min.js"></script>
  <script src="/gvk/bootstrap/js/bootstrap-select.min.js"></script>

  <script src="/gvk/js/common.js"></script>

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
					 <div class="panel-body" style="font-size:14px;">
					 		<h5>Genes: <s:if test="mmapGeneCount>0"><a href="/gvk/fuzzyGeneRangeSearch?searchParam=<s:property value='searchParam'/>&searchSpecies=<s:property value='searchSpecies'/>&mmapGeneCount=<s:property value='mmapGeneCount' />&mgenotypeCount=<s:property value='mgenotypeCount' />&searchTrait=<s:property value='searchTrait' />&psitu=<s:property value='psitu' />&pvalue=<s:property value='pvalue' />"><s:property value="mmapGeneCount" /></a></s:if><s:else>0</s:else></h5>
							<h5>Variants: <s:if test="mgenotypeCount>0"><a href="/gvk/fuzzyVariationSearch?searchParam=<s:property value='searchParam'/>&searchSpecies=<s:property value='searchSpecies'/>&mgenotypeCount=<s:property value='mgenotypeCount' />&mmapGeneCount=<s:property value='mmapGeneCount' />&searchTrait=<s:property value='searchTrait' />&psitu=<s:property value='psitu' />&pvalue=<s:property value='pvalue' />"><s:property value="mgenotypeCount" /></a></s:if><s:else>0</s:else></h5>
					 </div>
				</div>
			</div> 
			
			<div class="panel panel-default">
				<jsp:include page="/inc/filter.jsp" />
			</div> 
			
		
		
		
           
        </div>
        
        <div id="result" class="col-md-9" style="padding-top: 15px">
			
				<div class="panel panel-default" style="height:600px;">
				<div class="panel-body">
					There is no results
				</div>
			</div>
			
		</div>
	</div>
    
</div>

</body>
</html>
