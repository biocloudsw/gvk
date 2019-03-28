<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	

	
			<!-- Quick Search -->
           			
                    <form action="/gvk/fuzzySearch" method="post" class="form-horizontal" style="margin-bottom: 0px;  " role="form">
						
                        <div class="form-group">
							
                            <label class="col-sm-1 control-label" style="text-align:left;">Search:&nbsp;</label>
							<div class="col-sm-4">
                            <select id="search-situ-type" name="searchSpecies" class="form-control" onChange="chooseSearch()">
                                <option value="all" selected="selected">All Species</option>
	        	                <option value="1">Gossypium hirsutum (Cotton Ghir.BGI)</option>
								<option value="2">Prunus mume (Japanese apricot P.mume_V1.0)</option>
								<option value="3">Zea mays (Maize RefGen_v4)</option>
	                        	<option value="4">Brassica napus (Rape seed Bra_napus_v2.0)</option>
	        	                <option value="5">Oryza sativa (Rice IRGSP1.0)</option>
								<option value="6">Sorghum bicolor (Sorghum Sbiv3.1)</option>
								<option value="7">Glycine max (Soybean Wm82.a2.v1)</option>
                            </select>
							</div>
                            <label class="col-sm-1 control-label">&nbsp;for&nbsp;</label>
							<div class="col-sm-4">
                            <div class="input-group">
                                <input type="text" id="search-param" name="searchParam" class="form-control " style="width: 300px;">
                                <span class="input-group-btn">
                                    <button id="quick-search-icon" class="btn btn-default" type="submit" value="Search"  style="">
                                        <span class="glyphicon glyphicon-search" style="line-height: 20px;"></span>
                                    </button>
                                </span>
                            </div>
							
							</div>
							<div class="col-sm-12">
							<div style="text-align:left; padding-top:5px; ">
									<span style="padding-left: 10px; color: grey;">
										e.g.  <a href="/gvk/fuzzySearch?searchParam=plant height&searchSpecies=all">plant height</a>, <a href="/gvk/fuzzySearch?searchParam=LOC100281588&searchSpecies=all">LOC100281588</a>, <a href="/gvk/fuzzySearch?searchParam=chr1:14702150-37601000&searchSpecies=all">chr1:14702150-37601000</a>
									</span>
							</div>
							</div>
                        </div>
                    </form>
            
 <script src="/gvk/js/searchac.js"></script>