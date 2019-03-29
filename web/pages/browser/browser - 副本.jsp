<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
 
  <link href="/gvk/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
  <link href="/gvk/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
  <link href="/gvk/css/jstree/style.min.css" rel="stylesheet" />
  <link href="/gvk/css/common.css" rel="stylesheet" />
  <link href="/gvk/js/jquery-ui.css" rel="stylesheet" />
  <script src="/gvk/js/jquery-3.2.1.min.js"></script>
  <script src="/gvk/js/jquery-ui.js"></script>
  <script src="/gvk/bootstrap/js/bootstrap.min.js"></script>
  <script src="/gvk/js/jstree.min.js"></script>

  <script src="/gvk/js/common.js"></script>

  <title>GWAS Atlas</title>
  
</head>
  
<body>
<div class="container">
	
	<jsp:include page="/inc/header.jsp" />
    
	<div id="main-info" class="row">
		<div class="col-md-12">
			<!-- You are now at... -->
			<ol class="breadcrumb">
				<li><a href="/gvk/index.jsp">GWAS Atlas</a></li>
				<li class="active">Browser</li>
			</ol>
		</div>
	</div>
  
<!-- Content -->
	<div class="row">
		<div id="browser-tree" class="col-md-4 col-lg-3" style="padding-left: 1%;padding-top: 15px; padding-right: 0px;">
		    <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active">
                    <a href="#plant-trait-ontology" role="tab" data-toggle="tab">
                        Plant Trait Ontology
                    </a>
                </li>
                <li>
                    <a href="#crop-ontology" role="tab" data-toggle="tab">
                        Crop Ontology
                    </a>
                </li>
            </ul>

            <div class="tab-content">
                <div id="plant-trait-ontology" class="tab-pane active">
      
                        <div class="input-group">
                            <input type="text" id="idontolgoy" class="form-control">
                            <span class="input-group-btn">
                                <a id="" class="btn btn-default" type="button" value="Search" href="javascript:selectOneGoTermByGOAcc()" >
                                    Search
                                </a>
                            </span>
                        </div>
						
						<!--
						
                        <div class="form-group" style="padding-top: 10px; text-align: right;">
                           <span>
                               0/0&nbsp;&nbsp;
                           </span>
                            <span>
                                <a id="" class="btn btn-info" type="submit" value="Search" href="#">
                                    Prev
                                </a>&nbsp;&nbsp;
                                <a id="" class="btn btn-info" type="submit" value="Search" href="#">
                                    Next
                                </a>
                            </span>
                        </div>
              			-->
					
					<input type="hidden" id="idhideontology" />
					
					
					
					
					
                    
                    <div id="jstree-ontology" style="overflow:auto;">
						<div id="godata"> </div>

                    </div>
                    
                </div>
                <div id="crop-ontology" class="tab-pane">
                    <form action="#" method="post">
                        <div class="input-group">
                            <input type="text" name="" class="form-control">
                            <span class="input-group-btn">
                                <a id="" class="btn btn-default" type="submit" value="Search" href="#">
                                    Search
                                </a>
                            </span>
                        </div>
						<!--
                        <div class="form-group" style="padding-top: 10px; text-align: right;">
                           <span>
                               0/0&nbsp;&nbsp;
                           </span>
                            <span>
                                <a id="" class="btn btn-info" type="submit" value="Search" href="#">
                                    Prev
                                </a>&nbsp;&nbsp;
                                <a id="" class="btn btn-info" type="submit" value="Search" href="#">
                                    Next
                                </a>
                            </span>
                        </div> -->
                    </form>
                </div>
            </div>
        </div>
        
        <div class="col-md-8 col-lg-9" style="padding-top: 15px">
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default" style="margin-bottom: 15px;">
                        <div class="panel-heading" style="font-size: 16px; font-weight: bold;">
                            Trait: <span id="idcurrentterm"> <s:property value="gotermbean.goterm"/></span>
                        </div>
                        <div class="panel-body" style="padding-top: 20px;padding-bottom: 20px;">
                            <div class="row">
                                <div class="col-md-12">

									<!--start-->
									<div>
										<input type="hidden" id="idtrait"  value="<s:property value='gotermbean.traitid' />"/>		
									</div>				
									<div>
										<ul id="myTabs" class="nav nav-tabs" role="tablist">
										
											<li class="active" role="presentation">
											  <a href="#trait" aria-controls="trait" role="tab" data-toggle="tab">Trait information</a>
											</li>
											<li role="presentation">
												<a href="#association" aria-controls="association" role="tab" data-toggle="tab">
													Association (<span id="idassociationcount"><s:property value="gotermbean.associationCount"/></span>)
												</a>
											</li>
											
											<li role="presentation">
												<a href="#study" aria-controls="study" role="tab" data-toggle="tab">
													Studies (<span id="idstudycount"><s:property value="gotermbean.studyCount"/></span>)
												</a>
											</li>
											<li role="presentation">
												<a href="#publication" aria-controls="publication" role="tab" data-toggle="tab">
													Publications (<span id="idpapercount"><s:property value="gotermbean.paperCount"/></span>)
												</a>
											</li>
										</ul>
						
										<div class="tab-content">
											
											
											<div id="trait" class="tab-pane active" role="tabpanel">
													 <table class="table table-striped table-hover" id="ontology-term" style="font-size: 14px;">
														<tbody>
															<tr>
																<th>ID</th>
																<td id="idgoid"><s:property value="gotermbean.goacc"/></td>
															</tr>
															<tr>
																<th>Name</th>
																<td id="idterm"> <s:property value="gotermbean.goterm"/></td>
															</tr>
															<tr id="idtrsynonym">
																<th>Synonyms</th>
																<td id="idsynonym">
																   <s:property value="gowordsynonym"/>
																</td>
															</tr>
															<tr id="idtrdefinition">
																<th>Definitions</th>
																<td id="iddefinition"><s:property value="gotermdefinition.gotermdefinition"/></td>
															</tr>
															<tr id="idtrcomment" >
																<th>Comment</th>
																<td id="idcomment"><s:property value="gotermdefinition.gotermcomment"/></td>
															</tr>
															
															<tr id="idtrsubclass" <s:if test="gotermbean.goParentTerm == null "> style="display:none" </s:if> <s:else> style="display:block" </s:else> >
																<th>SubClassOf</th>
																<td id="idparentterm"><s:if test="gotermbean.goParentTerm != null "><s:property value="gotermbean.goParentTerm"/></s:if></td>
															</tr>
															
														</tbody>
													</table>
                            
											
											</div>
						
											<div id="association" class="tab-pane" role="tabpanel">
												<iframe id="idgbframe1"  height="780" frameBorder="0" width="100%" src="#" style="-ms-zoom:1; overflow:scroll; min-height:780px;">
												</iframe>
												<div class="clearfix"></div>
											</div>
						
										
						
											<div id="study" class="tab-pane" role="tabpanel">
												<iframe id="idgbframe3"  height="800" frameBorder="0" width="100%" src="#" style="-ms-zoom:1; overflow:scroll; min-height:800px;">
												</iframe>
												<div class="clearfix"></div>
											</div>
						
											<div id="publication" class="tab-pane" role="tabpanel">
											
												<iframe id="idgbframe4"  height="780" frameBorder="0" width="100%" src="#" style="-ms-zoom:1; overflow:scroll; min-height:780px;">
												</iframe>
												<div class="clearfix"></div>
											</div>
											
						
										</div>
									</div>
															
								 <!--end-->							
									

									
                                </div>
                            </div>
                          
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>

