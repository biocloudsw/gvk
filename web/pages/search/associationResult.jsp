						<%@ taglib prefix="s" uri="/struts-tags"%>
						<link href="/gvk/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
						<link href="/gvk/bootstrap/css/bootstrap-select.min.css" rel="stylesheet" />
						<link href="/gvk/bootstrap/css/bootstrap-table.min.css" rel="stylesheet" />
						<link href="/gvk/css/common.css" rel="stylesheet" />
						
							<div class="fixed-table-toolbar">
							   <s:if test="itemCountList != null">
							   <div class="columns columns-left pull-left">
							   		<s:iterator value="itemCountList" id="itemCount">
									
									<button class="btn btn-default" onclick="window.location.href='/gvk/browse/getAssociation?param=<s:property value="#parameters.param" />&amp;param1=<s:property value="#itemCount.id" />'"><s:property value="#itemCount.name" />( <s:property value="#itemCount.count" /> )</button>
									
									</s:iterator>
							   </div>
							   </s:if>
								<div class="columns columns-right btn-group pull-right">
									
									
								</div>
							</div>
							<div  style="padding-bottom: 0px;">
							
								<!-- <div class="fixed-table-body"> -->
								<div >
									<table id="gwas_list" class="table table-bordered table-hover">
										<thead>
											<tr>
												<th data-field="snpid" tabindex="0">
													<div class="th-inner">VarID</div>
												</th>
												<th data-field="reporttrait" tabindex="0">
													<div class="th-inner">Reported Traits</div>
												</th>
												<th data-field="species" tabindex="0">
													<div class="th-inner">Species</div>
												</th>
												
												<th data-field="ref"  data-visible="false" tabindex="0">
													<div class="th-inner">Ref</div>
												</th>
												<th data-field="pos" tabindex="0">
													<div class="th-inner">Position</div>
												</th>
												<th data-field="pvalue" tabindex="0">
													<div class="th-inner">Pvalue</div>
												</th>
												
												<th data-field="r2" tabindex="0">
													<div class="th-inner">R2%</div>
												</th>
												<th data-field="reportgene" tabindex="0">
													<div class="th-inner">Reported Gene(s)</div>
												</th>
												<th data-field="mapgene" tabindex="0">
													<div class="th-inner">Mapped Gene(s)|</div>
												</th>
												<th data-field="effect" tabindex="0">
													<div class="th-inner">Effect</div>
												</th>
												<th data-field="pmid"  data-visible="false" tabindex="0">
													<div class="th-inner">PMID</div>
												</th>
												<th data-field="studyid"  data-visible="false" tabindex="0">
													<div class="th-inner">Study ID</div>
												</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator id="gwasAssociationView" value="gwasAssociationViewList">
											<tr>
											<td <s:if test="#gwasAssociationView.genotypeAnnotateViewList!=null and #gwasAssociationView.genotypeAnnotateViewList.size >0 "> rowspan="<s:property value='#gwasAssociationView.genotypeAnnotateViewList.size'/>"</s:if>><a href="http://bigd.big.ac.cn/gvm/snp/getSNPDetail?snpname=<s:property value='#gwasAssociationView.varId'/>" target="_blank"><s:property value="#gwasAssociationView.varId"/></a> <span class="glyphicon glyphicon-share"></span></td>
												<td <s:if test="#gwasAssociationView.genotypeAnnotateViewList!=null and #gwasAssociationView.genotypeAnnotateViewList.size >0 "> rowspan="<s:property value='#gwasAssociationView.genotypeAnnotateViewList.size'/>"</s:if>><s:property value="#gwasAssociationView.traitName"/></td>
												<td <s:if test="#gwasAssociationView.genotypeAnnotateViewList!=null and #gwasAssociationView.genotypeAnnotateViewList.size >0 "> rowspan="<s:property value='#gwasAssociationView.genotypeAnnotateViewList.size'/>"</s:if>><s:property value="#gwasAssociationView.speciesCommonName"/></td>
												
												<td <s:if test="#gwasAssociationView.genotypeAnnotateViewList!=null and #gwasAssociationView.genotypeAnnotateViewList.size >0 "> rowspan="<s:property value='#gwasAssociationView.genotypeAnnotateViewList.size'/>"</s:if>><s:property value="#gwasAssociationView.refversion"/></td>
												<td <s:if test="#gwasAssociationView.genotypeAnnotateViewList!=null and #gwasAssociationView.genotypeAnnotateViewList.size >0 "> rowspan="<s:property value='#gwasAssociationView.genotypeAnnotateViewList.size'/>"</s:if>><s:property value="#gwasAssociationView.chrom"/>:<s:property value="#gwasAssociationView.startPos"/>-<s:property value="#gwasAssociationView.endPos"/></td>
												<td <s:if test="#gwasAssociationView.genotypeAnnotateViewList!=null and #gwasAssociationView.genotypeAnnotateViewList.size >0 "> rowspan="<s:property value='#gwasAssociationView.genotypeAnnotateViewList.size'/>"</s:if>><s:property value="#gwasAssociationView.pvalue"/></td>
												<td <s:if test="#gwasAssociationView.genotypeAnnotateViewList!=null and #gwasAssociationView.genotypeAnnotateViewList.size >0 "> rowspan="<s:property value='#gwasAssociationView.genotypeAnnotateViewList.size'/>"</s:if>><s:property value="#gwasAssociationView.rval"/></td>
												<td <s:if test="#gwasAssociationView.genotypeAnnotateViewList!=null and #gwasAssociationView.genotypeAnnotateViewList.size >0 "> rowspan="<s:property value='#gwasAssociationView.genotypeAnnotateViewList.size'/>"</s:if>><s:property value="#gwasAssociationView.gwasReportGene"/></td>
												
												<s:if test="#gwasAssociationView.genotypeAnnotateViewList != null and #gwasAssociationView.genotypeAnnotateViewList.size> 0">
													<s:iterator value="#gwasAssociationView.genotypeAnnotateViewList" id="genotypeview" status="rowindex">
														<s:if test="#rowindex.index == 0 ">
															<td>
																<s:property value="#genotypeview.mapGeneId"/>
															</td>
															<td><s:property value="#genotypeview.conseqtype"/>|<s:property value="#genotypeview.effect"/>
															</td>
														</s:if>
													</s:iterator>
												</s:if>
												<s:else>
													<td>
													</td>
													<td>
													</td>
												</s:else>
								
												
												<td <s:if test="#gwasAssociationView.genotypeAnnotateViewList!=null and #gwasAssociationView.genotypeAnnotateViewList.size >0 "> rowspan="<s:property value='#gwasAssociationView.genotypeAnnotateViewList.size'/>"</s:if>><s:property value="#gwasAssociationView.pmid"/> <span class="glyphicon glyphicon-share"></span></td>
												<td <s:if test="#gwasAssociationView.genotypeAnnotateViewList!=null and #gwasAssociationView.genotypeAnnotateViewList.size >0 "> rowspan="<s:property value='#gwasAssociationView.genotypeAnnotateViewList.size'/>"</s:if>><a href="https://www.ncbi.nlm.nih.gov/pubmed/27714888" target="_blank"><s:property value="#gwasAssociationView.studyAcc"/></a> <span class="glyphicon glyphicon-share"></span></td>
											</tr>
											<s:if test="#gwasAssociationView.genotypeAnnotateViewList != null and #gwasAssociationView.genotypeAnnotateViewList.size> 0">
													<s:iterator value="#gwasAssociationView.genotypeAnnotateViewList" id="genotypeview" status="rowindex">
														<s:if test="#rowindex.index > 0 ">
														<tr>
															<td>
																<s:property value="#genotypeview.mapGeneId"/>
															</td>
															<td><s:property value="#genotypeview.conseqtype"/>|<s:property value="#genotypeview.effect"/>
															</td>
														</tr>
														</s:if>
													</s:iterator>
												</s:if>
											
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
	
	
		
					$("#gwas_list").bootstrapTable({
				
						
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
						showColumns:true,
						showExport:true,
						exportDataType:'all',
						exportTypes:['excel','csv', 'txt','json'],
						 fixedColumns: true, 
						fixedNumber: 10,
						height : 600
				
					});
		</script>