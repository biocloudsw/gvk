					
	<link href="/gvk/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
  <link href="/gvk/bootstrap/css/bootstrap-select.min.css" rel="stylesheet" />
  <link href="/gvk/bootstrap/css/bootstrap-table.min.css" rel="stylesheet" />
      <link href="/gvk/css/common.css" rel="stylesheet" />
						<%@ taglib prefix="s" uri="/struts-tags"%>
						<div class="bootstrap-table">
							<div class="fixed-table-toolbar">
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
							<div style="padding-bottom: 0px;">
								<div>
								<!-- <div class="fixed-table-body"> -->
									<table id="trait_list" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th data-field="reporttrait" tabindex="0">
													<div class="th-inner">Reported Traits</div>
												</th>
												<th data-field="ontologytrait" data-sortable="true" tabindex="0">
													<div class="th-inner">Ontology Trait</div>
												</th>
												<th data-field="ontology" tabindex="0">
													<div class="th-inner">Ontology</div>
												</th>
												<th data-field="acc" data-sortable="true" tabindex="0">
													<div class="th-inner">ID</div>
												</th>
												<th data-field="synonyms" tabindex="0">
													<div class="th-inner">Synonyms</div>
												</th>
												<th data-field="definition" tabindex="0">
													<div class="th-inner">Definition</div>
												</th>
												<th data-field="comment" tabindex="0">
													<div class="th-inner">Comment</div>
												</th>
												<th data-field="subclass" tabindex="0">
													<div class="th-inner">Subclass of</div>
												</th>
												<th data-field="traitcount" tabindex="0">
													<div class="th-inner">Association#</div>
												</th>
											</tr>
										</thead>
										
										<tbody>
											<s:iterator id="termInfoBean" value="termInfoList">
											<tr>
												<td><s:property value="#termInfoBean.traitName"/></td>
												<td><s:property value="#termInfoBean.termName"/></td>
                                                <td><s:property value="#termInfoBean.termType"/></td>
                                                <td><s:property value="#termInfoBean.acc"/></td>
                                                <td><s:property value="#termInfoBean.termSynonym"/></td>
                                                <td><s:property value="#termInfoBean.termDefinition"/></td>
                                                <td><s:property value="#termInfoBean.termComment"/></td>
                                                <td><s:property value="#termInfoBean.termType"/></td>
												<td><a href="#"><s:property value="#termInfoBean.traitCount"/></a></td>
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
	
	
		
					$("#trait_list").bootstrapTable({
				
					
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
						height : 800
				
					});
		</script>