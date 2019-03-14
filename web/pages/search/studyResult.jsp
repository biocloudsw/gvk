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
				<script  src="/gvk/js/bootstrap-table.min.js"></script>
				 <script src="/gvk/js/common.js"></script>


</head>
  
<body>

						<div class="fixed-table-toolbar">
							   <s:if test="itemCountList != null">
							   <div class="columns columns-left pull-left">
							   		<s:iterator value="itemCountList" id="itemCount">
									
									<button class="btn btn-default" onclick="window.location.href='/gvk/browse/getStudy?param=<s:property value="#parameters.param" />&amp;param1=<s:property value="#itemCount.id" />'"><s:property value="#itemCount.name" />( <s:property value="#itemCount.count" /> )</button>
									
									</s:iterator>
							   </div>
							   </s:if>
								<div class="columns columns-right btn-group pull-right">
									<div class="keep-open btn-group" title="Columns">
										<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
											<i class="glyphicon glyphicon-th icon-th"></i>
											<span class="caret"></span>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li><label><input type="checkbox" data-field="0" value="0" checked="checked">Example 1</label></li>
											<li><label><input type="checkbox" data-field="0" value="0" checked="checked">Example 2</label></li>
											<li><label><input type="checkbox" data-field="0" value="0" checked="checked">Example 3</label></li>
											<li><label><input type="checkbox" data-field="0" value="0" checked="checked">Example 4</label></li>
										</ul>
									</div>
									<div class="export btn-group">
										<button class="btn btn-default dropdown-toggle" aria-label="export type" title="Export data" data-toggle="dropdown" type="button">
											<i class="glyphicon glyphicon-export icon-share"></i> <span class="caret"></span>
										</button>
										<ul class="dropdown-menu" role="menu">
											<li role="menuitem" data-type="csv">
												<a href="javascript:void(0)">CSV</a>
											</li>
											<li role="menuitem" data-type="txt">
												<a href="javascript:void(0)">TXT</a>
											</li>
										</ul>
									</div>
								</div>
							</div>


							<div  style="padding-bottom: 0px;">
								<div>
									<table id="study_list" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th data-field="studyacc"  data-sortable="true" tabindex="0">
													Study ID
												</th>
												<th data-field="species" tabindex="0">
													Species
												</th>
												<th data-field="spot" tabindex="0">
													Sampling spot
												</th>
												<th data-field="year" tabindex="0">
													Sampling year
												</th>
												<th data-field="condition" tabindex="0">
													Condition
												</th>
												<th data-field="population" tabindex="0">
													Population
												</th>
												<th data-field="size" tabindex="0">
													Sample Size
												</th>
												<th data-field="tissue" tabindex="0">
													Tissue
												</th>
												<th data-field="tech" tabindex="0">
													Genotype Technology
												</th>
												<th data-field="model" tabindex="0">
													Association model
												</th>
												<th data-field="gwascount" tabindex="0">
													Association#
												</th>
												<th data-field="pmid" tabindex="0">
													PMID
												</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator id="studyBean" value="studyList">
											<tr>
												<td><s:property value="#studyBean.studyAcc"/></td>
												<td><s:property value="#studyBean.species"/></td>
												<td><s:property value="#studyBean.sampleSpot"/></td>
												<td><s:property value="#studyBean.sampleYear"/></td>
												<td><s:property value="#studyBean.sampleCondition"/></td>
												<td><s:property value="#studyBean.population"/></td>
												<td><s:property value="#studyBean.sampleSize"/></td>
												<td><s:property value="#studyBean.tissue"/></td>
												<td><s:property value="#studyBean.techName"/></td>
												<td><s:property value="#studyBean.modelName"/></td>
												<td><s:property value="#studyBean.gwasCount"/></td>
												<td><a href="https://www.ncbi.nlm.nih.gov/pubmed/<s:property value='#studyBean.pmid'/>" target="_blank"><s:property value="#studyBean.pmid"/></a><span class="glyphicon glyphicon-share"></span></td>
											</tr>
											</s:iterator>
										
										</tbody>
									</table>
								</div>
								
								
							</div>
				
						
			
				<script type="text/javascript">
	
	
		
					$("#study_list").bootstrapTable({
						sortable: true,
						strictSearch: false,  
						showRefresh: false,
						pagination: true, 
						paginationVAlign:'both',
						pageList: [10,50,100],
						showToggle:false,
						cardView: false,
						showHeader:true,
						 
						height : 600
				
					});
		</script>
		
		</body>
		</html>