<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

  <meta http-equiv="pragma" content="no-cache">
  <meta http-equiv="cache-control" content="no-cache">
  <meta http-equiv="expires" content="0">    
  <meta http-equiv="keywords" content="GWAS,Genotype">
  <meta http-equiv="description" content="GWAS Atlas">
	
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 
  <link href="/gvk/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
  <link href="/gvk/css/common.css" rel="stylesheet" />
    <link href="/gvk/js/jquery-ui.css" rel="stylesheet" />
  <script src="/gvk/js/jquery-3.2.1.min.js"></script>
    <script src="/gvk/js/jquery-ui.js"></script>
  <script src="/gvk/bootstrap/js/bootstrap.min.js"></script>

  <script src="/gvk/js/common.js"></script>

  <title>GWAS Atlas</title>
  
</head>
  
<body>
<div class="container">
	
   <jsp:include page="/inc/header.jsp" />
    
	<div id="main-info" class="row">
		<div class="col-md-12">
			
            <!-- Quick Search -->
        
                    <form action="/gvk/fuzzySearch" method="post"  class="form-inline"  style="margin-bottom: 0px; text-align: center;padding-top:20px;padding-bottom:20px;" role="form">
                        <div class="form-group">
                            <label>Search:&nbsp;</label>
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
                            <label>&nbsp;for&nbsp;</label>
                            <div class="input-group">
                                <input type="text" id="search-param" name="searchParam" class="form-control " style="width: 300px;">
                                <span class="input-group-btn">
                                    <button id="quick-search-icon" class="btn btn-default" type="submit" value="Search"  style="">
                                        <span class="glyphicon glyphicon-search" style="line-height: 20px;"></span>
                                    </button>
                                </span>
                            </div>
							<div style="text-align:left; padding-top:5px;">
                            <span style="padding-left: 10px; color: grey;">
                                e.g. <a href="/gvk/fuzzySearch?searchParam=plant height&searchSpecies=all">plant height</a>, <a href="/gvk/fuzzySearch?searchParam=LOC100281588&searchSpecies=all">LOC100281588</a>, <a href="/gvk/fuzzySearch?searchParam=chr1:14702150-37601000&searchSpecies=all">chr1:14702150-37601000</a>
                            </span>
							</div>
                        </div>
                    </form>
          
            
		</div>
	</div>
  
	<!-- Content -->
      <div class="row" style="padding: 30px 15px 0px 15px;" align="center">
	  <div class="col-md-12">
      	<h1><b>GWAS</b> <span style="color: #777777;">Atlas</span></h1>
        <p style="color: grey;">The NHGRI-EBI Catalog of published genome-wide association studies，NNNNNNNNNNNNNNNN NNNNNNNNN</p>
		</div>
      </div>
      
      
      
      <div id="index-statistics" class="row" align="center">
      	  <div class="col-md-4 col-sm-6">
      	  	<div>
              <p>Species</p>
              <p>
                  <img src="images/index/tissue.png">
                  <a href="pages/statistics/species.html"><span>13</span></a>
              </p>
            </div>
          </div>
          <div class="col-md-4 col-sm-6">
            <div>
              <p>Associations</p>
              <p>
                  <img src="images/index/association.png">
                  <a href="#"><span>312,523</span></a>
              </p>
            </div>
          </div>
          <div class="col-md-4 col-sm-6">
            <div>
              <p>Traits</p>
              <p>
                  <img src="images/index/trait.png">
                  <a href="#"><span>305</span></a>
              </p>
            </div>
          </div>
          <div class="col-md-4 col-sm-6">
            <div>
              <p>Studies</p>
              <p>
                  <img src="images/index/study.png">
                  <a href="#"><span>648</span></a>
              </p>
            </div>
          </div>
          <div class="col-md-4 col-sm-6">
            <div>
              <p>Publications</p>
              <p>
                  <img src="images/index/publication.png">
                  <a href="#"><span>441</span></a>
              </p>
            </div>
          </div>
          
        <div class="col-md-12 col-sm-12" align="left" style="padding: 20px;">
            <h4><b>View Species specific G2P information</b></h4>
            <select id="speciesG2P" name="" class="form-control" onChange="">
                <option value="defualt" selected="selected">----- Select a species -----</option>
                <option>Human</option>
                <option>Mouse</option>
                <option>Dog</option>
            </select>
        </div>
          
      </div>
</div>

 <script src="/gvk/js/searchac.js"></script>

</body>
</html>
