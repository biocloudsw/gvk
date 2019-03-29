
function showTrait(){
	
	var url="/gvk/goajax/filterReportTrait";
	var species= $("#idspecies").val();
	var params={"param1":species};
	$.ajax({
					   url: url,
					   type:'post',
					   dataType:'json',
					   data:params,
					   success:function(value){
						   if(value.traitBeanList != null ){
							 
							   $("#idSearchTrait").empty();
							   $('#idSearchTrait').selectpicker('refresh' );
							   for(var i=0;i<value.traitBeanList.length;i++){
								   var traitbean = value.traitBeanList[i];
								   var option = "<option value='"+traitbean.traitId+"'>"+traitbean.reportTraitName+"</option>";
								  // alert(option);
								   $("#idSearchTrait.selectpicker").append(option);
							   }
							   
							 // $('.selectpicker').selectpicker('val', '');
								$('#idSearchTrait').selectpicker('refresh' );
						   }
						   
					   }
	});
	
	
}