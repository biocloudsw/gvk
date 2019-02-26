<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

    <!-- Quick Search -->
    <div class="row">
      	<div class="col-md-12">
      		
	        <!-- Quick Search -->
	        <form action="#" method="post" class="form-inline" style="margin-bottom: 0px; text-align: center;" role="form">
	        	<div class="form-group">
	        		<label>Search:&nbsp;</label>
	        		<select id="search-situ-type" name="searchSituType" class="form-control" onChange="chooseSearch()">
	        			<option value="all-species" selected="selected">All Species</option>
	        		</select>
	        		<label>&nbsp;for&nbsp;</label>
	        		<div class="input-group">
	        			<input type="text" id="search-param" name="searchParam" class="form-control" style="width: 300px;">
	        			<span class="input-group-btn">
	        				<a id="quick-search-icon" class="btn btn-default" type="submit" value="Search" href="/gwas_atlas/pages/search/searchResults.jsp">
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
