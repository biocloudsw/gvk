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
				<script src="/gvk/js/jquery-3.2.1.min.js"></script>
				<script src="/gvk/bootstrap/js/bootstrap.min.js"></script>
				<link href="/gvk/css/bootstrap-table.min.css" rel="stylesheet">	
				<script  src="/gvk/js/bootstrap-table.min.js"></script>
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
						fixedNumber: 10,
						height : 600
				
					});
		</script>