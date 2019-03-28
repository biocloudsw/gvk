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
    <script src="/gvk/js/headerfooter.js"></script>
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
		<div class="col-md-12">
			<!-- You are now at... -->
			<ol class="breadcrumb">
				<li><a href="/gvk/index.jsp">GWAS Atlas</a></li>
				<li class="active">Search</li>
			</ol>
		</div>
		
	</div>
  
	<!-- Content -->
	<div class="row">
		
        
        <div id="result" class="col-md-12" style="padding-top: 15px">

          
			<div>
				 <div class="panel panel-default">
				     <div class="panel-heading" > 
				 		<h4 class="panel-title" style="font-size:14px; font-weight:bold;">Gene information</h4>
					 </div>
					 <div class="panel-body" style="font-size:14px;">
					 	<div class="row">
							<div class="col-md-2">
								Gene ID:&nbsp;&nbsp;
							</div>
							<div class="col-md-9">
								<s:property value="mapGeneBean.mapGeneId"/>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								Gene Symbol:&nbsp;&nbsp;
							</div>
							<div class="col-md-9">
								<s:property value="mapGeneBean.mapGeneSymbol"/>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								Genomic Location:&nbsp;&nbsp;
							</div>
							<div class="col-md-9">
								<s:property value="mapGeneBean.mapGeneChrom"/>:<s:property value="mapGeneBean.mapGeneStart"/>-<s:property value="mapGeneBean.mapGeneEnd"/>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2">
								Description:&nbsp;&nbsp;
							</div>
							<div class="col-md-9">
								<s:property value="mapGeneBean.description"/>
							</div>
						</div>
						
						
					
					 </div>
				 </div>			
			</div>



			<div>
				<ul id="myTabs" class="nav nav-tabs" role="tablist">
				
					<li class="active" role="presentation">
						<a href="#association" aria-controls="association" role="tab" data-toggle="tab">
							Association<span id="association_count"> (<s:property value="mapGeneBean.traitCount"/>)</span>
						</a>
					</li>
					
					<li role="presentation">
						<a href="#study" aria-controls="study" role="tab" data-toggle="tab">
							Studies<span id="study_count"> (<s:property value="mapGeneBean.studyCount"/>)</span>
						</a>
					</li>
					<li role="presentation">
						<a href="#publication" aria-controls="publication" role="tab" data-toggle="tab">
							Publications<span id="publication_count"> (<s:property value="mapGeneBean.publicationCount"/>)</span>
						</a>
					</li>
				</ul>

				<div class="tab-content">
					   

					<div id="association" class="tab-pane active" role="tabpanel">
						<iframe id="idgbframe1"  height="780" frameBorder="0" width="100%" src="/gvk/browse/getGeneAssociation?param=<s:property value='param' />" style="-ms-zoom:1; overflow:scroll; min-height:780px;">
						</iframe>
						<div class="clearfix"></div>
					</div>

				

					<div id="study" class="tab-pane" role="tabpanel">
						<iframe id="idgbframe3"  height="800" frameBorder="0" width="100%" src="#" style="-ms-zoom:1; overflow:scroll; min-height:800px;">
						</iframe>
						<div class="clearfix"></div>
					</div>

					<div id="publication" class="tab-pane" role="tabpanel">
					
						<iframe id="idgbframe4"  height="780" frameBorder="0" width="100%" src="#" style="-ms-zoom:1; overflow:scroll; min-height:780px;">
						</iframe>
						<div class="clearfix"></div>
					</div>
					

				</div>
			</div>

			</div>
	</div>
    
</div>

<script type="text/javascript" language="javascript">
	 
        var param="<s:property value='param' />";

		
	
		
		
		$('#myTabs a[href="#study"]').click(function (e) {
		  e.preventDefault()
		  $("#idgbframe3").attr("src","/gvk/browse/getGeneStudy?param="+param+"&time="+new Date().getTime());
		  $(this).tab('show');
		  
		})
		
		$('#myTabs a[href="#publication"]').click(function (e) {
			e.preventDefault()
			$("#idgbframe4").attr("src","/gvk/browse/getGenePublication?param="+param+"&time="+new Date().getTime());
			$(this).tab('show');
		});
		
		
  

</script>

</body>
</html>
