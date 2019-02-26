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
<div class="container-fluid">
	
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
			<!-- Quick Search -->
            <div class="row">
                <div class="col-md-12">
                    <form action="#" method="post" class="form-inline" style="margin-bottom: 0px; text-align: right;" role="form">
                        <div class="form-group">
                            <label>Search:&nbsp;</label>
                            <select id="search-situ-type" name="searchSituType" class="form-control" onChange="chooseSearch()">
                                <option value="all-species" selected="selected">All Species</option>
	        	                <option>Human</option>
	                        	<option>Mouse</option>
	        	                <option>Dog</option>
                            </select>
                            <label>&nbsp;for&nbsp;</label>
                            <div class="input-group">
                                <input type="text" id="search-param" name="searchParam" class="form-control" style="width: 300px;">
                                <span class="input-group-btn">
                                    <a id="quick-search-icon" class="btn btn-default" type="submit" value="Search" href="searchResults.html">
                                        <span class="glyphicon glyphicon-search" style="line-height: 20px;"></span>
                                    </a>
                                </span>
                            </div>
                            <span style="padding-left: 10px; color: grey;">
                                e.g. <a href="#">Carboxy*</a>, <a href="#">chx28</a>
                            </span>
                        </div>
                    </form>
                </div>
            </div>
		</div>
	</div>
  
	<!-- Content -->
	<div class="row">
		<div id="filter" class="col-md-3" style="padding-left: 1%;padding-top: 15px; padding-right: 0px">
            <div>
			
				<div>Traits:<s:property value="mtraitCount" /></div>
				<div>Genes:<s:property value="mgeneCount" /></div>
				
				
                <ul class="nav nav-tabs" role="tablist" id="filter_tab">
                    <li role="presentation" class="active">
                    	<a href="#filters" aria-controls="filter" role="tab" data-toggle="tab" style="border-bottom: 0px">
                    		Filter
                    	</a>
                    </li>
                    <a onClick="hideFilter()" style="line-height:40px; text-align:center; float: right;">
                    	Hide
                    	<span class="glyphicon glyphicon-chevron-left"></span>
                    </a>
                </ul>

                <div class="tab-content">
                    <div id="filters" class="tab-pane active" style="border-top: 0px">
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