</div>

<script type="text/javascript" language="javascript">
  var odiv = document.getElementById('nav-tab2');
  addClass(odiv, 'active');
</script>

<script type="text/javascript" language="javascript">
loadgochildterm();

$('#myTabs a[href="#association"]').click(function (e) {
		  e.preventDefault();
		  var traitid= $("#idtrait").val();
		  $("#idgbframe1").attr("src","/gvk/browse/getAssociation?param="+traitid);
		  $(this).tab('show');
		  
})


$('#myTabs a[href="#study"]').click(function (e) {
		  e.preventDefault();
		   var traitid= $("#idtrait").val();
		  $("#idgbframe3").attr("src","/gvk/browse/getStudy?param="+traitid);
		  $(this).tab('show');
		  
})

$('#myTabs a[href="#publication"]').click(function (e) {
		  e.preventDefault();
		   var traitid= $("#idtrait").val();
		  $("#idgbframe4").attr("src","/gvk/browse/getPublication?param="+traitid);
		  $(this).tab('show');
		  
})



function loadgochildterm(){

		//$('#godata').data('jstree', false).empty();
		$('#jstree-ontology').jstree({
			'core' : {
				'data' : {
				"url" : "//localhost:8080/gvk/goajax/goterm",
				"dataType" : "json",
				"data" : function (node) {
					if(node != null ){
						return { "id" : node.id };
					}
					
				   }
			   }
			  
			},
			"themes": {
			  "theme": "classic",
			  "dots": true,
			  "icons": false,
			},
			"plugins" : [ "search" ]
			
		});
	
	
	}

	loadtermdetail();
	//this is used to load term detail information
	function loadtermdetail(){
		$('#jstree-ontology')
		.on("select_node.jstree", function (e, data) {
	
			if(data.selected.length) {
				
				var id = data.instance.get_node(data.selected[0]).id;
				changeGoTermDetail(id);
				addAnnotationLinks(); // add annotation links
				loadgochildterm();
			}
		})
	}
	
	
	  //change go term detail information
  function changeGoTermDetail(id){
 	 var url ="/gvk/goajax/gotermdetail?time="+new Date().getTime();
  	var params={"goacc": id};
				$.ajax({
					   url: url,
					   type:'post',
					   dataType:'json',
					   data:params,
					   success:function(value){
					   	  
					   //	alert("id="+id);
					   	 if(value.gotermbean != null ){
						 
						 	$("#idcurrentterm").html(value.gotermbean.goterm);
						 	$("#idterm").html(value.gotermbean.goterm);
							$("#idgoid").html(value.gotermbean.goacc);
							$("#idtrsubclass").css("display","");
							$("#idparentterm").html(value.gotermbean.goParentTerm);
							$("#idassociationcount").html(value.gotermbean.associationCount);
							$("#idstudycount").html(value.gotermbean.studyCount);
							$("#idpapercount").html(value.gotermbean.paperCount);
							$("#idtrait").val(value.gotermbean.traitid);
							
							
						 }
						 
						 if(value.gowordsynonym != null ){
						 	$("#idtrsynonym").css("display","");
						 	$("#idsynonym").html(value.gowordsynonym);
						 }else{
						 	$("#idtrsynonym").css("display","none");
						 	$("#idsynonym").html("");
						 }
						
						 
						 if(value.gotermdefinition != null ){
						 	if(value.gotermdefinition.gotermdefinition != null){
								$("#idtrdefinition").css("display","");
								$("#iddefinition").html(value.gotermdefinition.gotermdefinition);
							}else{
								$("#idtrdefinition").css("display","none");
								$("#iddefinition").html("");
							}
							
							if(value.gotermdefinition.gotermcomment != null && value.gotermdefinition.gotermcomment!='\\N' ){
								$("#idtrcomment").css("display","");
								$("#idcomment").html(value.gotermdefinition.gotermcomment);
							}else{
								$("#idtrcomment").css("display","none");
								$("#idcomment").html("");
							}
						 	
						 }else{
						 	$("#iddefinition").html("");
							$("#idcomment").html("");
							$("#idtrdefinition").css("display","none");
							$("#idtrcomment").css("display","none");
							
						 }
						 
						
						
						 
					   } 
					   
					   
					   
					 });
  
  
  }
	
	    function addAnnotationLinks(){
		$(".annotationLink").remove();
		var fewiurl="/gvk/";
		var b=$("#jstree-ontology").jstree().get_selected(true);
		//console.log("add annotation links"+ b.length);
		for(var d=0;d<b.length;d++){
			var e=b[d];
			$("#"+e.id+"_annotations").remove();
			//console.log("e.data.annotationLabel =" + e.data.annotationLabel+",e.id="+e.id);
			if(e.data.annotationLabel!=null){
				//var c='<span id="'+e.id+'_annotations" class="annotationLink">(<a href="'+fewiurl+e.data.annotationUrl+'" target="_blank">'+e.data.annotationLabel+"</a>)</span>";
				var c='<span id="'+e.id+'_annotations" class="annotationLink">('+e.data.annotationLabel+')</span';
				
				if(e.data.annotationUrl==null){
					c='<span id="'+e.id+'_annotations" class="annotationLink"> ('+e.data.annotationLabel+")</span>";
				}
				var a=$("#"+e.id+"_anchor"); //GO:0005488_anchor
				if(a!=null){
					a.after(c);
				}
			 }
		 }
		 
		 };
	
	
