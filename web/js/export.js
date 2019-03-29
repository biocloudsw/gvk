	function exportmstrait(){
	
	}
	
	function exportcsvtrait(){
	
	
	}
	
	
	function exporttxttrait(){
		var url = $("#idhreffirst").attr('href');
		if(url.indexOf("?")>-1){
			var index = url.indexOf("?");
			var turl = url.substring(0,index);
			var tparam = url.substring(index+1,url.length);
			
			var saveurl = turl+"Export?"+tparam+"&format=1";
			
			//open another window
			var a = document.getElementById("goto");
			if(a == null){
				a=document.createElement('a');
				a.id="goto";
				document.body.appendChild(a);
			}
			
			
			a.href = saveurl;
			a.target="_blank";
			
			document.getElementById("goto").click();

		}
	
	}
	
	function exportjsontrait(){
	
	
	}