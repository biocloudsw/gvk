<link href="/gvk/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
  <link href="/gvk/bootstrap/css/bootstrap-select.min.css" rel="stylesheet" />
  <link href="/gvk/bootstrap/css/bootstrap-table.min.css" rel="stylesheet" />
    <link href="/gvk/css/common.css" rel="stylesheet" />
						<%@ taglib prefix="s" uri="/struts-tags"%>
						<div class="bootstrap-table">
							<div class="fixed-table-toolbar">
								<div class="columns columns-left pull-left">
									<button class="btn btn-default">Maize(50)</button>
									<button class="btn btn-default">Rice(30)</button>
									<button class="btn btn-default">Sorghum(10)</button>
									<button class="btn btn-default">NNN(10)</button>
								</div>
								<div class="columns columns-right btn-group pull-right">
									
								</div>
							</div>
							<div class="fixed-table-container" style="padding-bottom: 0px;">
								<!-- <div class="fixed-table-body"> -->
								<div>
									<table id="gene_list" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th data-field="geneid" >
													Gene ID
												</th>
												<th data-field="location" >
													Location
												</th>
												<th data-field="species">
													Species
												</th>
												<th data-field="trait" >
													Related Traits
												</th>
												<th data-field="snpid" >
													SNPID
												</th>
												<th data-field="effect">
													Effect
												</th>
												<th data-field="pmid" >
													PMID
												</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator id="genotypeAnnotateGeneView" value="genotypeAnnotateGeneViewList">
											<tr>
												<td><s:property value="#genotypeAnnotateGeneView.mapGeneId"/><span class="glyphicon glyphicon-share"></span></td>
												<td><s:property value="#genotypeAnnotateGeneView.mapGeneChrom"/>:<s:property value="#genotypeAnnotateGeneView.mapGeneStart"/>-<s:property value="#genotypeAnnotateGeneView.mapGeneEnd"/></td>
												<td><s:property value="#genotypeAnnotateGeneView.commonName"/></td>
												<td><s:property value="#genotypeAnnotateGeneView.traitName"/></td>
												<td><a href="http://bigd.big.ac.cn/gvm/snp/getSNPDetail?snpname=<s:property value='#genotypeAnnotateGeneView.varId'/>" target="_blank"><s:property value="#genotypeAnnotateGeneView.varId"/></a><span class="glyphicon glyphicon-share"></span></td>
												<td><s:property value="#genotypeAnnotateGeneView.conseqtype"/>|<s:property value="#genotypeAnnotateGeneView.effect"/></td>
												<td><a href="https://www.ncbi.nlm.nih.gov/pubmed/<s:property value='#genotypeAnnotateGeneView.pmid'/>" target="_blank"><s:property value="#genotypeAnnotateGeneView.pmid"/></a><span class="glyphicon glyphicon-share"></span></td>
											</tr>
											</s:iterator>
											
											
										</tbody>
									</table>
								</div>
								
							</div>
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
	
	
		
					$("#gene_list").bootstrapTable({
				
					
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
						 fixedColumns: true,
						 showColumns:true,
						showExport:true,
						exportDataType:'all',
						exportTypes:['excel','csv', 'txt','json'], 
						
						height : 600
				
					});
		</script>