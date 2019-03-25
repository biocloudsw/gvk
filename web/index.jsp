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
  
  <script src="/gvk/js/jquery-3.2.1.min.js"></script>
  <script src="/gvk/bootstrap/js/bootstrap.min.js"></script>
  <script src="/gvk/js/headerfooter.js"></script>
  <script src="/gvk/js/common.js"></script>

  <title>GWAS Atlas</title>
  
</head>
  
<body>
<div class="container-fluid">
	
   <jsp:include page="/inc/header.jsp" />
    
	<div id="main-info" class="row">
		<div class="col-md-12">
			
            <!-- Quick Search -->
            <div class="row">
                <div class="col-md-12">
                    <form action="/gvk/fuzzySearch" method="post" class="form-inline" style="margin-bottom: 0px; text-align: center;padding-top:20px;padding-bottom:20px;" role="form">
                        <div class="form-group">
                            <label>Search:&nbsp;</label>
                            <select id="search-situ-type" name="searchSpecies" class="form-control" onChange="chooseSearch()">
                                <option value="all" selected="selected">All Species</option>
	        	                <option value="1">Gossypium hirsutum (Cotton Ghir.BGI)</option>
								<option value="2">Prunus mume (Japanese apricot P.mume_V1.0)</option>
								<option value="3">Zea mays (Maize RefGen_v4)</option>
	                        	<option value="4"> Brassica napus (Rape seed Bra_napus_v2.0)</option>
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
                            <span style="padding-left: 10px; color: grey;">
                                e.g. <a href="#">Carboxy*</a>, <a href="#">chr1</a>
                            </span>
                        </div>
                    </form>
                </div>
            </div>
            
		</div>
	</div>
  
	<!-- Content -->
      <div class="row" style="margin: 30px 15px 0px 15px;" align="center">
      	<h1><b>GWAS</b> <span style="color: #777777;">Atlas</span></h1>
        <p style="color: grey;">The NHGRI-EBI Catalog of published genome-wide association studies，NNNNNNNNNNNNNNNN NNNNNNNNN</p>
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
            <select id="speciesG2P" name="" class="form-control" onChange="show()">
                <option value="defualt" selected="selected">----- Select a species -----</option>
                <option value="cotton">Gossypium hirsutum (Cotton Ghir.BGI)</option>
                <option value="mei">Prunus mume (Japanese apricot P.mume_V1.0)</option>
                <option value="maize">Zea mays (Maize RefGen_v4)</option>
                <option value="rapeseed"> Brassica napus (Rape seed Bra_napus_v2.0)</option>
                <option value="rice">Oryza sativa (Rice IRGSP1.0)</option>
                <option value="sorghum">Sorghum bicolor (Sorghum Sbiv3.1)</option>
                <option value="soybean">Glycine max (Soybean Wm82.a2.v1)</option>
            </select>
        </div>
          
      </div>
</div>

<script type="text/javascript" language="JavaScript">
    function show(){
        var selValue = document.getElementById("speciesG2P").value;
        if(selValue=="cotton"){
            window.location='/gvk/pages/statistics/species/cotton.jsp';
        }else if(selValue=="mei") {
            window.location = "/gvk/pages/statistics/species/mei.jsp";
        }else if(selValue=="maize"){
            window.location ="/gvk/pages/statistics/species/maize.jsp";
        }
    }
</script>


<script type="text/javascript" language="javascript">
  var odiv = document.getElementById('');
  addClass(odiv, 'active');
</script>



</body>
</html>
