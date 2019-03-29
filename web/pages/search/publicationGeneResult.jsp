<link href="/gvk/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="/gvk/bootstrap/css/bootstrap-select.min.css" rel="stylesheet" />
<link href="/gvk/bootstrap/css/bootstrap-table.min.css" rel="stylesheet" />
<link href="/gvk/css/common.css" rel="stylesheet" />
<%@ taglib prefix="s" uri="/struts-tags"%>
						
					
							<div class="fixed-table-toolbar">
							
							 <s:if test="itemCountList != null">
								<div class="columns columns-left pull-left">
								
									<s:iterator value="itemCountList" id="itemCount">
									
									<button class="btn btn-default" onclick="window.location.href='/gvk/browse/getGenePublication?param=<s:property value="#parameters.param" />'"><s:property value="#itemCount.name" />( <s:property value="#itemCount.count" /> )</button>
									
									</s:iterator>

								</div>
								</s:if>
								<div class="columns columns-right btn-group pull-right">
									
									
								</div>
							</div>
							
							<div style="padding-bottom: 0px;">
								<!-- <div class="fixed-table-body"> -->
								<div>
									<table id="publication_list" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th data-field="pmid"  data-sortable="true"  tabindex="0">
													<div class="th-inner">PMID</div>
												</th>
												<th data-field="journal"  data-sortable="true" tabindex="0">
													<div class="th-inner">Journal</div>
												</th>
												<th data-field="title" tabindex="0">
													<div class="th-inner">Title</div>
												</th>
												<th data-field="author" tabindex="0">
													<div class="th-inner">Authors</div>
												</th>
												<th data-field="year" tabindex="0">
													<div class="th-inner">Year</div>
												</th>
												<th data-field="species" tabindex="0">
													<div class="th-inner">Species</div>
												</th>
												
												<th data-field="gwasCount" tabindex="0">
													<div class="th-inner">Association#</div>
												</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator id="publication" value="publicationList">
												<tr>
									
												<td><a href="https://www.ncbi.nlm.nih.gov/pubmed/27714888" target="_blank"><s:property value="#publication.pmid"/></a><span class="glyphicon glyphicon-share"></span></td>
												<td><s:property value="#publication.journal"/></td>
												<td><s:property value="#publication.title"/></td>
												<td><s:property value="#publication.author"/></td>
												<td><s:property value="#publication.pubyear"/></td>
												<td><s:property value="#publication.species"/></td>
												<td><s:property value="#publication.gwasCount"/></td>
											</tr>
											
											</s:iterator>
											
										</tbody>
									</table>
								</div>
							</div>
						
						
						
						
				<script src="/gvk/js/jquery-3.2.1.min.js"></script>
				<script src="/gvk/bootstrap/js/bootstrap.min.js"></script>
				<!--<link href="/gvk/css/bootstrap-table.min.css" rel="stylesheet">	
				 <script  src="/gvk/js/bootstrap-table.min.js"></script> -->
				 <script src="/gvk/js/tableExport.min.js" type="text/javascript"></script>
				 <link href="/gvk/css/bootstrap-table.min.1.14.1.css" rel="stylesheet">	
				 <script  src="/gvk/js/bootstrap-table.min.1.14.1.js" type="text/javascript"></script> 
				 <script src="/gvk/js/bootstrap-table-export.min.1.14.1.js" type="text/javascript"></script>
				<script type="text/javascript">
	
	
		
					$("#publication_list").bootstrapTable('destroy').bootstrapTable({
				
					
						sortable: true,
						search: false, 
				
						searchAlign: 'right',
						strictSearch: false,  
						showRefresh: false,
						pagination: true, 
						paginationVAlign:'both',
						pageList: [10,50,100],
						showToggle:false,
						cardView: false,
						showHeader:true,
						 fixedColumns: false,
						 showColumns:true,
						showExport:true,
						exportDataType:'all',
						exportTypes:['excel','csv', 'txt','json'],  
					
						height : 600
				
					});
		</script>