function fnassociation(){
	var term = $("#idcurrentterm").html();
	window.location.href='/gvk/browse/getAssociation?param='+term;
	
}

function fnassociation(){
	var term = $("#idcurrentterm").html();
	window.location.href='/gvk/browse/getStudy?param='+term;
	
}

function fnassociation(){
	var term = $("#idcurrentterm").html();
	window.location.href='/gvk/browse/getPublication?param='+term;
	
}	

//autocomplete
 $( "#idontolgoy" ).autocomplete({
 	  classes: {
		"ui-autocomplete": "highlight"
	  },
      source: function( request, response ) {
        $.ajax({
          url: "/gvk/goajax/getterm?time="+new Date().getTime(),
          dataType: "json",
          data: {
            featureClass: "P",
            style: "full",
            maxRows: 12,
            nameStartsWith: request.term,
			ontologyType : 1
          },
          success: function( data ) {
            response( $.map( data.ontologyTermList, function( item ) {
              return {
                label: item.goterm,
                value: item.goterm,
				id: item.goacc
              }
            }));
          }
        });
      },
      minLength: 2,
      select: function( event, ui ) {
        console.log( ui.item ?
          "Selected: " + ui.item.label :
          "Nothing selected, input was " + this.value);
		 // $("#idontolgoy").val(ui.item.label);
		  $("#idhideontology").val(ui.item.id);
      },
      open: function() {
        $( this ).removeClass( "ui-corner-all" ).addClass( "ui-corner-top" );
      },
      close: function() {
        $( this ).removeClass( "ui-corner-top" ).addClass( "ui-corner-all" );
      }
    });



  function selectOneGoTermByGOAcc(){
  
  		var id = $("#idhideontology").val();
	//	alert(id);
  
	  changeGoTermDetail(id);
	  //then update the tree
	  
		/*$("#godata").bind("loaded.jstree", function (e, data) {  
			alert("open");
       		 data.instance.open_node(39028);    
        });*/
	 //  $('#godata').jstree('open_node', id);
	   // $('#godata').jstree('select_node', id);
		$('#jstree-ontology').data('jstree', false).empty();
		
		//this is used to get children data
		
		$('#jstree-ontology').jstree({
			'core' : {
				'data' : function (obj, callback) {  
                           // var jsonstr="[]";  
                            var jsonarray ;  
                            $.ajax({  
                                type: "GET",  
                                url:"http://localhost:8080/gvk/goajax/gochildrenterm?id="+id,  
                                dataType:"json",  
                                async: false,  
                                success:function(result) {  
                                    jsonarray= result ; 
                                }  
  
                            });  
                            callback.call(this, jsonarray);  
                        }  
			  
			},
			"plugins" : [ "search" ]
			
		});

	  
  }
  
  $.ui.autocomplete.prototype._renderItem = function (ul, item) {        
    var t = String(item.value).replace(
            new RegExp(this.term, "gi"),
            "<strong>$&</strong>");
    return $("<li></li>")
        .data("item.autocomplete", item)
        .append("<div>" + t + "</div>")
        .appendTo(ul);
};

</script>

</body>
</html>
