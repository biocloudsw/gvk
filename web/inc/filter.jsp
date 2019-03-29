<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

				<div class="panel-heading search_condition" data-toggle="collapse" data-target="#idFilter">
				  <h4 class="panel-title" style="font-size:14px;font-weight:bold;">Filter</h4>
				</div>
				<div id="idFilter" class="panel-collapse collapse in">
					 <div class="panel-body" style="font-size:13px;">
					 		 <form action="/gvk/fuzzySearch"  method="post">
							
							 <input type="hidden" name="searchParam" value="<s:property value='searchParam'/>" />
							
					        <div>
					            <div>Species</div> 
					            <select class="selectpicker form-control" id="idspecies" name="searchSpecies"  data-live-search="true" onchange="showTrait()" >
									 	<option value="all" <s:if test="searchSpecies=='all'"> selected="selected" </s:if> >All Species</option>
										<option value="1"  <s:if test="searchSpecies==1"> selected="selected" </s:if> >Gossypium hirsutum (Cotton Ghir.BGI)</option>
										<option value="2" <s:if test="searchSpecies==2"> selected="selected" </s:if>>Prunus mume (Japanese apricot P.mume_V1.0)</option>
										<option value="3" <s:if test="searchSpecies==3"> selected="selected" </s:if>>Zea mays (Maize RefGen_v4)</option>
										<option value="4" <s:if test="searchSpecies==4"> selected="selected" </s:if>>Brassica napus (Rape seed Bra_napus_v2.0)</option>
										<option value="5" <s:if test="searchSpecies==5"> selected="selected" </s:if>>Oryza sativa (Rice IRGSP1.0)</option>
										<option value="6" <s:if test="searchSpecies==6"> selected="selected" </s:if>>Sorghum bicolor (Sorghum Sbiv3.1)</option>
										<option value="7" <s:if test="searchSpecies==7"> selected="selected" </s:if>>Glycine max (Soybean Wm82.a2.v1)</option>                    
								</select>
					       <hr>
						   
					        </div>
							
					        <div>
					            <div>Catalog Trait</div>
					            <select class="selectpicker form-control" id="idSearchTrait" name="searchTrait" title="Select catalog trait" multiple="multiple"  data-live-search="true">
									                     
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
					            <div class="form-group">
					                <select name="psitu" style="width:80px;height:30px;" >
									<option value="&lt;=">&lt;=</option>
									<option value="&gt;=">&gt;= </option>
								</select><input type="text" name="pvalue" style="width:100px;height:30px;" >(0-1)
					            </div>
					            <hr>
					        </div>
					        <!--
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
					        </div> -->
							
							<button type="submit" class="btn btn-primary">Apply</button>
					   		<button type="reset" class="btn btn-info" >Clear all</button>
					    </form>
					 </div>
				</div>
				
