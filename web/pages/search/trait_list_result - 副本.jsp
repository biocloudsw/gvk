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
  <script src="/gvk/js/headerfooter.js"></script>
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
					 		<h5>Traits: <s:if test="mtraitCount>0"><a href="/gvk/fuzzyTraitSearch?searchParam=<s:property value='searchParam'/>&searchSpecies=<s:property value='searchSpecies'/>&mtraitCount=<s:property value='mtraitCount' />"><s:property value="mtraitCount" /></a></s:if><s:else>0</s:else></h5>
							<h5>Genes: <s:if test="mgeneCount>0"><a href="/gvk/fuzzyGeneSearch?searchParam=<s:property value='searchParam'/>&searchSpecies=<s:property value='searchSpecies'/>&mgeneCount=<s:property value='mgeneCount' />"><s:property value="mgeneCount" /></a></s:if><s:else>0</s:else></h5>
					 </div>
				</div>
			</div> 
			
			<div class="panel panel-default">
				<div class="panel-heading search_condition" data-toggle="collapse" data-target="#idFilter">
				  <h4 class="panel-title" style="font-size:14px;font-weight:bold;">Filter</h4>
				</div>
				<div id="idFilter" class="panel-collapse collapse in">
					 <div class="panel-body" style="font-size:13px;">
					 		 <form>
					        <div>
					            <div>Species</div>
					            <select class="selectpicker form-control" title="Select species" multiple data-live-search="true" data-selected-text-format="count > 3">
									<option value="1">Human</option>
									<option value="2">Dog</option>
									<option value="3">Chicken</option>
									<option value="4">Rice</option>
									<option value="5">Sheep</option>                            
								</select>
					            <hr>
					        </div>
							
					        <div>
					            <div>Catalog Trait</div>
					            <select class="selectpicker form-control" title="Select catalog trait" multiple data-live-search="true" data-selected-text-format="count > 3">
									<option value="1">Example 1</option>
									<option value="2">Example 2</option>
									<option value="3">Example 3</option>
									<option value="4">Example 4</option>
									<option value="5">Example 5</option>                            
								</select>
					            <hr>
					        </div>
					        
					        <div>
					            <span>
					                P value rank
					                <span class="cancel" id="rank_cancel" hidden="">
					                    <a><span class="glyphicon glyphicon-remove-circle"></span></a>
					                </span>
					            </span>
					            <div class="input-group">
					                <span class="input-group-addon">&lt;=</span>
					                <input type="number" class="form-control">
					                <span class="input-group-addon">E-5</span>
					            </div>
					            <hr>
					        </div>
					        
					        <div class="form-group">
					            <span>
					                Study year
					            </span>
					            <div class="input-group">
					                <span class="input-group-addon">from</span>
					                <input type="number" class="form-control" min="1900" max="2030" id="year_start">
					                <span class="input-group-addon">to</span>
					                <input type="number" class="form-control" min="1900" max="2030" id="year_end">
					            </div>
					            <hr>
					        </div>
					        
					        <div>
					            <span>
					                Association model
					            </span>
					            <select class="form-control" style="width: 100%">
					                <option selected="selected" disabled="disabled" style="display: none"></option>
					                <option value="1">Example 1</option>
					                <option value="2">Example 2</option>
					            </select>
					            <hr>
					        </div>
					        
					        <div>
					            <span>
					                Genotyping technology
					            </span>
					            <select class="form-control" style="width: 100%">
					                <option selected="selected" disabled="disabled" style="display: none"></option>
					                <option value="1">Example 1</option>
					                <option value="2">Example 2</option>
					            </select>
					            <hr>
					        </div>
							
							<button type="button" class="btn btn-primary" onClick="">Apply</button>
					   		<button type="button" class="btn btn-info" onClick="">Clear all</button>
					    </form>
					 </div>
				</div>
			</div> 
			
		
		
		
           
        </div>
        
        <div id="result" class="col-md-9" style="padding-top: 15px">
			<s:if test="gwasAssociationList != null ">
			<s:iterator id="gwasAssociation" value="gwasAssociationList">
			<div class="panel panel-default">
				 <div class="panel-body">
				 
					<h4><a href="/gvk/browse/getTrait?param=<s:property value='#gwasAssociation.traitId'/>&param1=<s:property value='searchSpecies'/>"><s:property value="#gwasAssociation.traitName"/></a></h4>
					<h5><s:property value="#gwasAssociation.termDefinition"/></h5>
					<h5>Associations:<span class="label label-default"><s:property value="#gwasAssociation.traitCount"/></span>&nbsp;&nbsp;&nbsp;&nbsp;Studies:<span class="label label-default"><s:property value="#gwasAssociation.gwasCount"/></span></h5>
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

</body>
</html>
